/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.CashRegister;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.Session;
import eapli.ecafeteria.domain.authz.SystemUser;
import static eapli.ecafeteria.domain.cafeteria.CashRegister.ShiftState.closed;
import static eapli.ecafeteria.domain.cafeteria.CashRegister.ShiftState.finished;
import static eapli.ecafeteria.domain.cafeteria.CashRegister.ShiftState.opened;
import eapli.ecafeteria.domain.meals.MealType;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
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
public class ShiftTest {

	Shift instance;

	public ShiftTest() {
		final Set<RoleType> roles = new HashSet<RoleType>();
		roles.add(RoleType.Admin);
		AppSettings.instance().
			setSession(new Session(new SystemUser("user", "user", "user", "user", "user@isep.ipp.pt", roles)));
		instance = new Shift(new MealType("Lunch", 0, 0), new CashRegister("1"));
	}

	@BeforeClass
	public static void setUpClass() {

	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of open method, of class Shift.
	 */
	@Test
	public void testOpen() {
		boolean expResult = true;
		boolean result = instance.open(Calendar.getInstance());
		assertEquals(expResult, result);
		expResult = false;
		result = instance.open(Calendar.getInstance());
		assertEquals(expResult, result);
	}

	/**
	 * Test of close method, of class Shift.
	 */
	@Test
	public void testClose() {
		instance.open(Calendar.getInstance());
		ShiftState expResult = opened;
		ShiftState result = instance.state();
		assertEquals(expResult, result);
		instance.close();
		expResult = finished;
		result = instance.state();
		assertEquals(expResult, result);
	}

	/**
	 * Test of mealType method, of class Shift.
	 */
	@Test
	public void testMealType() {
		MealType expResult = new MealType("Lunch", 0, 0);
		MealType result = instance.mealType();
		assertEquals(expResult, result);
	}

	/**
	 * Test of user method, of class Shift.
	 */
	@Test
	public void testUser() {
		final Set<RoleType> roles = new HashSet<RoleType>();
		roles.add(RoleType.Admin);
		SystemUser expResult = new SystemUser("user", "user", "user", "user", "user@isep.ipp.pt", roles);
		SystemUser result = instance.user();
		assertEquals(expResult, result);
	}

	/**
	 * Test of state method, of class Shift.
	 */
	@Test
	public void testState() {
		ShiftState expResult = closed;
		ShiftState result = instance.state();
		assertEquals(expResult, result);
		instance.open(Calendar.getInstance());
		expResult = opened;
		result = instance.state();
		assertEquals(expResult, result);
		instance.close();
		expResult = finished;
		result = instance.state();
		assertEquals(expResult, result);
	}

	/**
	 * Test of equals method, of class Shift.
	 */
	@Test
	public void testEquals() {
		Shift obj = new Shift(new MealType("Dinner", 0, 1), new CashRegister("2"));
		obj.open(Calendar.getInstance());
		obj.close();
		instance.open(Calendar.getInstance());
		instance.close();
		boolean expResult = false;
//		boolean result = instance.equals(obj);
		//assertEquals(expResult, result);
		obj = instance = new Shift(new MealType("Lunch", 0, 0), new CashRegister("1"));
		expResult = true;
//		result = instance.equals(obj);
		//assertEquals(expResult, result);
	}

}
