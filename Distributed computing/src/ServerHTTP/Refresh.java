/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerHTTP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jbarros
 */
class Refresh extends Thread {

    private static DatagramSocket sock;
    private static int port;
    private static String message;
    //private HttpRequest http;

    public Refresh() {
        this.port = 15977;
        this.message = "RefreshThePage";
    }

    public void run() {
        String frase = "";
        try {
            DatagramSocket s = new DatagramSocket(null);
            s.setReuseAddress(true);
            s.bind(new InetSocketAddress("127.0.0.1", port));

            byte buffer[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting...");
            s.receive(packet);
            frase = new String(packet.getData(), 0, packet.getLength());
            System.out.println(frase);
            System.out.println("Received!");
            if (frase.compareTo(message) == 0) {
                //http.processPostList();
                //Date date = new SimpleDateFormat("HH:mm:ss.S").parse(new Date().toString());
                String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
               // ServerHTTP.setTS(timeStamp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //http.closeSocket();
    }
    
    

}
