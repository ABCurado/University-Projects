package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.MealPrinter;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Eduardo
 */
public class ListMealUI extends AbstractListUI<Meal> {

	private final ListMealController controller = new ListMealController();

	protected Controller controller() {
		return (Controller) this.controller;
	}

	@Override
	protected Iterable<Meal> listOfElements() {
		return controller.listMeals();
	}

	@Override
	protected Visitor<Meal> elementPrinter() {
		return new MealPrinter();
	}

	@Override
	protected String elementName() {
		return "Menu";
	}

}
