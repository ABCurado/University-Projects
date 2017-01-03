package eapli.ecafeteria.domain.mealbooking;

import static eapli.ecafeteria.domain.mealbooking.TransactionType.DEBT;
import static eapli.ecafeteria.domain.mealbooking.TransactionType.LOAD;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.domain.Money;
import eapli.util.Strings;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * This class represents the user account that stores his balance and
 * transactions
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Entity
public class Account implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Money balance;

        /**
         * List of Transactions.
         */
	private List<Transaction> transactionList;

	protected Account() {
		// for ORM
		this.balance = new Money(0, Currency.getInstance(Locale.getDefault()));
		transactionList = new ArrayList<>();
	}

	public Account(String currency) {
		if (Strings.isNullOrEmpty(currency)) {
			throw new IllegalStateException("Currency should neither be null nor empty");
		}
		this.balance = new Money(0, Currency.getInstance(currency));
		transactionList = new ArrayList<>();
	}

        /**
         * Add amount to the account.
         * @param amount
         * @return 
         */
	public boolean addAccountLoad(BigDecimal amount) {
		Money money = new Money(amount.doubleValue(), balance.currency());
		Transaction load = new Transaction(money, LOAD);

		if (!transactionList.add(load)) {
			return false;
		}
		updateBalance(load);
		return true;
	}

        /**
         * Remove amount from the account.
         * @param amount
         * @return 
         */
	public boolean addAccountDebt(BigDecimal amount) {
		Money money = new Money(amount.doubleValue(), balance.currency());
		if (this.balance.lessThan(money)) {
			return false;
		}
		Transaction debt = new Transaction(money, DEBT);

		if (!transactionList.add(debt)) {
			return false;
		}
		updateBalance(debt);
		return true;
	}

	/**
	 * Updates the current balance of the account
	 *
	 * @param ammount to be added to the balance
	 * @return operation result
	 */
	private boolean updateBalance(Transaction transaction) {
		if (transaction.getType() == LOAD) {
			balance = balance.add(transaction.getAmount());
			return true;
		}
		if (transaction.getType() == DEBT) {
			balance = balance.subtract(transaction.getAmount());
			return true;
		}
		return false;
	}

	/**
	 * Canceles the reserve.
	 *
	 * @param reserve reserve to cancel
	 */
	public void cancelReserve(Reserve reserve) {

		BigDecimal value = BigDecimal.valueOf(reserve.meal().dish().dishValue().
			amount());

		if (!reserve.penalty()) {
			addAccountLoad(value.divide(new BigDecimal(2)));
		} else {
			addAccountLoad(value);
		}
	}

        /**
         * Verify if the account has enough money.
         * @param expenseValue
         * @return 
         */
	protected boolean hasSufficientBalance(Money expenseValue) {
		return (this.balance.greaterThanOrEqual(expenseValue));
	}

        /**
         * Number of transactions of the account.
         * @return 
         */
	public int transactionsNumber() {
		return transactionList.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Account)) {
			return false;
		}

		final Account that = (Account) o;

		return this.balance.equals(that.balance);

	}

	@Override
	public int hashCode() {
		return this.balance.hashCode();
	}

	@Override
	public String toString() {
		return "Balance:" + this.balance;
	}

	/**
	 * FOR ORM
	 *
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * FOR ORM
	 *
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Money getBalance() {
		return balance;
	}
	//For unit testing

	public void setBalance(Money amount) {
		this.balance = amount;
	}

        /**
         * Return the list of the transactions.
         * @return 
         */
	public Iterable<Transaction> allTransactions() {
		return this.transactionList;
	}
}
