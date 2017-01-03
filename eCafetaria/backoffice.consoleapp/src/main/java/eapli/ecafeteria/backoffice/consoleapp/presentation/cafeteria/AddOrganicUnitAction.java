package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.framework.actions.Action;

/**
 * @author arocha
 */
public class AddOrganicUnitAction implements Action {
	@Override
	public boolean execute() {
		return new AddOrganicUnitUI().show();
	}
}
