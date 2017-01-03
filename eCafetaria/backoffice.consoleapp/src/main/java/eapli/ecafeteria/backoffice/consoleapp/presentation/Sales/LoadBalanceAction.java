package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.framework.actions.Action;

/**
 *
 * @author AB-<1140280>
 */
public class LoadBalanceAction implements Action{
    	@Override
	public boolean execute() {
		return new LoadBalanceUI().show();
	}
}
