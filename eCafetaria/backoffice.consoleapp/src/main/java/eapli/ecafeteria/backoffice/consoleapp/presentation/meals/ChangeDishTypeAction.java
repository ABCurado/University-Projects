package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeAction implements Action {

    @Override
    public boolean execute() {
        return new ChangeDishTypeUI().show();
    }
}
