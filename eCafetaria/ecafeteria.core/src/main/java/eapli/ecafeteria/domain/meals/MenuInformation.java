package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;

/**
 * TODO javadoc
 *
 * @author nervousdev
 */
@Embeddable
public class MenuInformation implements Serializable, ValueObject {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endDate;

    @Enumerated(EnumType.STRING)
    private MenuState menuState;

    public MenuInformation(Calendar startDate, Calendar endDate, MenuState state) {
        if (startDate == null || endDate == null || state == null) {
            throw new IllegalArgumentException();
        }

        this.startDate = startDate;
        this.endDate = endDate;
        this.menuState = state;
    }

    protected MenuInformation() {
    }

    /**
     * Constructor
     *
     * @return start date of menu
     */
    public Calendar startDate() {
        return this.startDate;
    }

    /**
     * Constructor
     *
     * @return end date of menu
     */
    public Calendar endDate() {
        return this.endDate;
    }

    /**
     * Constructor
     *
     * @return menu state
     */
    public MenuState menuState() {
        return this.menuState;
    }

    public void publishMenu() {
        this.menuState = MenuState.published;
    }

    @Override
    public int hashCode() {
        int hashcode = 21;
        hashcode += this.startDate.hashCode();
        hashcode += this.endDate.hashCode();
        hashcode += this.menuState.hashCode();
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
        if (!(object instanceof MenuInformation)) {
            return false;
        }
        MenuInformation instance = (MenuInformation) object;
        return this.hashCode() == instance.hashCode();
    }

}
