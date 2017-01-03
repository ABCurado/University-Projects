/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.domain.meals.*;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class CreateMenuController implements Controller{
    
    private Menu menu;

    public CreateMenuController() {
        // TODO
    }

    public void createMenu(Calendar startDate, Calendar endDate) {
        // NB: Let's use the same date as we are currently only 
        // implementing this UC for single day Menu's
    }
    
    public List<MealType> getMealTypes() {
        // TODO
        return null;
    }
    
    public List<DishType> newMeal(MealType mealType) {
        // TODO
        return null;
    }
    
    public List<Dish> setDishType(DishType dishType) {
        // TODO
        return null;
    }
    
    public boolean selectDish(Dish newDish) {
        // TODO
        return false;
    }
}
