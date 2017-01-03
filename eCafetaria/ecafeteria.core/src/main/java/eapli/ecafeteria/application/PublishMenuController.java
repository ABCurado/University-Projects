package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;

public class PublishMenuController {

    public Iterable<Menu> listMenus() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);
        return new ListMenuService().nextNotPublishedMenus();
    }

    public void publishMenu(Menu menu) throws DataConcurrencyException {
        menu.publishMenu();
        PersistenceContext.repositories().menus().save(menu);
    }

}
