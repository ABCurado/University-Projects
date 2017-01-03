/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.AssignedRating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.Repository;
import java.util.List;

/**
 *
 * @author JoséBarros(1140364)
 */
public interface AssignedRatingRepository extends Repository<AssignedRating, Long> {

    public AssignedRating findMealRatingByUser(Meal meal, CafeteriaUser user);

    public List<AssignedRating> findMealRatings(Meal aThis);
    
}
