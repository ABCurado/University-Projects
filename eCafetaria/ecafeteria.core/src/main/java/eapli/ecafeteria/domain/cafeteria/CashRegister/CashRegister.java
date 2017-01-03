package eapli.ecafeteria.domain.cafeteria.CashRegister;

import static eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterState.closed;
import static eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterState.opened;
import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Version;

/*
 *
 * TODO javadoc
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
@Entity
public class CashRegister implements Serializable, AggregateRoot<String> {

	@Version
	private Long version;

	/**
	 * Cash Register number
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String number;

	/**
	 * Cash Register State
	 */
	@Enumerated(EnumType.STRING)
	private CashRegisterState state;

	//JPA only
	protected CashRegister() {

	}

	/**
	 * Constructor with the cash register number
	 *
	 * @param number Cash Register number
	 */
	public CashRegister(String number) {
		if (number.isEmpty()) {
			throw new IllegalArgumentException("New CashRegister missing arguments.");
		}
		this.number = number;
		this.state = closed;
	}

	/**
	 * Method that opens the register. return true if it does, false if it's
	 * already opened
	 *
	 * @return true if cash register can be opened,otherwise return false
	 */
	public boolean open() {
		if (state.equals(closed)) {
			this.state = opened;
			return true;
		}
		return false;
	}

	/**
	 * Get cash register state
	 *
	 * @return cash register state
	 */
	public CashRegisterState state() {
		return this.state;
	}

	public boolean close() {
		if (this.state != closed) {
			this.state = closed;
			return true;
		}

		return false;
	}

	/**
	 * Get cash register number
	 *
	 * @return cash register number
	 */
	public String number() {
		return number;
	}

	/**
	 * Checks if two different cash register objects are the same
	 *
	 * @param other
	 * @return true if the two objects are the same ,otherwise return false
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		final CashRegister cr = (CashRegister) other;
		if (!Objects.equals(this.number, cr.number)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + Objects.hashCode(this.number);
		hash = 41 * hash + Objects.hashCode(this.state);
		return hash;
	}

	@Override
	public boolean sameAs(Object other) {
		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		final CashRegister cr = (CashRegister) other;
		if (!Objects.equals(this.number, cr.number)) {
			return false;
		}
		return true;
	}

	@Override
	public String id() {
		return number;
	}

	@Override
	public boolean is(String id) {
		return this.number.equals(id);
	}

}
