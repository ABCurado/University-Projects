/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO javadoc
 *
 * @author nervousdev
 * @param <T>
 */
public abstract class FileSystem<T> {

	public abstract void writeElements(Iterable<T> listElements);

	protected boolean saveFile(String fileName, String content, String extension) throws IOException {

		File file = new File(fileName);

		if (!file.exists()) {
			if (file.createNewFile()) {
				PrintWriter outputFile = new PrintWriter(file);
				outputFile.print(content);
				outputFile.close();
			}
		}

		return true;
	}

	public abstract boolean saveFile(String fileName) throws Exception;

}
