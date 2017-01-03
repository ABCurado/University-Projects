package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author Rui Freitas
 */
public class DefineDishController implements Controller {

	/**
	 * in the context of this use case only active dish types are meaningful.
	 *
	 * @return
	 */
	public Iterable<DishType> listDishTypes() {
		return new ListDishTypeService().activeDishTypes();
	}

	public Dish defineDish(DishType theDishType, String name, double calories,
						   double amountSalt, double amountFat, BigDecimal price)
		throws DataIntegrityViolationException {
		final Dish newDish = new Dish(theDishType, name, calories, amountSalt,
									  amountFat, new Money(price.doubleValue(),
														   Currency.
														   getInstance(Locale.
															   getDefault())));

		final DishRepository repo = PersistenceContext.repositories().dishes();

		repo.add(newDish);
		return newDish;
	}
}
