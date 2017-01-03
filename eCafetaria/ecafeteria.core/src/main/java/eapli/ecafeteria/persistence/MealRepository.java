/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.repositories.Repository;
import java.util.Calendar;

/**
 *
 * @author Joao Martins
 */
public interface MealRepository extends Repository<Meal, Long> {

	public Iterable<Meal> dayMeals(Calendar day);

	public Iterable<Meal> mealsByDateMealType(Calendar date, MealType mealType,
											  Menu menu);

	public Iterable<Meal> mealsOfMenu(Menu menu);

	public Iterable<Meal> mealsOfPublishedMenuFromDate(Calendar date);

	public Iterable<Meal> mealsCurrentWeekDay(int dayWeek);

}
