package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.AggregateRoot;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * This class works as an aggregate root and represents a temporal instantiation
 * of a dish.
 *
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"TYPE_ID", "DISH_NAME", "MENU_DESCRIPTION", "DAY", "MONTH", "YEAR"})})
public class Meal implements Serializable, AggregateRoot<MealInformation> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private static final long serialVersionUID = 1L;

	@Version
	private Long version;

	private MealInformation mealInformation;

	private QuantitySold qtSold;

	private QuantityPlanned qtPlanned;

	/**
	 * Needs a Date upon instantiation
	 *
	 * @param type
	 * @param dish
	 * @param mealDate
	 * @param numberPlanned
	 * @param menu
	 */
	public Meal(MealType type, Dish dish, Calendar mealDate, int numberPlanned,
				Menu menu) {
		if (type == null || dish == null || mealDate == null || menu == null || numberPlanned < 0) {
			throw new IllegalArgumentException("New Meal missing arguments.");
		}
		if (!DateTime.isBetweenDates(menu.startDate(), menu.endDate(), mealDate)) {
			//throw new IllegalArgumentException("Meal date is off of menu period.");
		}
		this.mealInformation = new MealInformation(type, dish, menu, mealDate);
		this.qtPlanned = new QuantityPlanned(numberPlanned);
		this.qtSold = new QuantitySold();
	}

	protected Meal() {
	}

	/**
	 *
	 * @return date of meal
	 */
	public Calendar date() {
		return this.mealInformation.date();
	}

	/**
	 *
	 * @return quantity of meals planned
	 */
	public int numberDishesPlanned() {
		return this.qtPlanned.quantityPlanned();
	}

	/**
	 *
	 * @return number of dishes reserved
	 */
	public int numberDishesReserverd() {
		return this.qtSold.quantityReserved();
	}

	/**
	 *
	 * @return dish
	 */
	public Dish dish() {
		return this.mealInformation.dish();
	}

	/**
	 *
	 * @return mealType
	 */
	public MealType mealType() {
		return this.mealInformation.mealType();
	}

	/**
	 *
	 * @return meal menu
	 */
	public Menu mealMenu() {
		return this.mealInformation.menu();
	}

	/**
	 *
	 * @param quantity
	 */
	public void defineQuantityPlaned(int quantity) {
		this.qtPlanned.changePlaned(quantity);
	}

	/**
	 * verify if exists available meals only if quantity reserved it´s inferior
	 * quantity planned
	 *
	 * @return boolean
	 */
	protected boolean areThereAvailableMeals() {
		return (this.qtSold.quantityReserved() < this.qtPlanned.
			quantityPlanned());
	}

	/**
	 * verify if current date it´s lower in relation of limit date for
	 * reservation
	 *
	 * @return boolean
	 */
	protected boolean isTimeForReserveNotExceed() {
		Calendar now = DateTime.now();

		if (DateTime.isPreviousDate(now, this.date())) {
			return true;
		}

		return DateTime.isSameDate(now, this.date()) && now.
			get(Calendar.HOUR_OF_DAY) <= this.mealInformation.
			mealType().
			limitForReservation();
	}

	private boolean isMealAvailableToReserve() {
		return (areThereAvailableMeals() && isTimeForReserveNotExceed());
	}

	/**
	 * verify if a meal is available to reserve and add a reserve if true
	 *
	 * @return boolean
	 */
	public boolean registerReservation() {
		if (isMealAvailableToReserve()) {
			return this.qtSold.addReserve();
		} else {
			return false;
		}
	}

	/**
	 * verify if a meal is available to reserve and add a reserve if true
	 *
	 * @return boolean
	 */
	public boolean registerLastMinuteMeal() {
		if (areThereAvailableMeals()) {
			return this.qtSold.addReserve();
		} else {
			return false;
		}
	}

	/**
	 *
	 * @return hascode
	 */
	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.mealInformation.hashCode();
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
		if (!(object instanceof Meal)) {
			return false;
		}
		Meal instance = (Meal) object;
		return this.hashCode() == instance.hashCode();
	}

	/**
	 *
	 * @return tostring
	 */
	@Override
	public String toString() {
		return "Data: " + DateTime.format(this.date()) + "; Tipo Prato: " + this.mealInformation.
			dish().dishType() + "; Prato: " + this.mealInformation.dish().name()
			+ "; Tipo Refeição: " + this.mealInformation.mealType().
			designation() + "; Planned: " + this.qtPlanned.quantityPlanned();
	}

	@Override
	public boolean sameAs(Object other) {
		return this.equals(other);
	}

	/**
	 *
	 * @param id
	 * @return boolean
	 */
	@Override
	public boolean is(MealInformation id) {
		return id().equals(id);
	}

	/**
	 *
	 * @return mealInformation
	 */
	@Override
	public MealInformation id() {
		return this.mealInformation;
	}

	public Long getId() {
		return id;
	}

}
