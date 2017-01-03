package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.ecafeteria.application.RegisterExecutedMealsController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealPrinter;
import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import eapli.util.DateTime;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class RegisterExecutedMealsUI extends AbstractUI implements Observer {

    @Override
    protected boolean doShow() {
        final RegisterExecutedMealsController controller = new RegisterExecutedMealsController();

        final List<Meal> meals = (List) controller.getMealsOfActiveMenu();
        if (!meals.isEmpty()) {
            final SelectWidget<Meal> selector = new SelectWidget<>(meals, new MealPrinter());
            selector.show();
            final Meal meal = selector.selectedElement();

            if (meal != null) {
                boolean exit = true;
                do {
                    System.out.println(controller.quantityOfExecuted(meal).toString());

                    if (Console.readBoolean("Do you want to execute (y/n)")) {
                        int quantity = Console.readInteger("Insert the quantity of executed meals:");

                        if (quantity >= 0) {
                            try {
                                controller.addExecutedQuantitiesToMeal(DateTime.now(), meal, quantity, this);
                                
                            } catch (DataConcurrencyException ex) {
                                System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
                                
                                return false;
                            }
                        } else {
                            System.out.println("Invalid quantity to execute. You can't execute negative quantities. Please insert a valid number of executions.");
                        }
                    } else {
                        exit = false;
                    }
                } while (exit);
            }
        } else {
            System.out.println("Currently there are no active menus. Please try again later.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Resgiter Executed Meals";
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof KitchenAlert) {
            KitchenAlert alert = (KitchenAlert) arg;
            System.out.println("\nAlert: " + alert.name() + "");
        }
    }

}
