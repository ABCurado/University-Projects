/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.agenda.ui;

import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.persistence.PersistenceContext;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Controller of the wizard process to simplify the WizardFrame code and use
 * good practices of code structuring
 *
 * @author AB-1140280
 */
public class AgendaController {

	UIController uicontroller;

	public AgendaController(UIController uicontroller) {
		this.uicontroller = uicontroller;
	}

	/**
	 * Returns a list model with the contacts on teh database
	 *
	 * @return ContactListModel
	 */
	public ContactListModel getContacts() {
		ArrayList<Contact> list = new ArrayList();
		for (Contact contact : PersistenceContext.repositories().contacts().
			all()) {
			list.add(contact);
		}
		return new ContactListModel(list);
	}

	/**
	 * Returns a list model with the calendars of the contacts on the database
	 *
	 * @return ContactListModel
	 */
	public List<ContactCalendar> getCalendars() {
		ArrayList<ContactCalendar> list = new ArrayList();
		for (ContactCalendar calendar : PersistenceContext.repositories().
			calendars().
			all()) {
			list.add(calendar);
		}
		return list;
	}

	/**
	 * Returns a list of events for a given date, contact and calendar
	 *
	 * @param date date
	 * @param contact contact
	 * @param calendar the calendar
	 * @return list of events
	 */
	public List<Event> updateEvents(Calendar date, Contact contact,
									ContactCalendar calendar) {
		ArrayList<Event> list = new ArrayList<Event>();
		for (Event event : PersistenceContext.repositories().events().
			eventsContactPerDayPerCalendar(contact, date, calendar)) {
			list.add(event);
		}
		return list;
	}

	public void saveEvent(Event theEvent) {
		PersistenceContext.repositories().events().save(theEvent);
	}

	public Calendar nextDay(Calendar calendar) {
		calendar.add(Calendar.DATE, 1);
		return calendar;
	}

	public Calendar previousDay(Calendar calendar) {
		calendar.add(Calendar.DATE, -1);
		return calendar;
	}

	public void deleteEvent(Event theEvent) {
		PersistenceContext.repositories().events().delete(theEvent);
	}

}
