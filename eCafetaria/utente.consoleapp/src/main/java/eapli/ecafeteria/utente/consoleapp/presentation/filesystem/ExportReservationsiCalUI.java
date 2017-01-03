/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.filesystem;

import eapli.ecafeteria.application.ExportExpensesController;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import eapli.util.Strings;
import java.util.Calendar;

/**
 *
 * @author nervousdev
 */
public class ExportReservationsiCalUI extends AbstractUI {

	private final ExportExpensesController controller = new ExportExpensesController();

	@Override
	protected boolean doShow() {

		Calendar startDate = Console.
			readCalendar("Insert initial date");
		Calendar endDate = Console.readCalendar("Insert end date");

		String fileName;
		do {
			fileName = Console.readLine("Enter file name (with path): ");
		} while (Strings.isNullOrEmpty(fileName));

		Iterable<Reserve> theList = controller.
			reservationsByPeriod(startDate, endDate);

		if (!theList.iterator().hasNext()) {
			System.out.println("No reservations in the select period!");
			return false;
		}

		try {
			controller.exportReservationsiCal(theList, fileName);
			System.out.println("\nFile created!");
		} catch (Exception ex) {
			System.out.println("\nError creating file!");
		}

		return false;
	}

	@Override
	public String headline() {
		return "Export reservations to iCal file";
	}

}
