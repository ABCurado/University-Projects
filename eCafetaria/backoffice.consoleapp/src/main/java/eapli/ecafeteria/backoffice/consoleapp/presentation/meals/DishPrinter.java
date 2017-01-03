package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Rui Freitas
 */
public class DishPrinter implements Visitor<Dish> {

	@Override
	public void visit(Dish visitee) {
		System.out.
			printf("%-50s%-10s%-10s%-10s%-10s%s\n", visitee.name(), visitee.
				   dishType(), String.valueOf(visitee.calories()), String.
				   valueOf(visitee.amountSalt()), String.valueOf(visitee.
				   amountFat()), visitee.priceValue());
	}

	@Override
	public void beforeVisiting(Dish visitee) {
	}

	@Override
	public void afterVisiting(Dish visitee) {
	}

}
