/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.CashRegister.Shift;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterController implements Controller{

    public OpenCashRegisterController(String number) {
        CashRegister cashRegister = PersistenceContext.repositories().cashRegisters().findByNumber(number);
        if (cashRegister == null) {
            cashRegister = new CashRegister(number);
            PersistenceContext.repositories().cashRegisters().save(cashRegister);
        }
    }

    public Iterable<MealType> mealTypes() {

        final MealTypeRepository mealRepo = PersistenceContext.repositories().mealTypes();

        return mealRepo.all();
    }

    public Shift chooseMeal(Calendar day, MealType mealType) {

        MealRepository mealRepo = PersistenceContext.repositories().meals();
        List<Meal> meals = new ArrayList<>();
        mealRepo.dayMeals(day).iterator().forEachRemaining(meals::add);
        List<Meal> remove = new ArrayList<>();
        
        for(Meal m : meals){
            if(!m.mealType().equals(mealType)) remove.add(m);
        }
        
        meals.removeAll(remove);
        
        if (meals.isEmpty()) {
            return null;
        }

        Shift shift = new Shift(meals);

        return shift;
    }

    public boolean open(String number, Shift shift) {
        CashRegister cashRegister = PersistenceContext.repositories().cashRegisters().findByNumber(number);
        return cashRegister.open(shift);
    }

}
