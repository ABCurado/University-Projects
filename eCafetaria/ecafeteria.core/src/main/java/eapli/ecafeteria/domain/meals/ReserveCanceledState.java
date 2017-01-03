package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import javax.persistence.Embeddable;

/**
 *
 * @author ab
 */
@Embeddable
public class ReserveCanceledState implements ReserveState, ValueObject {

	public ReserveCanceledState() {

	}

	@Override
	public boolean isDeliverable() {
		return false;
	}

	@Override
	public boolean isDelivered() {
		return false;
	}

	@Override
	public String toString() {
		return "Canceled";
	}
}
