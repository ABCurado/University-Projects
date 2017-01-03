package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Rúben
 */
public class MenuPrinter implements Visitor<Menu> {

	@Override
	public void visit(Menu visitee) {
		System.out.printf("%-30s\n", visitee.description());
	}

	@Override
	public void beforeVisiting(Menu visitee) {
		// nothing to do
	}

	@Override
	public void afterVisiting(Menu visitee) {
		// nothing to do
	}

}
