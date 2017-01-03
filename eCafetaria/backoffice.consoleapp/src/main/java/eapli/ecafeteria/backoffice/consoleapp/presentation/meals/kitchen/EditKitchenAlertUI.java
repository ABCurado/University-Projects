package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.ecafeteria.application.KitchenAlertController;
import eapli.ecafeteria.domain.cafeteria.Kitchen.KitchenAlert;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

public class EditKitchenAlertUI extends AbstractUI {

	@Override
	protected boolean doShow() {
		final KitchenAlertController controller = new KitchenAlertController();

		final Iterable<KitchenAlert> kitchenAlerts = controller.allAlerts();
		final SelectWidget<KitchenAlert> selector = new SelectWidget<>(kitchenAlerts, new KitchenAlertsPrinter());
		selector.show();
		final KitchenAlert kitchenAlertSelected = selector.selectedElement();

		if (kitchenAlertSelected != null) {
			Double ratio = Console.readDouble("Insert new Percentage:") / 100;
			try {
				controller.editAlert(kitchenAlertSelected, ratio);
			} catch (DataConcurrencyException ex) {
                            System.out.println("Data has changed or has been deleted since it was last read. Please try again.");
			}
		}
		return true;
	}

	@Override
	public String headline() {
		return "Edit Kitchen Alert";
	}

}
