/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.awt.Color;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NAME"})})
public class ContactCalendar implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.MERGE)
	private Contact contact;

	private String name;

	private String description;

	private Color color;

	protected ContactCalendar() {
	}

	public ContactCalendar(String name, String text, Color color, Contact contato) {
		if (name == null || text == null || color == null || contato == null) {
			throw new IllegalArgumentException("name of reminder can´t be null.");

		} else if (name.isEmpty() || text.isEmpty()) {
			throw new IllegalArgumentException("description of reminder can´t be null.");
		}
		this.name = name;
		this.description = text;
		this.color = color;
		this.contact = contato;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return description;
	}

	public void setText(String text) {
		this.description = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void defineCalendar(Contact contact, String name, String description,
							   Color colour) {
		this.contact = contact;
		this.name = name;
		this.description = description;
		this.color = colour;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof ContactCalendar)) {
			return false;
		}
		ContactCalendar instance = (ContactCalendar) obj;
		return this.hashCode() == instance.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.name.hashCode();
		hashcode += this.description.hashCode();
		hashcode += this.contact.hashCode();
		hashcode += this.color.hashCode();
		return hashcode;
	}
}
