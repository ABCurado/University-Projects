package csheets.ext.chatApp.application;

import csheets.ext.NetworkManager;
import csheets.notification.Notification;
import csheets.notification.Notifier;
import csheets.support.ThreadManager;
import java.util.LinkedHashMap;
import java.util.Map;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.tcp.TcpClient;
import vendor.volt.protocols.tcp.TcpServer;

/**
 * This service allows to easily set up an run the TCP protocol.
 */
public class TcpService extends Notifier {

	/**
	 * Server instance.
	 */
	private TcpServer server;

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 */
	public void server() {
		ThreadManager.create("ipc.chat-tcpServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.tcp();

								 server.expect(":chat", new Action() {
											   @Override
											   public void run(Request request) {
												   Map<String, String> mapMessage = new LinkedHashMap<>();
												   mapMessage.
													   put("reference", "chatMessage");
												   mapMessage.
													   put("nickname", request.
														   message().split(";")[0]);
												   mapMessage.
													   put("from", request.
														   from());
												   mapMessage.
													   put("message", request.
														   message().split(";")[1]);

												   Notification.
													   chatMessageInformer().
													   notifyChange(mapMessage);
											   }
										   });
							 }
						 });

		ThreadManager.run("ipc.chat-tcpServer");
	}

	/**
	 * Initializes a client following the TCP protocol.
	 *
	 * @param target The target IPv4:Port
	 * @param message Message to send to the target.
	 */
	public void client(String target, String message) {
		ThreadManager.create("ipc.chat-tcpClient", new Thread() {
							 @Override
							 public void run() {
								 new TcpClient(0).
									 send(":chat", target, message);
							 }
						 });

		ThreadManager.run("ipc.chat-tcpClient");
	}

	/**
	 * Stops all the TCP services.
	 */
	public void stop() {
		server.neglect(":chat");
		ThreadManager.destroy("ipc.chat-tcpClient");
		ThreadManager.destroy("ipc.chat-tcpServer");
	}
}
