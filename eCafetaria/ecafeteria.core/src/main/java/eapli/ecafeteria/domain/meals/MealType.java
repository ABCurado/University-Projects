package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.AggregateRoot;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * TODO javadoc
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
public class MealType implements Serializable, AggregateRoot<MealTypeDescription> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    @Id
    @GeneratedValue
    private Long id;

    @AttributeOverride(name = "mealTypeName", column = @Column(name = "designation", unique = true))
    private MealTypeDescription designation;

    private int reservationHourLimit;

    private int penaltyHourLimit;

    protected MealType() {
    }

    /**
     * Constructor
     *
     * @param designation
     * @param reservationHour
     * @param penaltyHour
     */
    public MealType(String designation, int reservationHour, int penaltyHour) {
        if (Strings.isNullOrEmpty(designation) || reservationHour < 0 || reservationHour > 24 || penaltyHour < 0 || penaltyHour > 24) {
            throw new IllegalArgumentException();
        }
        this.designation = new MealTypeDescription(designation);
        this.reservationHourLimit = reservationHour;
        this.penaltyHourLimit = penaltyHour;
    }

    /**
     *
     * @return tostring designation
     */
    public String designation() {
        return this.designation.toString();
    }

    /**
     *
     * @return limit hour for reservation
     */
    public int limitForReservation() {
        return this.reservationHourLimit;
    }

    /**
     *
     * @return penalty if pass hour limit
     */
    public int limitForCancelReserve() {
        return this.penaltyHourLimit;
    }

    /**
     *
     * @return tostring for mealtypes
     */
    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.meals.MealType[ id=" + id().toString() + " ]";
    }

    /**
     * Contructor for new designation of mealtypedescription
     *
     * @param newDesignation
     */
    void changeDesignationTo(String newDesignation) {
        if (newDesignation == null || newDesignation.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.designation = new MealTypeDescription(newDesignation);
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MealType)) {
            return false;
        }

        final MealType that = (MealType) other;
        if (this == that) {
            return true;
        }
        return (!this.designation.equals(that.designation));
    }

    /**
     *
     * @param id
     * @return true if id of mealtypedescription it´s equals by designation id
     */
    @Override
    public boolean is(MealTypeDescription id) {
        return this.designation.equals(id);
    }

    /**
     *
     * @return designation of mealtype
     */
    @Override
    public MealTypeDescription id() {
        return this.designation;
    }

    /**
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.designation);
        return hash;
    }

    /**
     * Compare if two objects are equals
     *
     * @param obj
     * @return designation
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MealType other = (MealType) obj;
        return Objects.equals(this.designation, other.designation);
    }

}
