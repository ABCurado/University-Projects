/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Martins
 */
public final class PostalCodeValidator {

	static String path;
	static List<String> listPostalCodes = new ArrayList();

	public static void importData() throws FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().
				getAbsolutePath();
			Scanner input = new Scanner(new File(path));
			while (input.hasNextLine()) {
				listPostalCodes.add(input.nextLine());
			}
		}
	}

	static public boolean validatePostalCode(String expression) {
		if (path == null) {
			return true;
		}
		if (expression.matches("[0-9]{4}-[0-9]{3}")) {
			for (String string : listPostalCodes) {
				if (string.equalsIgnoreCase(expression)) {
					return true;
				}
			}
		} else {
			throw new IllegalArgumentException("Postal Code not from Portugal!");
		}
		throw new IllegalArgumentException("Postal Code not exists in the list!");
	}
}
