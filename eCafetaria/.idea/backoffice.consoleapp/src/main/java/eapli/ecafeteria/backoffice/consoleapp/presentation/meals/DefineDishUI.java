package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.DefineDishController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;
import java.math.BigDecimal;

/**
 *
 * @author Rui Freitas
 */
public class DefineDishUI extends AbstractUI {

	private final DefineDishController theController = new DefineDishController();

	protected Controller controller() {
		return this.theController;
	}

	@Override
	protected boolean doShow() {

		final Iterable<DishType> dishTypes = this.theController.listDishTypes();
		final SelectWidget<DishType> selector = new SelectWidget<>(dishTypes, new DishTypePrinter());
		selector.show();
		final DishType theDishType = selector.selectedElement();

		final String name = Console.readLine("Dish name:");
		final double calories = Console.readDouble("Dish calories:");
		final double amountSalt = Console.readDouble("Dish salt:");
		final double amountFat = Console.readDouble("Dish fat:");
		final BigDecimal price = BigDecimal.valueOf(Console.
			readDouble("Dish price:"));

		try {
			this.theController.
				defineDish(theDishType, name, calories, amountSalt, amountFat, price);
		} catch (final DataIntegrityViolationException e) {
			System.out.println("That dish is already in use.");
		}
		return false;
	}

	@Override
	public String headline() {
		return "Define Dish";
	}

}
