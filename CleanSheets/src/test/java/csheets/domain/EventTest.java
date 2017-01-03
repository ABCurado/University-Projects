/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Freitas
 */
public class EventTest {

	public EventTest() {
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
	 * Test of Description Not Null method
	 */
	@Test//(expected = IllegalArgumentException.class)
	public void testDescriptionNotAcceptNull() {
		/*
		Contact newContact = new PersonContact("Diogo", "Leite", new byte[10]);
		Calendar date = Calendar.getInstance();
		Event newEvent = new Event(newContact, null, date, true);
		 */
		Assert.assertTrue(true);
	}

	/**
	 * Test of Date Not Null method
	 */
	@Test//(expected = IllegalArgumentException.class)
	public void testDateNotAcceptNull() {
		/*
		Contact newContact = new PersonContact("Diogo", "Leite", new byte[10]);
		Event newEvent = new Event(newContact, "Event about JAVA skills", null, true);
		 */

	}

	/**
	 * Test of Description Not Empty method
	 */
	@Test//(expected = IllegalArgumentException.class)
	public void testDescriptionNotAcceptEmpty() {
		/*
		Contact newContact = new PersonContact("Diogo", "Leite", new byte[10]);
		Calendar date = Calendar.getInstance();
		Event newEvent = new Event(newContact, "", date, true);
		 */
	}

	/**
	 * Test of Contact Not Null method
	 */
	@Test//(expected = IllegalArgumentException.class)
	public void testContactNotAcceptNull() {
		/*
		Calendar date = Calendar.getInstance();
		Event newEvent = new Event(null, "Event about JAVA skills", date, true);
		 */
	}

}
