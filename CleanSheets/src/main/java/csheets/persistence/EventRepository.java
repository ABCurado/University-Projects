/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.domain.Contact;
import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import csheets.framework.persistence.repositories.Repository;
import java.util.Calendar;

/**
 *
 * @author João Martins
 */
public interface EventRepository extends Repository<Event, Long> {

	public Iterable<Event> eventsContact(Contact contact);

	public Iterable<Event> eventsCalendar(ContactCalendar calendar);

	public Iterable<Event> eventsContactPerDay(Contact contact, Calendar date);

	public Iterable<Event> eventsContactPerDayPerCalendar(Contact contact,
														  Calendar date,
														  ContactCalendar calendar);

}
