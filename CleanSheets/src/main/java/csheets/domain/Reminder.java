/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import csheets.support.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Gabriel
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NAME"})})
public class Reminder implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * The name of reminder
	 */
	private String name;
	/**
	 * The description of reminder
	 */
	private String description;

	/**
	 * The Time of reminder
	 */
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Calendar timeStep;
	/**
	 * bollean, if true the alert are active
	 */
	private boolean alert;

	public Reminder() {
	}

	/**
	 * The complete constructor of reminder
	 *
	 * @param name name
	 * @param description description
	 * @param ts ts
	 * @param alert alert
	 */
	public Reminder(String name, String description, Calendar ts, boolean alert) {
		if (name == null || description == null || ts == null) {
			throw new IllegalArgumentException("name of reminder can´t be null.");

		} else if (name.isEmpty() || description.isEmpty()) {
			throw new IllegalArgumentException("description of reminder can´t be null.");
		}
		this.name = name;
		this.description = description;
		this.timeStep = ts;
		this.alert = alert;
	}

	/**
	 * This method return the name of reminder.
	 *
	 * @return name
	 */
	public String name() {
		return this.name;
	}

	/**
	 * This method return the description of reminder.
	 *
	 * @return name
	 */
	public String description() {
		return this.description;
	}

	/**
	 * This method return the date of reminder.
	 *
	 * @return name
	 */
	public Calendar timeOfReminder() {
            return this.timeStep;
	}
        
        public String timeOfRemider() {
            String result="";
            result += DateTime.format(timeStep);
            result += " " + DateTime.hour(timeStep);
            result += ":" + DateTime.min(timeStep);
            return result;
        }

	/**
	 * This method return the true if the alert is active.
	 *
	 * @return name
	 */
	public boolean alert() {
		return alert;
	}

	/**
	 * This method define the alert of reminder, whit the boolean received.
	 *
	 * @param alert alert
	 */
	public void defineAlert(boolean alert) {
		this.alert = alert;
	}

	/**
	 * This method are used to edit the information of the reminder.
	 *
	 * @param name name
	 * @param description description
	 * @param date date
	 * @param alert alert
	 */
	public void defineReminder(String name, String description, Calendar date,
							   boolean alert) {
		this.name = name;
		this.description = description;
		this.timeStep = date;
		this.alert = alert;
	}

	/**
	 * This method increments the timeStep of reminder.
	 *
	 * @param field field
	 * @param amount amount
	 */
	public void add(int field, int amount) {
		this.timeStep.add(field, amount);
	}

	public void alert(boolean active) {
		this.alert = active;
	}

	@Override
	public String toString() {
		return "Reminder: " + this.name + " - Description: " + this.description + " - " + DateTime.
			format(this.timeStep);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof Reminder)) {
			return false;
		}
		Reminder instance = (Reminder) obj;
		return this.hashCode() == instance.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.name.hashCode();
		hashcode += this.description.hashCode();
		hashcode += this.timeStep.hashCode();
		return hashcode;
	}
}
