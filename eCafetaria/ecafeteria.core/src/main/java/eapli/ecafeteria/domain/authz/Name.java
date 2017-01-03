package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Name implements ValueObject, Serializable {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;

	/**
	 * Constructor
	 *
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName) {
		if (Strings.isNullOrEmpty(firstName) || Strings.isNullOrEmpty(lastName)) {
			throw new IllegalStateException("first name and last name should neither be null nor empty");
		}
		// FIXME validate other invariants, e.g., regular expression
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected Name() {
	}

	/**
	 *
	 * @return first name
	 */
	public String firstName() {
		return this.firstName;
	}

	/**
	 *
	 * @return last name
	 */
	public String lastName() {
		return this.lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Name)) {
			return false;
		}

		final Name name = (Name) o;

		if (!this.firstName.equals(name.firstName)) {
			return false;
		}
		return this.lastName.equals(name.lastName);

	}

	@Override
	public int hashCode() {
		int result = this.firstName.hashCode();
		result = 31 * result + this.lastName.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
}
