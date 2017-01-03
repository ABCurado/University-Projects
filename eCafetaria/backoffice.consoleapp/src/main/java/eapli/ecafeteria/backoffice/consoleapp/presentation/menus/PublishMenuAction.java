package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

/**
 *
 * @author Martins
 */
public class PublishMenuAction implements Action {

	@Override
	public boolean execute() {
		return new PublishMenuUI().show();
	}

}
