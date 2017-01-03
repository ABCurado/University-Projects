package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author João Martins
 */
public class ListAllMenusAction implements Action {

	@Override
	public boolean execute() {
		return new ListAllMenusUI().show();
	}
}
