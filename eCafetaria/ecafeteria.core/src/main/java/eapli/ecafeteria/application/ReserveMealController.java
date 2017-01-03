package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ruben
 */
public class ReserveMealController implements Controller {

	/**
	 *
	 * @return a list with all mealTypes
	 */
	public Iterable<MealType> listMealType(Calendar date) {
		Set<MealType> mealtypes = new HashSet();
		for (Meal meal : PersistenceContext.repositories().meals().
			mealsOfPublishedMenuFromDate(date)) {
			mealtypes.add(meal.mealType());
		}
		return mealtypes;
	}

	/**
	 *
	 * @param date
	 * @param mealType
	 * @return a list with all meals by date
	 */
	public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
		Iterable<Menu> menu = new ListMenuService().betweenDate(date);

		if (!menu.iterator().hasNext()) {
			return null;
		}

		return new ListMealService().mealsByDateMealType(date, mealType, menu.
														 iterator().next());
	}

	/**
	 *
	 * @param user
	 * @param meal
	 * @return a reserv for a user with a specific meal
	 * @throws DataConcurrencyException
	 * @throws DataIntegrityViolationException
	 */
	public Reserve registerReserve(SystemUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
		Iterable<CafeteriaUser> cafeUser = new UserService().
			cafeteriaUserBySystemUser(user);
		if (!cafeUser.iterator().hasNext()) {
			return null;
		}
		CafeteriaUser theUser = cafeUser.iterator().next();
		/* Check if user has enough balance to reserve meal */
		if (theUser.hasSufficientBalance(meal.dish().dishValue())) {
			/* Check if time limit not exceeded and if there are available meals
			 * If positive add reservation to meal
			 */
			if (meal.registerReservation()) {
				Reserve reserve = new Reserve(theUser, meal);
				PersistenceContext.repositories().reserves().add(reserve);
				theUser.registerExpense(meal.dish().dishValue().
					amountAsDecimal());
				PersistenceContext.repositories().cafeteriaUsers().save(theUser);
				PersistenceContext.repositories().meals().save(meal);
				return reserve;
			} else {
				System.out.
					println("Reservation time limit exceed or there are no available meals to reserve!");
			}
		} else {
			System.out.println("Your current balance is not enough!");
		}
		return null;
	}
}
