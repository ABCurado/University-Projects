package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.framework.actions.Action;

/**
 *
 * @author Diogo Leite<1140260@isep.ipp.pt>
 */
public class CloseCashRegisterAction implements Action {

	@Override
	public boolean execute() {
		new CloseCashRegisterUI().doShow();
		throw new NullPointerException();
	}

}
