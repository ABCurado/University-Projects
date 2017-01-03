package csheets.ext;

import csheets.AppSettings;
import vendor.volt.Volt;
import vendor.volt.protocols.tcp.TcpServer;
import vendor.volt.protocols.udp.UdpServer;

public class NetworkManager {

    /** 
     * Number of UDP services in use.
     */
    private static int udpServices = 0;
    
    /**
     * Number of TCP services in use.
     */
    private static int tcpServices = 0;
    
    /**
     * Application Settings.
     */
    private static String UDP_PORT = "UDP_PORT";
    private static String TCP_PORT = "TCP_PORT";
    
    /**
     * UDP server port.
     */
    private static int udpPort = Integer.valueOf(AppSettings.instance().get(UDP_PORT));
    
    /**
     * TCP server port.
     */
    private static int tcpPort = Integer.valueOf(AppSettings.instance().get(TCP_PORT));
    
    
    /**
     * Gets the UDP Server of CleanSheets.
     * 
     * @return UdpServer. Can throw an exception if port is in use.
     */
    public static UdpServer udp()
    {
        udpServices++;
        
        return Volt.udp(udpPort);
    }
    
    /**
     * Gets the TCP Server of CleanSheets.
     * 
     * @return TcpServer. Can throw an exception if port is in use. 
     */
    public static TcpServer tcp()
    {
        tcpServices++;
        
        return Volt.tcp(tcpPort);
    }
    
    /**
     * Stops a UDP service. If there are no UDP services left, the server will
     * stop, thus releasing its port.
     *
     * @return true if the server successfully stops, false otherwise.
     */
    public static boolean stopUdpService()
    {
        if (udpServices == 0) {
            return false;
        }
        
        udpServices--;
        
        if (udpServices == 0) {
            Volt.stop(udpServices);
            return true;
        }
        
        return false;
    }
    
    /**
     * Stops a TCP service. If there are no TCP services left, the server will
     * stop, thus releasing its port.
     * 
     * @return true if the server successfully stops, false otherwise.
     */
    public static boolean stopTcpService() {
        if (udpServices == 0) {
            return false;
        }

        udpServices--;

        if (udpServices == 0) {
            Volt.stop(udpServices);
            return true;
        }

        return false;
    }
}
