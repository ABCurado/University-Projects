/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao Martins
 */
class InMemoryMealRepository extends InMemoryRepository<Meal, Long> implements MealRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Meal entity) {
		return ++nextID;
	}

	@Override
	public Iterable<Meal> dayMeals(Calendar day) {
		List<Meal> meals = new ArrayList();

		for (Map.Entry<Long, Meal> entry : repository.entrySet()) {
			if (entry.getValue().date().get(Calendar.DAY_OF_MONTH) == day.
				get((Calendar.DAY_OF_MONTH))
				&& entry.getValue().date().get(Calendar.MONTH) == day.
				get(Calendar.MONTH)
				&& entry.getValue().date().get(Calendar.YEAR) == day.
				get(Calendar.YEAR)) {
				meals.add(entry.getValue());
			}
		}

		return meals;
	}

	@Override
	public Iterable<Meal> mealsOfMenu(Menu menu) {
		List<Meal> meals = new ArrayList();

		for (Map.Entry<Long, Meal> entry : repository.entrySet()) {
			if (entry.getValue().mealMenu().sameAs(menu)) {
				meals.add(entry.getValue());
			}
		}

		return meals;
	}

	@Override
	public Iterable<Meal> mealsByDateMealType(Calendar date, MealType mealType,
											  Menu menu) {
		List<Meal> meals = new ArrayList();

		for (Meal meal : this.mealsOfMenu(menu)) {
			if (DateTime.isSameDate(meal.date(), date) && meal.mealType().
				equals(mealType)) {
				meals.add(meal);
			}
		}

		return meals;
	}

	@Override
	public Iterable<Meal> mealsOfPublishedMenuFromDate(Calendar date) {
		List<Meal> meals = new ArrayList<>();

		for (Meal meal : this.dayMeals(date)) {
			if (meal.mealMenu().isPublished()) {
				meals.add(meal);
			}
		}

		return meals;
	}

	@Override
	public Iterable<Meal> mealsCurrentWeekDay(int dayWeek) {
		Calendar date = DateTime.dateCurrentWeekDay(dayWeek);
		List<Meal> meals = new ArrayList();
		for (Menu menu : PersistenceContext.repositories().menus().
			publishedMenuBetweenDates(date)) {
			for (Meal meal : this.mealsOfMenu(menu)) {
				if (DateTime.isSameDate(meal.date(), date)) {
					meals.add(meal);
				}
			}
		}
		return meals;
	}

}
