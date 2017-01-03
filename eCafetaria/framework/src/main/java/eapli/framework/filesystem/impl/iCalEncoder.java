/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.filesystem.impl;

import biweekly.Biweekly;
import biweekly.ICalendar;
import eapli.framework.filesystem.FileSystem;
import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author nervousdev
 * @param <T>
 */
public abstract class iCalEncoder<T> extends FileSystem<T> {

	private static final String FILE_EXTENSION = ".ics";
	protected ICalendar output;

	public iCalEncoder() {
		output = new ICalendar();
		output.setProductId("-//eCafeteria//EN");
	}

	@Override

	public boolean saveFile(String fileName) throws Exception {
		File file = new File(fileName + FILE_EXTENSION);

		if (!file.exists()) {
			if (file.createNewFile()) {
				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.print(Biweekly.write(output).go());
				writer.close();
			}
		}
		return true;
	}

}
