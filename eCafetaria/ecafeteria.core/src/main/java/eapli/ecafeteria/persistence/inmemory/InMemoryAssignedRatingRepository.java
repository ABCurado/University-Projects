/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.AssignedRating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.AssignedRatingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JoséBarros(1140364)
 */
class InMemoryAssignedRatingRepository extends InMemoryRepository<AssignedRating, Long> implements AssignedRatingRepository {

    long nextID = 1;

    @Override
    protected Long newPK(AssignedRating entity) {
         return ++nextID;
    }

    @Override
    public AssignedRating findMealRatingByUser(Meal meal, CafeteriaUser user) {
        Iterable<AssignedRating> ratings = repository.values();
        for(AssignedRating aRating : ratings){
            if(aRating.user().equals(user) && aRating.meal().equals(meal)){
                return aRating;
            }
        }
        return null;
    }

    @Override
    public List<AssignedRating> findMealRatings(Meal thisMeal) {
        List<AssignedRating> mealRatings = new ArrayList<>();
        Iterable<AssignedRating> ratings = repository.values();
        for(AssignedRating aRating : ratings){
            if(aRating.meal().equals(thisMeal)){
                mealRatings.add(aRating);
            }
        }
        return mealRatings;
    }

}
