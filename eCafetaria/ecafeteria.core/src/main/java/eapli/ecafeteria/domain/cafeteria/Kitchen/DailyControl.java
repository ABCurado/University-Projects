package eapli.ecafeteria.domain.cafeteria.Kitchen;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 * Defines the daily control of the kitchen.
 * Its purpose is to allow to easily search and filter the kitchen's executions controls.
 */
@Embeddable
public class DailyControl implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    //TODO if this object is used for a certain Executioncontrol then it must garantee that this date is the same of the date of the meal
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    
    /**
     * Executed meals.
     */
    private int executed = 0;

    protected DailyControl() {
    }

    public DailyControl(Calendar date) {
        if (date == null) {
            throw new IllegalArgumentException("Invalid daily control date was provided. A date cannot be null.");
        }

        this.date = date;
    }
    
    /**
     * Returns the number of meals executed during the associated daily control
     * and meal.
     *
     * @return Number of meals that were executed.
     */
    public int executed() {
        return this.executed;
    }
    
    /**
     * Updates the quantity of the executed meals.
     *
     * @param quantity Quantity of meals that were additionally executed.
     */
    public void addExecutedMeals(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("You can't execute negative quantities of meals.");
        }
        
        this.executed += quantity;
    }
    
    /**
     * Gets the date of this daily control.
     *
     * @return Daily Control date.
     */
    public Calendar date() {
        return this.date;
    }

}
