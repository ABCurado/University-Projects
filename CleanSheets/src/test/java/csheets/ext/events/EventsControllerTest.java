/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.events;

import csheets.domain.Contact;
import csheets.domain.Event;
import csheets.domain.PersonContact;
import csheets.ext.events.ui.EventsPanel;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.ui.ctrl.UIController;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author utilizador
 */
public class EventsControllerTest {

	EventsController instance;
	UIController uiController;
	EventsPanel eventsPanel;
	Event event;
	Calendar date;
	Contact contact;

	public EventsControllerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		contact = new PersonContact("User", "test", null, null, new byte[10]);
		date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, 1);
		//event = new Event(contact, "New Event", date, true);
		/*
		uiController = new UIController(cleanSheets);
		eventsPanel = new EventsPanel(uiController);
		instance = new EventsController(uiController, eventsPanel);
		 */
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of createEvent method, of class EventsController.
	 */
	@Test
	public void testCreateEvent() throws DataIntegrityViolationException {

		//TODO missing in-memory persistence implementation
	}

	/**
	 * Test of editEvent method, of class EventsController.
	 */
	@Test
	public void testEditEvent() {
		//TODO missing in-memory persistence implementation

	}

	/**
	 * Test of removeEvent method, of class EventsController.
	 */
	@Test
	public void testRemoveEvent() {
		//TODO missing in-memory persistence implementation

	}

	/**
	 * Test of allContacts method, of class EventsController.
	 */
	@Test
	public void testAllContacts() {
		//TODO missing in-memory persistence implementation

	}

	/**
	 * Test of allEvents method, of class EventsController.
	 */
	@Test
	public void testAllEvents() {
		//TODO missing in-memory persistence implementation

	}
}
