package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.domain.meals.LastMinuteSale;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.NoSuchElementException;

/**
 *
 * @author Rafael Rocha <1140329>
 */
public class SellLastMinuteMealController implements Controller {
    
        /**
         * Returns the cashier's shift.
         * @return Cashier's shift
         */
	public Shift getShift() {
		SystemUser currentUser = AppSettings.getCurrentUser();
		Shift shift = PersistenceContext.repositories().shift().
			isOpenedByUser(currentUser);
		if (shift == null) {
			throw new NoSuchElementException("Shift not found");
		}
		return shift;
	}

        /**
         * Lists the current day's active meals corresponding to the 
         * cash register's shift.
         * @param shift Cash Register's current shift
         * @return A "list" of meals
         */
	public Iterable<Meal> listTodaysMeals(Shift shift) {
		Iterable<Menu> menu = PersistenceContext.repositories().menus().
			publishedMenuBetweenDates(DateTime.now());

		if (!menu.iterator().hasNext()) {
			throw new NoSuchElementException("Menu not found.");
		}

		Iterable<Meal> meals = new ListMealService().
			mealsByDateMealType(DateTime.now(), shift.mealType(), menu.iterator().next());

		if (!meals.iterator().hasNext()) {
			throw new NoSuchElementException("Meals not found.");
		}

		return meals;
	}
        
        /**
         * Returns the registered cafeteria user.
         * @param id Mecanographic number
         * @return Registered cafeteria user
         */
	public CafeteriaUser getCafeteriaUser(String id) {
		MecanographicNumber number = new MecanographicNumber(id);
		CafeteriaUser user = PersistenceContext.repositories().cafeteriaUsers().
			findById(number);

		if (user == null) {
			throw new NoSuchElementException("User not found");
		}

		return user;
	}
        
        /**
         * Registers a last minute sale from a registered cafeteria user and
         * his respective meal, persisting both elements.
         * @param user Registered Cafeteria User
         * @param meal Meal being sold
         * @return True, if the transaction is successful. False, if not.
         * @throws DataConcurrencyException
         * @throws DataIntegrityViolationException 
         */
	public boolean sellMealToRegisteredUser(CafeteriaUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
		Money price = meal.dish().dishValue();
		
                if (user.registerExpense(price.amountAsDecimal()) == false){
                    return false;
                }

		LastMinuteSale sale = new LastMinuteSale(user, meal);
		meal.registerLastMinuteMeal();
                persistUser(user);
                addSale(sale);
                
		sale.deliverLastMinuteSale(getShift());
		persistSale(sale);
                
                return true;
	}
        
        /**
         * Registers a last minute sale from a non registered cafeteria user
         * persisting only his meal. 
         * @param meal Meal being sold
         * @throws DataConcurrencyException
         * @throws DataIntegrityViolationException 
         */
	public void sellMealToNonRegisteredUser(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
		LastMinuteSale sale = new LastMinuteSale(meal);
		meal.registerLastMinuteMeal();
                addSale(sale);
                
		sale.deliverLastMinuteSale(getShift());
		persistSale(sale);
	}
        
        /**
         * Updated the registered cafeteria user on the database.
         * @param user Cafeteria User
         * @throws DataConcurrencyException 
         */
	public void persistUser(CafeteriaUser user) throws DataConcurrencyException {
		PersistenceContext.repositories().cafeteriaUsers().save(user);
	}
        
        /**
         * Adds a sale on the database.
         * @param sale Meal being sold
         * @throws DataIntegrityViolationException 
         */
	public void addSale(LastMinuteSale sale) throws DataIntegrityViolationException {
		PersistenceContext.repositories().lastMinuteSale().add(sale);
	}
        
        /**
         * Updates the sale on the database.
         * @param sale Sold meal
         * @throws DataConcurrencyException 
         */
	public void persistSale(LastMinuteSale sale) throws DataConcurrencyException {
		PersistenceContext.repositories().lastMinuteSale().save(sale);
	}
}
