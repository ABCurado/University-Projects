
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.framework.actions.Action;

/**
 *
 * @author Diogo Leite<1140260@isep.ipp.pt>
 */
public class CancelReserveAction implements Action {

    @Override
    public boolean execute() {
        return new CancelReserveUI().show();
    }
    
}
