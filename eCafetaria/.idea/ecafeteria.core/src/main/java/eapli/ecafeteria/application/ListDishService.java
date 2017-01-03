package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Rui Freitas
 */
public class ListDishService {

	public Iterable<Dish> allDishes() {
		ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

		final DishRepository dishRepository = PersistenceContext.repositories().
			dishes();
		return dishRepository.all();
	}
}
