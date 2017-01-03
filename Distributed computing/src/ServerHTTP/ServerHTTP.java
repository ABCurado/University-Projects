package ServerHTTP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class ServerHTTP {

    static String port = "30620";
    static List<String> list = new ArrayList();
    static Map<String, List<String>> serversList = new HashMap();
    static String ts;
    static Refresh r;

    /* public static void setTS(String ts) {
        ServerHTTP.ts = ts;
    }
    
    public static String getTS() {
        return ts;
    } */
    
    private static void prepareRefresh() throws InterruptedException {
        r = new Refresh();
        r.start();
    }
    
    public static void main(String args[]) throws Exception {
       
        prepareRefresh();
        try {
            ServerSocket sock = new ServerSocket(Integer.parseInt(port));
            while (true) {
                Socket cliSock = sock.accept();
                new Thread(new HttpRequest(cliSock)).start();
            }
        } catch (IOException ex) {
            System.out.println("The local port " + args[0] + " is busy.");
            System.exit(1);
        }
    }

    public static void updateList() {
        try {
            List<String> newList = new ArrayList();
            JSONArray json = (JSONArray) new JSONParser().
                    parse(new FileReader("filesystem.json"));
            Iterator<JSONObject> jsonIterator = json.iterator();
            while (jsonIterator.hasNext()) {
                JSONObject server = jsonIterator.next();
                String name = server.get("name").toString();
                String ipServer = server.get("ip").toString();
                JSONArray filesServers = (JSONArray) server.get("files");
                Iterator<JSONObject> filesIterator = filesServers.iterator();
                List<String> list = new ArrayList();
                while (filesIterator.hasNext()) {
                    JSONObject file = filesIterator.next();
                    newList.add(name + ";" + ipServer + ";" + file.
                            get("path").toString().split("files/")[1]);
                }
            }
            list = newList;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
