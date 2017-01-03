package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.cafeteria.Kitchen.ExecutionControl;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.ExecutionControlRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MealMenuController implements Controller {

	/**
	 * List all meal type - calls the class ListTypeMeals.
	 *
	 * @return all meal types in a iterable of meal type
	 */
	public Iterable<MealType> listAllMealTypes() {
		return new ListTypeMealsService().allMealTypes();
	}

	/**
	 * List all dish types - calls the class ListDishTypeService.
	 *
	 * @return all dish types in a iterable of dish type
	 */
	public Iterable<DishType> listAllDishTypes() {
		return new ListDishTypeService().allDishTypes();
	}

	/**
	 * List dishes of an especific type. The method return the dishes of the
	 * parameter type.
	 *
	 * @param dishType
	 * @return list of dish (specific type)
	 */
	public List<Dish> listDishesOfEspecificType(DishType dishType) {
		ListDishService listDishService = new ListDishService();
		Iterable<Dish> listDishes = listDishService.allDishes();
		List<Dish> listSpecificType = new ArrayList<>();
		for (Dish dish : listDishes) {
			if (dish.dishType().equals(dishType.id())) {
				listSpecificType.add(dish);
			}
		}
		return listSpecificType;
	}

	public Iterable<Dish> listAllDishes() {
		ListDishService listDishService = new ListDishService();
		return listDishService.allDishes();
	}

	/**
	 * Save new meal in the database. Create an object and send it to the
	 * repository.
	 *
	 * @param mealType
	 * @param date
	 * @param dish
	 * @param menu
	 * @param planned
	 * @return
	 * @throws DataIntegrityViolationException
	 * @throws DataConcurrencyException
	 */
	public Meal addMeal(MealType mealType, Calendar date, Dish dish,
						Menu menu, int planned) throws DataIntegrityViolationException, DataConcurrencyException {
		Meal meal = new Meal(mealType, dish, date, planned, menu);
		PersistenceContext.repositories().meals().add(meal);
		ExecutionControl executionControl = new ExecutionControl(meal);
		PersistenceContext.repositories().executionControl().
			add(executionControl);
		return meal;
	}

	public void editMeal(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
		PersistenceContext.repositories().meals().save(meal);
	}

	public void removeMenu(Menu menu) throws DataIntegrityViolationException, DataConcurrencyException {
		for (Meal meal : PersistenceContext.repositories().meals().
			mealsOfMenu(menu)) {
			this.removeMeal(meal);
		}
		PersistenceContext.repositories().menus().delete(menu);
	}

	/**
	 * Save new menu in the database. Create an object and send it to the
	 * repository.
	 *
	 * @param acronym
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DataIntegrityViolationException
	 * @throws DataConcurrencyException
	 */
	public Menu addMenu(String acronym, Calendar startDate, Calendar endDate) throws DataIntegrityViolationException, DataConcurrencyException {
		final MenuRepository repo = PersistenceContext.repositories().menus();
		Menu menu = new Menu(startDate, endDate, acronym);
		repo.add(menu);
		return menu;
	}

	public void removeMeal(Meal meal) throws DataIntegrityViolationException, DataConcurrencyException {
		ExecutionControlRepository repo = PersistenceContext.repositories().
			executionControl();
		ExecutionControl executionControl = repo.executionControlByMeal(meal);
		repo.delete(executionControl);
	}

	public Iterable<Meal> currentMenuMeals(Menu menu) {
		return new ListMealService().mealsOfMenu(menu);
	}

	public Iterable<Menu> listUnPublishedMenus() {
		return new ListMenuService().listUnPublishedMenus();
	}
}
