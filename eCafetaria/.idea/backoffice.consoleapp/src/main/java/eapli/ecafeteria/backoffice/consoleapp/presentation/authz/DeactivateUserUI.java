/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.authz;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.DeactivateUserController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author Fernando
 */
public class DeactivateUserUI extends AbstractUI {

    private final DeactivateUserController theController = new DeactivateUserController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final List<SystemUser> list = new ArrayList<>();
        final Iterable<SystemUser> iterable = this.theController.listUsers();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            int cont = 1;
            System.out.println("SELECT User to deactivate\n");
            // FIXME use select widget, see, ChangeDishTypeUI
            System.out.printf("%-6s%-10s%-30s%-30s\n", "Nº:", "Username", "Firstname", "Lastname");
            for (final SystemUser user : iterable) {
                list.add(user);
                System.out.printf("%-6d%-10s%-30s%-30s\n", cont, user.username(), user.name().firstName(),
                        user.name().lastName());
                cont++;
            }
            final int option = Console.readInteger("Enter user nº to deactivate or 0 to finish ");
            if (option == 0) {
                System.out.println("No user selected");
            } else {
                this.theController.deactivateUser(list.get(option - 1));
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Deactivate User";
    }
}
