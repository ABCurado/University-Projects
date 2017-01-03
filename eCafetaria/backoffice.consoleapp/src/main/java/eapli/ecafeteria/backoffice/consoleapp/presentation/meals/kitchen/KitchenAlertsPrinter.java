package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Martins
 */
class KitchenAlertsPrinter implements Visitor<KitchenAlert> {

	@Override
	public void visit(KitchenAlert visitee) {
		System.out.printf("%-30s\n", visitee.toString());
	}

	@Override
	public void beforeVisiting(KitchenAlert visitee) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void afterVisiting(KitchenAlert visitee) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
