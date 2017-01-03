package eapli.ecafeteria.backoffice.consoleapp.presentation.authz;

import eapli.framework.actions.Action;

/**
 *
 * @author losa
 */
public class ListUsersAction implements Action {

    @Override
    public boolean execute() {
        return new ListUsersUI().show();
    }
}
