package eapli.ecafeteria.domain.meals.Dish;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Rui Freitas
 */
@Embeddable
public class NutricionalInformation implements ValueObject, Serializable {

	private double calories;
	private double amountSalt;
	private double amountFat;

	public NutricionalInformation(double calories, double amountSalt,
								  double amountFat) {

		if (calories < 0.0 || amountSalt < 0.0 || amountFat < 0.0) {
			throw new IllegalArgumentException("Nutricional Information values should be positive!");
		}
		this.calories = calories;
		this.amountSalt = amountSalt;
		this.amountFat = amountFat;
	}

	protected NutricionalInformation() {
	}

	public double calories() {
		return calories;
	}

	public double amountSalt() {
		return amountSalt;
	}

	public double amountFat() {
		return amountFat;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof NutricionalInformation)) {
			return false;
		}

		final NutricionalInformation other = (NutricionalInformation) obj;

		if (Double.doubleToLongBits(this.calories) != Double.
			doubleToLongBits(other.calories)) {
			return false;
		}
		if (Double.doubleToLongBits(this.amountSalt) != Double.
			doubleToLongBits(other.amountSalt)) {
			return false;
		}
		if (Double.doubleToLongBits(this.amountFat) != Double.
			doubleToLongBits(other.amountFat)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.calories) ^ (Double.
			doubleToLongBits(this.calories) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.amountSalt) ^ (Double.
			doubleToLongBits(this.amountSalt) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.amountFat) ^ (Double.
			doubleToLongBits(this.amountFat) >>> 32));
		return hash;
	}

}
