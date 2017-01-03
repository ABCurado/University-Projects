/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author ruben
 */
public class ListTypeMeals {

	public Iterable<MealType> allMealTypes() {
		ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

		final MealTypeRepository mealTypeRepository = PersistenceContext.
			repositories().mealTypes();

		return mealTypeRepository.all();

	}

	public Iterable<MealType> activeDishMeals() {
		ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

		final MealTypeRepository mealTypeRepository = PersistenceContext.
			repositories().mealTypes();

		return mealTypeRepository.activeMealTypes();
	}

}
