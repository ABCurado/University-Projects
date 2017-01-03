package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class MealTypePrinter implements Visitor<MealType> {

	@Override
	public void visit(MealType visitee) {
		System.out.println(visitee.designation());
	}

	@Override
	public void beforeVisiting(MealType visitee) {
		//do nothing
	}

	@Override
	public void afterVisiting(MealType visitee) {
	}

}
