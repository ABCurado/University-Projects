package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;

/**
 *
 * @author mcn
 */
public class ActivateDeactivateDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
        return new ListDishTypeService().allDishTypes();
    }

    public void changeDishTypeState(DishType dType) throws DataConcurrencyException {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);
        
        dType.changeDishTypeState();
        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        dishTypeRepository.save(dType);
    }
}
