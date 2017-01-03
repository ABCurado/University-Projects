/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.agenda.ui;

import csheets.domain.CompanyContact;
import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.domain.PersonContact;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.persistence.PersistenceContext;
import csheets.support.DateTime;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ab
 */
public class AgendaControllerTest {

	AgendaController controller;
	PersonContact contact1;
	PersonContact contact2;
	ContactCalendar cal;
	Event event1;
	Event event2;
	Event event3;
	CompanyContact c;
	Calendar startDate;
	Calendar endDate;

	public AgendaControllerTest() throws DataIntegrityViolationException {
		startDate = DateTime.newCalendar(2016, 5, 4);
		startDate.add(Calendar.YEAR, 5);
		endDate = DateTime.newCalendar(2016, 5, 4);
		endDate.add(Calendar.YEAR, 5);
		endDate.add(Calendar.DAY_OF_MONTH, 5);
		UIController uiController = UIController.getUIController();
		controller = new AgendaController(uiController);
		byte[] photo = {};
		c = new CompanyContact("testiiiiing", photo);
		PersistenceContext.repositories().contacts().add(c);
		contact1 = new PersonContact("testtesttest", "testtesttest", "testtesttest", c, photo);
		PersistenceContext.repositories().contacts().add(contact1);
		contact2 = new PersonContact("testtesttest2", "testtesttest2", "testtesttest2", c, photo);
		PersistenceContext.repositories().contacts().add(contact2);
		cal = new ContactCalendar("testeste", "testeste", Color.yellow, contact2);
		PersistenceContext.repositories().calendars().add(cal);
		event1 = new Event(cal, "testeste", startDate, endDate);
		PersistenceContext.repositories().events().add(event1);
		event2 = new Event(cal, "teffsteste", DateTime.newCalendar(2016, 5, 4), Calendar.
						   getInstance());
		PersistenceContext.repositories().events().add(event2);
		Calendar date1 = DateTime.newCalendar(2016, 5, 4);
		Calendar endDate2 = DateTime.newCalendar(2016, 5, 4);
		endDate2.add(Calendar.YEAR, 5);
		endDate2.add(Calendar.DAY_OF_MONTH, 10);
		event3 = new Event(cal, "teffstesweete", DateTime.
						   newCalendar(2016, 5, 4), endDate2);
		PersistenceContext.repositories().events().add(event3);
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
		PersistenceContext.repositories().events().delete(event1);
		PersistenceContext.repositories().events().delete(event2);
		PersistenceContext.repositories().calendars().delete(cal);
		PersistenceContext.repositories().contacts().delete(contact1);
		PersistenceContext.repositories().contacts().delete(contact2);
		PersistenceContext.repositories().contacts().delete(c);
	}

	/**
	 * Test of getContacts method, of class AgendaController.
	 */
	@Test
	public void testGetContacts() {
		ArrayList<Contact> list = new ArrayList();
		for (Contact contact : PersistenceContext.repositories().contacts().
			all()) {
			list.add(contact);
		}
		ContactListModel expectedResult = new ContactListModel(list);
		ContactListModel result = controller.getContacts();
		Assert.assertEquals(expectedResult, result);
	}

	@Test
	public void testFailGetContacts() {
		ArrayList<Contact> list = new ArrayList();
		ContactListModel expectedResult = new ContactListModel(list);
		ContactListModel result = controller.getContacts();
		assertNotEquals(expectedResult, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullGetContacts() {
		ContactListModel expectedResult = new ContactListModel(null);
		ContactListModel result = controller.getContacts();
		assertTrue(false);
	}

	/**
	 * Test of updateEvents method, of class AgendaController.
	 */
	@Test
	public void testGet0Events() {
		ContactListModel list = controller.getContacts();
		Iterable result = new ArrayList();
		Iterable expected = PersistenceContext.repositories().events().
			eventsContactPerDay(contact1, DateTime.newCalendar(2016, 5, 4));
		assertEquals(expected, result);
	}

	/**
	 * Test of updateEvents method, of class AgendaController.
	 */
	@Test
	public void testGet1Events() {
		Calendar date = DateTime.newCalendar(2016, 5, 4);
		date.add(Calendar.YEAR, 3);
		ContactListModel list = controller.getContacts();
		Iterable expected = new ArrayList();
		((ArrayList) expected).add(event3);
		Iterable result = PersistenceContext.repositories().events().
			eventsContactPerDay(contact2, date);
		assertEquals(expected, result);
	}

	/**
	 * Test of updateEvents method, of class AgendaController.
	 */
	@Test
	public void testGet2Events() {
		Calendar date = DateTime.newCalendar(2016, 5, 4);
		date.add(Calendar.YEAR, 5);
		ContactListModel list = controller.getContacts();
		Iterable expected = new ArrayList();
		((ArrayList) expected).add(event1);
		((ArrayList) expected).add(event3);
		Iterable result = PersistenceContext.repositories().events().
			eventsContactPerDay(contact2, date);
		assertEquals(expected, result);
	}

	/**
	 * Test of updateEvents method, of class AgendaController.
	 */
	@Test
	public void testNextDay() {
		Calendar expected = DateTime.newCalendar(2016, 5, 4);
		expected.add(Calendar.DAY_OF_MONTH, 1);
		Calendar result = controller.nextDay(DateTime.newCalendar(2016, 5, 4));
		assertEquals(expected, result);
	}

	/**
	 * Test of updateEvents method, of class AgendaController.
	 */
	@Test
	public void testPreviousDay() {
		Calendar expected = DateTime.newCalendar(2016, 5, 4);
		expected.add(Calendar.DAY_OF_MONTH, -1);
		Calendar result = controller.previousDay(DateTime.
			newCalendar(2016, 5, 4));
		assertEquals(expected, result);
	}

}
