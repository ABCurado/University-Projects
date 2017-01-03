package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.ValueObject;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author nervousdev
 */
@Embeddable
public class MealInformation implements Serializable, ValueObject {

	@ManyToOne(cascade = CascadeType.MERGE)
	private MealType type;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Dish dish;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Menu menu;

	private int day;
	private int month;
	private int year;

	public MealInformation(MealType type, Dish dish, Menu menu, Calendar date) {
		if (type == null || dish == null || date == null) {
			throw new IllegalArgumentException();
		}
		this.type = type;
		this.dish = dish;
		this.menu = menu;
		this.day = DateTime.day(date);
		this.month = DateTime.month(date);
		this.year = DateTime.year(date);
	}

	protected MealInformation() {
	}

	/**
	 *
	 * @return mealType
	 */
	public MealType mealType() {
		return this.type;
	}

	/**
	 *
	 * @return dish
	 */
	public Dish dish() {
		return this.dish;
	}

	/**
	 *
	 * @return menu
	 */
	public Menu menu() {
		return menu;
	}

	/**
	 *
	 * @return day of meal
	 */
	public int day() {
		return day;
	}

	/**
	 *
	 * @return month of meal
	 */
	public int month() {
		return month;
	}

	/**
	 *
	 * @return year of meal
	 */
	public int year() {
		return year;
	}

	/**
	 *
	 * @return date
	 */
	public Calendar date() {
		return DateTime.newCalendar(this.year(), this.month(), this.day());
	}

	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.type.hashCode();
		hashcode += this.dish.hashCode();
		hashcode += this.day;
		hashcode += this.month;
		hashcode += this.year;
		return hashcode;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		if (!(object instanceof MealInformation)) {
			return false;
		}
		MealInformation instance = (MealInformation) object;
		return this.hashCode() == instance.hashCode();
	}

}
