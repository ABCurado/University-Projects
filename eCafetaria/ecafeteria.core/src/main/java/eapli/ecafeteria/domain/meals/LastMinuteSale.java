package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.MecanographicNumber;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * TODO Reserve and LastMinuteSale both represent sales, maybe implement a sale
 * interface or abstact class
 *
 * @author <AB-1140280>
 */
@Entity
public class LastMinuteSale implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private CafeteriaUser user;

    @ManyToOne
    private Meal meal;

    private LastMinuteMealState lastMinuteMealState;

    @Version
    private Long version;

    protected LastMinuteSale() {
        //For ORM
    }
    
    /**
     * Represents the sale of a non-reserved meal.
     * When a user is not specified, the system uses generic user 9999999 to 
     * agregate the meals.
     * The meal is initially at a "not delivered" state.
     * @param meal Meal being sold
     */
    public LastMinuteSale(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException();
        }

        this.user = PersistenceContext.repositories().cafeteriaUsers().
                findById(new MecanographicNumber("999999999"));
        this.meal = meal;
        this.lastMinuteMealState = new LastMinuteMealNotDeliveredState();
    }
    
    /**
     * Represents the sale of a non-reserved meal.
     * It registers the user and his respective meal.
     * The meal is initially at a "not delivered" state.
     * @param user Registered cafeteria user
     * @param meal Meal being sold
     */
    public LastMinuteSale(CafeteriaUser user, Meal meal) {
        if (user == null || meal == null) {
            throw new IllegalArgumentException();
        }

        this.user = user;
        this.meal = meal;
        this.lastMinuteMealState = new LastMinuteMealNotDeliveredState();
    }

    /**
     * Changes the meal's "not delivered" state to a "delivered" state.
     * @param shift Shift of the current cash register
     * @return True, if the meal is changed to a "delivered" state. 
     *         False, if it is already at "delivered" state.
     */
    public boolean deliverLastMinuteSale(Shift shift) {
        if (this.lastMinuteMealState.isDelivered()) {
            return false;
        } else {
            this.lastMinuteMealState = new LastMinuteMealDeliveredState(shift);
            return true;
        }
    }

    //For unit testing
    public CafeteriaUser getUser() {
        return this.user;
    }

    //For unit testing
    public Meal getMeal() {
        return this.meal;
    }
    
    /**
     * Returns a boolean answer to the question "Has the meal been delivered?"
     * @return True, if the meal has been delivered. False, if not.
     */
    public boolean isDelivered() {
        return this.lastMinuteMealState.isDelivered();
    }
}