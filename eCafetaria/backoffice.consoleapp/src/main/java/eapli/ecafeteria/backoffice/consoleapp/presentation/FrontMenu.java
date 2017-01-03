package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitWithoutMessageAction;
import eapli.cafeteria.consoleapp.presentation.authz.LoginAction;
import eapli.framework.actions.IfThenAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowUiAction;
import eapli.framework.presentation.console.VerticalMenuRenderer;

/**
 *
 * @author 1130303
 */
public class FrontMenu extends AbstractUI {

	private static final int EXIT_OPTION = 0;

	private static final int LOGIN_OPTION = 1;

	public FrontMenu() {
	}

	@Override
	public boolean show() {
		drawFormTitle();
		return doShow();
	}

	/**
	 * @return true if the user selected the exit option
	 */
	@Override
	public boolean doShow() {
		final Menu menu = new Menu();
		menu.add(new MenuItem(LOGIN_OPTION, "Login",
							  new IfThenAction(new LoginAction(), new ShowUiAction(new MainMenu()))));
		menu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithoutMessageAction()));
		final MenuRenderer renderer = new VerticalMenuRenderer(menu);
		return renderer.show();
	}

	@Override
	public String headline() {
		return "eCAFETERIA";
	}
}
