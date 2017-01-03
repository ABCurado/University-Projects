package csheets.ext.calendar;

import csheets.domain.ContactCalendar;
import csheets.domain.Contact;
import csheets.ext.calendar.ui.CalendarPanel;
import csheets.factory.CalendarFactory;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.ui.ctrl.UIController;
import java.awt.Color;

/**
 * A controller for updating the user-specified comment of a cell.
 *
 * @author João Martins
 */
public class CalendarController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * User interface panel *
	 */
	private CalendarPanel uiPanel;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiController the user interface controller
	 * @param uiPanel the user interface panel
	 */
	public CalendarController(UIController uiController, CalendarPanel uiPanel) {
		this.uiController = uiController;
		this.uiPanel = uiPanel;
	}

	public ContactCalendar createCalendar(Contact contact, String name,
								   String description, Color colour) throws DataIntegrityViolationException {
		csheets.domain.ContactCalendar cal = CalendarFactory.
			createCalendar(name, description, colour, contact);
		PersistenceContext.repositories().calendars().add(cal);
		Notification.calendarInformer().notifyChange();
		return cal;
	}

	public ContactCalendar editCalendar(ContactCalendar calendar) {
		PersistenceContext.repositories().calendars().save(calendar);
		Notification.calendarInformer().notifyChange();
		return calendar;
	}

	public void removeCalendar(ContactCalendar calendar) {
		PersistenceContext.repositories().calendars().delete(calendar);
		Notification.calendarInformer().notifyChange();
	}

	public Iterable<Contact> allContacts() {
		return PersistenceContext.repositories().contacts().
			all();
	}

	public Iterable<ContactCalendar> allCalendars() {
		return PersistenceContext.repositories().calendars().all();
	}
}
