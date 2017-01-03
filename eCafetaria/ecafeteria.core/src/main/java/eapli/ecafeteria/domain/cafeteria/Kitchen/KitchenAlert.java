package eapli.ecafeteria.domain.cafeteria.Kitchen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * TODO javadoc
 *
 * TODO what is the purpose ofn this class? why is it a persistent class?
 *
 * @author João Martins
 */
@Entity
public class KitchenAlert {

	@Version
	private Long version;

	@Id
	private String name;

	private Double ratio;

	protected KitchenAlert() {
	}

	/**
	 * Constructor of Kitchen alert
	 *
	 * @param name
	 * @param ratio
	 */
	public KitchenAlert(String name, Double ratio) {
		this.name = name;
		this.ratio = ratio;
	}

	/**
	 *
	 * @param executionControl
	 * @return
	 */
	public boolean alert(ExecutionControl executionControl) {
		int executed = executionControl.executed();
		int reserved = executionControl.id().numberDishesReserverd();
		return executed > reserved * this.ratio;
	}

	/**
	 *
	 * @return name
	 */
	public String name() {
		return name;
	}

	/**
	 *
	 * @return ratio
	 */
	public Double ratio() {
		return ratio;
	}

	/**
	 *
	 * @param ratio
	 */
	public void defineRatio(Double ratio) {
		this.ratio = ratio;
	}

	/**
	 *
	 * @return toString
	 */
	@Override
	public String toString() {
		return "Name: " + this.name + " - Percentage: " + this.ratio * 100;
	}
}
