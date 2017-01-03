package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.backoffice.consoleapp.presentation.Sales.RegisterDeliveredReserveAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.Sales.SellLastMinuteMealAction;
import eapli.framework.actions.Action;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterAction implements Action {

	//Cash Register
	private static final int SELL_LAST_MINUTE_MEAL = 1;
	private static final int REGISTER_DELIVERED_RESERVE = 2;
	private static final int CLOSE_CASH_REGISTER = 3;
	private static final int EXIT_OPTION = 0;

	@Override
	public boolean execute() {
		if (new OpenCashRegisterUI().doShow()) {
			final Menu CashRegisterMenu = buidCashRegisterMenu();

			while (true) {
				try {
					new ShowVerticalSubMenuAction(CashRegisterMenu).
						execute();
				} catch (Exception e) {
					return false;
				}
			}
		}

		return false;
	}

	private Menu buidCashRegisterMenu() {
		final Menu menu = new Menu("Cash Register");

		menu.
			add(new MenuItem(SELL_LAST_MINUTE_MEAL, "Sell a last minute meal", new SellLastMinuteMealAction()));
		menu.
			add(new MenuItem(REGISTER_DELIVERED_RESERVE, "Register delivered meal reserve", new RegisterDeliveredReserveAction()));
		menu.
			add(new MenuItem(CLOSE_CASH_REGISTER, "Close Cash Register", new CloseCashRegisterAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnToSalesAction()));

		return menu;
	}

}
