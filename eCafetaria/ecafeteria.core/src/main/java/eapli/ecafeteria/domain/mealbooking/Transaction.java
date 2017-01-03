package eapli.ecafeteria.domain.mealbooking;

import static eapli.ecafeteria.domain.mealbooking.TransactionType.DEBT;
import static eapli.ecafeteria.domain.mealbooking.TransactionType.LOAD;
import eapli.framework.domain.Money;
import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;
import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;

/**
 * This class represents the amount of money added to or removed from an
 * account.
 *
 * @author Rafael Rocha <1140329>
 */
@Embeddable
public class Transaction implements ValueObject {

    private Money money;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar transactionDate;

    public Transaction(Money money, TransactionType type) {
        //FIXME validate params

        this.type = type;

        if (money.amount() < 0) {
            throw new IllegalArgumentException();
        }
        this.money = money;

        this.transactionDate = DateTime.now();
    }

    protected Transaction() {
        // for ORM
    }

    public TransactionType getType() {
        return this.type;
    }

    public Money getAmount() {
        return this.money;
    }

    public Calendar transactionDate() {
        return this.transactionDate;
    }

    @Override
    public String toString() {
        String transaction = this.transactionDate.toString();

        if (this.type == LOAD) {
            transaction += ": added " + money.toString() + " to the account;";
        } else if (this.type == DEBT) {
            transaction += ": removed " + money.toString() + " from the account;";
        }

        return transaction;
    }
}
