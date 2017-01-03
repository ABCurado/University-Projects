/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeUI extends AbstractUI {

    private final ChangeDishTypeController theController = new ChangeDishTypeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<DishType> dishTypes = this.theController.listDishTypes();
        final SelectWidget<DishType> selector = new SelectWidget<>(dishTypes, new DishTypePrinter());
        selector.show();
        final DishType theDishType = selector.selectedElement();
        if (theDishType != null) {
            final String newDescription = Console
                    .readLine("Enter new description for " + theDishType.description() + ": ");
            this.theController.changeDishType(theDishType, newDescription);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";
    }
}
