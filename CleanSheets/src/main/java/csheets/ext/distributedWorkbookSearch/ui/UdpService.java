package csheets.ext.distributedWorkbookSearch.ui;

import csheets.AppSettings;
import csheets.ext.NetworkManager;
import csheets.notification.Notifier;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import java.util.ArrayList;
import java.util.List;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.udp.UdpClient;
import vendor.volt.protocols.udp.UdpServer;

/**
 * This service allows to easily set up an run the UDP protocol.
 */
public class UdpService extends Notifier {

	private static final String BROADCAST_HEADER = ":distributed-broadcast";
	private static final String PORT_HEADER = ":distributed-port";
	private static final int BROADCAST_TIMEOUT = 20;

	private List<String> foundInstances;

	/**
	 * Server instance.
	 */
	private UdpServer server;

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 */
	public void server() {

		ThreadManager.create("ipc.distributed-udpServer", new Thread() {
			@Override
			public void run() {
				server = NetworkManager.udp();
				foundInstances = new ArrayList<>();

				server.
					expect(BROADCAST_HEADER, new Action() {
						@Override
						public void run(Request request) {
//							if (request.same()) {
//								return;
//							}
							// Destination = Target's IP and Port
							String destination = server.target(request.from());
							System.out.
							println("Received BROADCAST_HEADER from " + destination);
							System.out.println("Sending PORT_HEADER");
							server.
							send(PORT_HEADER, destination, AppSettings.
								 instance().
								 get("TCP_PORT"));
						}
					});

				server.
					expect(PORT_HEADER, new Action() {
						@Override
						public void run(Request request) {
							System.out.println("Received PORT_HEADER");
							List<String> ports = request.get("distributed-port");
							List<String> addresses = new ArrayList<>();
							System.out.println("Addresses that i got are:");
							for (String port : ports) {
								String address = request.from() + ":" + port;
								System.out.println(address);
								if (foundInstances.contains(address)) {
									break;
								}
								foundInstances.add(address);
								addresses.add(address);
							}

							notifyChange(addresses);
						}
					});

			}
		});

		ThreadManager.run("ipc.distributed-udpServer");
	}

	/**
	 * Initializes a client following the UDP protocol.
	 *
	 * @param seconds Time in seconds to send another request.
	 */
	public void client(int seconds) {
		ThreadManager.create("ipc.distributed-udpClient", new Thread() {
			@Override
			public void run() {
				UdpClient client = new UdpClient(0);

				Task broadcast = new Task() {
					@Override
					public void fire() {
						System.out.println("Sending BROADCAST_HEADER");
						client.
							send(BROADCAST_HEADER, "all:" + AppSettings.
								 instance().get("UDP_PORT"), "check");
					}
				};

				TaskManager manager = new TaskManager();

				manager.after(3).every(seconds).fire(broadcast);
				manager.after(BROADCAST_TIMEOUT).once(new Task() {
					@Override
					public void fire() {
						broadcast.kill();
					}
				});
			}
		});

		ThreadManager.run("ipc.distributed-udpClient");
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
	 * Stops all the UDP services.
	 */
	public void stop() {
		server.shutdown();
		ThreadManager.destroy("ipc.distributed-udpServer");
		ThreadManager.destroy("ipc.distributed-udpClient");
	}

}
