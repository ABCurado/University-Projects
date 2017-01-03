/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.mealbooking.AccountLoad;
import eapli.ecafeteria.persistence.AccountLoadRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author AB-1140280
 */
public class JpaAccountLoadRepository extends JpaRepository<AccountLoad, Long> implements AccountLoadRepository{

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }  
}
