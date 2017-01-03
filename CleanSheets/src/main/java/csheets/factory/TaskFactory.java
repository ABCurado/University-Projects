/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.factory;

import csheets.domain.Contact;
import csheets.domain.Event;
import csheets.domain.Reminder;
import csheets.domain.Task;
import java.util.Calendar;

/**
 *
 * @author Bruno
 */
public class TaskFactory {

	public TaskFactory() {

	}

	public static final Task createTask(String taskName, String description,
										int priority,
										float percentage, Contact contact,
										Calendar date, Event event,
										Reminder reminder) {
		if (date instanceof Calendar) {
			event = null;
			reminder = null;
		} else if (event instanceof Event) {
			date = null;
			reminder = null;
		} else if (reminder instanceof Reminder) {
			date = null;
			event = null;
		} else {
			date = null;
			event = null;
			reminder = null;
		}
		return new Task(taskName, description, priority, percentage, contact, date, event, reminder);
	}

	public static final Task createTask(String taskName, String description,
										int priority, float percentage,
										Contact contact, Object object) {
		Calendar date = null;
		Event event = null;
		Reminder reminder = null;
		if (object instanceof Calendar) {
			date = (Calendar) object;
		} else if (object instanceof Event) {
			event = (Event) object;
		} else if (object instanceof Reminder) {
			reminder = (Reminder) object;
		}
		return new Task(taskName, description, priority, percentage, contact, date, event, reminder);
	}
}
