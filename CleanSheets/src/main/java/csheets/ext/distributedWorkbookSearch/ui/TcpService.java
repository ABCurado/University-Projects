package csheets.ext.distributedWorkbookSearch.ui;

import csheets.ext.NetworkManager;
import csheets.ext.distributedWorkbookSearch.WorkBookDTO;
import csheets.framework.ObjectSerialization;
import csheets.notification.Notifier;
import csheets.support.ThreadManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.tcp.TcpClient;
import vendor.volt.protocols.tcp.TcpServer;

/**
 * This service allows to easily set up an run the TCP protocol.
 */
public class TcpService extends Notifier {

	private static final int PORT = 55000;
	private static final String PERMISSION_REQUEST_HEADER = ":distributed-permission-request";
	private static final String PERMISSION_RESPONSE_HEADER = ":distributed-reply";
	private static final String SEARCH_REQUEST_HEADER = ":distributed-search";
	private static final String SEARCH_RESPONSE_HEADER = ":distributed-result";

	/**
	 * Server instance.
	 */
	private TcpServer server;

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 */
	public void server() {

		ThreadManager.create("ipc.distributed-tcpServer", new Thread() {
			@Override
			public void run() {
				server = NetworkManager.tcp();

				// Expect permission request (YES/NO)
				server.
					expect(PERMISSION_REQUEST_HEADER, new Action() {
						@Override
						public void run(Request request) {
							System.out.
							println("Received permission REQUEST");
							String message = request.message() + "\nFrom: " + request.
							hostname();
							String destination = server.target(request.from());
							// request.from() + ":" + AppSettings.instance().get("TCP_PORT");
							int reply = JOptionPane.
							showConfirmDialog(null, message);

							switch (reply) {
								case JOptionPane.YES_OPTION: {
									server.
									send(PERMISSION_RESPONSE_HEADER, destination, "TRUE");
									break;
								}
								case JOptionPane.NO_OPTION: {
									server.
									send(PERMISSION_RESPONSE_HEADER, destination, "FALSE");
									break;
								}
								default:
									server.
									send(PERMISSION_RESPONSE_HEADER, destination, "FALSE");
									break;
							}
						}
					});

				// expect permission response (TRUE/FALSE)
				server.
					expect(PERMISSION_RESPONSE_HEADER, new Action() {
						@Override
						public void run(Request request) {
							System.out.
							println("Received permission RESPONSE");
							String response = request.message();
							if (response.equalsIgnoreCase("TRUE")) {
								// POSITIVE RESPONSE: initialize search
								String target = server.target(request.from());
								notifyChange(target);
								//searchWorkbookOnTarget(target, pattern);
							}
						}
					});

				server.
					expect(SEARCH_REQUEST_HEADER, new Action() {
						@Override
						public void run(Request request) {
							System.out.println("Received search REQUEST");
							String[] search = new String[3];
							search[0] = "Search";
							search[1] = request.message();
							search[2] = server.target(request.from());
							notifyChange(search);
						}
					});

				server.
					expect(SEARCH_RESPONSE_HEADER, new Action() {
						@Override
						public void run(Request request) {
							System.out.println("Received a new result");
							String message = request.message();
							WorkBookDTO deSerializedObject;
							try {
								deSerializedObject = (WorkBookDTO) ObjectSerialization.
								fromString(message);
							} catch (IOException | ClassNotFoundException ex) {
								Logger.getLogger(TcpService.class.getName()).
								log(Level.SEVERE, null, ex);
								return;
							}
							Map<String, Object> searchResult = new HashMap<>();
							searchResult.put("instance", request.hostname());
							searchResult.put("dto", deSerializedObject);
							notifyChange(searchResult);
						}
					});

			}
		}
		);

		ThreadManager.run(
			"ipc.distributed-tcpServer");
	}

	/**
	 * Asks for search permission to given target host.
	 *
	 * @param target The target IPv4:Port
	 * @param message Message to send to the target.
	 */
	public void requestPermission(String target, String message) {
		ThreadManager.
			create("ipc.distributed-tcpClient", new Thread() {
				@Override
				public void run() {
					System.out.
					println("Sending PERMISSION_REQUEST_HEADER to " + target);
					new TcpClient(0).
					send(PERMISSION_REQUEST_HEADER, target, message);
				}
			});

		ThreadManager.run("ipc.distributed-tcpClient");
	}

	/**
	 * Sends search pattern to the target host
	 *
	 * @param target The target IPv4:Port
	 * @param message Message to send to the target.
	 */
	public void searchWorkbookOnTarget(String target, String message) {
		ThreadManager.
			create("ipc.distributed-searchTcpClient", new Thread() {
				@Override
				public void run() {
					System.out.println("Sending SEARCH_REQUEST_HEADER");
					new TcpClient(0).
					send(SEARCH_REQUEST_HEADER, target, message);
				}
			});

		ThreadManager.run("ipc.distributed-searchTcpClient");
	}

	/**
	 * Initializes a client following the TCP protocol.
	 *
	 * @param target The target IPv4:Port
	 * @param message Message to send to the target.
	 */
	public void sendSearchResult(String target, String message) {
		ThreadManager.
			create("ipc.distributed-resultTcpClient", new Thread() {
				@Override
				public void run() {
					System.out.println("Sending SEARCH_RESPONSE_HEADER");
					new TcpClient(0).
					send(SEARCH_RESPONSE_HEADER, target, message);
				}
			});

		ThreadManager.run("ipc.distributed-resultTcpClient");
	}

	/**
	 * Returns if the server is currently working.
	 *
	 * @return True if the server is active, false otherwise.
	 */
	public boolean isActive() {
		return server.isActive();
	}

	/**
	 * Stops all the TCP services.
	 */
	public void stop() {
		ThreadManager.destroy("ipc.distributed-tcpServer");
		ThreadManager.destroy("ipc.distributed-tcpClient");
		ThreadManager.destroy("ipc.distributed-searchTcpClient");
		ThreadManager.destroy("ipc.distributed-resultTcpClient");
	}
}
