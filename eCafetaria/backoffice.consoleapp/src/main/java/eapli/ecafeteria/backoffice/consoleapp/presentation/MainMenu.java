package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitAndLogoutWithMessage;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.ListOrganicUnitsController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.Sales.LoadBalanceAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.AcceptRefuseSignupRequestAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.AddUserUI;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.DeactivateUserAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.ListUsersAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.AddOrganicUnitUI;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.OpenCashRegisterAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.OrganicUnitPrinter;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ChangeDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.CreateMenuAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.DefineDishAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.EditMenuAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ListAllMenusAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ListDishAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ListDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.RegisterDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.RemoveMenuAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen.CreateKitchenAlertAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen.EditKitchenAlertAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen.RegisterExecutedMealsAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen.RemoveKitchenAlertAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.menus.ConsultMealRatingAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.menus.PublishMenuAction;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.utente.consoleapp.presentation.mealbooking.ConsultWeeklyMenuAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.ListUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

	private static final int EXIT_OPTION = 0;

	// USERS
	private static final int ADD_USER_OPTION = 1;
	private static final int LIST_USERS_OPTION = 2;
	private static final int DEACTIVATE_USER_OPTION = 3;
	private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

	//Sales
	private static final int OPEN_CASH_REGISTER = 1;
	private static final int ADD_BALANCE_TO_ACCOUNT = 2;

	// ORGANIC UNITS
	private static final int ADD_ORGANIC_UNIT_OPTION = 1;
	private static final int LIST_ORGANIC_UNIT_OPTION = 2;

	// SETTINGS
	private static final int CREATE_KITCHEN_ALERT_OPTION = 1;
	private static final int EDIT_KITCHEN_ALERT_OPTION = 2;
	private static final int REMOVE_KITCHEN_ALERT_OPTION = 3;

	// DISH TYPES
	private static final int DISH_TYPE_REGISTER_OPTION = 1;
	private static final int DISH_TYPE_LIST_OPTION = 2;
	private static final int DISH_TYPE_CHANGE_OPTION = 3;
	private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

	//DISH
	private static final int DISH_DEFINE_OPTION = 1;
	private static final int DISH_LIST_OPTION = 2;

	//MEAL
	private static final int CREATE_MENU_OPTION = 1;
	private static final int EDIT_MENU_OPTION = 2;
	private static final int REMOVE_MENU_OPTION = 3;
	private static final int LIST_MENU_OPTION = 4;
	private static final int PUBLISH_MENU_OPTION = 5;
	private static final int CONSULT_WEEKLY_MENU = 6;
	private static final int MEAL_RATINGS_OPTION = 7;

	//KITCHEN
	private static final int REGISTER_MADE_MEALS_OPTION = 1;

	// MAIN MENU
	private static final int MY_USER_OPTION = 1;
	private static final int USERS_OPTION = 2;
	private static final int ORGANIC_UNITS_OPTION = 3;
	private static final int SETTINGS_OPTION = 4;
	private static final int DISH_TYPES_OPTION = 5;
	private static final int DISH_OPTION = 6;
	private static final int MEAL_OPTION = 7;
	private static final int SALES_OPTION = 8;
	private static final int KITCHEN_OPTION = 9;

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

		final Menu myUserMenu = new MyUserMenu();
		mainMenu.
			add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

		if (!AppSettings.instance().isMenuLayoutHorizontal()) {
			mainMenu.add(VerticalSeparator.separator());
		}

		if (AppSettings.instance().session().authenticatedUser().
			isAuthorizedTo(ActionRight.Administer)) {
			final Menu usersMenu = buildUsersMenu();
			mainMenu.
				add(new SubMenu(USERS_OPTION, usersMenu, new ShowVerticalSubMenuAction(usersMenu)));
			final Menu organicUnitsMenu = buildOrganicUnitsMenu();
			mainMenu.
				add(new SubMenu(ORGANIC_UNITS_OPTION, organicUnitsMenu, new ShowVerticalSubMenuAction(organicUnitsMenu)));
			final Menu settingsMenu = buildAdminSettingsMenu();
			mainMenu.
				add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));
		}
		if (AppSettings.instance().session().authenticatedUser().
			isAuthorizedTo(ActionRight.ManageMenus)) {
			final Menu myDishTypeMenu = buildDishTypeMenu();
			mainMenu.
				add(new SubMenu(DISH_TYPES_OPTION, myDishTypeMenu, new ShowVerticalSubMenuAction(myDishTypeMenu)));
			final Menu myDishMenu = buildDishMenu();
			mainMenu.
				add(new SubMenu(DISH_OPTION, myDishMenu, new ShowVerticalSubMenuAction(myDishMenu)));
			final Menu myMealMenu = buildMealMenu();
			mainMenu.
				add(new SubMenu(MEAL_OPTION, myMealMenu, new ShowVerticalSubMenuAction(myMealMenu)));

		}
		if (AppSettings.instance().session().authenticatedUser().
			isAuthorizedTo(ActionRight.Sale)) {
			final Menu SalesMenu = buildSalesMenu();
			mainMenu.
				add(new SubMenu(SALES_OPTION, SalesMenu, new ShowVerticalSubMenuAction(SalesMenu)));

			/*final Menu CashRegisterMenu = buidCashRegisterMenu();
			 SalesMenu.
			 add(new SubMenu(OPEN_CASH_REGISTER, CashRegisterMenu, new ShowVerticalSubMenuAction(CashRegisterMenu)));
			 */		}
		if (AppSettings.instance().session().authenticatedUser().
			isAuthorizedTo(ActionRight.ManageKitchen)) {
			final Menu registerMadeMeals = buildKitchenMenu();
			mainMenu.
				add(new SubMenu(KITCHEN_OPTION, registerMadeMeals, new ShowVerticalSubMenuAction(registerMadeMeals)));
		}
		if (AppSettings.instance().session().authenticatedUser().
			isAuthorizedTo(ActionRight.ConsultMenu)) {
			final Menu consultWeeklyMenu = buildMealMenu();
			mainMenu.
				add(new SubMenu(CONSULT_WEEKLY_MENU, consultWeeklyMenu, new ShowVerticalSubMenuAction(consultWeeklyMenu)));
		}

		if (!AppSettings.instance().isMenuLayoutHorizontal()) {
			mainMenu.
				add(VerticalSeparator.separator());
		}

		mainMenu.
			add(new MenuItem(EXIT_OPTION, "Exit", new ExitAndLogoutWithMessage()));
		return mainMenu;
	}

	private Menu buildAdminSettingsMenu() {
		final Menu menu = new Menu("Settings >");
		menu.
			add(new MenuItem(CREATE_KITCHEN_ALERT_OPTION, "Create kitchen alert menu", new CreateKitchenAlertAction()));
		menu.
			add(new MenuItem(EDIT_KITCHEN_ALERT_OPTION, "Edit kitchen alert menu", new EditKitchenAlertAction()));
		menu.
			add(new MenuItem(REMOVE_KITCHEN_ALERT_OPTION, "Remove kitchen alert menu", new RemoveKitchenAlertAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}

	private Menu buildOrganicUnitsMenu() {
		final Menu menu = new Menu("Organic units >");
		menu.
			add(new MenuItem(ADD_ORGANIC_UNIT_OPTION, "Add Organic Unit", () -> {
							 return new AddOrganicUnitUI().show();
						 }));
		menu.
			add(new MenuItem(LIST_ORGANIC_UNIT_OPTION, "List Organic Unit", () -> {
							 // example of using the generic list ui from the framework
							 new ListUI<OrganicUnit>(new ListOrganicUnitsController().
								 listOrganicUnits(), new OrganicUnitPrinter(),
													 "Organic Unit").show();
							 return false;
						 }));
		// TODO add other options for Organic Unit management
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}

	private Menu buildUsersMenu() {
		final Menu menu = new Menu("Users >");
		menu.add(new MenuItem(ADD_USER_OPTION, "Add User", () -> {
							  return new AddUserUI().show();
						  }));
		menu.
			add(new MenuItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction()));
		menu.
			add(new MenuItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction()));
		menu.
			add(new MenuItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}

	private Menu buildDishTypeMenu() {
		final Menu menu = new Menu("Dish Type >");
		menu.
			add(new MenuItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type", new RegisterDishTypeAction()));
		menu.
			add(new MenuItem(DISH_TYPE_LIST_OPTION, "List all Dish Type", new ListDishTypeAction()));
		menu.
			add(new MenuItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description", new ChangeDishTypeAction()));
		menu.
			add(new MenuItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type", new ActivateDeactivateDishTypeAction()));
		menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}

	private Menu buildDishMenu() {
		final Menu menu = new Menu("Dish >");
		menu.
			add(new MenuItem(DISH_DEFINE_OPTION, "Define new Dish", new DefineDishAction()));
		menu.
			add(new MenuItem(DISH_LIST_OPTION, "List all dish", new ListDishAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}

	private Menu buildSalesMenu() {
		final Menu menu = new Menu("Sales >");
		menu.
			add(new MenuItem(OPEN_CASH_REGISTER, "Open Cash Register", new OpenCashRegisterAction()));
		menu.
			add(new MenuItem(ADD_BALANCE_TO_ACCOUNT, "Add balance to an account", new LoadBalanceAction()));

		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

		return menu;
	}

	private Menu buildMealMenu() {
		final Menu menu = new Menu("Meal >");
		menu.
			add(new MenuItem(CREATE_MENU_OPTION, "Create Menu", new CreateMenuAction()));
		menu.
			add(new MenuItem(EDIT_MENU_OPTION, "Edit Menu", new EditMenuAction()));
		menu.
			add(new MenuItem(REMOVE_MENU_OPTION, "Remove Menu", new RemoveMenuAction()));
		menu.
			add(new MenuItem(LIST_MENU_OPTION, "List All Menus", new ListAllMenusAction()));
		menu.
			add(new MenuItem(PUBLISH_MENU_OPTION, "Publish Menu", new PublishMenuAction()));
		menu.
			add(new MenuItem(CONSULT_WEEKLY_MENU, "Consult current week menu", new ConsultWeeklyMenuAction()));
		menu.
			add(new MenuItem(MEAL_RATINGS_OPTION, "Consult meals ratings", new ConsultMealRatingAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}

	private Menu buildKitchenMenu() {
		final Menu menu = new Menu("Kitchen >");
		menu.
			add(new MenuItem(REGISTER_MADE_MEALS_OPTION, "Register executed meals menu", new RegisterExecutedMealsAction()));
		menu.
			add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
		return menu;
	}
}
