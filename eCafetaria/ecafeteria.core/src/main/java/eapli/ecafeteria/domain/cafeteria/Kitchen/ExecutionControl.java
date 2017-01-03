package eapli.ecafeteria.domain.cafeteria.Kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * This class makes the association between a meal and its daily control.
 */
@Entity
public class ExecutionControl extends Observable implements AggregateRoot<Meal>, Serializable {

    @Id
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    private Meal meal;

    @Embedded
    private DailyControl day;

    protected ExecutionControl() {
    }

    public ExecutionControl(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("The meal cannot be null.");
        }
        
        this.meal = meal;
        this.day = new DailyControl(meal.date());
    }

    /**
     * Returns the number of meals executed during the associated daily control
     * and meal.
     *
     * @return Number of meals that were executed.
     */
    public int executed() {
        return this.day.executed();
    }

    /**
     * Returns the day associated with this execution control.
     *
     * @return The day associated with this execution control.
     */
    public Calendar day() {
        return this.day.date();
    }

    /**
     * Updates the quantity of the executed meals.
     *
     * @param quantity Quantity of meals that were additionally executed.
     */
    public void addExecutedMeals(int quantity) {
        this.day.addExecutedMeals(quantity);
        
        this.setChanged();
        this.notifyObservers(KitchenAlertService.alert(this));
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public int hashCode() {
        int hashcode = 21;
        hashcode += this.meal.hashCode();
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
        if (!(object instanceof ExecutionControl)) {
            return false;
        }
        ExecutionControl instance = (ExecutionControl) object;
        return this.hashCode() == instance.hashCode();
    }

    @Override
    public boolean is(Meal id) {
        return this.id().equals(id);
    }

    @Override
    public Meal id() {
        return this.meal;
    }

    @Override
    public String toString() {
        return this.meal.dish().name() + " - " + this.meal.dish().dishType() + " - Executed: " + this.day.executed();
    }
}
