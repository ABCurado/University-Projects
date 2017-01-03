package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.framework.actions.Action;

public class RegisterExecutedMealsAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterExecutedMealsUI().show();
    }

}
