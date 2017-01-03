package vendor.volt.protocols.udp;

import vendor.volt.Client;

/**
 * This class represents a client that allows to quickly send a message to
 * a Volt server instance.
 * 
 * @author Renato Machado
 */
public class UdpClient extends Client {
    
    /**
     * Creates a new UDP Client using a dynamic port.
     */
    public UdpClient()
    {
        super(new UdpServer(), 0);
    }
    
    /**
     * Creates a new UDP Client.
     *
     * @param port Client port. Use 0 to use a dynamic port.
     */
    public UdpClient(int port)
    {
        super(new UdpServer(), port);
    }
}
