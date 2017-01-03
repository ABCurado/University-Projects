/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * This class is an AggregateRoot. Represents an domain entity Contact.
 *
 * @author Rui Freitas
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NAME"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMIN")
public abstract class Contact implements Serializable {

	/**
	 * Id of the contact it is for JPA
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	protected String name;

	/**
	 * The photo of the contact.
	 */
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] photo = null;

	Set<String> tags;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address mainAddress = null;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address secundaryAddress = null;

	/**
	 *
	 * @param photo photo
	 */
	public Contact(byte[] photo) {
		if (photo == null) {
			throw new IllegalArgumentException();
		}
		this.photo = photo;
		this.tags = new HashSet();
	}

	/**
	 * construct JPA
	 */
	protected Contact() {
	}

	/**
	 * Get Photo
	 *
	 * @return the photoFileName
	 */
	public byte[] photo() {
		return this.photo;
	}

	/**
	 * Set Photo
	 *
	 * @param photo photo
	 */
	public void photo(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * Get Id
	 *
	 * @return identifier
	 */
	public long id() {
		return id;
	}

	public Set<String> tags() {
		return this.tags;
	}

	public void addTag(String tagName) {
		this.tags.add(tagName);
	}

	/**
	 * Get Name
	 *
	 * @return nane
	 */
	public abstract String name();

	@Override
	public String toString() {
		return this.name().trim();
	}

	public void id(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!(obj instanceof Contact)) {
			return false;
		}
		Contact instance = (Contact) obj;
		return this.name.equals(instance.name);
	}

	@Override
	public int hashCode() {
		int hashcode = 27;
		hashcode = hashcode + 11 + this.name().hashCode();
		return hashcode;
	}

	public void removeTag(String tag) {
		this.tags.remove(tag);
	}

	public Address getMainAddress() {
		return this.mainAddress;
	}

	public Address getSecundaryAddress() {
		return this.secundaryAddress;
	}

	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}

	public void setSecundaryAddress(Address secundaryAddress) {
		this.secundaryAddress = secundaryAddress;
	}

	public void removeAddress(Address address, boolean main) {
		if (main) {
			this.mainAddress = null;
		} else {
			this.secundaryAddress = null;
		}
	}

	public Iterable<Address> contactAddress() {
		ArrayList<Address> listAddresses = new ArrayList();
		if (this.mainAddress != null) {
			listAddresses.add(mainAddress);
		}
		if (this.secundaryAddress != null) {
			listAddresses.add(secundaryAddress);
		}
		return listAddresses;
	}
}
