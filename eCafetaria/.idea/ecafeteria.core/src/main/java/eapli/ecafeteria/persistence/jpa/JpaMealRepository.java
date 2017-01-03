/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.Calendar;

/**
 *
 * @author Joao Martins
 */
public class JpaMealRepository extends JpaRepository<Meal, Long> implements MealRepository{
    
    
    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public Iterable<Meal> dayMeals(Calendar day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}