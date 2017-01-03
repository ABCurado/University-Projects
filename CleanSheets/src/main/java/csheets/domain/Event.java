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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Rui Freitas
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"DESCRIPTION"})})
public class Event implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private ContactCalendar calendar;

	private String description;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Calendar startDate;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Calendar endDate;

	protected Event() {
	}

	public Event(ContactCalendar calendar, String description,
				 Calendar startDate,
				 Calendar endDate) {
		if (calendar == null || description == null || startDate == null || endDate == null) {
			throw new IllegalArgumentException();
		} else if (description.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.calendar = calendar;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ContactCalendar calendar() {
		return this.calendar;
	}

	public Calendar startDate() {
		return this.startDate;
	}

	public Calendar endDate() {
		return this.endDate;
	}

	public String description() {
		return this.description;
	}

	public void defineEvent(ContactCalendar calendar, String description,
							Calendar startDate,
							Calendar endDate) {
		this.calendar = calendar;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return this.calendar + " - " + this.description + " - " + DateTime.
			format(startDate) + " - " + DateTime.format(endDate);
	}

	public void addStartDate(int field, int amount) {
		this.startDate.add(field, amount);
	}

	public void addEndDate(int field, int amount) {
		this.endDate.add(field, amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof Event)) {
			return false;
		}
		Event instance = (Event) obj;
		return this.hashCode() == instance.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.calendar.hashCode();
		hashcode += this.description.hashCode();
		hashcode += this.startDate.hashCode();
		hashcode += this.endDate.hashCode();
		return hashcode;
	}
}
