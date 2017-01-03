package csheets.ext.reminder;

import csheets.domain.Reminder;
import csheets.ext.reminder.ui.ReminderPanel;
import csheets.factory.ReminderFactory;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.support.DateTime;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import csheets.ui.ctrl.UIController;
import java.util.Calendar;

/**
 * A controller for updating the user-specified comment of a cell.
 *
 * @author João Martins
 */
public class ReminderController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * User interface panel *
	 */
	private ReminderPanel uiPanel;

	/**
	 * Creates a new Reminder controller.
	 *
	 * @param uiController the user interface controller
	 * @param uiPanel the user interface panel
	 */
	public ReminderController(UIController uiController, ReminderPanel uiPanel) {
		this.uiController = uiController;
		this.uiPanel = uiPanel;

		TaskManager tm = new TaskManager();
		Task verify;
		verify = new Task() {
			public void fire() {
				for (Reminder reminder : allReminders()) {
					if (reminder.alert() && reminder.timeOfReminder().
						before(DateTime.now())) {
						Notification.reminderInformer().notifyChange(reminder);
					}
				}
			}
		};
		ThreadManager.create("crm.reminders", new Thread() {
							 public void run() {
								 tm.after(5).every(60).fire(verify);
							 }
						 });
		ThreadManager.run("crm.reminders");
	}

	/**
	 * This méthod creates a new Reminder whit the parameters received and save
	 * the new reminder in persistence.
	 *
	 * @param name name
	 * @param description description
	 * @param date date
	 * @param alert alert
	 * @return reminder
	 * @throws
	 * csheets.framework.persistence.repositories.DataIntegrityViolationException
	 * exception
	 */
	public Reminder createReminder(String name, String description,
								   Calendar date,
								   boolean alert) throws DataIntegrityViolationException {
		Reminder reminder = ReminderFactory.
			createReminder(name, description, date, alert);
		PersistenceContext.repositories().reminders().add(reminder);
		Notification.reminderInformer().notifyChange();
		return reminder;
	}

	/**
	 * This méthod save a edited reminder and save him in persistence.
	 *
	 * @param reminder reminder
	 * @return reminder
	 */
	public Reminder editReminder(Reminder reminder) {
		PersistenceContext.repositories().reminders().save(reminder);
		Notification.reminderInformer().notifyChange();
		return reminder;
	}

	/**
	 * This méthod remove a reminder received by parameters from the
	 * persistence.
	 *
	 * @param reminder reminder
	 */
	public void removeReminder(Reminder reminder) {
		PersistenceContext.repositories().reminders().delete(reminder);
		Notification.reminderInformer().notifyChange();
	}

	/**
	 * This méthod return reminders from the persistence.
	 *
	 * @return allReminders
	 */
	public Iterable<Reminder> allReminders() {
		return PersistenceContext.repositories().reminders().all();
	}

	/**
	 * This méthod add 5 minutes at time step of the reminder.
	 *
	 * @param reminder reminder
	 */
	public void snoozeReminder(Reminder reminder) {
		reminder.add(Calendar.MINUTE, 5);
		PersistenceContext.repositories().reminders().save(reminder);
		Notification.reminderInformer().notifyChange();
	}

	/**
	 * This méthod activate or desactivate the alert of reminder received by
	 * parameters.
	 *
	 * @param reminder reminder
	 * @param active active
	 */
	public void alert(Reminder reminder, boolean active) {
		reminder.
			defineReminder(reminder.name(), reminder.description(), reminder.
						   timeOfReminder(), active);
		PersistenceContext.repositories().reminders().save(reminder);
		Notification.reminderInformer().notifyChange();
	}
}
