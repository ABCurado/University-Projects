/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.meals.Dish.Dish;
import eapli.framework.domain.Money;
import java.util.Currency;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Freitas
 */
public class DishTest {

	public DishTest() {
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

	@Test(expected = IllegalArgumentException.class)
	public void testNameMustNotBeEmpty() {
		System.out.println("must have non-empty name");
		new Dish(new DishType("Carne", "Carne"), "", 10.2, 12.3, 0, new Money(10, Currency.
																			  getInstance(Locale.
																				  getDefault())));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNameMustNotBeNull() {
		System.out.println("must have an name");
		new Dish(new DishType("Carne", "Carne"), null, 10.2, 12.3, 0, new Money(10, Currency.
																				getInstance(Locale.
																					getDefault())));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDishTypeMustNotBeNull() {
		System.out.println("must have an dish type");
		new Dish(null, "Rojões", 10.2, 12.3, 0, new Money(10, Currency.
														  getInstance(Locale.
															  getDefault())));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDishPriceMustNotBeNull() {
		System.out.println("must have dish price");
		new Dish(new DishType("Carne", "Carne"), "Rojões", 10.2, 12.3, 0, null);
	}

}
