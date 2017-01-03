/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.CashRegister;

import static eapli.ecafeteria.domain.cafeteria.CashRegister.CashRegisterState.closed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import java.util.Calendar;
import java.util.List;
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
public class CashRegisterTest {

	CashRegister instance;
	CashRegisterState state;
	Shift shift;
	Menu menu;
	List<Meal> lstMeals;
	MealType mealType;
	Calendar startDate;
	Calendar endDate;

	public CashRegisterTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {

		instance = new CashRegister("1");
		startDate = Calendar.getInstance();
		startDate.add(Calendar.DAY_OF_MONTH, +1);
		endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, +3);
		menu = new Menu(startDate, endDate, "Menu Semana 2");
		//shift = new Shift(menu);

	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of state method, of class CashRegister.
	 */
	@Test
	public void testState() {
		CashRegisterState expResult = closed;
		CashRegisterState result = instance.state();
		assertEquals(expResult, result);
	}

	/**
	 * Test of open method, of class CashRegister.
	 */
	@Test
	public void testOpen() {
		boolean result = instance.open();
		boolean expResult = true;
		assertEquals(expResult, result);
		result = instance.open();
		expResult = false;
		assertEquals(expResult, result);
	}

	/**
	 * Test of close method, of class CashRegister.
	 */
	@Test
	public void testClose() {
		CashRegister cashRegister = new CashRegister("1");
		cashRegister.open();
		boolean result = cashRegister.close();
		boolean expResult = true;
		assertEquals(expResult, result);
		result = cashRegister.close();
		expResult = false;
		assertEquals(expResult, result);

	}

	/**
	 * Test of number method, of class CashRegister.
	 */
	@Test
	public void testNumber() {
		String expResult = "1";
		String result = instance.number();
		assertEquals(expResult, result);
	}

	/**
	 * Test of equals method, of class CashRegister.
	 */
	@Test
	public void testEquals() {
		CashRegister obj = new CashRegister("1");
		obj.open();
		obj.close();
		instance.open();
		instance.close();
		boolean expResult = true;
		boolean result = instance.equals(obj);
		assertEquals(expResult, result);
		CashRegister obj2 = new CashRegister("2");
		obj2.open();
		obj2.close();
		expResult = false;
		result = instance.equals(obj2);
		assertEquals(expResult, result);

	}

}
