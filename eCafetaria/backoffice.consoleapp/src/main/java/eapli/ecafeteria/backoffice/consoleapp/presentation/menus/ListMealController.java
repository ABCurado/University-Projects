package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.application.ListMealService;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;

/**
 *
 * @author Martins
 */
class ListMealController implements Controller {

	/**
	 *
	 * @return all meals
	 */
	public Iterable<Meal> listMeals() {
		return new ListMealService().allMeals();
	}
}
