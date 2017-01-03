package vendor.volt.protocols.udp;

import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.Server;
import vendor.volt.Volt;
import vendor.volt.channels.MessageDecryptionChannel;
import vendor.volt.channels.MessageEncryptionChannel;
import vendor.volt.support.Task;
import vendor.volt.support.TaskManager;

public class UdpTest {

    public static void main(String[] args) {

        Volt.debug();
        Server server = Volt.udp(30600);

        server.channel("*", new MessageDecryptionChannel("AcklWq203FgSSVgH"));
        
        server.expect(":hello", new Action() {
            @Override
            public void run(Request request) {
                System.out.println(request.get("hello").get(0));
            }
        });
        
        server.expect(":broadcast", new Action() {
            @Override
            public void run(Request request) {

                // Improved same method.
                if (request.same()) {
                    return;
                }

                System.out.println("Message: " + request.message());
                System.out.println("From: " + request.from());
                System.out.println("Port: " + request.port());
                System.out.println("Requester: " + request.requester());
                System.out.println("Hostname: " + request.hostname());
                System.out.println("Username: " + request.username());
                System.out.println("Route: " + request.route());
                System.out.println("Length: " + request.length());
                System.out.println("Packets: " + request.packets());
                System.out.println("\n\n");
                
                server.send(":hello", server.target(request.from()), "Hello there.");
            }
        });

        final UdpClient client = new UdpClient(0);
        
        client.client().channel("*", new MessageEncryptionChannel("AcklWq203FgSSVgH"));
        
        final TaskManager tm = new TaskManager();
        
        tm.after(2).every(3).fire(new Task() {
            public void fire() {
                System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
                
                client.send(":broadcast", "all:30600", "Hello from Volt.");
                client.send(":broadcast", "all:30600", "Hello from Volt. Hello from Volt. Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt.Hello from Volt. YOOOOO");
            }
        })
                .after(20).once(new Task() {
                    public void fire() {
                        Volt.kill(30600);
                        tm.destroy();
                    }
                }
                );

    }

}
