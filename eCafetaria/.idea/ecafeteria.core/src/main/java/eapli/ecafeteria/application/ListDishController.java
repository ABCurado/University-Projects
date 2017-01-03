package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.application.Controller;

/**
 *
 * @author Rui Freitas
 */
public class ListDishController implements Controller {

	public Iterable<Dish> listDish() {
		return new ListDishService().allDishes();
	}
}
