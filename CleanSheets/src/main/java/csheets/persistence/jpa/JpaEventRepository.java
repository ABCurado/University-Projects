/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence.jpa;

import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.framework.persistence.repositories.impl.jpa.JpaRepository;
import csheets.persistence.EventRepository;
import csheets.persistence.PersistenceContext;
import csheets.support.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author João Martins
 */
public class JpaEventRepository extends JpaRepository<Event, Long> implements EventRepository {

	@Override
	protected String persistenceUnitName() {
		return PersistenceSettings.PERSISTENCE_UNIT_NAME;
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
		final Query q = entityManager().createQuery("SELECT e "
			+ "FROM Event e "
			+ "JOIN e.calendar cc "
			+ "JOIN cc.contact c "
			+ "where c.name=:name", Event.class);
		q.setParameter("name", contact.name());

		//TODO Improve this part!! data comparasion should be done within the query!
		//Problem: Event has dates has TIMESTAMPE. No way to compare only the date and not the time.
		ArrayList<Event> res = new ArrayList<>();
		for (Event eve : (List<Event>) q.getResultList()) {
			if (DateTime.isBetweenDates(eve.startDate(), eve.endDate(), date)) {
				res.add(eve);
			}
		}
		return res;
	}

	@Override
	public Iterable<Event> eventsContactPerDayPerCalendar(Contact contact,
														  Calendar date,
														  ContactCalendar calendar) {
		final Query q = entityManager().
			createQuery("SELECT e FROM Event e JOIN e.calendar cc JOIN cc.contact c where c.name=:name AND e.calendar=:calendar", Event.class);
		q.setParameter("name", contact.name());
		q.setParameter("calendar", calendar);

		//TODO Improve this part!! data comparasion should be done within the query!
		//Problem: Event has dates has TIMESTAMPE. No way to compare only the date and not the time.
		ArrayList<Event> res = new ArrayList<>();
		for (Event eve : (List<Event>) q.getResultList()) {
			if (DateTime.isBetweenDates(eve.startDate(), eve.endDate(), date)) {
				res.add(eve);
			}
		}
		return res;
	}

}
