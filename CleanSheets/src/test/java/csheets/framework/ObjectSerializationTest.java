/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.framework;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class ObjectSerializationTest {

	public ObjectSerializationTest() {
	}

	/**
	 * Test of fromString method, of class ObjectSerialization.
	 *
	 * @throws java.lang.Exception
	 */
	@Test
	public void ensureObjectAfterSerializationAndDeSerializationMatchesOriginal() throws Exception {
		System.out.
			println("Make sure object after serialization and deserialization matches original");
		Integer original = 1234567890;
		String serializedString = ObjectSerialization.toString(original);
		Integer expResult = 1234567890;
		Integer result = (Integer) ObjectSerialization.
			fromString(serializedString);
		assertEquals(expResult, result);
	}

}
