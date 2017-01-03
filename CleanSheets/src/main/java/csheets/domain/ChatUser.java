/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import csheets.domain.ChatMessage.MessageType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author nervousdev
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NICKNAME"})})
public class ChatUser implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String nickname;

	private String name;

	@Lob
	private byte[] chatIcon;

	@Transient
	private boolean state;

	private ArrayList<ChatMessage> history;

	protected ChatUser() {
	}

	public ChatUser(String nick, byte[] icon) {
		this(null, nick, icon);
	}

	public ChatUser(String name, String nick, byte[] icon) {
		this.nickname = nick;
		this.chatIcon = icon;
		this.name = name;
		this.history = new ArrayList<>();
		this.state = false;
	}

	public String nickname() {
		return this.nickname;
	}

	public byte[] icon() {
		return this.chatIcon;
	}

	public void newChatMessage(String nickname, String text, MessageType type) {
		this.history.add(new ChatMessage(nickname, text, type));
	}

	public String getNickname() {
		return nickname;
	}

	public boolean state() {
		return this.state;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void changeState(boolean state) {
		this.state = state;
	}

	public ArrayList<ChatMessage> history() {
		return this.history;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + Objects.hashCode(this.nickname);
		return hash;
	}

	public String name() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ChatUser other = (ChatUser) obj;
		return Objects.equals(this.nickname, other.nickname);
	}

}
