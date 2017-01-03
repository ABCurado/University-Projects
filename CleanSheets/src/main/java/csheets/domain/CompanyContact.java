/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcelo Barroso 1131399
 */
@Entity
@DiscriminatorValue("COMPANY")
public class CompanyContact extends Contact {

	/**
	 * The Enterprise's designation
	 */
	private String designation;

	/**
	 *
	 * @param designation designation
	 * @param photo photo
	 */
	public CompanyContact(String designation, byte[] photo) {
		super(photo);
		if (designation == null || designation.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.designation = designation;
		this.name = this.toString();
	}

	/**
	 * contruct JPA
	 */
	protected CompanyContact() {
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
	 * Get Designation
	 *
	 * @return designation
	 */
	public String designation() {
		return designation;
	}

	/**
	 * Set Designation
	 *
	 * @param designation designation
	 */
	public void designation(String designation) {
		this.designation = designation;
		this.name = this.toString();
	}

	@Override
	public String toString() {
		return this.designation.trim();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof CompanyContact)) {
			return false;
		}
		CompanyContact instance = (CompanyContact) obj;
		return this.hashCode() == instance.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 27;
		hashcode = hashcode + 11 + this.name().hashCode();
		return hashcode;
	}

}
