package vendor.volt.channels;

import java.util.Map;
import vendor.volt.Channel;
import vendor.volt.Request;
import vendor.volt.support.Encrypter;

/**
 * This channel allows the encryption of a message before the action is executed.
 * 
 * @author Renato Machado
 */
public class MessageEncryptionChannel extends Channel {
    
    /**
     * Encryption key.
     */
    private final String key;
    
    public MessageEncryptionChannel(String key)
    {
        if (key == null) {
            throw new IllegalArgumentException("The encryption key cannot be null.");
        }
        
        if (key.length() != 16) {
            throw new IllegalArgumentException("The encryption key must have 16 characters.");
        }
        
        this.key = key;
    }
    
    @Override
    public void before(Request request, Map<String, Object> dependencies) {
        String message = request.message();
        
        try {
            request.message(Encrypter.encrypt(message, key));
        } catch (Exception e) {
            // Ignore the exception and don't do anything with the message.
        }
    }
    
}
