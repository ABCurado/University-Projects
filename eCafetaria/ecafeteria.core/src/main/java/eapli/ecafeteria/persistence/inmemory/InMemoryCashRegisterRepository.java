/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegister;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Map;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class InMemoryCashRegisterRepository extends InMemoryRepository<CashRegister, Long>
        implements CashRegisterRepository {

    long nextID = 1;

    @Override
    protected Long newPK(CashRegister entity) {
        return ++nextID;
    }

    @Override
    public CashRegister findByNumber(String number) {
        for (Map.Entry<Long, CashRegister> entry : this.repository.entrySet()) {
            if (entry.getValue().number().equals(number)) {
                return entry.getValue();
            }
        }
        return null;
    }

}
