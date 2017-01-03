/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.authz.EmailAddress;
import eapli.ecafeteria.domain.authz.Name;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.RoleSet;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.Money;
import eapli.util.DateTime;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class ReserveTest {

	private Reserve instance;
	private Calendar date;

	public ReserveTest() {
	}

	@BeforeClass
	public static void setUpClass() {

	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {

		SystemUser u = new SystemUser(new Username("user"), new Password("user"),
									  new Name("user", "lastname"), new EmailAddress("user@email.pt"),
									  new RoleSet(), Calendar.getInstance());

		OrganicUnit ou = new OrganicUnit("ISEP", "AE", "Cantina");

		CafeteriaUser user = new CafeteriaUser(u, ou, "1111111");

		date = Calendar.getInstance();
		date.set(2030 + 1900, Calendar.MONTH, Calendar.DAY_OF_MONTH,
				 18, 01);

		Calendar rDate = Calendar.getInstance();
		rDate.set(2030 + 1900, Calendar.MONTH, Calendar.DAY_OF_MONTH,
				  19, 00);
		Calendar b = (Calendar) date.clone();
		b.add(Calendar.DAY_OF_MONTH, -1);

		Calendar e = (Calendar) date.clone();
		e.add(Calendar.DAY_OF_MONTH, +2);

		Menu menu = new Menu(b, e, "Menu Semana 12");
		Money price = Money.euros(5);
		String desc = "meat Dish";
		DishType dishType = new DishType("meat", desc);
		Dish dish = new Dish(dishType, "Lasagne", 342, 30, 53, price);
		Meal meal = new Meal(new MealType("Jantar", 19, 16), dish, rDate, 50, menu
		);

		instance = new Reserve(user, meal);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of user method, of class Reserve.
	 */
	@Test
	public void testUser() {
		String expResult = "user";
		String result = instance.user().getUsername().toString();
		assertEquals(expResult, result);
	}

	/**
	 * Test of meal method, of class Reserve.
	 */
	@Test
	public void testMeal() {
		String expResult = "Lasagne";
		String result = instance.meal().dish().name();
		assertEquals(expResult, result);
	}

//    /**
//     * Test of deliverReserve method, of class Reserve.
//     *
//     */
//    @Test
//    public void deliverReserveSucessfully() {
//        boolean expResult = true;
//        boolean result = instance.deliverReserve();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of deliverReserve method, of class Reserve.
//     *
//     */
//    @Test
//    public void deliverReserveAlreadyDelivered() {
//        instance.deliverReserve();
//        boolean expResult = false;
//        boolean result = instance.deliverReserve();
//        assertEquals(expResult, result);
//
//    }
	/**
	 * Test of cancelReserve, of class Reserve.
	 */
	@Test
	public void cancelReserve() {
		instance.cancelReserve();
	}

	/**
	 * Test of penalty method, of class Reserve.
	 */
	@Test
	public void testPenalty() {
		Calendar now = DateTime.now();

		if (now.get(Calendar.HOUR_OF_DAY) >= instance.meal().mealType().
			limitForCancelReserve()) {
			boolean expResult = true;
			boolean result = instance.penalty();
			assertEquals(expResult, result);
		} else {
			boolean expResult = false;
			boolean result = instance.penalty();
			assertEquals(expResult, result);
		}
	}

}
