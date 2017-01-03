/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.utente.consoleapp.presentation.filesystem;

import eapli.ecafeteria.application.ExportExpensesController;
import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;
import eapli.util.Strings;
import java.util.Calendar;

/**
 *
 * @author nervousdev
 */
public class ExportExpensesXMLUI extends AbstractUI {

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

		Iterable<Transaction> finalList = controller.
			cafeteriaserTransactionsInPeriod(startDate, endDate);

		if (!finalList.iterator().hasNext()) {
			System.out.
				println("User has no expenses registered in the select period!");
			return true;

		}
		try {
			if (controller.exportExpensesXML(finalList, fileName)) {
				System.out.println("\nFile created!");
			} else {
				System.out.println("\nError creating file!");
			}
		} catch (Exception ex) {
			System.out.println("\nError creating file!");
		}
		return true;
	}

	@Override
	public String headline() {
		return "Export expenses to XML";
	}
}
