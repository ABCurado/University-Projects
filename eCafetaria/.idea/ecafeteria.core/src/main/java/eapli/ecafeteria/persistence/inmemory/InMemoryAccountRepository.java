/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.mealbooking.Account;
import eapli.ecafeteria.persistence.AccountRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Barros
 */
class InMemoryAccountRepository extends InMemoryRepository<Account, Long> implements AccountRepository {
    
    long nextID = 1;

    @Override
    protected Long newPK(Account entity) {
       return ++nextID;
    }  
}
