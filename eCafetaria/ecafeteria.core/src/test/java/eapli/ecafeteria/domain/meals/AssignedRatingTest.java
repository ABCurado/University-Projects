/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.Money;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author scarl
 */
public class AssignedRatingTest {

    Menu menu;
    Dish dish;
    DishType dishType;
    Money money;
    Meal meal;
    MealType mealType;
    Calendar startDate;
    SystemUser user;
    CafeteriaUser cafeteriaUser;
    OrganicUnit organicUnit;

    public AssignedRatingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_YEAR, +1);
        menu = new Menu(startDate, startDate, "Menu Semana 4");
        mealType = new MealType("Jantar", 16, 16);
        dishType = new DishType("Peixe", "Bacalhau Com Natas");
        money = new Money(12, Currency.getInstance("EUR"));
        dish = new Dish(dishType, "Peixe", 200.5, 48, 56.8, money);
        meal = new Meal(mealType, dish, startDate, 0, menu);
        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.User);
        user = new SystemUser("poweruser", "poweruser", "joe", "doe", "joe@email.org", roles);
        organicUnit = new OrganicUnit("ISEP", "Instituto Politecnico do porto", "Good School");
        cafeteriaUser = new CafeteriaUser(user, organicUnit, "1140611");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of user method, of class AssignedRating.
     */
    @Test
    public void testUser() {
        System.out.println("user");
        AssignedRating instance = new AssignedRating(cafeteriaUser, meal, RatingType.GOOD);
        CafeteriaUser expResult = cafeteriaUser;
        CafeteriaUser result = instance.user();
        assertEquals(expResult, result);

    }

    /**
     * Test of meal method, of class AssignedRating.
     */
    @Test
    public void testMeal() {
        System.out.println("meal");
        AssignedRating instance = new AssignedRating(cafeteriaUser, meal, RatingType.GOOD);
        Meal expResult = meal;
        Meal result = instance.meal();
        assertEquals(expResult, result);

    }

    /**
     * Test of rating method, of class AssignedRating.
     */
    @Test
    public void testValidRating() {
        System.out.println("rating");
        AssignedRating instance = new AssignedRating(cafeteriaUser, meal, RatingType.GOOD);
        RatingType expResult = RatingType.GOOD;
        RatingType result = instance.rating();
        assertEquals(expResult, result);
    }

    /**
     * Test of rating method, of class AssignedRating.
     */
    @Test(expected = AssertionError.class)
    public void testNotCoresponDingRating() {
        System.out.println("rating");
        AssignedRating instance = new AssignedRating(cafeteriaUser, meal, RatingType.GOOD);
        RatingType expResult = RatingType.BAD;
        RatingType result = instance.rating();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRating method, of class AssignedRating.
     */
    @Test
    public void testUpdateRating() {
        System.out.println("setRating");

        AssignedRating instance = new AssignedRating(cafeteriaUser, meal, RatingType.GOOD);
        instance.setRating(RatingType.VERYGOOD);
        RatingType expResult = RatingType.VERYGOOD;
        RatingType result = instance.rating();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRating method, of class AssignedRating.
     */
    @Test
    public void testUpdateSameDataRating() {
        System.out.println("setRating");
        AssignedRating instance = new AssignedRating(cafeteriaUser, meal, RatingType.GOOD);
        instance.setRating(RatingType.GOOD);
        RatingType expResult = RatingType.GOOD;
        RatingType result = instance.rating();
        assertEquals(expResult, result);
    }

}
