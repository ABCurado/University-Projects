package eapli.ecafeteria.backoffice.consoleapp.presentation.meals.kitchen;

import eapli.ecafeteria.application.KitchenAlertController;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateKitchenAlertUI extends AbstractUI {

	@Override
	protected boolean doShow() {
		final KitchenAlertController controller = new KitchenAlertController();
		final String name = Console.readLine("Alert name:");
		final Double ratio = Console.readDouble("Alert percentenge:") / 100;
		try {
			controller.addAlert(name, ratio);
		} catch (DataIntegrityViolationException ex) {
			Logger.getLogger(CreateKitchenAlertUI.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		return true;
	}

	@Override
	public String headline() {
		return "Create Kitchen Alert";
	}

}
