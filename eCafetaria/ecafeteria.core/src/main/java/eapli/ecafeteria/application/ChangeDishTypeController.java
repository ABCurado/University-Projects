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
 * @author Nuno
 */
public class ChangeDishTypeController implements Controller {

	public DishType changeDishType(DishType theDishType, String newDescription) throws DataConcurrencyException {
		ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

		if (theDishType == null) {
			throw new IllegalStateException();
		}

		theDishType.changeDescriptionTo(newDescription);

		final DishTypeRepository repo = PersistenceContext.repositories().
			dishTypes();

		return repo.save(theDishType);
	}

	/**
	 * in the context of this use case only active dish types are meaningful.
	 *
	 * @return
	 */
	public Iterable<DishType> listDishTypes() {
		return new ListDishTypeService().activeDishTypes();
	}
}
