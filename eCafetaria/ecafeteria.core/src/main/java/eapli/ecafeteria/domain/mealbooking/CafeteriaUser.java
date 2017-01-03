package eapli.ecafeteria.domain.mealbooking;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.Reserve;
import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.Money;
import eapli.util.Strings;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * An Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. An Cafeteria User contains an User in it
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.MERGE)
	private SystemUser systemUser;

	@ManyToOne(cascade = CascadeType.MERGE)
	private OrganicUnit organicUnit;

	@Id
	private MecanographicNumber mecanographicNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private Account account;
        
        @Version
        private Long version;
        
	public CafeteriaUser(SystemUser user, OrganicUnit organicUnit,
						 String mecanographicNumber) {
		if (mecanographicNumber == null || user == null || organicUnit == null
			|| Strings.isNullOrEmpty(mecanographicNumber)) {
			throw new IllegalStateException();
		}
		this.systemUser = user;
		this.organicUnit = organicUnit;
		this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
		this.account = new Account("EUR");

	}

	public CafeteriaUser(SystemUser user, OrganicUnit organicUnit,
						 MecanographicNumber mecanographicNumber) {
		if (mecanographicNumber == null || user == null || organicUnit == null
			|| mecanographicNumber == null) {
			throw new IllegalStateException();
		}
		this.systemUser = user;
		this.organicUnit = organicUnit;
		this.mecanographicNumber = mecanographicNumber;
		this.account = new Account("EUR");

	}

	protected CafeteriaUser() {
		// for ORM only
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CafeteriaUser)) {
			return false;
		}

		final CafeteriaUser other = (CafeteriaUser) o;
		if (!this.mecanographicNumber.equals(other.mecanographicNumber)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return this.mecanographicNumber.hashCode();
	}

	@Override
	public boolean sameAs(Object other) {
		if (!(other instanceof CafeteriaUser)) {
			return false;
		}

		final CafeteriaUser that = (CafeteriaUser) other;
		if (this == that) {
			return true;
		}
		if (!this.mecanographicNumber.equals(that.mecanographicNumber)) {
			return false;
		}

		if (!this.systemUser.equals(that.systemUser)) {
			return false;
		}

		if (!this.organicUnit.equals(that.organicUnit)) {
			return false;
		}

		return true;
	}

	@Override
	public boolean is(MecanographicNumber id) {
		return id().equals(id);
	}

	public Username getUsername() {
		return systemUser.username();
	}

	public MecanographicNumber mecanographicNumber() {
		return this.mecanographicNumber;
	}

	@Override
	public MecanographicNumber id() {
		return this.mecanographicNumber;
	}

	public OrganicUnit organicUnit() {
		return this.organicUnit;
	}

	public boolean hasSufficientBalance(Money expenseValue) {
		return this.account.hasSufficientBalance(expenseValue);
	}

	public boolean registerExpense(BigDecimal value) {
		return this.account.addAccountDebt(value);
	}
        
        /**
         * Loads the Cafeteria User's account balance.
         * @param value Ammount to be added to the user's account.
         * @return True, if the account's balance is successfully loaded. False, if not.
         */
	public boolean registerLoad(BigDecimal value) {
		return this.account.addAccountLoad(value);
	}

	public Money accountBalance() {
		return this.account.getBalance();
	}

	public void cancelReserve(Reserve reserve) {
		this.account.cancelReserve(reserve);
	}

	public Iterable<Transaction> allTransactions() {
		return this.account.allTransactions();
	}

	public boolean hasTransactions() {
		return (this.account.transactionsNumber() != 0);
	}
}
