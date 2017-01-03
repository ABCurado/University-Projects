/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.inmemory;

import csheets.domain.ContactCalendar;
import csheets.domain.Contact;
import csheets.framework.persistence.repositories.impl.immemory.InMemoryRepository;
import csheets.persistence.CalendarRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martins
 */
class InMemoryCalendarRepository extends InMemoryRepository<ContactCalendar, Long>
	implements CalendarRepository {

	long nextID = 1;

	@Override
	protected Long newPK(ContactCalendar entity) {
		return ++nextID;
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
}
