/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.ReserveMealController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import java.util.Calendar;

/**
 *
 * @author Martins
 */
public class ReserveMealUI extends AbstractUI {

	private final ReserveMealController theController = new ReserveMealController();

	@Override
	protected boolean doShow() {

		/* Meal section */
		Calendar date = Console.readCalendar("Insert meal date: (dd-MM-yyyy)");

		/* Meal type section */
		final Iterable<MealType> mealType = this.theController.
			listMealType(date);

		if (!mealType.iterator().hasNext()) {
			System.out.println("There no meal types defined!");
			return false;
		}

		final SelectWidget<MealType> mealTypeSelector = new SelectWidget<>(mealType, new MealTypePrinter());
		mealTypeSelector.show();

		final MealType theMealType = mealTypeSelector.selectedElement();

		if (theMealType == null) {
			return false;
		}

		final Iterable<Meal> meals = this.theController.
			listMeals(date, theMealType);

		if (meals == null) {
			System.out.println("There are no meals available!");
			return false;
		}

		final SelectWidget<Meal> mealSelector = new SelectWidget<>(meals, new MealPrinter());
		mealSelector.show();

		final Meal theMeal = mealSelector.selectedElement();

		if (theMeal != null) {
			try {
				if (this.theController.
					registerReserve(AppSettings.
						getCurrentUser(), theMeal) == null) {
					System.out.println("Reserve not registered.");
				} else {
					System.out.println("Reserve registerd.");
				}
			} catch (DataConcurrencyException ex) {
				System.out.
					println("Data was changed meanwhile. Please try again.");
			} catch (DataIntegrityViolationException ex) {
				System.out.println("Reservation already registered!");
			}
		}

		return false;
	}

	@Override
	public String headline() {
		return "Reserve Meal";
	}

}
