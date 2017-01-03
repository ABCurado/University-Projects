package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.cafeteria.CashRegister.ShiftState;
import eapli.framework.domain.ValueObject;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

/**
 *
 * @author ab
 */
@Embeddable
public class ReserveDeliveredState implements ReserveState, ValueObject {

    @OneToOne
    Shift shift;

    public ReserveDeliveredState(Shift shift) {
        if (shift.state() != ShiftState.opened) {
            throw new IllegalStateException("Shift must be open to deliver meal");
        }
        this.shift = shift;
    }

    @Override
    public boolean isDeliverable() {
        return false;
    }

    public Shift getShift() {
        return this.shift;
    }

    @Override
    public boolean isDelivered() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReserveDeliveredState other = (ReserveDeliveredState) obj;
        if (!Objects.equals(this.shift, other.shift)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Delivered";
    }

}
