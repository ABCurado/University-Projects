package eapli.ecafeteria.backoffice.consoleapp.presentation.authz;

import eapli.framework.actions.Action;

/**
 *
 * @author Fernando
 */
public class DeactivateUserAction implements Action {

    @Override
    public boolean execute() {
        return new DeactivateUserUI().show();
    }
}
