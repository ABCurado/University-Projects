package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import javax.persistence.NoResultException;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterController implements Controller {

    /**
     * This method return true if the shift is being used
     *
     * @return true if the shift is being used ,otherwise return false
     */
    public boolean isOpenedforUser() {
        SystemUser currentUser = AppSettings.getCurrentUser();
        return PersistenceContext.repositories().shift().
                isOpenedByUser(currentUser) != null;
    }

    /**
     * Method that validate if cash register number exists
     *
     * @param number Cash Register number
     * @return true if cash register number exists,otherwise return false
     */
    public boolean hasCashRegister(String number) {

        try {
            CashRegister cashRegister = PersistenceContext.repositories().
                    cashRegisters().findByNumber(number);
            return cashRegister != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method that return all the types of meal
     *
     * @return all the meal types
     */
    public Iterable<MealType> getMealTypes() {

        return PersistenceContext.repositories().
                mealTypes().all();
    }

    /**
     * Method that return the type of meal by default depending on the current
     * time
     *
     * @return the meal type
     */
    public MealType mealByDefault() {
        final MealTypeRepository mealRepo = PersistenceContext.repositories().
                mealTypes();

        return mealRepo.findByDefault();
    }

    /**
     * Method that allows to choose a meal given a certain day and meal type
     *
     * @param day Day of the meal
     * @param mealType Meal type (can be Dinner ,Lunch or Default)
     * @return new Shift that matches with the day and the type of meal received
     * previously
     */
    public boolean hasMeal(Calendar day, MealType mealType) {

        try {
            return PersistenceContext.repositories().shift().
                    openWithMealType(mealType, day) != null;
        } catch (NoResultException e) {
            //continue
        }

        Iterable<Menu> menu = PersistenceContext.repositories().menus().
                publishedMenuBetweenDates(day);

        MealRepository mealRepo = PersistenceContext.repositories().meals();

        return mealRepo.mealsByDateMealType(day, mealType, menu.iterator().
                next()).iterator().
                hasNext();
    }

    /**
     * This method checks if the shift can be opened
     *
     * @param mt meal type
     * @param date date
     * @return false if shift can be opened ,otherwise returns true
     */
    public boolean cantOpen(MealType mt, Calendar date) {
        return PersistenceContext.repositories().shift().cantOpen(mt, date);
    }

    /**
     * Method that open if the cash register can be open with specified Shift by
     * seeing if cash register number exists
     *
     * @param number cash register number
     * @param mealType
     * @return true if cash register can be opened ,otherwise return false
     * @throws eapli.framework.persistence.DataConcurrencyException
     * @throws eapli.framework.persistence.DataIntegrityViolationException
     */
    public boolean open(String number, MealType mealType, Calendar date) throws DataConcurrencyException, DataIntegrityViolationException {
        CashRegister cashRegister = PersistenceContext.repositories().
                cashRegisters().findByNumber(number);

        Shift shift = null;
        boolean flag;

        try {
            shift = PersistenceContext.repositories().shift().
                    findByOpenedCashRegister(cashRegister);
            flag = shift.open(date);
            updateShift(shift);
        } catch (Exception e) {
            shift = new Shift(mealType, cashRegister);
            flag = shift.open(date);
            addShift(shift);
        }

        persist(cashRegister);

        return flag;
    }

    /**
     * This method updates any changes on the shift
     *
     * @param shift Shift
     * @throws DataConcurrencyException
     */
    private void updateShift(Shift shift) throws DataConcurrencyException {
        PersistenceContext.repositories().shift().save(shift);
    }

    /**
     * This method add a new shift in the shift repository
     *
     * @param shift Shift
     * @throws DataIntegrityViolationException
     */
    private void addShift(Shift shift) throws DataIntegrityViolationException {
        PersistenceContext.repositories().shift().add(shift);
    }

    /**
     * This method persist any changes in cash register
     *
     * @param cashRegister Cash Register
     * @throws DataConcurrencyException
     */
    private void persist(CashRegister cashRegister) throws DataConcurrencyException {

        PersistenceContext.repositories().
                cashRegisters().save(cashRegister);
    }

}
