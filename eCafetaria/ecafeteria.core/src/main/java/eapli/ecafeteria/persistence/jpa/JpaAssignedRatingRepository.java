/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.AssignedRating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.AssignedRatingRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JoséBarros(1140364)
 */
public class JpaAssignedRatingRepository extends JpaRepository<AssignedRating, Long> implements AssignedRatingRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public AssignedRating findMealRatingByUser(Meal meal, CafeteriaUser user) {
        final Query q = entityManager().createQuery("select ar from AssignedRating ar where ar.meal=:meal and ar.user=:user", AssignedRating.class);
        q.setParameter("user", user);
        q.setParameter("meal", meal);

        q.setMaxResults(1);
        List<AssignedRating> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<AssignedRating> findMealRatings(Meal thisMeal) {
        final Query q = entityManager().createQuery("select ar from AssignedRating ar where ar.meal=:meal", AssignedRating.class);
        q.setParameter("meal", thisMeal);

        return q.getResultList();
    }

}
