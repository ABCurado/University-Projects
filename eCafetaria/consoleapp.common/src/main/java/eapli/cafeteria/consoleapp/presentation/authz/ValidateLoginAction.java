/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.consoleapp.presentation.authz;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.Action;
import eapli.framework.actions.CompoundAction;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class ValidateLoginAction extends CompoundAction {

    private ActionRight expectedActionRight;

    public ValidateLoginAction(ActionRight permission, Action next) {
        super(next);
        expectedActionRight = permission;
    }

    @Override
    public boolean execute() {
        LoginUI loginUI = new LoginUI();
        if (loginUI.show()) {
            if (AppSettings.instance().session().authenticatedUser().isAuthorizedTo(expectedActionRight)) {
                return next();
            } else {
                System.out.println("you are not authorized for using this app");
            }
        }
        return false;
    }
}
