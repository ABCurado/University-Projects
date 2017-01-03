package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.framework.actions.Action;

/**
 *
 * @author Martins
 */
public class RemoveKitchenAlertAction implements Action {

    @Override
    public boolean execute() {
        return new RemoveKitchenAlertUI().show();
    }

}
