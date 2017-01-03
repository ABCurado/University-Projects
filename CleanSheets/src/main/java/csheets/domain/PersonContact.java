/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Marcelo Barroso 1131399
 */
@Entity
@DiscriminatorValue("PERSON")
public class PersonContact extends Contact {

	/**
	 * First name of the contact
	 */
	private String firstName;

	/**
	 * Last name of the contact
	 */
	private String lastName;

	/**
	 * The contact company of the contact.
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	private Contact company = null;

	/**
	 * Profession of the contact
	 */
	private String profession = null;

	/**
	 *
	 * @param firstName firstName
	 * @param lastName lastName
	 * @param profession profession
	 * @param company company
	 * @param photo photo
	 */
	public PersonContact(String firstName, String lastName, String profession,
						 Contact company, byte[] photo) {
		super(photo);
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.firstName = firstName;
		if (lastName == null) {
			this.lastName = "";
		} else {
			this.lastName = lastName;
		}
		this.company = company;
		this.profession = profession;
		this.name = this.toString();
	}

	/**
	 * contruct JAP
	 */
	protected PersonContact() {
	}

	/**
	 * Get Name
	 *
	 * @return name
	 */
	@Override
	public String name() {
		return this.toString();
	}

	/**
	 * Get First Name
	 *
	 * @return the first name
	 */
	public String firstName() {
		return this.firstName;
	}

	/**
	 * Set First Name
	 *
	 * @param firstName firstName
	 */
	public void firstName(String firstName) {
		this.firstName = firstName;
		this.name = this.toString();
	}

	/**
	 * Get Last Name
	 *
	 * @return the last name
	 */
	public String lastName() {
		return this.lastName;
	}

	/**
	 * @param lastName lastName
	 */
	public void lastName(String lastName) {
		this.lastName = lastName;
		this.name = this.toString();
	}

	/**
	 * Get Business
	 *
	 * @return Contact
	 */
	public Contact company() {
		return company;
	}

	/**
	 * Get Profession
	 *
	 * @return String
	 */
	public String profession() {
		return profession;
	}

	/**
	 * Set Business
	 *
	 * @param company company
	 */
	public void company(Contact company) {
		this.company = company;
	}

	/**
	 * Set Profession
	 *
	 * @param profession profession
	 */
	public void profession(String profession) {
		this.profession = profession;
	}

	@Override
	public String toString() {
		return (this.firstName.trim() + " " + this.lastName.trim()).trim();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof PersonContact)) {
			return false;
		}
		PersonContact instance = (PersonContact) obj;
		return this.firstName.equals(instance.firstName) && this.lastName.
			equals(instance.lastName);
	}

	@Override
	public int hashCode() {
		int hashcode = 27;
		hashcode = hashcode + 11 + this.name().hashCode();
		return hashcode;
	}

}
