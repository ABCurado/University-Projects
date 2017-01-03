package vendor.volt.protocols.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * This class abstracts the Datagram Socket to allow to easily mock the object in
 * order to test.
 * 
 * @author Renato Machado
 */
public class UdpSocket extends DatagramSocket {
    
    public UdpSocket() throws SocketException
    {
        super();
    }
    
    public UdpSocket(int port) throws SocketException
    {
        super(port);
    }
}
