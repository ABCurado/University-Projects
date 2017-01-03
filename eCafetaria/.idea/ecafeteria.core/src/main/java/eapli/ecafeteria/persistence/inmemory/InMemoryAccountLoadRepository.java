/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.mealbooking.AccountLoad;
import eapli.ecafeteria.persistence.AccountLoadRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Barros
 */
class InMemoryAccountLoadRepository extends InMemoryRepository<AccountLoad, Long> implements AccountLoadRepository {
    
    long nextID = 1;

    @Override
    protected Long newPK(AccountLoad entity) {
       return ++nextID;
    }  
}