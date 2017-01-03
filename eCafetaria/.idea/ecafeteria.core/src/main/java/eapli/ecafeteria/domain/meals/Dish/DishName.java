package eapli.ecafeteria.domain.meals.Dish;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Rui Freitas
 */
@Embeddable
public class DishName implements ValueObject, Serializable {

	private String dishName;

	public DishName(String name) {
		if (Strings.isNullOrEmpty(name)) {
			throw new IllegalStateException("Dish name should not be empty");
		}
		this.dishName = name;
	}

	protected DishName() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DishName)) {
			return false;
		}

		final DishName other = (DishName) o;

		return this.dishName.equals(other.dishName);

	}

	public String dishName() {
		return dishName;
	}

	@Override
	public String toString() {
		return this.dishName;
	}

	@Override
	public int hashCode() {
		return this.dishName.hashCode();
	}
}
