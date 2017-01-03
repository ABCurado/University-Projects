package eapli.ecafeteria.filesystem.xml;

import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.framework.filesystem.impl.XMLEncoder;
import eapli.util.DateTime;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;

/**
 *
 * @author nervousdev
 */
public class XMLExpensesEncoder extends XMLEncoder<Transaction> {

	public XMLExpensesEncoder(String fileName) throws ParserConfigurationException {
		super();
	}

	@Override
	public void writeElements(Iterable<Transaction> listElements) {
		Element root = newElement("expenses");
		addElement(root);
		Element expense;
		Element amount;
		Element date;
		for (Transaction t : listElements) {
			expense = newElement("expense");
			root.appendChild(expense);

			date = newElement("date");
			date.appendChild(newText(DateTime.
				format(t.transactionDate(), "dd-MM-yyyy")));
			expense.appendChild(date);

			amount = newElement("amount");
			amount.appendChild(newText(t.getAmount().toString()));
			expense.appendChild(amount);

		}

	}

}
