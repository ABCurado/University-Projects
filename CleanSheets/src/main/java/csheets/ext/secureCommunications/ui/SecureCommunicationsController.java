package csheets.ext.secureCommunications.ui;

import csheets.AppSettings;
import csheets.ext.NetworkManager;
import csheets.ext.secureCommunications.IncomingChannel;
import csheets.ext.secureCommunications.OutgoingChannel;
import java.util.Observer;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.Volt;
import vendor.volt.channels.MessageEncryptionChannel;
import vendor.volt.channels.MessageReceivedChannel;
import vendor.volt.channels.MessageSentChannel;
import vendor.volt.protocols.tcp.TcpServer;
import vendor.volt.protocols.udp.UdpClient;
import vendor.volt.protocols.udp.UdpServer;

public class SecureCommunicationsController {

	/**
	 * Starts the service of the current extension.
	 *
	 * @param observer User Interface to observe the communications.
	 */
	public void startServices(Observer observer) {

		UdpServer udp = NetworkManager.udp();

		Volt.
			channel("*", new MessageReceivedChannel("Incoming from ", observer),
					new MessageSentChannel("Sent from ", observer));

		udp.expect(":secure-communication", new Action() {
			@Override
			public void run(Request request) {
			}
		});

		TcpServer tcp = NetworkManager.tcp();

		tcp.channel("*", new MessageReceivedChannel("Incoming from ", observer),
					new MessageSentChannel("Sent from ", observer));

		tcp.expect(":secure-communication", new Action() {
			@Override
			public void run(Request request) {

			}
		});

		analyser(observer);
	}

	/**
	 * Sends a message to all other CleanSheets instances in the current
	 * network.
	 *
	 * @param observer User Interface to observe the communications.
	 * @param message Message to be sent.
	 * @param secure True if the message is to be sent encrypted, false
	 * otherwise.
	 */
	public void send(Observer observer, String message, boolean secure) {
		UdpClient client = new UdpClient(0);

		if (secure) {
			client.client().channel(":secure-communication",
									new MessageEncryptionChannel(AppSettings.
										instance().getApplicationKey()));
		}
		client.send(":secure-communication", "all:" + AppSettings.instance().
					get("UDP_PORT"), message);
	}

	/**
	 * Checks if the message is Incomming,Outgoing,Incomming Secure or Outgoing
	 * Segure
	 *
	 * @param observer User Interface to observe the communications.
	 */
	public void analyser(Observer observer) {
		Volt.channel("*", new IncomingChannel(AppSettings.instance().
					 getApplicationKey(), observer),
					 new OutgoingChannel(AppSettings.instance().
						 getApplicationKey(), observer)
		);

	}

	/**
	 * change the unit to be used
	 *
	 * @param range meedia of values in bars
	 * @return unit
	 */
	public String unitToBeUsed(int range) {
		if (range < 50) {
			return "bytes";
		} else if (range < 100) {
			return "kilobytes";
		} else if (range < 150) {
			return "megabytes";
		} else {
			return "gigabytes";
		}

	}

}
