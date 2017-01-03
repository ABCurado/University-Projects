package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.ListDishController;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Rui Freitas
 */
public class ListDishUI extends AbstractListUI<Dish> {

	private final ListDishController theController = new ListDishController();

	@Override
	protected Iterable<Dish> listOfElements() {
		return theController.listDish();
	}

	@Override
	protected Visitor<Dish> elementPrinter() {
		return new DishPrinter();
	}

	@Override
	protected String elementName() {
		return "Dish";
	}

}
