/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.framework.persistence.DataIntegrityViolationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Bastos<1140491@isep.ipp.pt>
 */
public class OpenCashRegisterControllerTest {

	OpenCashRegisterController instance;

	public OpenCashRegisterControllerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws DataIntegrityViolationException {
		/*
		CashRegisterRepository cashRegisters = PersistenceContext.repositories().cashRegisters();
		cashRegisters.add(new CashRegister("1"));
		//List<Meal> meals = new ArrayList();

		DishType meat = new DishType("Meat", "Meat Dish");
		Money money = new Money(2, Currency.getInstance("EUR"));

		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, +1);
		Calendar later = Calendar.getInstance();
		later.add(Calendar.DATE, +3);
		MealType mt = new MealType("Lunch");

		PersistenceContext.repositories().mealTypes().add(mt);
		Meal meal1 = new Meal(mt, new Dish(meat, "Arroz de Frango", 200, 3, 6, money), now);
		Meal meal2 = new Meal(mt, new Dish(meat, "Massa com Panado", 200, 6, 15, money), now);

		PersistenceContext.repositories().meals().add(meal1);
		PersistenceContext.repositories().meals().add(meal2);
		 */
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() throws DataIntegrityViolationException {
		//instance = new OpenCashRegisterController("1");
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of mealTypes method, of class OpenCashRegisterController.
	 */
	@Test
	public void testMealType() {
		/*
		MealType expResult = null;
		MealType result = instance.mealType("Dinner");
		assertEquals(expResult, result);
		result = instance.mealType("Lunch");
		assertNotEquals(expResult, result);
		 */
	}

	/**
	 * Test of chooseMeal method, of class OpenCashRegisterController.
	 */
	@Test
	public void testChooseMealAndOpen() {
		/*
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, +1);
		MealType mealType = new MealType("Lunch");
		Shift expResult = null;
		Shift result = instance.chooseMeal(day, mealType);
		assertNotEquals(expResult, result);
		instance.open("1", result);
		CashRegister cR = PersistenceContext.repositories().cashRegisters().
			findByNumber("1");
		assertEquals(cR.state(), opened);
		 */
	}

}
