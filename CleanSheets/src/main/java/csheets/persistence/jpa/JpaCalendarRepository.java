/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.ContactCalendar;
import csheets.domain.Contact;
import csheets.domain.Event;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.CalendarRepository;
import csheets.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class JpaCalendarRepository extends JpaRepository<ContactCalendar, Long> implements CalendarRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
	}

	@Override
	public Iterable<ContactCalendar> calendarsContact(Contact contact) {
		List<ContactCalendar> list = new ArrayList();
		for (ContactCalendar calendar : this.all()) {
			if (calendar.getContact().equals(contact)) {
				list.add(calendar);
			}
		}
		return list;
	}

	@Override
	public void delete(ContactCalendar entity) {
		for (Event event : PersistenceContext.repositories().events().
			eventsCalendar(entity)) {
			PersistenceContext.repositories().events().delete(event);
		}
		super.delete(entity);
	}

}
