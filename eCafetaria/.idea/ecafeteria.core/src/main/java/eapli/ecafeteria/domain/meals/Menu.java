/*
 *
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Menu (Ementa) consists of a list of scheduled meals for a given time span.
 *
 * Eg Menu for 1 day (07-03-2016 -> 07-03-2016):
 *
 * Date Dish DishType Dishes 07-03-2016 Peixe Bacalhau à Brás Almoço 70
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
public class Menu implements Serializable {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Meal> meals;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar publish_date;

    private boolean flag_publicacao;

    public Menu(Calendar startDate, Calendar endDate) {
        final Calendar now = Calendar.getInstance();
        if (null == startDate || null == endDate) {
            throw new IllegalArgumentException("Missing Start/End Date.");
        }
        if (startDate.before(now)) {
            throw new IllegalArgumentException("Start Date is in the past.");
        }
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("End Date is before start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.meals = new ArrayList<>();
    }

    public Menu(Calendar startDate, Calendar endDate, List<Meal> meals) {
        final Calendar now = Calendar.getInstance();
        if (null == startDate || null == endDate) {
            throw new IllegalArgumentException("Missing Start/End Date.");
        }
        if (startDate.before(now)) {
            throw new IllegalArgumentException("Start Date is in the past.");
        }
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("End Date is before start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.meals = meals;
    }

    /**
     * ORM only
     */
    protected Menu() {
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Calendar startDate() {
        return this.startDate;
    }

    public Calendar endDate() {
        return endDate;
    }

    public List<Meal> meals() {
        return meals;
    }
    
    

    public boolean addMeal(Meal newMeal) {
        if (newMeal == null) {
            throw new IllegalArgumentException();
        }
        return this.meals.add(newMeal);
    }

    public List<Meal> meals(Calendar day, MealType mealType) {
        List<Meal> lstMeal = new ArrayList<>();
        for (Meal m : this.meals) {
           if(day.equals(m.date())&& mealType.equals(m.mealType())){
                   lstMeal.add(m);
           }
           
        }
        return lstMeal;
    }

    public boolean isEmpty() {
        return this.meals.isEmpty();
    }
    
    public void daysOfCurrentWeek(){
        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        String[] days = new String[7];
        int startDay = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, startDay );
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        //System.out.println(Arrays.toString(days));
   }
    
    public void defineMenuAsPublished(){
        if(this.flag_publicacao == true){
            throw new IllegalArgumentException("A ementa que pretende publicar, já esta publicada!");
        }else{
            this.flag_publicacao=true;
            this.publish_date = Calendar.getInstance();
        }
    }
    
    public boolean isPublished(){
        return this.flag_publicacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
       
        
        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        Menu menu = (Menu) object;

        if (!Objects.equals(this.startDate, menu.startDate())) {
            return false;
        }

        if (!Objects.equals(this.endDate, menu.endDate())) {
            return false;
        }

        return Objects.equals(this.meals, menu.meals());
    
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.meals.Menu[ id=" + id + " ]";
    }

}
