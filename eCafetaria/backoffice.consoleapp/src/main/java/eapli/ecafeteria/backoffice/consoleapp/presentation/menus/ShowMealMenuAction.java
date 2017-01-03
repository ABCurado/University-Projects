package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

/**
 *
 * @author Martins
 */
public class ShowMealMenuAction implements Action {

	@Override
	public boolean execute() {
		return new ListMealUI().show();
	}

}
