/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.mealbooking.Account;
import eapli.ecafeteria.persistence.AccountRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author Barros
 */
public class JpaAccountRepository extends JpaRepository<Account, Long> implements AccountRepository{

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }  
}
