package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.MealMenuController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import java.util.Calendar;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class CreateMenuUI extends AbstractUI {

	@Override
	protected boolean doShow() {
		final MealMenuController controller = new MealMenuController();

		//Define a Menu
		final Calendar beginDate = Console.
			readCalendar("\nInsert Menu begin date (dd-MM-yyyy):", "dd-MM-yyyy");
		final Calendar endDate = Console.
			readCalendar("\nInsert Menu end date (dd-MM-yyyy):", "dd-MM-yyyy");
		final String acronym = Console.
			readLine("\nInsert Menu name:");
		try {
			controller.addMenu(acronym, beginDate, endDate);
		} catch (DataIntegrityViolationException ex) {
			System.out.
				println("The data integrity was violated. Please try again.");
		} catch (DataConcurrencyException ex) {
			System.out.
				println("Data has changed or has been deleted since it was last read. Please try again.");
		}

		return true;
	}

	@Override
	public String headline() {
		return "Create Meal Menu";
	}
}
