package vendor.volt.protocols.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * This class abstracts the Datagram Packet to allow to easily mock the object
 * in order to test.
 * 
 * @author Renato Machado
 */
public class UdpPacket {

    /**
     * Packet.
     */
    private final DatagramPacket packet;
    
    public UdpPacket(byte[] buf, int length)
    {
        this.packet = new DatagramPacket(buf, length);
    }
    
    public UdpPacket(byte buf[], int length, InetAddress address, int port)
    {
        this.packet = new DatagramPacket(buf, length, address, port);
    }
    
    /**
     * Gets the packet.
     * 
     * @return Packet.
     */
    public DatagramPacket packet()
    {
        return this.packet;
    }
    
    /**
     * Gets the data of a packet.
     * 
     * @return Packet data.
     */
    public byte[] getData()
    {
        return this.packet.getData();
    }
    
    /**
     * Gets the address of a packet.
     * 
     * @return Packet address.
     */
    public InetAddress getAddress()
    {
        return this.packet.getAddress();
    }
    
    /**
     * Gets the port of a packet.
     * 
     * @return Packet port.
     */
    public int getPort()
    {
        return this.packet.getPort();
    }
}
