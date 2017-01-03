package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.AggregateRoot;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * a dish type, e.g., vegetarian or fish or meat.
 *
 * this class is implemented in a more traditional way than DDD, by using
 * primitive types for the attributes instead of value objects. this means that
 * some semantic is lost and potential code duplication may rise if the same
 * concept is used as an attribute in more than one class. however, the learning
 * curve is smoother when compared to full DDD.
 *
 *
 * Created by MCN on 29/03/2016.
 */
@Entity
public class DishType implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Version
    private Long version;

    @Column(unique = true)
    private String acronym;

    private String description;
    private boolean active;

    protected DishType() {
        // for ORM
    }

    public DishType(String name, String description) {
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(description)) {
            throw new IllegalArgumentException();
        }
        this.acronym = name;
        this.description = description;
        this.active = true;
    }

    public String name() {
        return this.acronym;
    }

    public String description() {
        return this.description;
    }

    /**
     * Return the status of the dish type - active (true), not active (false).
     * @return 
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Change the state of the dish type.
     */
    public void changeDishTypeState() {

        this.active = !this.active;
    }
    
    /**
     * Change description of the dish type.
     * @param newDescription 
     */
    public void changeDescriptionTo(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.description = newDescription;
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    /*
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DishType)) {
			return false;
		}

		final DishType that = (DishType) o;

		return this.acronym.equals(that.acronym);
	}

	@Override
	public int hashCode() {
		int hash = 7;

		return 67 * hash + this.acronym.hashCode();
	}
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof DishType)) {
            return false;
        }

        final DishType that = (DishType) other;
        if (this == that) {
            return true;
        }

        if (!this.acronym.equals(that.acronym)) {
            return false;
        }

        return this.description.equals(that.description);
    }
}
