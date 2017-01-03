package vendor.volt.channels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import vendor.volt.Channel;
import vendor.volt.Request;

/**
 * This channel allows to notify every observer of the messages sent.
 * 
 * @author Renato Machado
 */
public class MessageSentChannel extends Channel {
    
    /**
     * Where the message is being sent from.
     */
    private final String from;
    
    /**
     * The observer that wants to know about the sent messages.
     */
    private final List<Observer> observers;
    
    public MessageSentChannel(String from, Observer... observers)
    {
        this.from = from;
        
        this.observers = new ArrayList<>();
        this.observers.addAll(Arrays.asList(observers));
    }
    
    @Override
    public void after(Request request, Map<String, Object> dependencies) {
        if (request.target() == null) {
            return;
        }
        
        String message = this.from + request.route() + ": " + request.message();
        
        for (Observer observer : this.observers) {
            observer.update(null, message);
        }
    }
    
}
