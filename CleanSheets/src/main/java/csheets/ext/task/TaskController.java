/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.task;

import csheets.domain.Contact;
import csheets.domain.Event;
import csheets.domain.Reminder;
import csheets.domain.Task;
import csheets.factory.TaskFactory;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class TaskController {

	/**
	 * The user interface controller
	 */
	private final UIController uiController;
	public Iterable<Reminder> allReminders;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiCtrl the user interface controller
	 */
	public TaskController(UIController uiCtrl) {
		this.uiController = uiCtrl;

	}

	public Iterable<Contact> showContacts() {
		return PersistenceContext.repositories().contacts().all();
	}

        /**
         * method of creating a new task
         * @param taskName Task name 
         * @param description Task Description
         * @param priority Task priority
         * @param percentageofcompletion Task status in percentage
         * @param contact contact that creates the task
         * @param deadLine deaLine Task
         * @return Task
         * @throws DataIntegrityViolationException 
         */
	public Task createTask(String taskName, String description, int priority,
						   float percentageofcompletion, Contact contact,
						   Object deadLine) throws DataIntegrityViolationException {
		Task task = TaskFactory.
			createTask(taskName, description, priority, percentageofcompletion, contact, deadLine);
		PersistenceContext.repositories().task().add(task);
		Notification.taskInformer().notifyChange(task);
		return task;
	}

        /**
         * method of edit a new task
         * @param task task to editing
         * @return task edited
         */
	public Task editTask(Task task) {
		PersistenceContext.repositories().task().save(task);
		Notification.taskInformer().notifyChange(task);
		return task;
	}

        /**
         * method of remove a new task
         * @param task task to removing
         */
	public void removeTask(Task task) {
		PersistenceContext.repositories().task().delete(task);
		Notification.taskInformer().notifyChange();
	}

        /**
         * Method to access a list of tasks created by contact
         * @param contact contact associated with the task
         * @return all task created by the conctact
         */
	public List<Task> allTasksContact(Contact contact) {
            List<Task> list = new ArrayList();
            for (Task task : PersistenceContext.repositories().task().task(contact)) {
                list.add(task);
            }
		return list;
	}
        /**
         * Methodo  to access list of all tasks
         * @return list of tasks.
         */
	public List<Task> allTasks() {
            List<Task> list = new ArrayList();
            for (Task task : PersistenceContext.repositories().task().all()) {
                list.add(task);
            }
		return list;
	}

	public List<Task> allTasks(String left, String right) {
		if (left == null || left.isEmpty()) {
			left = "0";
		}
		if (right == null || right.isEmpty()) {
			right = "100";
		}
		Double numLeft = Double.parseDouble(left);
		Double numRight = Double.parseDouble(right);
		if (numLeft == null || numRight == null || numRight < numLeft) {
			return this.allTasks();
		}
		List<Task> percent = new ArrayList();
		for (Task task : PersistenceContext.repositories().task().all()) {
			if (numLeft <= task.Percentage() && task.Percentage() <= numRight) {
				percent.add(task);
			}
		}
		return percent;
	}

        /**
         * Methodo  to access list of all contacts
         * @return  all existing contacts in the repository
         */
	public Iterable<Contact> allContacts() {
		return PersistenceContext.repositories().contacts().
			all();
	}
        /**
         * Methodo  to access list of all Events
         * @return  all existing events in the repository
         */
	public Iterable<Event> allEvents() {
		return PersistenceContext.repositories().events().all();
	}
        /**
         *  Methodo  to access list of all Reminders
         * @return all existing reminders in the repository
         */
	public Iterable<Reminder> allReminders() {
		return PersistenceContext.repositories().reminders().all();
	}
}
