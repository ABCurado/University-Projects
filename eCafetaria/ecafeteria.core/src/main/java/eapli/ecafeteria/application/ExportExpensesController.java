package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.ecafeteria.domain.mealbooking.TransactionType;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.ecafeteria.filesystem.csv.CSVExpensesEncoder;
import eapli.ecafeteria.filesystem.ical.iCalReservationsEncoder;
import eapli.ecafeteria.filesystem.xml.XMLExpensesEncoder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.filesystem.impl.CSVEncoder;
import eapli.framework.filesystem.impl.XMLEncoder;
import eapli.framework.filesystem.impl.iCalEncoder;
import eapli.util.DateTime;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author nervousdev
 */
public class ExportExpensesController implements Controller {

	public Iterable<Transaction> cafeteriaserTransactionsInPeriod(
		Calendar startDate, Calendar endDate) {

		List<Transaction> finalList = new ArrayList<>();

		Iterable<CafeteriaUser> user = new UserService().
			cafeteriaUserBySystemUser(AppSettings.getCurrentUser());

		CafeteriaUser theUser = user.iterator().next();

		if (!theUser.hasTransactions()) {
			return finalList;
		}

		for (Transaction t : theUser.allTransactions()) {
			if (DateTime.isBetweenDates(startDate, endDate, t.transactionDate()) && t.
				getType().equals(TransactionType.DEBT)) {
				finalList.add(t);
			}
		}
		return finalList;
	}

	public Iterable<Reserve> reservationsByPeriod(Calendar startDate,
												  Calendar endDate) {
		Iterable<CafeteriaUser> user = new UserService().
			cafeteriaUserBySystemUser(AppSettings.getCurrentUser());

		CafeteriaUser theUser = user.iterator().next();

		return PersistenceContext.repositories().reserves().
			FindNextReverves(startDate, endDate, theUser);
	}

	public boolean exportExpensesCSV(Iterable<Transaction> listTransactions,
									 String fileName) throws IOException {
		CSVEncoder encoder = new CSVExpensesEncoder();

		encoder.writeElements(listTransactions);
		return encoder.saveFile(fileName);
	}

	public boolean exportExpensesXML(Iterable<Transaction> listTransactions,
									 String fileName) throws Exception {

		XMLEncoder encoder = new XMLExpensesEncoder(fileName);

		encoder.writeElements(listTransactions);
		encoder.saveXMLFile(fileName);
		return true;
	}

	public boolean exportReservationsiCal(Iterable<Reserve> listReservations,
										  String fileName) throws Exception {
		iCalEncoder encoder = new iCalReservationsEncoder();

		encoder.writeElements(listReservations);
		return encoder.saveFile(fileName);
	}
}
