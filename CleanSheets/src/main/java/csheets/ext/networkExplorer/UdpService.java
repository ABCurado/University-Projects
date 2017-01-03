/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.networkExplorer;

import csheets.AppSettings;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ext.Extension;
import csheets.ext.NetworkManager;
import csheets.ext.chat.domain.User;
import csheets.notification.Notification;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.util.LinkedHashMap;
import java.util.Map;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.udp.UdpClient;
import vendor.volt.protocols.udp.UdpServer;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class UdpService {

	/**
	 * Server instance.
	 */
	private UdpServer server;
	private UdpClient client;
	private User user;

	public void user(User user) {
		this.user = user;
	}

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 * @param chatUserNickname Username
	 * @param chatUserIcon User icon
	 */
	public void server() {
		ThreadManager.create("ipc.networkExplorer-udpServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.udp();

								 server.
									 expect(":networkExplorer-broadcast", new Action() {
											@Override
											public void run(Request request) {
												/*if (request.same()) {
													return;
												}*/
												String destination = server.
													target(request.from());
												UIController uiController = UIController.
													getUIController();
												if (uiController != null) {
													String data = AppSettings.
														instance().
														get("TCP_PORT") + "|";
													for (UIExtension uiExtension : uiController.
														getExtensions()) {
														Extension extension = uiExtension.
															getExtension();
														data += "Extension-_-";
														data += extension.
															getName() + "-_-";
														data += extension.
															isEnabled() + "-_-";
														data += extension.
															getVersion() + "-_-";
														data += extension.
															getDescription() + ";";
													}
													for (Workbook workbook : uiController.
														workbooks()) {
														data += "Workbook-_-" + workbook.
															getParentFileName() + ";";
														for (Spreadsheet spreadsheet : workbook) {
															data += "Spreadsheet-_-" + spreadsheet.
																getTitle() + ";";
														}
													}
													data = data.
														substring(0, data.
																  length() - 1);
													server.
														send(":networkExplorer-port|:networkExplorer-info", destination, data);
												}
											}
										});

								 server.
									 expect(":networkExplorer-port|:networkExplorer-info", new Action() {
											@Override
											public void run(Request request) {
												/*if (request.same()) {
													return;
												}*/
												Map<String, String> hostInformations = new LinkedHashMap<>();
												hostInformations.
													put("reference", "AppInfo");
												hostInformations.
													put("ip", request.from());
												hostInformations.
													put("hostname", request.
														hostname());
												hostInformations.
													put("port", request.
														get("networkExplorer-port").
														get(0));
												hostInformations.
													put("info", request.
														get("networkExplorer-info").
														get(0));
												Notification.exploreInformer().
													notifyChange(hostInformations);
											}
										});
							 }
						 });

		ThreadManager.run("ipc.networkExplorer-udpServer");

	}

	/**
	 * Initializes a client following the UDP protocol.
	 *
	 * @param seconds Time in seconds to send another request.
	 */
	public void client(int seconds) {
		ThreadManager.create("ipc.networkExplorer-udpClient", new Thread() {
							 @Override
							 public void run() {
								 client = new UdpClient(0);
								 Task broadcast = new Task() {
									 @Override
									 public void fire() {
										 client.
											 send(":networkExplorer-broadcast", "all:" + AppSettings.
												  instance().get("UDP_PORT"), "check");
									 }
								 };
								 TaskManager manager = new TaskManager();
								 manager.after(1).every(seconds).
									 fire(broadcast);
							 }
						 });
		ThreadManager.run("ipc.networkExplorer-udpClient");
	}

	/**
	 * Stops all the UDP services.
	 */
	public void stop() {
		server.neglect(":networkExplorer-broadcast");
		ThreadManager.destroy("ipc.networkExplorer-udpClient");
		ThreadManager.destroy("ipc.networkExplorer-udpServer");
	}

}
