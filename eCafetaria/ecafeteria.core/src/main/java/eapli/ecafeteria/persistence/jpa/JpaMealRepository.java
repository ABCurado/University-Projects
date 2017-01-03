/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealInformation;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.domain.meals.MenuState;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Joao Martins
 */
public class JpaMealRepository extends JpaRepository<Meal, Long> implements MealRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<Meal> dayMeals(Calendar date) {
		/*
		 final Query q = entityManager().
		 createQuery("SELECT m FROM Meal m WHERE m.mealDate=:date", Meal.class);
		 q.setParameter("date", day);
		 return (Iterable<Meal>) q.getResultList();
		 */
		List<Meal> meals = new ArrayList();
		for (Meal meal : this.all()) {
			MealInformation info = meal.id();
			if (info.day() == DateTime.day(date) && info.month() == DateTime.
				month(date) && info.year() == DateTime.year(date)) {
				meals.add(meal);
			}
		}
		return meals;
	}

	@Override
	public Iterable<Meal> mealsOfMenu(Menu menu) {
		final Query q = entityManager().
			createQuery("SELECT m FROM Meal m WHERE m.mealInformation.menu=:menu", Meal.class);
		q.setParameter("menu", menu);
		return (Iterable<Meal>) q.getResultList();
	}

	@Override
	public Iterable<Meal> mealsByDateMealType(Calendar date, MealType mealType,
											  Menu menu) {

		final Query q = entityManager().
			createQuery("SELECT m FROM Meal m WHERE m.mealInformation.day=:day AND m.mealInformation.month=:month AND m.mealInformation.year=:year AND m.mealInformation.type=:mealType AND m.mealInformation.menu=:menu", Meal.class);
		q.setParameter("day", DateTime.day(date));
		q.setParameter("month", DateTime.month(date));
		q.setParameter("year", DateTime.year(date));
		q.setParameter("mealType", mealType);
		q.setParameter("menu", menu);
		return q.getResultList();
	}

	@Override
	public Iterable<Meal> mealsOfPublishedMenuFromDate(Calendar date) {
		final Query q = entityManager().
			createQuery("SELECT m FROM Meal m WHERE m.mealInformation.day=:day AND m.mealInformation.month=:month AND m.mealInformation.year=:year AND m.mealInformation.menu.menuPeriod.menuState = :state", Meal.class);
		q.setParameter("day", DateTime.day(date));
		q.setParameter("month", DateTime.month(date));
		q.setParameter("year", DateTime.year(date));
		q.setParameter("state", MenuState.published);
		return q.getResultList();
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
