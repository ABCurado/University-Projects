package eapli.ecafeteria.domain.meals.Dish;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.Money;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 *
 * @author Rui Freitas
 */
@Entity
public class Dish implements AggregateRoot<DishName>, Serializable {

	@Id
	private DishName name;

	@ManyToOne(cascade = CascadeType.MERGE)
	private DishType type;

	private Money price;

	private NutricionalInformation nutricionalInfo;

	protected Dish() {
	}

	public Dish(DishType type, String name, double calories, double amountSalt,
				double amountFat,
				Money price) {
		if (Strings.isNullOrEmpty(name) || price == null || type == null) {
			throw new IllegalArgumentException();
		}

		this.type = type;
		this.name = new DishName(name);
		this.nutricionalInfo = new NutricionalInformation(calories, amountSalt, amountFat);
		this.price = price;
	}

	public String name() {
		return name.dishName();
	}

	public String dishType() {
		return type.id();
	}

	public double calories() {
		return nutricionalInfo.calories();
	}

	public double amountSalt() {
		return nutricionalInfo.amountSalt();
	}

	public double amountFat() {
		return nutricionalInfo.amountFat();
	}

	public String priceValue() {
		return price.toString();
	}

	@Override
	public boolean sameAs(Object other) {
		if (!(other instanceof Dish)) {
			return false;
		}

		final Dish that = (Dish) other;
		if (this == that) {
			return true;
		}
		if (!this.name.equals(that.name)) {
			return false;
		}
		if (!this.type.equals(that.type)) {
			return false;
		}
		if (!this.price.equals(that.price)) {
			return false;
		}
		return this.nutricionalInfo.equals(that.nutricionalInfo);

	}

	@Override
	public boolean is(DishName id) {
		return this.id().equals(id);
	}

	@Override
	public DishName id() {
		return this.name;
	}

}
