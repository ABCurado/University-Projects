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
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * @author Carlos Mateus
 */
public class ConsultReservesTest {

	Menu menu;
	Money money;
	DishType dishType;
	Dish dish;
	Meal meal;
	MealType mealType;
	Calendar date;
	Set<RoleType> role;
	CafeteriaUser user;

	public ConsultReservesTest() {

	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		money = new Money(14, Currency.getInstance("EUR"));
		dishType = new DishType("Carne", "Salsichas a salsicheiro");
		date = DateTime.newCalendar(2017, 1, 1);
		mealType = new MealType("Jantar", 16, 16);
		dish = new Dish(dishType, "Carne", 200.5, 48, 60, money);
		menu = new Menu(DateTime.now(), date, "Menu Semana 5");
		meal = new Meal(mealType, dish, date, 1, menu);
		role = new HashSet<RoleType>();
		role.add(RoleType.User);
		user = new CafeteriaUser(new SystemUser("User1", "1234", "Manel", "Pereira", "manel@gmail.com", role), new OrganicUnit("ISEP", "Instituto", "So engenheiros"), "11111111");
	}

	@After
	public void tearDown() {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReserveMustNotBeEmpty() {
		System.out.println("Must have non-empty reserves");
		new Reserve(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSystemUserMustNotBeNull() {
		System.out.println("Must have a SystemUser.");

		new Reserve(null, meal);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testMealtNotBeNull() {
		System.out.println("Must have a Meal");
		Meal m = new Meal();
		new Reserve(user, null);
		assertNotEquals(m, null);
	}
}
