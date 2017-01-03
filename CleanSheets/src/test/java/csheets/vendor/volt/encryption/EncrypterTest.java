package csheets.vendor.volt.encryption;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vendor.volt.support.Encrypter;

/**
 *
 * @author Renato Machado
 */
public class EncrypterTest {
    
    public EncrypterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test_can_encrypt_and_decrypt_messages()
    {
        String message = "Message";
        String key = "ohT3e8TJ55QOsAsx";
        
        String result = Encrypter.encrypt(message, key);
        
        assertNotEquals(result, message);
        
        result = Encrypter.decrypt(result, key);
        
        assertEquals(message, result);
    }
    
    @Test
    public void test_encryption_with_different_keys_produces_different_messages()
    {
        String message = "Message";
        String key = "ohT3e8TJ55QOsAsx";
        String key2 = "jli2cmJk7QFXnS2P";

        String result = Encrypter.encrypt(message, key);
        String result2 = Encrypter.encrypt(message, key2);
        
        assertNotEquals(result, result2);
    }
    
}
