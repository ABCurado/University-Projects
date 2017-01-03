package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.MealMenuController;
import eapli.ecafeteria.application.PublishMenuController;
import eapli.ecafeteria.application.ReserveMealController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;

public class MenuBootstrap implements Action {

	@Override
	public boolean execute() {

		Calendar today = DateTime.now();
		Calendar nextDay = DateTime.now();
		nextDay.add(Calendar.DATE, 1);
		Calendar nextFiveDays = DateTime.now();
		nextFiveDays.add(Calendar.DATE, 5);

		Menu menu = registerMenu("Menu ISEP", 0, 5);

		Meal meal = registerMeal("Jantar", "Salada de Agrião", menu, 0, 450);
		registerMeal("Jantar", "Salada Russa", menu, 0, 400);
		registerMeal("Jantar", "Costeleta à Salsicheira", menu, 0, 350);
		registerMeal("Almoço", "Salada de Agrião", menu, 0, 300);
		registerMeal("Almoço", "Costeleta à Salsicheira", menu, 0, 250);
		registerMeal("Almoço", "Salada de Agrião", menu, 1, 200);
		registerMeal("Jantar", "Salada Russa", menu, 1, 150);
		registerMeal("Almoço", "Costeleta à Salsicheira", menu, 1, 100);
		registerMeal("Jantar", "Costeleta à Salsicheira", menu, 1, 50);

		publishMenu(menu);

		reserveMeal("utente", meal);
		reserveMeal("utente", meal);
		reserveMeal("utente", meal);
		reserveMeal("utente", meal);
		reserveMeal("utente", meal);

		today = DateTime.now();
		today.add(Calendar.DATE, 7);
		nextDay = DateTime.now();
		nextDay.add(Calendar.DATE, 8);
		nextFiveDays = DateTime.now();
		nextFiveDays.add(Calendar.DATE, 12);

		menu = registerMenu("Menu FEUP", 7, 12);

		registerMeal("Jantar", "Legumes de inverno com limão", menu, 7, 550);
		registerMeal("Jantar", "Bacalhau à Brás", menu, 7, 400);
		registerMeal("Jantar", "Bifinhos de Frango", menu, 7, 350);
		registerMeal("Almoço", "Legumes de inverno com limão", menu, 7, 300);
		registerMeal("Almoço", "Bifinhos de Frango", menu, 7, 250);
		registerMeal("Almoço", "Legumes de inverno com limão", menu, 8, 200);
		registerMeal("Jantar", "Bacalhau à Brás", menu, 8, 150);
		registerMeal("Almoço", "Bifinhos de Frango", menu, 8, 100);
		registerMeal("Jantar", "Bifinhos de Frango", menu, 8, 50);

		return true;
	}

	private Menu registerMenu(String description, int startDaysToday, int endDaysToday) {
		if (description == null) {
			return null;
		}
		Calendar start = DateTime.now();
		start.add(Calendar.DAY_OF_MONTH, startDaysToday);
		Calendar end = DateTime.now();
		end.add(Calendar.DAY_OF_MONTH, endDaysToday);
		Menu menu = null;
		try {
			final MealMenuController controller = new MealMenuController();
			menu = controller.addMenu(description, start, end);
		} catch (DataIntegrityViolationException | DataConcurrencyException ex) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
		}
		return menu;
	}

	private Meal registerMeal(String mealTypeName, String dishName, Menu menu,
							  int daysToday, int planned) {
		if (mealTypeName == null || dishName == null || menu == null || planned < 0) {
			return null;
		}
		Calendar date = DateTime.now();
		date.add(Calendar.DAY_OF_MONTH, daysToday);
		Meal meal = null;
		try {
			final MealMenuController controller = new MealMenuController();
			MealType type = PersistenceContext.repositories().mealTypes().
				findByDesignation(mealTypeName);
			if (type == null) {
				return null;
			}
			Dish dish = PersistenceContext.repositories().dishes().
				findByDesignation(dishName);
			if (dish == null) {
				return null;
			}
			meal = controller.addMeal(type, date, dish, menu, planned);
		} catch (DataIntegrityViolationException | DataConcurrencyException ex) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
		}
		return meal;
	}

	private void publishMenu(Menu menu) {
		if (menu == null) {
			return;
		}
		try {
			final PublishMenuController publishMenuController = new PublishMenuController();
			publishMenuController.publishMenu(menu);
		} catch (Exception ex) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
		}
	}

	private void reserveMeal(String username, Meal meal) {
		if (username == null || meal == null) {
			return;
		}
		try {
			final ReserveMealController reserveMealController = new ReserveMealController();
			SystemUser user = PersistenceContext.repositories().users().
				findById(new Username(username));
			if (user == null) {
				return;
			}
			meal = PersistenceContext.repositories().meals().findById(meal.
				getId());
			if (meal == null) {
				return;
			}
			reserveMealController.registerReserve(user, meal);
		} catch (DataConcurrencyException | DataIntegrityViolationException ex) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
		}
	}

}
