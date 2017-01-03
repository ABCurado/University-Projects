/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.CashRegister;

import static eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterState.closed;
import static eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterState.opened;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */

@Entity
public class CashRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String number;    
    
    private CashRegisterState state;
    
    @OneToMany
    private Shift shift;
    
    //JPA only
    protected CashRegister(){
        
    }

    public CashRegister(String number) {
        this.number = number;
        this.state = closed;
    }
    
    public boolean open(Shift shift) {
        if(state.equals(closed)){
            this.shift = shift;
            this.state = opened;
            return true;
        }
        return false;
    }
    
    public CashRegisterState state(){
        return this.state;
    }
    
    public String number(){
        return number;
    }
    
}