package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Eduardo
 */
public class ListMenuService {

    public Iterable<Menu> allMenus() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);
        final MenuRepository menuRepository = PersistenceContext.repositories().
                menus();
        return menuRepository.all();
    }

    public Iterable<Menu> nextNotPublishedMenus() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);
        final MenuRepository menuRepository = PersistenceContext.repositories().
                menus();
        return menuRepository.menusCanBePublished();
    }

    public Iterable<Menu> betweenDate(Calendar date) {
        final MenuRepository menuRepository = PersistenceContext.repositories().
                menus();
        return menuRepository.publishedMenuBetweenDates(date);
    }

    public Iterable<Menu> listUnPublishedMenus() {
        final MenuRepository repo = PersistenceContext.repositories().menus();
        return repo.listUnPublishedMenus();
    }

    //(StartA <= EndB) and (EndA >= StartB)
    public boolean isAnyMenuPublishedInPeriod(Calendar startDate,
            Calendar endDate) {
        final MenuRepository repo = PersistenceContext.repositories().menus();
        Iterable<Menu> listMenu = repo.nextPublishedMenus();

        for (Menu m : listMenu) {
            if ((DateTime.isPreviousDate(m.startDate(), endDate) || DateTime.
                    isSameDate(m.startDate(), endDate)) && (DateTime.isFutureDate(m.
                            endDate(), startDate) || DateTime.
                    isSameDate(m.endDate(), startDate))) {
                return true;
            }
        }

        return false;
    }

}
