/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.GiveMealRatingController;
import eapli.ecafeteria.application.UserService;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.AssignedRating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.RatingType;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoséBarros(1140364)
 */
public class GiveMealRatingUI extends AbstractUI {

	private final GiveMealRatingController theController = new GiveMealRatingController();

	@Override
	protected boolean doShow() {

		try {
			Iterable<CafeteriaUser> user = new UserService().
				cafeteriaUserBySystemUser(AppSettings.getCurrentUser());

			if (!user.iterator().hasNext()) {
				new ShowMessageAction("No user found or user is not a cafeteria user!").
					execute();
				return false;
			}

			CafeteriaUser theUser = user.iterator().next();
			final Iterable<Meal> meals = theController.
				listMealsByUserReserves(theUser);
			final SelectWidget<Meal> selector = new SelectWidget<>(meals, new MealPrinter());
			selector.show();

			final Meal theMeal = selector.selectedElement();
			if (theMeal != null) {

				AssignedRating aRating = theController.
					getRatingByUserTo(theMeal, theUser);

				if (aRating == null) {
					getMealRating(theMeal, theUser);
					return true;

				} else {
					final String option = Console.
						readLine("Already rated this meal. Wants to give again? (Y or N)");
					if (option.equalsIgnoreCase("y")) {
						replaceOldMealRating(theUser, theMeal, aRating);
					}
				}
			}

		} catch (NullPointerException ex) {
			System.out.println("This account isn't CafeteriaUser!" + "\n");
		} catch (DataConcurrencyException ex) {
                    System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
		}

		return false;
	}

	public void getMealRating(Meal meal, CafeteriaUser user) throws DataConcurrencyException {
		List<RatingType> ratings = new ArrayList<>();
		ratings.addAll(Arrays.asList(RatingType.values()));
		final SelectWidget<RatingType> selector = new SelectWidget<>(ratings, new RatingPrinter());
		selector.show();
		final RatingType theRating = selector.selectedElement();

		try {
			theController.
				addAssignedRating(new AssignedRating(user, meal, theRating));
			System.out.println("Assigned rating successfully");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@Override
	public String headline() {
		return "Meal Rating";
	}

	private void replaceOldMealRating(CafeteriaUser theUser, Meal theMeal,
									  AssignedRating oldRating) throws DataConcurrencyException {
		List<RatingType> ratings = new ArrayList<>();
		ratings.addAll(Arrays.asList(RatingType.values()));
		final SelectWidget<RatingType> selector = new SelectWidget<>(ratings, new RatingPrinter());
		selector.show();
		final RatingType theRating = selector.selectedElement();
		AssignedRating newRating = new AssignedRating(theUser, theMeal, theRating);
		try {
			theController.replaceAssignedRating(newRating, oldRating);
			System.out.println("Updated rating successfully");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
