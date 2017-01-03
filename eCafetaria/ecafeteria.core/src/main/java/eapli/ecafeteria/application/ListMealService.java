package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Martins
 */
public class ListMealService {

    public Iterable<Meal> allMeals() {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        final MealRepository mealRepository = PersistenceContext.
                repositories().meals();
        return mealRepository.all();
    }

    public Iterable<Meal> mealsByDateMealType(Calendar date, MealType mealType,
            Menu menu) {
        final MealRepository mealRepository = PersistenceContext.
                repositories().meals();
        return mealRepository.mealsByDateMealType(date, mealType, menu);
    }

    public Iterable<Meal> dayMeals(Calendar date) {
        final MealRepository mealRepository = PersistenceContext.
                repositories().meals();
        return mealRepository.dayMeals(date);
    }

    public Iterable<Meal> mealsOfMenu(Menu menu) {
        final MealRepository mealRepository = PersistenceContext.
                repositories().meals();
        return mealRepository.mealsOfMenu(menu);
    }

    public Iterable<Meal> mealsCurrentWeekDay(int dayWeek) {
        final MealRepository mealRepository = PersistenceContext.
                repositories().meals();
        return mealRepository.mealsCurrentWeekDay(dayWeek);
    }

    public Iterable<Meal> mealsFromWeeklyMenu() throws Exception {
        MenuRepository menuRepo = PersistenceContext.repositories().menus();

        Calendar initialDate = DateTime.beginningOfWeek(
                DateTime.currentYear(),
                DateTime.currentWeekNumber()
        );

        Calendar finalDate = DateTime.endOfWeek(
                DateTime.currentYear(),
                DateTime.currentWeekNumber()
        );

        Iterable<Menu> menus = menuRepo.
                publishedMenuBetweenDate(initialDate, finalDate);

        if (!menus.iterator().hasNext()) {
            throw new Exception("No menus were found for the current week.");
        }

        MealRepository mealRepo = PersistenceContext.repositories().meals();

        return mealRepo.mealsOfMenu(menus.iterator().next());
    }
}
