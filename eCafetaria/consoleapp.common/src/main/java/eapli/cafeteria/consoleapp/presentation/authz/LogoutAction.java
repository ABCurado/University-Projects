package eapli.cafeteria.consoleapp.presentation.authz;

import eapli.cafeteria.consoleapp.presentation.MainMenuController;
import eapli.framework.actions.Action;

/**
 * Menu action for user logout. Created by nuno on 20/03/16.
 */
public class LogoutAction implements Action {

	@Override
	public boolean execute() {
		new MainMenuController().logoutCurrentUser();
		return true;
	}
}
