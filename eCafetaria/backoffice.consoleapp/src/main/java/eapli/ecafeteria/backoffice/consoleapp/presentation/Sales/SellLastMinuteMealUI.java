package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.ecafeteria.application.SellLastMinuteMealController;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.MealPrinter;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Rocha <1140329>
 */
public class SellLastMinuteMealUI extends AbstractUI {

    private final SellLastMinuteMealController theController = new SellLastMinuteMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        String answer = "";
        Meal meal;
        
        while (!answer.equals("1") && !answer.equals("2") && !answer.equals("0")) {
            answer = Console.readLine("Registered User or Non-Registered User?\n"
                + "1. Registered User\n2. Non-Registered User\n0. Exit");
        }
        
        if (answer.equals("1")) {
            CafeteriaUser registeredUser = null;
            while (registeredUser == null) {
                final String id = Console.readLine("Mecanographic number: ");

                try {
                    registeredUser = this.theController.getCafeteriaUser(id);
                } catch (NoSuchElementException e) {
                    System.out.println("No account was found with this number.\n");
                }

            }
            System.out.println("Cafeteria User accepted.");

            meal = selectMeal();

            if (meal == null) {
                return false;
            }

            try {
                if (this.theController.sellMealToRegisteredUser(registeredUser, meal) == false){
                    System.out.println("Cafeteria User does not have the required funds for the transaction.");
                    return false;
                }
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(SellLastMinuteMealUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Transaction successful.");
            
        } else if (answer.equals("2")){
            meal = selectMeal();

            if (meal == null) {
                return false;
            }

            try {
                this.theController.sellMealToNonRegisteredUser(meal);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(SellLastMinuteMealUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Transaction successful.");
        }

        return true;
    }

    public Meal selectMeal() {
        Shift shift;
        try {
            shift = theController.getShift();
        } catch (NoSuchElementException e) {
            System.out.println("Cash register has not been opened.");
            return null;
        }
        
        Iterable<Meal> meals;
        try {
            meals = theController.listTodaysMeals(shift);
        } catch (NoSuchElementException e) {
            System.out.println("Menu not found.");
            return null;
        }
        
        Iterator<Meal> it = meals.iterator();
        List<Meal> mealsList = new ArrayList<>();
        while (it.hasNext()) {
            mealsList.add(it.next());
        }

        final SelectWidget<Meal> mealSelector = new SelectWidget<>(meals, new MealPrinter());
        System.out.println("\n-- Available Meals --");
        mealSelector.show();
        
        if (mealSelector.selectedOption() == 0){
            return null;
        }
        
        return mealsList.get(mealSelector.selectedOption() - 1);
    }

    @Override
    public String headline() {
        return "Sell Last Minute Meal";
    }
}
