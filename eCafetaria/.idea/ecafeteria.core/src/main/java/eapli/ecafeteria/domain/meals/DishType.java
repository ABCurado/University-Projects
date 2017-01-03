package eapli.ecafeteria.domain.meals;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import eapli.framework.domain.AggregateRoot;
import eapli.util.Strings;

/**
 * a dish type, e.g., vegetarian or fish or meat.
 *
 * this class is implemented in a more traditional way than DDD, by using
 * primitive types for the attributes instead of value objects. this means that
 * some semantic is lost and potential code duplication may rise if the same
 * concept is used as an attribute in more than one class. however, the learning
 * curve is smoother when compared to full DDD.
 *
 * TODO implement equals() and hashCode()
 *
 * Created by MCN on 29/03/2016.
 */
@Entity
public class DishType implements AggregateRoot<String>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

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

    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return this.active;
    }

    public void changeDishTypeState() {

        this.active = !this.active;
    }

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

    // FIXME implement equals() and hashCode()

    @Override
    public boolean sameAs(Object other) {
        // TODO Auto-generated method stub
        return false;
    }
}
