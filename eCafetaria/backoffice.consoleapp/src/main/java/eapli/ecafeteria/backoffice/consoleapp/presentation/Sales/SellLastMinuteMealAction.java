package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.framework.actions.Action;

/**
 *
 * @author Rafael Rocha <1140329>
 */
public class SellLastMinuteMealAction implements Action{
    	@Override
	public boolean execute() {
		return new SellLastMinuteMealUI().show();
	}
}
