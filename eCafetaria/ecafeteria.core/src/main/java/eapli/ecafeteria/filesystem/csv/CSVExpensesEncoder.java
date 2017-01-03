package eapli.ecafeteria.filesystem.csv;

import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.framework.filesystem.impl.CSVEncoder;
import eapli.util.DateTime;

/**
 * TODO javadoc
 *
 * @author nervousdev
 */
public class CSVExpensesEncoder extends CSVEncoder<Transaction> {

    @Override
    public void writeElements(Iterable<Transaction> listElements) {
        for (Transaction t : listElements) {
            append(DateTime.
                    format(t.transactionDate(), "dd-MM-yyyy"), t.getAmount().
                    toString());
        }
    }
}
