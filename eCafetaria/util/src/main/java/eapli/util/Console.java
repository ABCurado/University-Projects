/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class for reading different data types from the Console.
 *
 * based on code from Nuno Silva
 *
 * @author Paulo Gandra Sousa
 *
 */
public final class Console {

	public static String readLine(String prompt) {
		String str;
		do {
			try {
				do {
					System.out.println(prompt);
					InputStreamReader converter = new InputStreamReader(System.in);
					BufferedReader in = new BufferedReader(converter);
					str = in.readLine();
				} while (Strings.isEmptyOrWhiteSpace(str));

				return str;
			} catch (IOException ex) {
			}
		} while (true);
	}

	public static int readInteger(String prompt) {
		do {
			try {
				String strInt = readLine(prompt);
				int valor = Integer.parseInt(strInt);

				return valor;
			} catch (NumberFormatException ex) {
			}
		} while (true);
	}

	public static boolean readBoolean(String prompt) {
		do {
			try {
				String strBool = readLine(prompt).toLowerCase();
				if ("y".equals(strBool) || "s".equals(strBool)) {
					return true;
				} else if ("n".equals(strBool)) {
					return false;
				}
			} catch (NumberFormatException ex) {
			}
		} while (true);
	}

	public static int readOption(int low, int high, int exit) {
		int option;
		do {
			option = Console.readInteger("Please choose an option: ");
			if (option == exit) {
				break;
			}
		} while (option < low || option > high);
		return option;
	}

	public static Date readDate(String prompt) {
		return readDate(prompt, "dd-MM-yyyy");
	}

	public static Date readDate(String prompt, String dateFormat) {
		do {
			try {
				String strDate = readLine(prompt);
				SimpleDateFormat df = new SimpleDateFormat(dateFormat);
				Date date = df.parse(strDate);

				return date;
			} catch (ParseException ex) {
			}
		} while (true);
	}

	public static Calendar readCalendar(String prompt) {
		return readCalendar(prompt, "dd-MM-yyyy");
	}

	public static Calendar readCalendar(String prompt, String dateFormat) {
		do {
			try {
				String strDate = readLine(prompt);
				SimpleDateFormat df = new SimpleDateFormat(dateFormat);
				Calendar date = DateTime.dateToCalendar(df.parse(strDate));

				return date;
			} catch (Exception ex) {
			}
		} while (true);
	}

	public static double readDouble(String prompt) {
		do {
			try {
				String input = readLine(prompt);
				double valor = Double.parseDouble(input);

				return valor;
			} catch (NumberFormatException ex) {
			}
		} while (true);
	}

	public static void waitForKey(String prompt) {
		System.out.println(prompt);
		try {
			System.in.read();
		} catch (IOException ex) {
		}
	}

	private Console() {
		// to make sure this is an utility class
	}
}
