/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.EventRepository;
import csheets.persistence.PersistenceContext;
import csheets.support.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Martins
 */
class InMemoryEventRepository extends InMemoryRepository<Event, Long>
	implements EventRepository {

	long nextID = 1;

	@Override
	protected Long newPK(Event entity) {
		return ++nextID;
	}

	@Override
	public Iterable<Event> eventsContact(Contact contact) {
		List<Event> list = new ArrayList();
		for (ContactCalendar calendar : PersistenceContext.repositories().
			calendars().
			calendarsContact(contact)) {
			for (Event event : this.eventsCalendar(calendar)) {
				list.add(event);
			}
		}
		return list;
	}

	@Override
	public Iterable<Event> eventsCalendar(ContactCalendar calendar) {
		List<Event> list = new ArrayList();
		for (Event event : this.all()) {
			if (event.calendar().equals(calendar)) {
				list.add(event);

			}
		}
		return list;
	}

	@Override
	public Iterable<Event> eventsContactPerDay(Contact contact, Calendar date) {
		List<Event> list = new ArrayList();
		for (Event event : eventsContact(contact)) {
			if (DateTime.
				isBetweenDates(event.startDate(), event.endDate(), date)) {
				list.add(event);
			}
		}
		return list;
	}

	@Override
	public Iterable<Event> eventsContactPerDayPerCalendar(Contact contact,
														  Calendar date,
														  ContactCalendar calendar) {
		List<Event> list = new ArrayList();
		for (Event event : eventsContact(contact)) {
			if (DateTime.
				isBetweenDates(event.startDate(), event.endDate(), date) && event.
				calendar().equals(calendar)) {
				list.add(event);
			}
		}
		return list;
	}
}
