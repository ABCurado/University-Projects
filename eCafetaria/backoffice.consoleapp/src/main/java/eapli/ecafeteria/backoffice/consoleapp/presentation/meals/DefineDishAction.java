package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Rui Freitas
 */
public class DefineDishAction implements Action {

	@Override
	public boolean execute() {
		return new DefineDishUI().show();
	}

}
