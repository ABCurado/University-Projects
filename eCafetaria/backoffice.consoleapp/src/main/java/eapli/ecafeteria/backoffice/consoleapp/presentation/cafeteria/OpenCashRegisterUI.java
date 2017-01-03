package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.OpenCashRegisterController;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import java.util.Calendar;
import javax.persistence.NoResultException;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterUI extends AbstractUI {

	private OpenCashRegisterController controller;

	@Override
	protected boolean doShow() {

		controller = new OpenCashRegisterController();

		try {
			if (controller.isOpenedforUser()) {
				System.out.
					println("The register was already opened by this user");
				return true;
			}
		} catch (NoResultException e) {

		}

		final String number = Console.readLine("Insert cash register number");

		if (!controller.hasCashRegister(number)) {
			System.out.println("There is no cash register with that number");
			return false;
		}

		System.out.println("Choose option");

		Iterable<MealType> mealTypes = controller.getMealTypes();

		final MealTypeSelectWidget selector = new MealTypeSelectWidget(mealTypes, new MealTypePrinter());
		selector.show();

		if (selector.selectedOption() == 0) {
			MealType mt = controller.mealByDefault();
			if (!this.controller.hasMeal(Calendar.getInstance(), mt)) {
				System.out.println("No meal scheduled for now");
				return false;
			}

			if (this.controller.cantOpen(mt, Calendar.getInstance())) {
				System.out.println("Can't open again for this meal");
				return false;
			}

			try {
				if (this.controller.open(number, mt, Calendar.getInstance())) {
					System.out.
						println("Cash Register opened sucessfully for " + mt.
							designation() + ".");
				} else {
					System.out.println("Cash Register is already opened");
				}
			} catch (DataConcurrencyException ex) {
				System.out.
					println("Data was changed meanwhile. Please try again.");
			} catch (DataIntegrityViolationException ex) {
			}
			return true;
		} else {
			try {

				final MealType mt = selector.
					selectedElement();

				Calendar date = Console.
					readCalendar("Date (dd-MM-yyyy):", "dd-MM-yyyy");
				if (!this.controller.hasMeal(date, mt)) {
					System.out.println("There's no meal in the date selected");
					return false;
				}

				if (this.controller.cantOpen(mt, date)) {
					System.out.println("Can't open again for this meal");
					return false;
				}

				try {
					if (this.controller.open(number, mt, date)) {
						System.out.
							println("Cash Register opened sucessfully for " + mt.
								designation());
					} else {
						System.out.println("Cash Register is already opened");
					}
				} catch (DataConcurrencyException ex) {
					System.out.
						println("Data was changed meanwhile. Please try again.");
				} catch (DataIntegrityViolationException ex) {
				}
			} catch (NoResultException e) {
				System.out.println("There's no such Meal");
				return false;
			}

		}

		return true;
	}

	@Override
	public String headline() {
		return "Open CashRegister";
	}

}
