/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitWithMessageAction;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.utente.consoleapp.presentation.authz.ViewBalanceAction;
import eapli.framework.presentation.console.AbstractUI;
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

    /*
     * Option not being used private static final int DISH_TYPE_OPTION = 2;
     */
    public MainMenu() {
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
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        return renderer.show();
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AppSettings.instance().session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        
        
        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));
     
        mainMenu.add(VerticalSeparator.separator());

        // TODO add menu options
        System.out.println(new ViewBalanceAction().execute());

        mainMenu.add(VerticalSeparator.separator());

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }
}
