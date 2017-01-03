/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chat.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Marcelo Barroso 1131399
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NAME, CREATOR"})})
public class Room implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne(cascade = CascadeType.MERGE)
	private User creator;

	private Type type;

	@ManyToMany
	private Set<User> operators;

	@ManyToMany
	private Set<User> helpers;

	@ManyToMany
	private Set<User> users;

	public enum Type {
		PUBLIC, PRIVATE
	}

	public enum Hierarchy {
		OPERATOR, HELPER, USER
	}

	protected Room() {
	}

	public Room(String name, User creator, Type type) {
		this.name = name;
		this.creator = creator;
		this.type = type;
		this.operators = new HashSet();
		this.helpers = new HashSet();
		this.users = new HashSet();
	}

	public String name() {
		return this.name;
	}

	public User creator() {
		return this.creator;
	}

	public Type type() {
		return this.type;
	}

	public Integer count() {
		return this.operators.size() + this.helpers.size() + this.users.size() + 1;
	}

	public Set<User> hierarchy() {
		Set<User> hierarchy = new HashSet();
		hierarchy.add(creator);
		for (User user : this.operators) {
			hierarchy.add(user);
		}
		for (User user : this.helpers) {
			hierarchy.add(user);
		}
		for (User user : this.users) {
			hierarchy.add(user);
		}
		return hierarchy;
	}

	public boolean removeUser(User user) {
		if (this.creator == user) {
			return false;
		}
		if (this.operators.remove(user)) {
			return true;
		}
		if (this.helpers.remove(user)) {
			return true;
		}
		if (this.users.remove(user)) {
			return true;
		}
		return false;
	}

	public boolean addUser(User user, Hierarchy type) {
		if (this.creator == user) {
			return false;
		}
		this.removeUser(user);
		if (type == Hierarchy.OPERATOR) {
			return this.operators.add(user);
		}
		if (type == Hierarchy.HELPER) {
			return this.helpers.add(user);
		}
		if (type == Hierarchy.USER) {
			return this.users.add(user);
		}
		return false;
	}

	@Override
	public String toString() {
		return this.name + " (" + this.type + ")";
	}

	@Override
	public int hashCode() {
		int hash = 29;
		hash = hash * 11 + Objects.hashCode(this.name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Room other = (Room) obj;
		return Objects.equals(this.hashCode(), other.hashCode());
	}

}
