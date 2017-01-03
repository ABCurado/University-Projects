/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chat.domain;

import csheets.support.DateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marcelo Barroso 1131399
 */
@Entity
public class Message implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@OneToOne(cascade = CascadeType.MERGE)
	private List<Message> answers;

	@Enumerated(EnumType.STRING)
	private Type type;

	private String host;

	private String name;

	private String content;

	public enum Type {
		USER, ROOM
	}

	protected Message() {
	}

	public Message(User user, Calendar date, String content) {
		this.host = user.name();
		this.name = user.nickname();
		this.date = date;
		this.content = content;
		this.type = Type.USER;
		this.answers = new ArrayList();
	}

	public Message(Room room, Calendar date, String content) {
		this.host = room.creator().name();
		this.name = room.name();
		this.date = date;
		this.content = content;
		this.type = Type.ROOM;
		this.answers = new ArrayList();
	}

	public Message(String host, String name, Calendar date, String content,
				   Type type) {
		this.host = host;
		this.name = name;
		this.date = date;
		this.content = content;
		this.type = type;
		this.answers = new ArrayList();
	}

	public Calendar date() {
		return this.date;
	}

	public String host() {
		return host;
	}

	public String name() {
		return name;
	}

	public Type type() {
		return type;
	}

	public String content() {
		return content;
	}

	public List<Message> answers() {
		return this.answers;
	}

	public Message addAnswers(Calendar date, String content) {
		Message answer = new Message(this.host, this.name, date, content, this.type);
		if (this.answers.add(answer)) {
			return answer;
		}
		return null;
	}

	@Override
	public String toString() {
		String info = DateTime.format(this.date, "YYYY/MM/dd hh:mm (");
		if (this.type == Type.ROOM) {
			info += "room";
		} else {
			info += "user";
		}
		return info + " " + this.name + "(" + this.host + ")" + this.content;
	}

	@Override
	public int hashCode() {
		int hash = 29;
		hash = hash * 11 + Objects.hashCode(this.id);
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
		final Message other = (Message) obj;
		return Objects.equals(this.hashCode(), other.hashCode());
	}

}
