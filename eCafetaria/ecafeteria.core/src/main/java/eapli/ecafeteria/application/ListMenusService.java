package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Rui Freitas
 */
public class ListMenusService {

    public Iterable<Menu> allMenus() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final MenuRepository menuRepository = PersistenceContext.repositories().
                menus();
        return menuRepository.all();
    }
}
