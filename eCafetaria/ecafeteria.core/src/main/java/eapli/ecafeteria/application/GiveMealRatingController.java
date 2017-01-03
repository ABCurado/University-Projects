
package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.AssignedRating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.AssignedRatingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * Rates a meal
 * 
 * @author JoséBarros(1140364)
 */
public class GiveMealRatingController implements Controller {
                
                  /**
                   * Returns reserved meals of user
                   * 
                   * @param user
                   * @return reserved meals
                   */
	public Iterable<Meal> listMealsByUserReserves(CafeteriaUser user) {
		return PersistenceContext.repositories().reserves().findByUser(user);
	}

                  /**
                   * Returns the assigned rating by user
                   * 
                   * @param meal
                   * @param user
                   * @return  assigned rating by user
                   */
	public AssignedRating getRatingByUserTo(Meal meal, CafeteriaUser user) {
		return PersistenceContext.repositories().ratings().
			findMealRatingByUser(meal, user);
	}

                  /**
                   * Saves on database the assigned rating by user
                   * 
                   * @param assignedRating
                   * @throws DataIntegrityViolationException 
                   */
	public void addAssignedRating(AssignedRating assignedRating) throws DataIntegrityViolationException {
		PersistenceContext.repositories().ratings().add(assignedRating);
	}
                
                  /**
                   * Replaces the old rating
                   * 
                   * @param newRating
                   * @param oldRating
                   * @throws DataIntegrityViolationException
                   * @throws DataConcurrencyException 
                   */
	public void replaceAssignedRating(AssignedRating newRating,
									  AssignedRating oldRating) throws DataIntegrityViolationException, DataConcurrencyException {
		AssignedRatingRepository repo = PersistenceContext.repositories().
			ratings();
		repo.delete(oldRating);
		repo.add(newRating);
	}
}
