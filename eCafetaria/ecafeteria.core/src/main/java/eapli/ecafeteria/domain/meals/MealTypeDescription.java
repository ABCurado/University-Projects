package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * TODO javadoc
 *
 * @author nervousdev
 */
@Embeddable
public class MealTypeDescription implements ValueObject, Serializable {

    private String mealTypeName;

    public MealTypeDescription(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalStateException("Dish name should not be empty");
        }
        this.mealTypeName = name;
    }

    protected MealTypeDescription() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealTypeDescription)) {
            return false;
        }

        final MealTypeDescription other = (MealTypeDescription) o;

        return this.mealTypeName.equals(other.mealTypeName);

    }

    @Override
    public String toString() {
        return this.mealTypeName;
    }

    @Override
    public int hashCode() {
        return this.mealTypeName.hashCode();
    }

}
