package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author mcn
 */
public class RegisterDishTypeController implements Controller {

    public DishType registerDishType(String acronym, String description) throws DataIntegrityViolationException {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final DishType newDishType = new DishType(acronym, description);
        final DishTypeRepository repo = PersistenceContext.repositories().
                dishTypes();
		// FIXME error checking if the newDishType is already in the persistence
        // store
        repo.add(newDishType);
        return newDishType;
    }

}
