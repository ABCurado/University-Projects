package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author losa
 */
public class ListOrganicUnitsController implements Controller {

    public Iterable<OrganicUnit> listOrganicUnits() {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        final OrganicUnitRepository repo = PersistenceContext.repositories().organicUnits();
        return repo.all();
    }
}
