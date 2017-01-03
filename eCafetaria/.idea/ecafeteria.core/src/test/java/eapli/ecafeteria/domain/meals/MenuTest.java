/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.Money;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MenuTest {

	Meal meal;
	MealType mealType;
	List<Meal> lstMeal;
	Calendar startDate;
	Calendar endDate;
	Menu instance;
	Dish dish;
	DishType type;
	Money money;

	public MenuTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		lstMeal = new ArrayList<>();
		mealType = new MealType("Jantar");
		startDate = Calendar.getInstance();
		startDate.add(Calendar.DAY_OF_MONTH, +1);
		endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, +3);
		type = new DishType("Peixe", "Bacalhau Com Natas");
		money = new Money(12, Currency.getInstance("EUR"));
		dish = new Dish(type, "Peixe", 200.5, 48, 56.8, money);
		meal = new Meal(mealType, dish, startDate);
		instance = new Menu(startDate, endDate, lstMeal);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Ensure start date is not null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStartDateMustNotBeNull() {
		System.out.println("Menu must have not null start date");
		final Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, +1);
		new Menu(null, endDate);
	}

	/**
	 * Ensure end date is not null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateMustNotBeNull() {
		System.out.println("Menu must have not null end date");
		final Calendar startDate = Calendar.getInstance();
		new Menu(startDate, null);
	}

	/**
	 * Ensure end date is not previous to start date
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateIsNotBeforeStartDate() {
		System.out.println("Menu must have correct time span");
		final Calendar startDate = Calendar.getInstance();
		final Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, -1);
		new Menu(startDate, endDate);
	}

	/**
	 * Ensure start date is not in the past
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStartDateIsNotInThePast() {
		System.out.println("Start Date must not be in the past");
		final Calendar startDate = Calendar.getInstance();
		final Calendar endDate = Calendar.getInstance();
		startDate.add(Calendar.DAY_OF_MONTH, -1);
		new Menu(startDate, endDate);
	}

	/**
	 * Test creation of single day Menu
	 */
//    testCreateSingleDayMenu(eapli.ecafeteria.domain.meals.MenuTest): Start Date is in the past.
//    @Test
//    public void testCreateSingleDayMenu() {
//        System.out.println("Create single day menu.");
//        final Calendar startDate = Calendar.getInstance();
//        final Calendar endDate = Calendar.getInstance();
//        Menu instance = new Menu(startDate,endDate);
//        assertTrue(true);
//    }
	/**
	 * Test of addMeal method, of class Menu.
	 */
//   Tests in error:
//      testAddMeal(eapli.ecafeteria.domain.meals.MenuTest): Start Date is in the past.
//    @Test
//    public void testAddMeal() {
//        System.out.println("addMeal");
//        final Calendar startDate = Calendar.getInstance();
//        final Calendar endDate = Calendar.getInstance();
//        endDate.add(Calendar.DAY_OF_MONTH, +1);
//        Meal newMeal = new Meal();
//        Menu instance = new Menu(startDate,endDate);
//        boolean expResult = true;
//        boolean result = instance.addMeal(newMeal);
//        assertEquals(expResult, result);
//    }
	/**
	 * Test of isEmpty method, of class Menu.
	 */
	@Test
	public void testIsNotEmpty() {
		lstMeal.add(meal);
		boolean expResult = false;
		boolean result = lstMeal.isEmpty();
		assertEquals(expResult, result);
	}

	/**
	 * Test of isEmpty method, of class Menu.
	 */
	@Test
	public void testIsEmpty() {
		boolean expResult = true;
		boolean result = lstMeal.isEmpty();
		assertEquals(expResult, result);
	}

	/**
	 * Test of startDate method, of class Menu.
	 */
	@Test
	public void testStartDate() {
		Calendar expResult = startDate;
		Calendar result = instance.startDate();
		assertEquals(expResult, result);
	}

	/**
	 * Test of endDate method, of class Menu.
	 */
	@Test
	public void testEndDate() {
		Calendar expResult = endDate;
		Calendar result = instance.endDate();
		assertEquals(expResult, result);
	}

	/**
	 * Test of meals method, of class Menu.
	 */
	@Test
	public void testMeals() {
		List<Meal> expResult = lstMeal;
		List<Meal> result = instance.meals(startDate, mealType);
		assertEquals(expResult, result);
	}

//    /**
//     * Test of hashCode method, of class Menu.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Menu instance = new Menu();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
	/**
	 * Test of equals method, of class Menu.
	 */
	@Test
	public void testEquals() {
		Object obj = this.instance;
		boolean expResult = true;
		boolean result = this.instance.equals(obj);
		assertEquals(expResult, result);
	}

	/**
	 * Test of equals not method, of class Menu.
	 */
	@Test
	public void testEqualsNot() {
		Object obj = new Object();
		boolean expResult = false;
		boolean result = this.instance.equals(obj);
		assertEquals(expResult, result);
	}

//    /**
//     * Test of toString method, of class Menu.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Menu instance = new Menu();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
