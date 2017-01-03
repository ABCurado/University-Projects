package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author ruben
 */
public class ListTypeMealsService {

    public Iterable<MealType> allMealTypes() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final MealTypeRepository mealTypeRepository = PersistenceContext.
                repositories().mealTypes();

        return mealTypeRepository.all();

    }
}
