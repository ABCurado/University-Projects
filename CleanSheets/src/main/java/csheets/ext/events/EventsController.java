package csheets.ext.events;

import csheets.domain.Event;
import csheets.ext.events.ui.EventsPanel;
import csheets.factory.EventsFactory;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.ui.ctrl.UIController;
import java.util.Calendar;

/**
 * A controller for updating the user-specified comment of a cell.
 *
 * @author João Martins
 */
public class EventsController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * User interface panel *
	 */
	private EventsPanel uiPanel;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiController the user interface controller
	 * @param uiPanel the user interface panel
	 */
	public EventsController(UIController uiController, EventsPanel uiPanel) {
		this.uiController = uiController;
		this.uiPanel = uiPanel;
	}

	public Event createEvent(csheets.domain.ContactCalendar calendar,
							 String description, Calendar startDate,
							 Calendar endDate) throws DataIntegrityViolationException {
		Event event = EventsFactory.
			createEvent(calendar, description, startDate, endDate);
		PersistenceContext.repositories().events().add(event);
		Notification.eventInformer().notifyChange();
		return event;
	}

	public Event editEvent(Event event) {
		PersistenceContext.repositories().events().save(event);
		Notification.eventInformer().notifyChange();
		return event;
	}

	public void removeEvent(Event event) {
		PersistenceContext.repositories().events().delete(event);
		Notification.eventInformer().notifyChange();
	}

	public Iterable<csheets.domain.ContactCalendar> allCalendars() {
		return PersistenceContext.repositories().calendars().all();
	}

	public Iterable<Event> allEvents() {
		return PersistenceContext.repositories().events().all();
	}
}
