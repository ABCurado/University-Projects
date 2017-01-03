package vendor.volt.protocols.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.Server;
import vendor.volt.Volt;
import vendor.volt.support.Task;
import vendor.volt.support.TaskManager;

public class TcpServerTest {

    public static void main(String[] args) {
        Volt.debug();
        
        Server server = Volt.tcp(20000);
        
        server.expect(":message", new Action() {
            @Override
            public void run(Request request) {
                System.out.println(request.message());
                
                server.send(":hello", server.target(request.from()), "Hello Volt.");
            }
        });
        
        server.expect(":hello", new Action() {
            @Override
            public void run(Request request) {
                System.out.println(request.message());
            }
        });
        
        TcpClient client = new TcpClient();
        
        try {
            client.send(":message", InetAddress.getLocalHost().getHostAddress() + ":" + "20000", "Yoooooooooo");
        } catch (UnknownHostException ex) {
            Logger.getLogger(TcpServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TaskManager tm = new TaskManager();
        
        tm.after(5).once(new Task() {
            @Override
            public void fire() {
                Volt.kill(20000);
                tm.destroy();
            }
        });
    }

}
