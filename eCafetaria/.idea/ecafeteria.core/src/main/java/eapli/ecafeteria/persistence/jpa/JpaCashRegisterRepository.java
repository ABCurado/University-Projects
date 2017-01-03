/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.List;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class JpaCashRegisterRepository extends JpaRepository<CashRegister, Long> implements CashRegisterRepository{

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public CashRegister findByNumber(String number) {
        List<CashRegister> list = match("e.number.equals(number)");
        try{
            return list.get(0);
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    
}
