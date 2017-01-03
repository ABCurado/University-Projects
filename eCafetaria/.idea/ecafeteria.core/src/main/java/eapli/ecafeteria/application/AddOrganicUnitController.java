/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author arocha
 */
public class AddOrganicUnitController implements Controller {
    public OrganicUnit addOrganicUnit(String acronym, String name, String description)
            throws DataIntegrityViolationException {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        final OrganicUnit newOrganicUnit = new OrganicUnit(acronym, name, description);
        final OrganicUnitRepository organicUnitRepository = PersistenceContext.repositories().organicUnits();
        // TODO error checking if the acronym is already in the persistence
        // store
        organicUnitRepository.add(newOrganicUnit);
        return newOrganicUnit;
    }
}