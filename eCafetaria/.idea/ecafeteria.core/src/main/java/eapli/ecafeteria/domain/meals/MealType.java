/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
public class MealType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean active;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate, endDate;
    
    private String designation;

    protected MealType() {
    }

    

    public MealType(String designation) {
        this.designation = designation;
    }

    public MealType(Calendar startDate, Calendar endDate, String designation) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.designation = designation;
    }

    public Calendar startDate() {
        return startDate;
    }

    public Calendar endDate() {
        return endDate;
    }
    
    public String designation() {
        return designation;
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
        if (!(object instanceof MealType)) {
            return false;
        }
        MealType other = (MealType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.
                equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.meals.MealType[ id=" + id + " ]";
    }

    public boolean isActive() {
        return this.active;
    }

}
