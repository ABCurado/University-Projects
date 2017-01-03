package eapli.ecafeteria.domain.meals.Dish;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.Money;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 *
 *
 * @author Rui Freitas
 */
@Entity
public class Dish implements AggregateRoot<DishName>, Serializable {

    @Id
    private DishName name;

    @ManyToOne
    private DishType type;

    private Money price;

    private NutricionalInformation nutricionalInfo;

    @Version
    private Long version;
    
    protected Dish() {
    }

    public Dish(DishType type, String name, double calories, double amountSalt,
            double amountFat,
            Money price) {
        if (Strings.isNullOrEmpty(name) || price == null || type == null) {
            throw new IllegalArgumentException();
        }

        this.type = type;
        this.name = new DishName(name);
        this.nutricionalInfo = new NutricionalInformation(calories, amountSalt, amountFat);
        this.price = price;
    }

    /**
     * Dish name.
     * @return 
     */
    public String name() {
        return name.dishName();
    }

    /**
     * Dish type.
     * @return 
     */
    public String dishType() {
        return type.id();
    }

    /**
     * Number of calories.
     * @return 
     */
    public double calories() {
        return nutricionalInfo.calories();
    }

    /**
     * Amount of salt.
     * @return 
     */
    public double amountSalt() {
        return nutricionalInfo.amountSalt();
    }

    /**
     * Amount fat.
     * @return 
     */
    public double amountFat() {
        return nutricionalInfo.amountFat();
    }

    /**
     * Price.
     * @return 
     */
    public String priceValue() {
        return price.toString();
    }

    /**
     * Dish value.
     * @return 
     */
    public Money dishValue() {
        return price;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Dish)) {
            return false;
        }

        final Dish that = (Dish) other;
        if (this == that) {
            return true;
        }
        if (!this.name.equals(that.name)) {
            return false;
        }
        if (!this.type.equals(that.type)) {
            return false;
        }
        if (!this.price.equals(that.price)) {
            return false;
        }
        return this.nutricionalInfo.equals(that.nutricionalInfo);

    }

    @Override
    public boolean is(DishName id) {
        return this.id().equals(id);
    }

    @Override
    public DishName id() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hashcode = 21;
        hashcode += this.name.hashCode();
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
        if (!(object instanceof Dish)) {
            return false;
        }
        Dish instance = (Dish) object;
        return this.hashCode() == instance.hashCode();
    }

}
