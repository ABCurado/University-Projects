package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class CreateMenuAction implements Action {

    @Override
    public boolean execute() {
        return new CreateMenuUI().show();
    }
    
}
