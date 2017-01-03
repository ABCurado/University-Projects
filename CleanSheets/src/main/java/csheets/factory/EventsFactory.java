/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.factory;

import csheets.domain.ContactCalendar;
import csheets.domain.Event;
import java.util.Calendar;

/**
 *
 * @author Martins
 */
final public class EventsFactory {

	private EventsFactory() {
	}

	static public Event createEvent(ContactCalendar calendar,String description,Calendar startDate, Calendar endDate) {
		return new Event(calendar, description, startDate, endDate);
	}
}
