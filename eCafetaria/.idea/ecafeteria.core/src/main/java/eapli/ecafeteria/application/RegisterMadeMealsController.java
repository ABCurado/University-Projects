
package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.jpa.JpaRepositoryFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yur
 */
public class RegisterMadeMealsController {
    
    private List<Meal> mealsList;
    private Calendar date;
    
    public Map<Integer,String> getPlan(Calendar date) {
        HashMap<Integer,String> mealMap = new HashMap();
        mealsList = new ArrayList<>();
        this.date = date;
        
        JpaRepositoryFactory factory = new JpaRepositoryFactory();
        MealRepository mealRep = factory.meals();
        Iterator<Meal> dayMeals = mealRep.dayMeals(date).iterator();
        
        int i = 0;
        while(dayMeals.hasNext()) {
            mealsList.add(dayMeals.next());
            mealMap.put(i, mealsList.get(i).toString());
            i++;
        }
        return mealMap;
    }
    
    public void setDailyControl() {
        
    }
    
    public void setMadeMeals(int id, int quantity) {
        
    }
    
    public void apply() {
        
    }
}
