package eapli.ecafeteria.backoffice.consoleapp.presentation.menus;

import eapli.framework.actions.Action;

/**
 *
 * @author JoséBarros(1140364)
 */
public class ConsultMealRatingAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultMealRatingUI().show();
    }

}
