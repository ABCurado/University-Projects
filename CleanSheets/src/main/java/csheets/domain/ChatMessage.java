/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author nervousdev
 */
@Embeddable
public class ChatMessage implements Serializable {

	public enum MessageType {
		RECEIVED, SENT
	}
	private String otherUserNickname;

	private String messageText;

	private MessageType type;

	protected ChatMessage() {

	}

	public ChatMessage(String nickname, String text, MessageType type) {
		this.otherUserNickname = nickname;
		this.messageText = text;
		this.type = type;
	}

	public String nickname() {
		return this.otherUserNickname;
	}

	public String text() {
		return this.messageText;
	}

	public MessageType type() {
		return this.type;
	}
}
