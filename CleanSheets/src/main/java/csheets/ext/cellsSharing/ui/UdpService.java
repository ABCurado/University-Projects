package csheets.ext.cellsSharing.ui;

import csheets.AppSettings;
import csheets.ext.NetworkManager;
import csheets.notification.Notifier;
import csheets.support.Task;
import csheets.support.TaskManager;
import csheets.support.ThreadManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.udp.UdpClient;
import vendor.volt.protocols.udp.UdpServer;

/**
 * This service allows to easily set up an run the UDP protocol.
 */
public class UdpService extends Notifier {

    /**
     * Server instance.
     */
    private UdpServer server;

    /**
     * Initializes a server following the UDP protocol.
     *
     */
    public void server() {
        ThreadManager.create("ipc.share-cells-udpServer", new Thread() {
            @Override
            public void run() {
                server = NetworkManager.udp();

                server.
                        expect(":share-cells-broadcast", new Action() {
                            @Override
                            public void run(Request request) {
                                
                                if (request.same()) {
                                    return;
                                }
                                
                                // Destination = Target's IP and Port
                                String destination = server.target(request.from());

                                server.
                                        send(":share-cell-port", destination, AppSettings.
                                                instance().
                                                get("TCP_PORT"));
                            }
                        });

                server.
                        expect(":share-cell-port", new Action() {
                            @Override
                            public void run(Request request) {
                                List<String> ports = request.get("share-cell-port");

                                List<String> addresses = new ArrayList<>();

                                for (String port : ports) {
                                    addresses.
                                            add(request.from() + ":" + port);
                                }

                                notifyChange(addresses);
                            }
                        });
            }
        });

        ThreadManager.run("ipc.share-cells-udpServer");
    }

    /**
     * Initializes a client following the UDP protocol.
     *
     * @param seconds Time in seconds to send another request.
     */
    public void client(int seconds) {
        ThreadManager.create("ipc.share-cells-udpClient", new Thread() {
            @Override
            public void run() {
                UdpClient client = new UdpClient(0);

                Task broadcast = new Task() {
                    @Override
                    public void fire() {
                        client.
                                send(":share-cells-broadcast", "all:" + AppSettings.
                                        instance().get("UDP_PORT"), "check");
                    }
                };

                TaskManager manager = new TaskManager();

                manager.after(4).every(seconds).fire(broadcast);
            }
        });

        ThreadManager.run("ipc.share-cells-udpClient");
    }

    /**
     * Stops all the UDP services.
     */
    public void stop() {
        server.shutdown();
        ThreadManager.destroy("ipc.share-cells-udpServer");
        ThreadManager.destroy("ipc.share-cells-udpClient");
    }

}
