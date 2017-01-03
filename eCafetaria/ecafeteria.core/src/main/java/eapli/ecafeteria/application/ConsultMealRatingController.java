package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.AssignedRating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.RatingType;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;

/**
 * Consults meal rating
 * 
 * @author JoséBarros(1140364)
 */
public class ConsultMealRatingController {
    
    /**
     * Returns all meals in database
     * 
     * @return meals 
     */
    public Iterable<Meal> listMeals() {
        return PersistenceContext.repositories().meals().all();
    }
    
    /**
     * Returns rating votes
     * 
     * @param mealSelected
     * @return rating votes
     */
    public String getMealRating(Meal mealSelected) {
        int bad = 0, notGood = 0, good = 0, veryGood = 0, excelent = 0;
        List<AssignedRating> ratings = PersistenceContext.repositories().ratings().findMealRatings(mealSelected);
        for (AssignedRating aRating : ratings) {
            if (aRating.rating().equals(RatingType.BAD)) {
                bad++;
            }
            if (aRating.rating().equals(RatingType.NOTGOOD)) {
                notGood++;
            }
            if (aRating.rating().equals(RatingType.GOOD)) {
                good++;
            }
            if (aRating.rating().equals(RatingType.VERYGOOD)) {
                veryGood++;
            }
            if (aRating.rating().equals(RatingType.EXCELENT)) {
                excelent++;
            }
        }
        return "Ratings for meal:"
                + "\nBAD = " + bad
                + "\nNOTGOOD = " + notGood
                + "\nGOOD = " + good
                + "\nVERYGOOD = " + veryGood
                + "\nEXCELENT = " + excelent;
    }

}



