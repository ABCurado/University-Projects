/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Bruno
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"TASKNAME"})})
public class Task {

	@Id
	@GeneratedValue
	private Long id;

	private String taskName;
	private String description;
	private int priority;
	private float percentageofcompletion;

	@OneToOne(cascade = CascadeType.MERGE)
	private Event deadLineEvent = null;

	@OneToOne(cascade = CascadeType.MERGE)
	private Reminder deadLineReminder = null;

	/**
	 * The Time of reminder
	 */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Calendar deadLineTime = null;

	@ManyToOne
	private Contact contact;

	protected Task() {
	}

	public Task(String taskName, String description, int priority,
				float percentageofcompletion, Contact contact) {
		if (taskName == null || description == null || percentageofcompletion < 0 || percentageofcompletion > 100 || contact == null) {
			throw new IllegalArgumentException("Illegal arguments!");
		} else if (taskName.isEmpty() || description.isEmpty()) {
			throw new IllegalArgumentException("Illegal arguments empty");
		}
		this.taskName = taskName;
		this.contact = contact;
		this.description = description;
		this.priority = priority;
		this.percentageofcompletion = percentageofcompletion;
	}

	public Task(String taskName, String description, int priority,
				float percentage, Contact contact, Calendar date, Event event,
				Reminder reminder) {
		this(taskName, description, priority, percentage, contact);
		this.deadLineTime = date;
		this.deadLineEvent = event;
		this.deadLineReminder = reminder;
	}

	public void defineTask(String name, String description, int priority,
						   float percentage, Object deadLine) {
		if (name == null || description == null || percentage < 0 || percentage > 100) {
			throw new IllegalArgumentException("Illegal arguments!");
		} else if (name.isEmpty() || description.isEmpty()) {
			throw new IllegalArgumentException("Illegal arguments empty");
		}
		this.taskName = name;
		this.description = description;
		this.priority = priority;
		this.percentageofcompletion = percentage;
		this.deadLineEvent = null;
		this.deadLineReminder = null;
		this.deadLineTime = null;
		if (deadLine instanceof Event) {
			this.deadLineEvent = (Event) deadLine;
		} else if (deadLine instanceof Reminder) {
			this.deadLineReminder = (Reminder) deadLine;
		} else if (deadLine instanceof Calendar) {
			this.deadLineTime = (Calendar) deadLine;
		}
	}

	public String TaskName() {
		return this.taskName;
	}

	public String Description() {
		return this.description;
	}

	public int Priority() {
		return this.priority;
	}

	public float Percentage() {
		return this.percentageofcompletion;
	}

	public Contact getContact() {
		return this.contact;
	}

	public Calendar getDeadLineTime() {
		return this.deadLineTime;
	}

	public Event getDeadLineEvent() {
		return deadLineEvent;
	}

	public Reminder getDeadLineReminder() {
		return deadLineReminder;
	}

	@Override
	public String toString() {
		return this.taskName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof Task)) {
			return false;
		}
		Task instance = (Task) obj;
		return this.hashCode() == instance.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.taskName.hashCode();
		hashcode += this.description.hashCode();
		hashcode += this.contact.hashCode();
		return hashcode;
	}

	public Calendar timeOfReminder() {
		return this.deadLineTime;
	}

}
