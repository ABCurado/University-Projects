/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.CashRegister;

import eapli.ecafeteria.domain.meals.Meal;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class Shift {
    
    private List<Meal> meals;
    private Calendar start, end;
    
    
    protected Shift(){
        
    }
    
    public Shift(List<Meal> meals) {
        this.meals = meals;        
        this.start = Calendar.getInstance();
    }
    
    
    
}
