package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import javax.persistence.Embeddable;

/**
 *
 * @author ab
 */
@Embeddable
public class ReserveNotDeliveredState implements ReserveState, ValueObject {

    public ReserveNotDeliveredState() {

    }

    @Override
    public boolean isDeliverable() {
        return true;
    }

    @Override
    public boolean isDelivered() {
        return false;
    }

    @Override
    public String toString() {
        return "Not Delivered";
    }
}
