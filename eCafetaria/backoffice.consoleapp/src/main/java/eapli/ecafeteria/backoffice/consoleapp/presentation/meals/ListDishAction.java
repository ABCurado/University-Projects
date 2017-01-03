package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Rui Freitas
 */
public class ListDishAction implements Action {

	@Override
	public boolean execute() {
		return new ListDishUI().show();
	}
}
