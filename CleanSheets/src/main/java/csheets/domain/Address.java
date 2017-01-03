/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Martins
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"Country", "City", "Street", "PostalCode"})})
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String country;
	private String city;
	private String street;
	private String postalCode;

	protected Address() {
	}

	public Address(String country, String city, String street, String postalCode) {
		if (country == null || city == null || street == null || postalCode == null) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		if (country.isEmpty() || city.isEmpty() || street.isEmpty() || postalCode.
			isEmpty()) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}

	public void defineAddress(String country, String city, String street,
							  String postalCode) {
		if (country == null || city == null || street == null || postalCode == null) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		if (country.isEmpty() || city.isEmpty() || street.isEmpty() || postalCode.
			isEmpty()) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return this.country;
	}

	public String getCity() {
		return this.city;
	}

	public String getStreet() {
		return this.street;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof Address)) {
			return false;
		}
		Address instance = (Address) obj;
		return this.hashCode() == instance.hashCode();
	}

	@Override
	public int hashCode() {
		int hashcode = 21;
		hashcode += this.country.hashCode();
		hashcode += this.city.hashCode();
		hashcode += this.street.hashCode();
		hashcode += this.postalCode.hashCode();
		return hashcode;
	}
}
