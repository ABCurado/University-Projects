package csheets.ext.chatApp.application;

import csheets.AppSettings;
import csheets.ext.NetworkManager;
import csheets.notification.Notification;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import java.util.LinkedHashMap;
import java.util.Map;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.udp.UdpClient;
import vendor.volt.protocols.udp.UdpServer;

/**
 * This service allows to easily set up an run the UDP protocol.
 */
public class UdpService {

	/**
	 * Server instance.
	 */
	private UdpServer server;

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 * @param chatUserNickname Username
	 * @param chatUserIcon User icon
	 */
	public void server(String chatUserNickname, String chatUserIcon) {
		ThreadManager.create("ipc.chat-udpServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.udp();

								 server.expect(":offline", new Action() {
											   @Override
											   public void run(Request request) {

												   if (request.same()) {
													   return;
												   }
												   Map<String, String> info = new LinkedHashMap<>();

												   info.
													   put("requester", request.
														   from() + ":" + AppSettings.
														   instance().
														   get("TCP_PORT"));
												   info.
													   put("reference", "state");
												   Notification.
													   chatMessageInformer().
													   notifyChange(info);
											   }
										   });

								 server.expect(":chatbroadcast", new Action() {
											   @Override
											   public void run(Request request) {

												   if (request.same()) {
													   return;
												   }

												   // Destination = Target's IP and Port
												   String destination = server.
													   target(request.from());

												   final String finalMessage = AppSettings.
													   instance().
													   get("TCP_PORT") + "|" + chatUserNickname + "|" + chatUserIcon;

												   server.
													   send(":chat-port|:chat-nickname|:chat-icon", destination, finalMessage);

											   }
										   });

								 server.
									 expect(":chat-port|:chat-nickname|:chat-icon", new Action() {
											@Override
											public void run(Request request) {
												if (request.same()) {
													return;
												}

												Map<String, String> hostInformations = new LinkedHashMap<>();
												hostInformations.
													put("ip", request.from());
												hostInformations.
													put("port", request.
														get("chat-port").get(0));
												hostInformations.
													put("hostname", request.
														hostname());
												hostInformations.
													put("nickname", request.
														get("chat-nickname").
														get(0));
												hostInformations.
													put("icon", request.
														get("chat-icon").get(0));

												hostInformations.
													put("reference", "hosts");

												Notification.
													chatMessageInformer().
													notifyChange(hostInformations);
											}
										});

							 }
						 });

		ThreadManager.run("ipc.chat-udpServer");
	}

	/**
	 * Creates a udp client to send a "offline" status.
	 *
	 */
	public void sendOfflineState() {
		ThreadManager.create("ipc.chat-offline", new Thread() {
							 @Override
							 public void run() {
								 UdpClient client = new UdpClient(0);

								 Task broadcast = new Task() {
									 @Override
									 public void fire() {
										 client.
											 send(":offline", "all:" + AppSettings.
												  instance().get("UDP_PORT"), "check");
									 }
								 };

								 TaskManager manager = new TaskManager();

								 manager.after(1).fire(broadcast);
							 }
						 });

		ThreadManager.run("ipc.chat-offline");
	}

	/**
	 * Initializes a client following the UDP protocol.
	 *
	 * @param seconds Time in seconds to send another request.
	 */
	public void client(int seconds) {
		ThreadManager.create("ipc.chat-udpClient", new Thread() {
							 @Override
							 public void run() {
								 UdpClient client = new UdpClient(0);

								 Task broadcast = new Task() {
									 @Override
									 public void fire() {
										 client.
											 send(":chatbroadcast", "all:" + AppSettings.
												  instance().get("UDP_PORT"), "check");
									 }
								 };

								 TaskManager manager = new TaskManager();

								 manager.after(1).every(seconds).
									 fire(broadcast);
							 }
						 });

		ThreadManager.run("ipc.chat-udpClient");
	}

	/**
	 * Stops all the UDP services.
	 */
	public void stop() {
		server.neglect(":chat-port|:chat-nickname|:chat-icon");
		server.neglect(":chatbroadcast");
		server.neglect(":offline");

		ThreadManager.destroy("ipc.chat-udpClient");
		ThreadManager.destroy("ipc.chat-offline");
		ThreadManager.destroy("ipc.chat-udpServer");
	}

}
