package vendor.volt.protocols.tcp;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class abstracts the Datagram Socket to allow to easily mock the object
 * in order to test.
 *
 * @author Renato Machado
 */
public class TcpSocket extends ServerSocket {
    
    public TcpSocket() throws IOException
    {
        super();
    }
    
    public TcpSocket(int port) throws IOException {
        super(port);
    }
}
