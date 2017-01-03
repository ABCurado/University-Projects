package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * TODO Reserve and LastMinuteSale both represent sales, maybe implement a sale
 * interface or abstact class
 *
 * TODO is this a DDD entity, value object or aggregate root?
 *
 *
 * @author Hicham
 */
@Entity
public class Reserve implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private CafeteriaUser user;

    @ManyToOne
    private Meal meal;

    private ReserveState reserveState;

    protected Reserve() {
    }

    /**
     * Constructor of reserve
     *
     * @param user
     * @param meal
     */
    public Reserve(CafeteriaUser user, Meal meal) {

        if (user == null || meal == null) {
            throw new IllegalArgumentException();
        }

        this.user = user;
        this.meal = meal;
        this.reserveState = new ReserveNotDeliveredState();
    }

    @Override
    public String toString() {
        return DateTime.format(this.meal.date()) + " - " + this.meal.dish().
                name() + " (" + this.reserveState.toString() + ")";
    }

    /**
     * Constructor of user
     *
     * @return user
     */
    public CafeteriaUser user() {
        return user;
    }

    /**
     * Constructor of meal
     *
     * @return meal
     */
    public Meal meal() {
        return meal;
    }

    /**
     * Verify if actual date it´s higher regarding limit for cancel reserve date
     *
     * @return boolean
     */
    public boolean penalty() {
        Calendar now = DateTime.now();

        if (now.before(this.meal.date())) {
            return (now.get(Calendar.HOUR_OF_DAY) >= this.meal.mealType().
                    limitForCancelReserve());
        } else {
            return false;
        }
    }

    /**
     * Verify if reserveState it´s delivered
     *
     * @return boolean
     */
    public boolean deliverReserve(Shift shift) {
        if (!this.reserveState.isDeliverable()) {
            return false;
        } else {
            this.reserveState = new ReserveDeliveredState(shift);
            return true;
        }

    }

    /**
     * Contructor of cancelReserve
     */
    public void cancelReserve() {
        this.reserveState = new ReserveCanceledState();
    }

    /**
     * Verify if the reserve is "delivered".
     * @return 
     */
    public boolean isDelivered() {
        return this.reserveState.isDelivered();
    }
}
