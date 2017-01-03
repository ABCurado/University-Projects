/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chat;

import csheets.ext.chat.domain.Message;
import csheets.ext.chat.domain.Room;
import csheets.ext.chat.domain.User;
import csheets.notification.Notification;
import csheets.persistence.PersistenceContext;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class ChatController {

	private UIController uiController;
	private UdpService udpService;
	private TcpService tcpService;
	private User user;
	private Map<String, User> users;
	private Map<String, Room> rooms;

	public ChatController(UIController uiController) {
		String name = System.getProperty("user.name");
		this.user = PersistenceContext.repositories().users().findName(name);
		if (this.user == null) {
			this.user = new User(name, "myNick", null, User.State.ONLINE);
			this.user = PersistenceContext.repositories().users().
				save(this.user);
		}
		this.users = new HashMap();
		this.rooms = new HashMap();
		this.uiController = uiController;
		this.udpService = new UdpService();
		this.udpService.user(this.user);
		this.udpService.client(5);
		this.udpService.server();
		this.udpService.controller(this);
		this.tcpService = new TcpService();
		this.tcpService.server();
	}

	public Color stateColor(User.State state) {
		if (state == User.State.ONLINE) {
			return new Color(9, 80, 208, 255);
		} else if (state == User.State.AWAY) {
			return new Color(255, 255, 0, 255);
		} else {
			return new Color(255, 102, 102, 255);
		}
	}

	public String name() {
		return this.user.name();
	}

	public void nickname(String nickname) {
		if (nickname != null && !nickname.isEmpty() && !this.user.nickname().
			equals(nickname)) {
			this.user.nickname(nickname);
			this.saveUser();
		}
	}

	public String nickname() {
		return this.user.nickname();
	}

	public void state(User.State state) {
		if (state != null && !this.user.state().equals(state)) {
			this.user.state(state);
			this.saveUser();
		}
	}

	public User.State state() {
		return this.user.state();
	}

	public void image(byte[] image) {
		if (!this.user.image().equals(image)) {
			this.user.image(image);
			this.saveUser();
		}
	}

	public byte[] image() {
		return this.user.image();
	}

	public void saveUser() {
		//this.user = PersistenceContext.repositories().users().save(this.user);
		this.udpService.user(this.user);
		Notification.chatMessageInformer().notifyChange(this.user);
	}

	public Iterable<User> users() {
		//return PersistenceContext.repositories().users().all();
		return this.users.values();
	}

	public Iterable<Room> rooms() {
		//return PersistenceContext.repositories().rooms().all();
		return this.rooms.values();
	}

	public User addUser(String name, String nick, String status, String image,
						String target) {
		/*
		if (name.equals(this.user.name())) {
			return this.user;
		}
		 */
		User user = this.users.get(name); //PersistenceContext.repositories().users().findName(name);
		byte[] img = null;
		if (image != null && !image.isEmpty()) {
			img = image.getBytes();
		}
		User.State stts = User.State.ONLINE;
		if (status != null && !status.isEmpty()) {
			if (status.equals(User.State.AWAY)) {
				stts = User.State.AWAY;
			} else if (status.equals(User.State.OFFLINE)) {
				stts = User.State.OFFLINE;
			}
		}
		if (user == null) {
			user = new User(name, nick, img, stts);
		}
		user.nickname(nick);
		user.state(stts);
		user.image(img);
		user.target(target);
		this.users.put(name, user);
		//user = PersistenceContext.repositories().users().save(user);
		Notification.chatMessageInformer().notifyChange(user);
		return user;
	}

	public Room addRoom(String name, String creator, boolean isPrivate) {
		/*
		Room room = this.rooms.get(name + creator); //PersistenceContext.repositories().rooms().findName(name);
		if (room != null && room.name().equals(name)) {
			return room;
		}
		 */
		Room.Type type = Room.Type.PUBLIC;
		if (isPrivate) {
			type = Room.Type.PRIVATE;
		}
		User user = null;
		if (creator == null) {
			user = this.user;
		} else {
			user = this.users.get(creator);//PersistenceContext.repositories().users().findName(creator);
		}
		if (user == null) {
			return null;
		}
		Room room = new Room(name, user, type);
		//PersistenceContext.repositories().rooms().save(room);
		this.rooms.put(name, room);
		Notification.chatMessageInformer().notifyChange(room);
		return room;
	}

	public Iterable<Message> getMessages(String host, String name,
										 Message.Type type) {
		return PersistenceContext.repositories().messages().
			messages(host, name, type);
	}

	public void sendMessage(String host, String name, Message.Type type,
							String message) {
		if (type == Message.Type.USER) {
			User user = PersistenceContext.repositories().users().findName(host);
			if (user != null) {
				this.tcpService.sendMessage(user, message);
			}
		} else if (type == Message.Type.ROOM) {
			Room room = PersistenceContext.repositories().rooms().findName(host);
			if (room != null) {
				this.tcpService.sendMessage(room, message);
			}
		}
	}

	public void sendMessage(User user, String message) {
		this.tcpService.sendMessage(user.target(), "sendMessageUser;" + user.
									name() + ";" + message);
	}

	public void sendMessage(Room room, String message) {
		this.tcpService.
			sendMessage(room.creator().target(), "sendMessageRoom;" + room.
						name() + ";" + room.creator().name() + ";" + message);
	}

}
