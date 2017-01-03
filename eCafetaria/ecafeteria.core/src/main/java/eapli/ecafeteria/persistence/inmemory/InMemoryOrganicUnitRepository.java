/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author arocha
 */
public class InMemoryOrganicUnitRepository extends InMemoryRepository<OrganicUnit, Long> 
        implements OrganicUnitRepository {

    long nextID = 1;

    @Override
    protected Long newPK(OrganicUnit entity) {
        return ++nextID;
    }   
    
    }

