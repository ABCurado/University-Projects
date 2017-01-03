/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.support;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import org.jfree.xml.util.Base64;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public final class Converter {

	static public Image getImage(byte[] bytes) throws IOException {

		InputStream in = new ByteArrayInputStream(bytes);
		BufferedImage bImageFromConvert = ImageIO.read(in);

		ImageIO.write(bImageFromConvert, "jpg", new File(
					  "photo.jpg"));
		return bImageFromConvert;
	}

	static public byte[] setImage(File selectedFile) throws IOException {
		byte[] imageInByte;
		BufferedImage originalImage = ImageIO.read(selectedFile);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();

		return imageInByte;
	}

	static public String objectToString(Object object) {
		try {
			ByteArrayOutputStream bytesOutput = new ByteArrayOutputStream();
			ObjectOutputStream objectOutput = new ObjectOutputStream(bytesOutput);
			objectOutput.writeObject(object);
			objectOutput.flush();
			return new String(Base64.encode(bytesOutput.toByteArray()));
		} catch (Exception e) {
		}
		return null;
	}

	static public Object stringToObject(String string) {
		try {
			byte[] bytes = Base64.decode(string.toCharArray());
			ByteArrayInputStream bytesInput = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInput = new ObjectInputStream(bytesInput);
			return objectInput.readObject();
		} catch (Exception e) {
		}
		return null;
	}

}
