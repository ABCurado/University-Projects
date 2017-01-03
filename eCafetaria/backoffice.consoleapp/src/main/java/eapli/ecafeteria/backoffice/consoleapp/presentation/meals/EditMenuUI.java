package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.MealMenuController;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Martins
 */
public class EditMenuUI extends AbstractUI {

    private final MealMenuController controller = new MealMenuController();
    private Menu menu;

    @Override
    protected boolean doShow() {

        final Iterable<Menu> allMenus = this.controller.listUnPublishedMenus();
        final SelectWidget<Menu> selector = new SelectWidget<>(allMenus, new MenuPrinter());
        selector.show();
        this.menu = selector.selectedElement();
        if (this.menu != null) {
            do {
                int opcao;
                System.out.println("\nCurrent Menu:");
                List<Meal> menuMeals = (List) this.controller.
                        currentMenuMeals(menu);
                if (!menuMeals.isEmpty()) {
                    for (Meal meal : controller.currentMenuMeals(menu)) {
                        System.out.println(meal);
                    }
                } else {
                    System.out.println("EMPTY");
                }
                opcao = Console.
                        readInteger("\n1.Add Meal:\n2.Edit Meal:\n3.Delete Meal:\n0.Exit\nPlease choose an option:");
                switch (opcao) {
                    case 1:
                        addMeal();
                        break;
                    case 2:
                        editMeal();
                        break;
                    case 3:
                        removeMeal();
                        break;
                    case 0:
                        break;
                }
                if (opcao == 0) {
                    break;
                }
            } while (Console.readBoolean("\nDo you continue editing? (y/n)"));
        }
        return true;
    }

    public void addMeal() {
        // Select Meal Type (Lunch, Dinner, ...)
        final Iterable<MealType> listOfMealTypes = this.controller.
                listAllMealTypes();
        final SelectWidget<MealType> selectorOfMealTypes = new SelectWidget<>(listOfMealTypes, new MealTypePrinter());
        selectorOfMealTypes.show();
        final MealType mealType = selectorOfMealTypes.selectedElement();

        if (mealType != null) {
            System.out.println();//Just for presentation
            // Create a new Meal and add it to the repo
            final Iterable<Dish> listOfDishes = this.controller.listAllDishes();
            final SelectWidget<Dish> selectorOfDish = new SelectWidget<>(listOfDishes, new DishPrinter());
            selectorOfDish.show();
            final Dish dish = selectorOfDish.selectedElement();

            if (dish != null) {
                Calendar mealDate = null;
                String start = DateTime.
                        format(this.menu.startDate(), "dd-MM-yyyy");
                String end = DateTime.format(this.menu.endDate(), "dd-MM-yyyy");
                boolean validDate;
                do {
                    mealDate = Console.
                            readCalendar("\nInsert this meal date (dd-MM-yyyy):\nDate must be between " + start + " and " + end, "dd-MM-yyyy");
                    validDate = DateTime.
                            isBetweenDates(this.menu.startDate(), this.menu.
                                    endDate(), mealDate);
                    if (!validDate) {
                        System.out.
                                println("ERROR: Invalid date. Meal date must be inside the Menu period.");
                    }
                } while (!validDate);

                int planned;
                do {
                    planned = Console.
                            readInteger("\nInsert number of planned meals:");
                    if (planned < 0) {
                        System.out.println("Invalid number!");
                    }
                } while (planned < 0);

                try {
                    this.controller.
                            addMeal(mealType, mealDate, dish, this.menu, planned);
                } catch (Exception ex) {
                    System.out.
                            println("The meal is already inserted in the menu.");
                }

            }
        }
    }

    @Override
    public String headline() {
        return "Edit Meal Menu";
    }

    private void editMeal() {
        // Select a Meal if exists
        final List<Meal> listOfMeals = (List) this.controller.
                currentMenuMeals(this.menu);
        if (!listOfMeals.isEmpty()) {
            final SelectWidget<Meal> selectorOfMeal = new SelectWidget<>(listOfMeals, new MealPrinter());
            System.out.println();
            selectorOfMeal.show();
            final Meal meal = selectorOfMeal.selectedElement();
            if (meal != null) {
                int quantityPlaned = Console.
                        readInteger("\nInsert new quantity planned:");
                meal.defineQuantityPlaned(quantityPlaned);
                try {
                    this.controller.editMeal(meal);
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(EditMenuUI.class.getName()).
                            log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(EditMenuUI.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("Menu is empty!\nPlease insert a meal for edit");
        }
    }

    private void removeMeal() {
        // Select a Meal if exists
        final List<Meal> listOfMeals = (List) this.controller.
                currentMenuMeals(this.menu);
        if (!listOfMeals.isEmpty()) {
            final SelectWidget<Meal> selectorOfMeal = new SelectWidget<>(listOfMeals, new MealPrinter());
            System.out.println();
            selectorOfMeal.show();
            final Meal meal = selectorOfMeal.selectedElement();
            if (meal != null) {
                try {
                    this.controller.removeMeal(meal);
                } catch (DataIntegrityViolationException ex) {
                    System.out.println("Data has an integrity violation. Please try again.");
                } catch (DataConcurrencyException ex) {
                    System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
                }
            } else {
                System.out.println("No meal selected");
            }
        } else {
            System.out.println("Menu is empty!\nPlease insert a meal for edit");
        }
    }
}
