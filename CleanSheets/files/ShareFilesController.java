/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.chat;

import csheets.AppSettings;
import csheets.ext.NetworkManager;
import csheets.notification.Notification;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.udp.UdpClient;
import vendor.volt.protocols.udp.UdpServer;

/**
 *
 * @author valhalla
 */
public class ShareFilesController {

	private UdpServer server;
	private String keyPathDownloads = "ipc.shereFiles-pathDownloads";
	private String keyPathFiles = "ipc.shereFiles-pathFiles";

	public void pathDownloads(String path) {
		AppSettings.instance().set(keyPathDownloads, path);
	}

	public void pathFiles(String path) {
		AppSettings.instance().set(keyPathFiles, path);
	}

	public void server() {
		ThreadManager.create("ipc.shereFiles-UDPServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.udp();
								 server.
									 expect(":shereFilesBroadcast", new Action() {
											@Override
											public void run(Request request) {
												/*
												   if (request.same()) {
													   return;
												   }
												 */

												// Destination = Target's IP and Port
												String destination = server.
													target(request.from());
												File folder = new File(AppSettings.
													instance().get(keyPathFiles));

												if (!folder.exists()) {
													new File(AppSettings.
														instance().
														get(keyPathFiles)).
														mkdir();
													folder = new File(AppSettings.
														instance().
														get(keyPathFiles));
												}

												String message = AppSettings.
													instance().
													get("TCP_PORT") + "|" + "files";

												File[] listOfFiles = folder.
													listFiles();

												if (listOfFiles != null && listOfFiles.length > 0) {
													for (File file : listOfFiles) {
														if (file.isFile()) {
															System.out.
																println(file.
																	getName());
															message += file.
																getName() + ";";
														}
													}
												}

												System.out.
													println("PATHDOWN = " + AppSettings.
														instance().
														get(keyPathFiles));

												server.
													send(":shereFiles-port|:shereFiles-files", destination, message);
											}

										});

								 server.
									 expect(":shereFiles-port|:shereFiles-files", new Action() {
											@Override
											public void run(Request request) {
												/*
												   if (request.same()) {
													   return;
												   }
												 */

												Map<String, String> hostInformations = new LinkedHashMap<>();
												hostInformations.
													put("reference", "files");
												hostInformations.
													put("ip", request.from());
												hostInformations.
													put("hostname", request.
														hostname());
												hostInformations.
													put("port", request.
														get("shereFiles-port").
														get(0));
												hostInformations.
													put("files", request.
														get("shereFiles-files").
														get(0));
												Notification.
													chatMessageInformer().
													notifyChange(hostInformations);
											}
										});

							 }
						 });
		ThreadManager.run("ipc.shereFiles-UDPServer");
	}

	/**
	 * Initializes a client following the UDP protocol.
	 *
	 * @param seconds Time in seconds to send another request.
	 */
	public void client(int seconds) {
		ThreadManager.create("ipc.shereFiles-UDPClient", new Thread() {
							 @Override
							 public void run() {
								 UdpClient client = new UdpClient(0);
								 Task broadcast = new Task() {
									 @Override
									 public void fire() {
										 client.
											 send(":shereFilesBroadcast", "all:" + AppSettings.
												  instance().get("UDP_PORT"), "check");
									 }
								 };
								 TaskManager manager = new TaskManager();
								 manager.after(1).every(seconds).fire(broadcast);
							 }
						 });
		ThreadManager.run("ipc.shereFiles-UDPClient");
	}

	/**
	 * Stops all the UDP services.
	 */
	public void stop() {
		server.shutdown();
		ThreadManager.destroy("ipc.shereFiles-UDPServer");
		ThreadManager.destroy("ipc.shereFiles-UDPClient");
	}

}
