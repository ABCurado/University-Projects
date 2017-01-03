package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.framework.actions.Action;

public class CreateKitchenAlertAction implements Action {

	@Override
	public boolean execute() {
		return new CreateKitchenAlertUI().show();
	}

}
