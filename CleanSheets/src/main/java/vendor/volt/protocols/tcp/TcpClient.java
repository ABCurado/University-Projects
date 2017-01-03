package vendor.volt.protocols.tcp;

import vendor.volt.Client;

/**
 * This class represents a client that allows to quickly send a message to
 * a Volt server instance.
 * 
 * @author Renato Machado
 */
public class TcpClient extends Client {
   
    /**
     * Creates a new TCP Client using a dynamic port.
     */
    public TcpClient() {
        super(new TcpServer(), 0);
    }
    
    /**
     * Creates a new TCP Client.
     * 
     * @param port Client port. Use 0 to use a dynamic port.
     */
    public TcpClient(int port) {
        super(new TcpServer(), port);
    }
    
    /**
     * Sends a given set of headers with a message to the given target.
     *
     * @param headers Set of headers separated by ";". This headers follow a
     * strict structure:
     *
     * route;encrypted
     *
     * Route: (String) Gives the target route to where this message will land.
     * Encrypted: (Boolean) True if the embedded message is currently encrypted.
     *
     * @param target Target defined by IPv4:Port.
     * @param message Message.
     */
    @Override
    public void send(String headers, String target, String message) {
        // Redeclared method just to override the documentation.
        super.send(headers, target, message);
    }
}
