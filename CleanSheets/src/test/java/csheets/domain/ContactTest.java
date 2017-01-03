/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Freitas
 */
public class ContactTest {

	public ContactTest() {
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
	 * Test of First Name Not Null method
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFirstNameNotAcceptNull() {
		Contact newContact = new PersonContact(null, "Ramires", null, null, null);

	}

	/**
	 * Test of Last Name Not Null method
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLastNameNotAcceptNull() {
		Contact newContact = new PersonContact("Diogo", null, null, null, null);

	}

	/**
	 * Test of First Name Not Empty method
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFirstNameNotAcceptEmpty() {
		Contact newContact = new PersonContact("", "Leite", null, null, new byte[10]);

	}

}
