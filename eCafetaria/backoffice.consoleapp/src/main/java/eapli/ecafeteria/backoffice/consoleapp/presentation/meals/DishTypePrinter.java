package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Gandra Sousa
 *
 */
class DishTypePrinter implements Visitor<DishType> {

	@Override
	public void visit(DishType visitee) {
		System.out.printf("%-30s%-10s\n", visitee.description(), visitee.
						  isActive() ? "Active" : "Deactivated");
	}

	@Override
	public void beforeVisiting(DishType visitee) {
		// nothing to do
	}

	@Override
	public void afterVisiting(DishType visitee) {
		// nothing to do
	}
}
