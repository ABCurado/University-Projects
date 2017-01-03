/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitAndLogoutWithMessage;
import eapli.cafeteria.consoleapp.presentation.MainMenuController;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.utente.consoleapp.presentation.authz.ViewBalanceAction;
import eapli.ecafeteria.utente.consoleapp.presentation.filesystem.ExportExpensesCSVAction;
import eapli.ecafeteria.utente.consoleapp.presentation.filesystem.ExportExpensesXMLAction;
import eapli.ecafeteria.utente.consoleapp.presentation.filesystem.ExportReservationsiCalAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.CancelReserveAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ConsultAccountExpensesAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ConsultCaloricConsumptionAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ConsultReservesAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ConsultWeeklyMenuAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.GiveMealRatingAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ReserveMealAction;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ShowReservationsForWeekAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.actions.ShowMessageAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

	private static final int EXIT_OPTION = 0;

	// MAIN MENU
	private static final int MY_USER_OPTION = 1;
	private static final int RESERVE_MENU_OPTION = 2;
	private static final int ACCOUNT_CARD_MENU_OPTION = 3;
	private static final int RATING_CALORIES_MENU_OPTION = 4;

	// RESERVES MENU
	private static final int CONSULT_CURRENT_WEEK_MENU_OPTION = 1;
	private static final int RESERVE_MEAL_OPTION = 2;
	private static final int RESERVE_WEEK_MEALS_OPTION = 3;
	private static final int CONSULT_NEXT_RESERVES_OPTION = 4;
	private static final int CANCEL_RESERVE_OPTION = 5;
	private static final int EXPORT_ICAL_OPTION = 6;

	// ACCOUNT CARD
	private static final int CONSULT_ACCOUNT_EXPENSES_OPTION = 1;
	private static final int EXPORT_EXPENSES_CSV_OPTION = 2;
	private static final int EXPORT_EXPENSES_XML_OPTION = 3;
	private static final int CONSULT_MONTHLY_EXPENSES = 4;

	// RATING_CALORIES_OPTION
	private static final int CONSULT_CALORIES_OPTION = 1;
	private static final int MEAL_RATING_OPTION = 2;

	@Override
	public boolean show() {
		/*
		* Validation in case the user logout
		 */
		if (AppSettings.instance().session() == null) {
			return true;
		}
		drawFormTitle();
		return doShow();
	}

	/**
	 * @return true if the user selected the exit option
	 */
	@Override
	public boolean doShow() {
		final Menu menu = buildMainMenu();
		final MenuRenderer renderer;
		if (AppSettings.instance().isMenuLayoutHorizontal()) {
			renderer = new HorizontalMenuRenderer(menu);
		} else {
			renderer = new VerticalMenuRenderer(menu);
		}
		return renderer.show();
	}

	@Override
	public String headline() {
		return "eCAFETERIA [@" + AppSettings.instance().session().
			authenticatedUser().id() + "]";
	}

	private Menu buildMainMenu() {
		final Menu mainMenu = new Menu();

		if (!AppSettings.instance().isMenuLayoutHorizontal()) {
			mainMenu.add(VerticalSeparator.separator());
		}

		final Menu myUserMenu = new MyUserMenu();
		mainMenu.
			add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

		Iterable<CafeteriaUser> user = new MainMenuController().
			getCafeteriaUserAccount();

		if (user.iterator().hasNext()) {
			final Menu reserveMenu = buildReserveMenu();
			mainMenu.
				add(new SubMenu((RESERVE_MENU_OPTION), reserveMenu, new ShowVerticalSubMenuAction(reserveMenu)));

			final Menu accountCardMenu = buildAccountCardMenu();
			mainMenu.
				add(new SubMenu((ACCOUNT_CARD_MENU_OPTION), accountCardMenu, new ShowVerticalSubMenuAction(accountCardMenu)));

			final Menu ratingCaloriesMenu = buildRatingCalories();
			mainMenu.
				add(new SubMenu((RATING_CALORIES_MENU_OPTION), ratingCaloriesMenu, new ShowVerticalSubMenuAction(ratingCaloriesMenu)));

			new ViewBalanceAction().execute();
		}
		mainMenu.
			add(new MenuItem(EXIT_OPTION, "Exit", new ExitAndLogoutWithMessage()));

		return mainMenu;
	}

	private Menu buildReserveMenu() {
		final Menu menu = new Menu("Reservations >");

		menu.
			add(new MenuItem(CONSULT_CURRENT_WEEK_MENU_OPTION, "Consult current week's menu ", new ConsultWeeklyMenuAction()));
		menu.
			add(new MenuItem(RESERVE_MEAL_OPTION, "Meal's reservation", new ReserveMealAction()));
		menu.
			add(new MenuItem(RESERVE_WEEK_MEALS_OPTION, "Meal's reservation for a week", new ShowReservationsForWeekAction()));
		menu.
			add(new MenuItem(CONSULT_NEXT_RESERVES_OPTION, "Consult reservations for next days", new ConsultReservesAction()));
		menu.
			add(new MenuItem(CANCEL_RESERVE_OPTION, "Cancel reservations", new CancelReserveAction()));
		menu.
			add(new MenuItem(EXPORT_ICAL_OPTION, "Export to iCal", new ExportReservationsiCalAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	private Menu buildAccountCardMenu() {
		final Menu menu = new Menu("Account card >");

		menu.
			add(new MenuItem(CONSULT_ACCOUNT_EXPENSES_OPTION, "Consult account's expenses", new ConsultAccountExpensesAction()));
		menu.
			add(new MenuItem(EXPORT_EXPENSES_CSV_OPTION, "Export expenses to CSV file", new ExportExpensesCSVAction()));
		menu.
			add(new MenuItem(EXPORT_EXPENSES_XML_OPTION, "Export expenses to XML file", new ExportExpensesXMLAction()));
		menu.
			add(new MenuItem(CONSULT_MONTHLY_EXPENSES, "Consult monthly expenses with a chart", new ShowMessageAction("Not implemented yet")));

		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	private Menu buildRatingCalories() {
		final Menu menu = new Menu("Ratings / Calories >");
		menu.
			add(new MenuItem(CONSULT_CALORIES_OPTION, "Consult calorie consumption", new ConsultCaloricConsumptionAction()));
		menu.
			add(new MenuItem(MEAL_RATING_OPTION, "Rate a meal", new GiveMealRatingAction()));
		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}
}
