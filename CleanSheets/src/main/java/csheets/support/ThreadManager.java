package csheets.support;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Renato Machado
 */
public final class ThreadManager {
    
    /**
     * Thread to key association.
     */
    private static Map<String, Thread> threads = new HashMap<>();
    
    /**
     * Creates a new association with a key and a thread, without running the thread.
     * 
     * @param key Key to identify thread.
     * @param thread Thread.
     */
    public static void create(String key, Thread thread)
    {
        threads.put(key, thread);
    }
    
    /**
     * Executes a thread.
     * 
     * @param key Thread key.
     */
    public static void run(String key)
    {
        if (threads.containsKey(key)) {
            threads.get(key).start();
        }
    }
    
    /**
     * Gets the thread of a given key.
     * 
     * @param key Thread key.
     * @return Thread if the key exists, null otherwise.
     */
    public static Thread get(String key)
    {
        return threads.get(key);
    }
    
    /**
     * Checks if a thread key exists.
     * 
     * @param key Thread key.
     * @return True if it exists, false otherwise.
     */
    public static boolean has(String key)
    {
        return threads.containsKey(key);
    }
    
    /**
     * Destroys the given thread.
     * 
     * It can also destroy namespaced threads.
     * For example:
     *     destroy("group.*");
     * 
     * It will destroy every thread that starts with group.
     * 
     * @param key Thread key.
     */
    public static void destroy(String key)
    {
        if (!key.contains("*")) {
            if (! threads.containsKey(key)) {
                return;
            }
            
            if (threads.get(key).isAlive()) {
                threads.get(key).interrupt();
            }
            
            threads.remove(key);
            return;
        }
        
        String group = key.split("\\*")[0];
        
        threads.entrySet().stream()
            .filter((entry) -> (entry.getKey().startsWith(group)))
            .forEach((entry) -> {
                if (threads.get(entry.getKey()).isAlive()) {
                    entry.getValue().interrupt();
                }
        });
        
        threads.remove(key);
    }
    
}
