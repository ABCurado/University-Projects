package eapli.ecafeteria.domain.meals.Dish;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Rui Freitas
 */
@Embeddable
public class DishName implements ValueObject, Serializable {

    private String dishName;

    public DishName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalStateException("Dish name should not be empty");
        }
        this.dishName = name;
    }

    protected DishName() {
    }

    /**
     * Name of the dish.
     * @return 
     */
    public String dishName() {
        return dishName;
    }

    @Override
    public int hashCode() {
        int hashcode = 21;
        hashcode += this.dishName.hashCode();
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
        if (!(object instanceof DishName)) {
            return false;
        }
        DishName instance = (DishName) object;
        return this.hashCode() == instance.hashCode();
    }

    @Override
    public String toString() {
        return this.dishName;
    }

}
