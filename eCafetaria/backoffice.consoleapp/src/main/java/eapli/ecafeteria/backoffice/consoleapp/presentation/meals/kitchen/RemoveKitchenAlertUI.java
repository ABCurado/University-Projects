package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.ecafeteria.application.KitchenAlertController;
import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RemoveKitchenAlertUI extends AbstractUI {

    @Override
    protected boolean doShow() {
        final KitchenAlertController controller = new KitchenAlertController();

        final Iterable<KitchenAlert> kitchenAlerts = controller.allAlerts();
        final SelectWidget<KitchenAlert> selector = new SelectWidget<>(kitchenAlerts, new KitchenAlertsPrinter());
        selector.show();
        final KitchenAlert kitchenAlertSelected = selector.selectedElement();

        if (kitchenAlertSelected != null) {
            try {
                controller.removeAlert(kitchenAlertSelected);
            } catch (DataIntegrityViolationException ex) {
                System.out.println("Data has an integrity violation. Please try again.");
            } catch (DataConcurrencyException ex) {
                System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Remove Kitchen Alert";
    }

}
