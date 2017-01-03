package eapli.ecafeteria.backoffice.consoleapp.presentation.Sales;

import eapli.framework.actions.Action;

/**
 *
 * @author JoséBarros(1140364)
 */
public class RegisterDeliveredReserveAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterDeliveredReserveUI().show();
    }

}

