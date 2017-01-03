package csheets.ext.game.ui;

import csheets.ext.NetworkManager;
import csheets.ext.game.controllers.GameController;
import csheets.notification.Notifier;
import csheets.support.ThreadManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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

	String continuousTarget;

	List<String> connectedInstances;

	GameController controller;

	// Empty constructor
	public TcpService() {
	}

	public TcpService(GameController controller) {
		connectedInstances = new ArrayList<>();
		this.controller = controller;
	}

	/**
	 * Initializes a server following the UDP protocol.
	 *
	 * @param port The server port, customized by the user.
	 */
	public void server(int port) {
		ThreadManager.create("ipc.game-tcpServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.tcp();

								 server.expect(":game-request", new Action() {
											   @Override
											   public void run(Request request) {
												   String message = request.
													   message() + " with " + request.
													   hostname();
												   setContinuousTarget(request.
													   hostname() + ":" + request.
													   port() + ":" + request.
													   from());
												   String destination = server.
													   target(request.from());

												   int reply = JOptionPane.
													   showConfirmDialog(null, message);

												   switch (reply) {
													   case JOptionPane.YES_OPTION: {
														   server.
															   send(":game-reply", destination, "TRUE");
														   break;
													   }
													   case JOptionPane.NO_OPTION: {
														   server.
															   send(":game-reply", destination, "FALSE");
														   break;
													   }
													   default:
														   server.
															   send(":game-reply", destination, "FALSE");
														   break;
												   }
											   }
										   });
								 server.expect(":game-reply", new Action() {
											   @Override
											   public void run(Request request) {
												   notifyChange(request.
													   message());
											   }
										   });

								 server.
									 expect(":game-stopped", new Action() {
											@Override
											public void run(Request request) {
												JOptionPane.
													showMessageDialog(null, "Game has been stopped.");
												controller.stopCurrentGame();
											}
										});
								 server.
									 expect(":game-update", new Action() {
											@Override
											public void run(Request request) {
												notifyChange(request.message());
											}
										});

							 }
						 });

		ThreadManager.run("ipc.game-tcpServer");
	}

	public void setContinuousTarget(String target) {
		continuousTarget = target;
	}

	/**
	 * Initializes a client following the TCP protocol.
	 *
	 * @param target The target IPv4:Port
	 * @param message Message to send to the target.
	 */
	public void client(String target, String message) {
		ThreadManager.create("ipc.game-tcpClient", new Thread() {
							 @Override
							 public void run() {
								 new TcpClient(0).
									 send(":game-request", target, message);
							 }
						 });

		ThreadManager.run("ipc.game-tcpClient");
	}

	public void continuousSending(String message) {
		ThreadManager.create("ipc.game-continuousTcpClient", new Thread() {
							 @Override
							 public void run() {
//								 new TcpClient(0).
//									 send(":game-request", continuousTarget, message);
								 int reply = JOptionPane.
									 showConfirmDialog(null, "::. Receive information .::\n"
													   + "A host " + continuousTarget + " wants to play "
													   + " with you.\n Game: " + message + " Do you wish to play with him ?");

								 if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CANCEL_OPTION) {
									 return;
								 }

								 if (reply == JOptionPane.YES_OPTION) {
									 JOptionPane.
										 showMessageDialog(null, "Opponent: " + continuousTarget + "\n" + "Game: " + message);

								 }

							 }
						 });
		ThreadManager.run("ipc.game-continuousTcpClient");
	}

	/**
	 * End game.
	 */
	public void stopContinuousSending() {
		ThreadManager.create("ipc.game-continuousTcpClient", new Thread() {
							 @Override
							 public void run() {
								 String[] attr = continuousTarget.split(":");
								 String target = attr[0] + ":" + attr[1];
								 new TcpClient(0).
									 send(":game-stopped", target, "STOP");

							 }
						 });
		ThreadManager.run("ipc.game-continuousTcpClient");
	}

	/**
	 * Update Active game list.
	 *
	 * @param target the target
	 */
	public void updateOpponent(String target) {
		ThreadManager.create("ipc.game-opponetTcpClient", new Thread() {
							 @Override
							 public void run() {
								 new TcpClient(0).
									 send(":game-update", target, "update");

							 }
						 });
		ThreadManager.run("ipc.game-opponentTcpClient");
	}

	/**
	 * Stops all the TCP services.
	 */
	public void stop() {
		server.shutdown();
		ThreadManager.destroy("ipc.game-tcpServer");
		ThreadManager.destroy("ipc.game-tcpClient");
	}

	public String getTargetIP() {
		String[] attr = continuousTarget.split(":");
		return attr[2];
	}
}
