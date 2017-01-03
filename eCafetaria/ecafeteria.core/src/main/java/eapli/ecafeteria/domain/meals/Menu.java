package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.AggregateRoot;
import eapli.util.DateTime;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 * Menu (Ementa) consists of a list of scheduled meals for a given time span.
 *
 * Eg Menu for 1 day (07-03-2016 -> 07-03-2016):
 *
 * Date DishType Dish MealType Dishes 07-03-2016 Peixe Bacalhau à Brás Almoço 70
 *
 * This class is an aggregate root
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
public class Menu implements Serializable, AggregateRoot<String> {

    @Version
    private Long version;
    
    private static final long serialVersionUID = 1L;

    @Id
    private String description;

    private MenuInformation menuPeriod;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar publish_date;

    public Menu(Calendar startDate, Calendar endDate, String description) {
        final Calendar now = DateTime.now();
        if (null == startDate || null == endDate) {
            throw new IllegalArgumentException("Missing Start/End Date.");
        }
        if (DateTime.isPreviousDate(startDate, now)) {
            throw new IllegalArgumentException("Start Date is in the past.");
        }
        if (DateTime.isPreviousDate(endDate, startDate)) {
            throw new IllegalArgumentException("End Date is before start date.");
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Menu must Have a description.");
        }
        this.description = description;
        this.menuPeriod = new MenuInformation(startDate, endDate, MenuState.notPublished);
        this.publish_date = null;
    }

    /**
     * ORM only
     */
    protected Menu() {
    }

    /**
     * Set the start date.
     * @return 
     */
    public Calendar startDate() {
        return this.menuPeriod.startDate();
    }

    /**
     * Set the end date.
     * @return 
     */
    public Calendar endDate() {
        return this.menuPeriod.endDate();
    }

    /**
     * Description of the menu.
     * @return 
     */
    public String description() {
        return this.description + " (" + this.menuPeriod.menuState() + ")";
    }

    public void daysOfCurrentWeek() {
        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        String[] days = new String[7];
        int startDay = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, startDay);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public void publishMenu() {
        if (this.menuPeriod.menuState() == MenuState.published) {
            throw new IllegalArgumentException("A ementa que pretende publicar, já esta publicada!");
        } else {
            //Needs verification to see if there isnt an already published menu
            this.menuPeriod.publishMenu();
            this.publish_date = DateTime.now();
        }
    }

    /**
     * Verify if is published.
     * @return 
     */
    public boolean isPublished() {
        return (this.menuPeriod.menuState() == MenuState.published);
    }

    /**
     * Publish date.
     * @return 
     */
    public Calendar publishDate() {
        return this.publish_date;
    }

    @Override
    public int hashCode() {
        int hashcode = 21;
        hashcode += this.description.hashCode();
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu instance = (Menu) object;
        return this.hashCode() == instance.hashCode();
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.meals.Menu[ id=" + this.description + " ]";
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public boolean is(String id) {
        return id().equals(id);
    }

    @Override
    public String id() {
        return this.description;
    }

}
