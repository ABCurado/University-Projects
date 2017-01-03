package vendor.volt.channels;

import java.util.Map;
import vendor.volt.Channel;
import vendor.volt.Request;
import vendor.volt.support.Encrypter;

/**
 * This channel allows the decryption of a message before the action is executed.
 * 
 * @author Renato Machado
 */
public class MessageDecryptionChannel extends Channel {
    
    /**
     * Encryption key.
     */
    private final String key;

    public MessageDecryptionChannel(String key) {
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
            request.message(Encrypter.decrypt(message, key));
        } catch (Exception e) {
            // Ignore the exception and don't do anything with the message.
        }
    }
    
}
