/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;

/**
 *
 * @author Joao Martins
 */
class InMemoryMealRepository extends InMemoryRepository<Meal, Long> implements MealRepository {

    long nextID = 1;
    
    @Override
    protected Long newPK(Meal entity) {
        return ++nextID;
    }

    @Override
    public Iterable<Meal> dayMeals(Calendar day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}