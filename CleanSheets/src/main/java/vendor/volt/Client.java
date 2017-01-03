package vendor.volt;

/**
 * This class represents a client that allows to quickly send a message to a
 * Volt server instance.
 *
 * @author Renato Machado
 */
public abstract class Client {
    
    /**
     * Client server.
     */
    private final Server client;
    
    /**
     * Client port.
     */
    private final int port;
    
    /**
     * Creates a new Client.
     * 
     * @param client Client instance.
     * @param port Network port.
     */
    public Client(Server client, int port)
    {
        if (port < 0 || port > 49151) {
            throw new IllegalArgumentException("Invalid port was defined. Please select a valid port.");
        }
        
        this.client = client;
        this.port = port;
    }
    
    /**
     * Gets the client.
     *
     * @return Client instance.
     */
    public Server client() {
        return this.client;
    }
    
    /**
     * Sends a message, interpreted by a given route, for a target.
     * 
     * @param route Route the message is targeted for.
     * @param target IPv4:Port of the target.
     * @param message Message.
     */
    public void send(String route, String target, String message) {
        this.client.boot(this.port);
        this.client.send(route, target, message);
        this.client.shutdown();
    }
    
    /**
     * Returns the target IPv4:Port.
     * 
     * @param ip IP address.
     * @param port Service port.
     * @return IPv4:Port.
     */
    public String target(String ip, String port)
    {
        return this.target(ip, Integer.valueOf(port));
    }
    
    /**
     * Returns the target IPv4:Port.
     *
     * @param ip IP address.
     * @param port Service port.
     * @return IPv4:Port.
     */
    public String target(String ip, int port)
    {
        return ip + ":" + port;
    }

}
