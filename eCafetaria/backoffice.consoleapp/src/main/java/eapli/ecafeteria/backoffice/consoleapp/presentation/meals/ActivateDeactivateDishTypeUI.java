package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.ActivateDeactivateDishTypeController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * Created by MCN on 29/03/2016.
 */
public class ActivateDeactivateDishTypeUI extends AbstractUI {

    private final ActivateDeactivateDishTypeController theController = new ActivateDeactivateDishTypeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final Iterable<DishType> allDishTypes = this.theController.listDishTypes();
        if (!allDishTypes.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            try {
                final SelectWidget<DishType> selector = new SelectWidget<>(allDishTypes, new DishTypePrinter());
                selector.show();
                final DishType updtDishType = selector.selectedElement();
                if(updtDishType != null) {
                    this.theController.changeDishTypeState(updtDishType);
                }
            } catch (DataConcurrencyException ex) {
                System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dish Types";
    }
}
