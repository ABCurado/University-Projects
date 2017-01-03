/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Diogo Leite <1140260@isep.ipp.pt>
 */
public class MealTypeTest {

	MealType instance;

	public MealTypeTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		instance = new MealType("Jantar", 16, 16);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of designation method, of class MealType.
	 */
	@Test
	public void testDesignation() {
		String expResult = "Jantar";
		String result = instance.designation();
		assertEquals(expResult, result);
	}

	/**
	 * Test of toString method, of class MealType.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		MealType instance = new MealType("Jantar", 16, 12);
		String expResult = "eapli.ecafeteria.domain.meals.MealType[ id=" + instance.
			designation() + " ]";
		String result = instance.toString();
		assertEquals(expResult, result);

	}

//    /**
//     * Test of isActive method, of class MealType.
//     */
//    @Test
//    public void testIsActive() {
//        System.out.println("isActive");
//        MealType instance = new MealType();
//        boolean expResult = false;
//        boolean result = instance.isActive();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
	@Test(expected = IllegalArgumentException.class)
	public void testDescriptionMustNotBeEmpty() {
		System.out.println("must have non-empty description");
		new MealType("", 16, 16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDescriptionMustNotBeNull() {
		System.out.println("must have a description");
		new MealType(null, 16, 16);
	}

	/**
	 * Test of designation method, of class MealType.
	 */
	@Test
	public void testchangeDesignationTo() {
		System.out.println("attest changeDescriptionTo");
		final MealType instance = new MealType("Almoço", 16, 16);
		final String newDesignation = "new description";
		instance.changeDesignationTo(newDesignation);
		final String expResult = newDesignation;
		final String result = instance.designation();
		assertEquals(expResult, result);
	}

}
