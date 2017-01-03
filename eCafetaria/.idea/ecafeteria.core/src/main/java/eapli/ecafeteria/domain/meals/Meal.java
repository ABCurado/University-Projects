/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
public class Meal implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar mealDate;
    
    @ManyToOne(cascade = CascadeType.MERGE) //TODO: make sure MERGE is correct
    private MealType type;
    
    @OneToOne(fetch = FetchType.EAGER) // TODO: Cascade?
    private Dish dish;
    
    
    private int numberOfDishesPlanned;

    /**
     * Needs a Date upon instantiation
     * @param type 
     * @param dish 
     * @param mealDate 
     */
    public Meal(MealType type,Dish dish,Calendar mealDate) {
        if (type == null || dish == null || mealDate == null  )
            throw new IllegalArgumentException();
        this.type = type;
        this.dish = dish;
        this.mealDate = mealDate;
    }

    protected Meal() {
    }
    
    public void setDate(Calendar newDate) {
        final Calendar now = Calendar.getInstance();
        if (newDate == null || newDate.before(now))
            throw new IllegalArgumentException();
        this.mealDate = newDate;
    }
    
    public Calendar date() {
        return this.mealDate;
    }

    public MealType mealType() {
        return type;
    }
    
    public void setDish(Dish newDish) {
        if (newDish == null)
            throw new IllegalArgumentException();
        this.dish = newDish;
    }
    
    public Dish dish() {
        return this.dish;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meal)) {
            return false;
        }
        Meal other = (Meal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.meals.Meal[ id=" + id + " ]";
    }
    
}
