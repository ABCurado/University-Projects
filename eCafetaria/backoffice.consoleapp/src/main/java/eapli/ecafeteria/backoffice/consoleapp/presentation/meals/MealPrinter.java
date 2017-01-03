package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Rúben
 */
public class MealPrinter implements Visitor<Meal> {

	@Override
	public void visit(Meal visitee) {
		System.out.printf("%-30s\n", visitee);
	}

	@Override
	public void beforeVisiting(Meal visitee) {
		// nothing to do
	}

	@Override
	public void afterVisiting(Meal visitee) {
		// nothing to do
	}

}
