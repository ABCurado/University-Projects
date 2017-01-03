package eapli.ecafeteria.domain.cafeteria.CashRegister;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.AggregateRoot;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * TODO javadoc
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
@Entity
public class Shift implements Serializable, AggregateRoot<CashRegister> {

	@Version
	private Long version;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	/**
	 * Start date and end date of the Shift
	 */
	@Temporal(javax.persistence.TemporalType.DATE)
	private Calendar date;

	/**
	 * Meal Type
	 */
	@ManyToOne
	private MealType mealType;

	/**
	 * Cash Register
	 */
	@ManyToOne(cascade = CascadeType.MERGE)
	private CashRegister cashRegister;

	/**
	 * System User
	 */
	@ManyToOne
	private SystemUser user;

	/**
	 * Shift state
	 */
	@Enumerated(EnumType.STRING)
	private ShiftState state;

	//JPA ONLY
	protected Shift() {

	}

	/**
	 * Constructor with the meal type
	 *
	 * @param mealType working meal type of the shift
	 * @param cashRegister
	 */
	public Shift(MealType mealType, CashRegister cashRegister) {
		if (mealType == null || cashRegister == null) {
			throw new IllegalArgumentException("New Shift missing arguments.");
		}
		this.mealType = mealType;
		this.cashRegister = cashRegister;
		this.state = ShiftState.closed;
		this.user = AppSettings.getCurrentUser();
	}

	/**
	 * This method checks if the cash register can be opened
	 *
	 * @return true if cash register can be opened,otherwise return false
	 */
	public boolean open(Calendar date) {

		if (this.cashRegister.open()) {
			this.date = date;
			this.state = ShiftState.opened;
			return true;
		}

		return false;
	}

	/**
	 * This method closes the Shift
	 */
	public void close() {
		this.state = ShiftState.finished;
		this.cashRegister.close();
	}

	/**
	 * Start date of the shift
	 *
	 * @return start date
	 */
	public Calendar date() {
		return this.date;
	}

	/**
	 * Meal Type of the shift
	 *
	 * @return meal type
	 */
	public MealType mealType() {
		return this.mealType;
	}

	/**
	 * User working on this shift
	 *
	 * @return user
	 */
	public SystemUser user() {
		return this.user;
	}

	/**
	 * State of the shift
	 *
	 * @return state
	 */
	public ShiftState state() {
		return this.state;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + Objects.hashCode(this.date);
		hash = 31 * hash + Objects.hashCode(this.mealType);
		return hash;
	}

	/**
	 * Checks if two different shift objects are the same
	 *
	 * @param obj
	 * @return true if the two objects are the same ,otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final Shift other = (Shift) obj;
		if (DateTime.isSameDate(date, other.date)) {
			return false;
		}
		if (!Objects.equals(this.mealType, other.mealType)) {
			return false;
		}
		if (!Objects.equals(this.cashRegister, other.cashRegister)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean sameAs(Object other) {

		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		final Shift obj = (Shift) other;
		if (!(DateTime.isSameDate(date, obj.date))) {
			return false;
		}
		if (!Objects.equals(this.mealType.designation(), obj.mealType.
							designation())) {
			return false;
		}
		if (!Objects.equals(this.cashRegister, obj.cashRegister)) {
			return false;
		}

		return true;
	}

	@Override
	public boolean is(CashRegister id) {
		return id.equals(this.cashRegister);
	}

	@Override
	public CashRegister id() {
		return this.cashRegister;
	}

}
