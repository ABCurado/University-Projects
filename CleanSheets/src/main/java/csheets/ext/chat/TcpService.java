/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chat;

import csheets.ext.NetworkManager;
import csheets.ext.chat.domain.Room;
import csheets.ext.chat.domain.User;
import csheets.notification.Notification;
import csheets.support.ThreadManager;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.tcp.TcpClient;
import vendor.volt.protocols.tcp.TcpServer;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class TcpService {

	/**
	 * Server instance.
	 */
	private TcpServer server;

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 */
	public void server() {
		ThreadManager.create("ipc.chat2-tcpServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.tcp();

								 server.
									 expect(":chatSendMessage", new Action() {
											@Override
											public void run(Request request) {
												if (request.same()) {
													return;
												}
												Notification.
													chatMessageInformer().
													notifyChange(request.
														message());
											}
										});
							 }
						 });

		ThreadManager.run("ipc.chat2-tcpServer");
	}

	void sendMessage(String target, String message) {
		new TcpClient().send(":chatSendMessage", target, message);
	}

	/**
	 * Initializes a client following the TCP protocol.
	 *
	 * @param target The target IPv4:Port
	 * @param message Message to send to the target.
	 */
	public void client(String target, String message) {
		ThreadManager.create("ipc.chat2-tcpClient", new Thread() {
							 @Override
							 public void run() {
								 new TcpClient(0).
									 send(":chat2", target, message);
							 }
						 });

		ThreadManager.run("ipc.chat2-tcpClient");
	}

	public void sendMessage(Room room, String message) {
		ThreadManager.create("ipc.chatSendMessageRoom", new Thread() {
							 @Override
							 public void run() {
								 String data = "messageRoom;" + room.name() + ";" + message;
								 new TcpClient(0).
									 send(":chatTCP", room.
										  creator().target(), data);
							 }
						 });
		ThreadManager.run("ipc.chatSendMessageRoom");
	}

	public void sendMessage(User user, String message) {
		ThreadManager.create("ipc.chatSendMessageUser", new Thread() {
							 @Override
							 public void run() {
								 String data = "messageUser;" + user.name() + ";" + message;
								 System.out.println("ENVIEI1 = " + data);
								 new TcpClient(0).
									 send(":chatSendMessageUser", user.target(), data);
							 }
						 });
		ThreadManager.run("ipc.chatSendMessageUser");
	}

	/**
	 * Stops all the TCP services.
	 */
	public void stop() {
		server.neglect(":chat2");
		ThreadManager.destroy("ipc.chat2-tcpClient");
		ThreadManager.destroy("ipc.chat2-tcpServer");
	}

}
