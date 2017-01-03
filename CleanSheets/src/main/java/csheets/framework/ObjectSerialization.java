/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * Serialization of Objects to a Base64 String based on:
 * <a href="http://stackoverflow.com/questions/134492/how-to-serialize-an-object-into-a-string">stackoverflow</a>
 *
 * Use this class if you wish to send an Object through the network using
 * current Volt implementation which is only accepting String argument as
 * message to send.
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class ObjectSerialization {

	/**
	 * Read the object from Base64 string.
         * @param s String
         * @return Object
         * @throws java.io.IOException exception
         * @throws java.lang.ClassNotFoundException exception
	 */
	public static Object fromString(String s) throws IOException,
		ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois = new ObjectInputStream(
			new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}

	/**
	 * Write the object to a Base64 string.
         * @param o Object
         * @return String
         * @throws java.io.IOException exception
	 */
	public static String toString(Serializable o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
}
