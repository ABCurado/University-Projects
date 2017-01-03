package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.application.ConsultMealRatingController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealPrinter;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author JoséBarros(1140364)
 */
public class ConsultMealRatingUI extends AbstractUI {

    private final ConsultMealRatingController controller = new ConsultMealRatingController();

    @Override
    protected boolean doShow() {
        final Iterable<Meal> meals = this.controller.listMeals();
        final SelectWidget<Meal> selector = new SelectWidget<>(meals, new MealPrinter());
        selector.show();
        final Meal mealSelected = selector.selectedElement();

        if (mealSelected != null) {
            try {
                String ratings = controller.getMealRating(mealSelected);
                System.out.println(ratings);
            } catch (NullPointerException ex) {
                System.out.println("Don't exist assigned rating for this meal");
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Consult Meal Rating";
    }

}
