/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.mealbooking.*;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Money;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ab
 */
public class LastMinuteSaleTest {

    Menu menu;
    Dish dish;
    DishType dishType;
    Money money;
    Meal meal;
    MealType mealType;
    Calendar startDate;

    public LastMinuteSaleTest() {
    }

    @Before
    public void setUp() {
        startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_YEAR, +1);
        menu = new Menu(startDate, startDate, "Menu Semana 6");
        mealType = new MealType("Jantar", 16, 16);
        dishType = new DishType("Peixe", "Bacalhau Com Natas");
        money = new Money(12, Currency.getInstance("EUR"));
        dish = new Dish(dishType, "Peixe", 200.5, 48, 56.8, money);
        meal = new Meal(mealType, dish, startDate, 1, menu);

    }

    @Test
    public void ensureUSerrIsAddedToLastMinuteSell() {
        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);
        final SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", "a@a.en",
                roles);
        final String aMecanographicNumber = new String("999999999");
        final OrganicUnit anOrganicUnit = new OrganicUnit("acronymA", "nameA", "descriptionA");
        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit, aMecanographicNumber);
        LastMinuteSale Sale = new LastMinuteSale(aCafeteriaUser, meal);
        assertEquals(Sale.getUser(), aCafeteriaUser);
    }

    @Test
    public void ensureMealIsAddedToLastMinuteSell() {
        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);
        final SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", "a@a.en",
                roles);
        final String aMecanographicNumber = new String("999999999");
        final OrganicUnit anOrganicUnit = new OrganicUnit("acronymA", "nameA", "descriptionA");
        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit, aMecanographicNumber);
        LastMinuteSale Sale = new LastMinuteSale(aCafeteriaUser, meal);
        assertEquals(Sale.getMeal(), meal);
    }

//    /**
//     * Test of deliverLastMinuteSale method, of class LastMinuteSale.
//     */
//    @Test
//    public void testDeliverLastMinuteSale() {
//        System.out.println("deliverLastMinuteSale");
//        LastMinuteSale instance = new LastMinuteSale();
//        boolean expResult = true;
//        boolean result = instance.deliverLastMinuteSale();
//        assertEquals(expResult, result);
//
//    }
//
//    @Test
//    public void testFailingDeliverLastMinuteSale() {
//        System.out.println("deliverLastMinuteSale");
//        LastMinuteSale instance = new LastMinuteSale();
//        boolean expResult = false;
//        instance.deliverLastMinuteSale();
//        boolean result = instance.deliverLastMinuteSale();
//        assertEquals(expResult, result);
//
//    }

}
