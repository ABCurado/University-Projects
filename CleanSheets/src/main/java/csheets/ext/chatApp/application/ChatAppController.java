/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chatApp.application;

import csheets.domain.ChatMessage;
import csheets.domain.ChatMessage.MessageType;
import csheets.domain.ChatUser;
import csheets.framework.persistence.repositories.DataIntegrityViolationException;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.persistence.internal.oxm.conversion.Base64;

/**
 *
 * @author Carlos Santos
 */
public class ChatAppController {

	public ChatAppController() {

	}
	/**
	 * The UDP Service.
	 */
	private UdpService udpService;

	/**
	 * The TCP Service.
	 */
	private TcpService tcpService;

	public void startUdpService(int seconds, ChatUser theUser) {
		if (seconds <= 0) {
			throw new IllegalArgumentException("Invalid seconds. It's not possible to register negative or zero seconds.");
		}
		try {
			this.udpService = new UdpService();
			this.udpService.server(theUser.nickname(), new String(Base64.
								   base64Encode(theUser.icon())));
			this.udpService.client(seconds);
		} catch (IllegalArgumentException e) {
			this.udpService.stop();
			throw e;
		}
	}

	/**
	 * Starts the TCP service.
	 *
	 */
	public void startTcpService() {
		this.tcpService = new TcpService();
		try {
			this.tcpService.server();
		} catch (IllegalArgumentException e) {
			this.tcpService.stop();
			throw e;
		}
	}

	public void changeStatusToOffline() {
		udpService.sendOfflineState();
	}

	public void sendMessage(String nickname, String target, String message,
							String targetNickname) {
		Map<String, String> sendMessage = new LinkedHashMap<>();
		sendMessage.put("reference", "sendMessage");
		sendMessage.put("nickname", nickname);
		sendMessage.put("targetNickname", targetNickname);
		sendMessage.put("message", message);
		sendMessage.put("target", target);

		new TcpService().client(target, nickname + ";" + message);
		Notification.chatMessageInformer().
			notifyChange(sendMessage);
	}

	public ChatUser newChatUser(String name, String nick, byte[] icon) {
		return new ChatUser(name, nick, icon);
	}

	public boolean isCurrentUserDefined() {
		return PersistenceContext.repositories().chatUsers().hasOneChatUser();
	}

	public ChatUser systemChatUser() {
		if (isCurrentUserDefined()) {
			return PersistenceContext.repositories().chatUsers().getChatUser();
		} else {
			return null;
		}

	}

	public String systemName() {
		return System.getProperty("user.name");
	}

	public void persistChatUser(String name, String nick, byte[] icon) throws DataIntegrityViolationException {
		PersistenceContext.repositories().chatUsers().
			add(newChatUser(name, nick, icon));
	}

	public void stop() {
		udpService.sendOfflineState();

		udpService.stop();
		tcpService.stop();

	}

	public ArrayList<ChatMessage> chatUserHistory() {
		ChatUser tmp = systemChatUser();
		if (tmp != null) {
			return tmp.history();
		} else {
			return new ArrayList();
		}
	}

	public void addMessage(String message, String fromIP, MessageType type) {
		ChatUser tmp = systemChatUser();
		if (tmp != null) {
			tmp.newChatMessage(fromIP, message, type);
			PersistenceContext.repositories().chatUsers().save(tmp);
		}
	}

}
