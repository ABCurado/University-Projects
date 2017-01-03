package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Assigned rating by user for one meal
 *
 * @author JoséBarros(1140364)
 */
@Entity
public class AssignedRating implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CafeteriaUser user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Meal meal;

    @Enumerated(EnumType.STRING)
    private RatingType ratingType;

    protected AssignedRating() {

    }
    
    /**
     * Creates assigned rating of user for one meal
     * 
     * @param user
     * @param meal
     * @param rating 
     */
    public AssignedRating(CafeteriaUser user, Meal meal, RatingType rating) {

        if (user == null || meal == null || rating == null) {
            throw new IllegalArgumentException();
        }

        this.user = user;
        this.meal = meal;
        this.ratingType = rating;
    }
    
    /**
     * 
     * @return user 
     */
    public CafeteriaUser user() {
        return user;
    }
    
    /**
     * 
     * @return meal
     */
    public Meal meal() {
        return meal;
    }
    
    /**
     * Updates rating of assigned rating
     * 
     * @param rating 
     */
    public void setRating(RatingType rating) {
        if (!this.ratingType.equals(rating)) {
            this.ratingType = rating;
        }
    }
    
    /**
     * 
     * @return rating
     */
    public RatingType rating() {
        return ratingType;
    }

    @Override
    public String toString() {
        return "AssignedRating:\n"
                + "user - " + user
                + "\nmeal - " + meal
                + "\nratingType - " + ratingType;
    }
}
