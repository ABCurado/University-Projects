package csheets.ext.secureCommunications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import vendor.volt.Channel;
import vendor.volt.Request;
import vendor.volt.support.Encrypter;

/**
 * A Volt Channel to filter incoming messages.
 *
 * @author Diogo Leite
 */
public class IncomingChannel extends Channel {

    /**
     * Encryption key.
     */
    private final String key;

    /**
     * The observer that wants to know about the received messages.
     */
    private final List<Observer> observers;

    public IncomingChannel(String key, Observer... observers) {
        this.key = key;
        this.observers = new ArrayList<>();
        this.observers.addAll(Arrays.asList(observers));
    }

    @Override
    public void before(Request request, Map<String, Object> dependencies) {
        if (request.target() == null) {
            return;
        }
    }
    
    @Override
    public void after(Request request, Map<String, Object> dependencies) {
        if (request.target() == null) {
            return;
        }
        
        // S -> Sent
        // SE -> Sent Encrypted
        // R -> Received
        // RE -> Received Encrypted

        String incoming = "R";

        try {
            Encrypter.decrypt(request.message(), key);

            incoming += "E";
        } catch (Exception e) {
            // Ignore the exception and don't do anything with the message.
        }

        incoming += ":" + request.length();

        synchronized (observers) {
            for (Observer observer : this.observers) {
                observer.update(null, incoming);
            }
        }
    }
}
