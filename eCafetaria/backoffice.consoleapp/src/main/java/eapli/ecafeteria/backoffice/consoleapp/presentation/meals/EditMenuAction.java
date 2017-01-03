package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class EditMenuAction implements Action {

	@Override
	public boolean execute() {
		return new EditMenuUI().show();
	}

}
