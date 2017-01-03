/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.mealbooking;

import eapli.framework.domain.Money;

/**
 * 
 * 
 * @author Rafael Rocha <1140329@isep.ipp.pt>
 */
public interface Transaction {

    public Money getAmount();
    
    @Override
    public String toString();
}
