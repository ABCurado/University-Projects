/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chat.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Marcelo Barroso 1131399
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NAME"})})
public class User implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String nickname;

	private String ip;

	private String port;

	private String target;

	@Lob
	private byte[] image;

	@Enumerated(EnumType.STRING)
	private State state;

	public enum State {
		ONLINE, OFFLINE, AWAY
	}

	protected User() {
	}

	public User(String name, String nickname, byte[] image, State state) {
		this.name = name;
		this.nickname = nickname;
		this.image = image;
		this.state = state;
	}

	public User(String name, String nickname, byte[] image, State state,
				String ip, String port) {
		this.name = name;
		this.nickname = nickname;
		this.image = image;
		this.state = state;
		this.ip = ip;
		this.port = port;
	}

	public String name() {
		return name;
	}

	public String nickname() {
		return nickname;
	}

	public void nickname(String nickname) {
		this.nickname = nickname;
	}

	public byte[] image() {
		return image;
	}

	public void image(byte[] image) {
		this.image = image;
	}

	public State state() {
		return state;
	}

	public void state(State state) {
		this.state = state;
	}

	public String target() {
		return this.target;
	}

	public void target(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return this.nickname;
	}

	@Override
	public int hashCode() {
		int hash = 29;
		hash = hash * 11 + Objects.hashCode(this.nickname);
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
		final User other = (User) obj;
		return Objects.equals(this.hashCode(), other.hashCode());
	}

}
