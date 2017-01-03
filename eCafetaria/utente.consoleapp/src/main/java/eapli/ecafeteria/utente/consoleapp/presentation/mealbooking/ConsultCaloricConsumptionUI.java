/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.mealbooking;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.application.ConsultCaloricConsumptionController;
import eapli.ecafeteria.application.UserService;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author nervousdev
 */
class ConsultCaloricConsumptionUI extends AbstractUI {

	private final ConsultCaloricConsumptionController controller = new ConsultCaloricConsumptionController();

	@Override
	protected boolean doShow() {
		Calendar startDate = Console.
			readCalendar("Insert initial date");
		Calendar endDate = Console.readCalendar("Insert end date");

		Iterable<CafeteriaUser> user = new UserService().
			cafeteriaUserBySystemUser(AppSettings.getCurrentUser());

		if (!user.iterator().hasNext()) {
			System.out.println("User not found!");
			return true;
		}
		CafeteriaUser theUser = user.iterator().next();

		double res = controller.
			getCaloricConsumptionByPeriod(startDate, endDate);

		System.out.
			println("Caloric Consumption: (" + DateTime.format(startDate) + ")(" + DateTime.
				format(endDate) + "): " + res + " calories\n");

		return false;
	}

	@Override
	public String headline() {
		return "Consult caloric consumption";
	}

}
