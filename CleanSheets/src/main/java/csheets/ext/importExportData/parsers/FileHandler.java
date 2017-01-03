package csheets.ext.importExportData.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

	/**
	 * Creates a new instance of the FileHandler.
	 */
	public FileHandler() {
	}

	/**
	 * Checks if a file exists given its path.
	 *
	 * @param filename File path (including the file name).
	 * @return Returns true if the file exists, false otherwise.
	 */
	public boolean fileExists(String filename) {
		return new File(filename).isFile();
	}

	/**
	 * Creates an empty file with the path given.
	 *
	 * @param filename File path (including the file name).
	 * @return Returns true if it succeeds to create the file, false otherwise.
	 */
	public boolean createFile(String filename) {
		if (filename == null) {
			return false;
		}
		try {
			return new File(filename).createNewFile();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException ex) {
			return false;
		}
	}

	/**
	 * Writes the given content to the file. This method will overwrite the
	 * contents in the file.
	 *
	 * @param filename File path (including the file name).
	 * @param content Content to be written to the file.
	 * @return Returns true if it succeeds writing to the file, false otherwise.
	 */
	public boolean setFileContents(String filename, String content) {
		if (this.fileExists(filename)) {
			if (!new File(filename).delete()) {
				return false;
			}
		} else {
			if (!createFile(filename)) {
				return false;
			}
		}

		try {
			Formatter out = new Formatter(filename);

			out.format(content).flush();
			out.close();

			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	/**
	 * Preprend the given content to the file.
	 *
	 * @param filename File path (including the file name).
	 * @param content Content to be written to the file.
	 * @return Returns true if it succeeds writing to the file, false otherwise.
	 */
	public boolean prepend(String filename, String content) {
		return this.setFileContents(filename, content + this.
									getFileContents(filename));
	}

	/**
	 * Append the given content to the file.
	 *
	 * @param filename File path (including the file name).
	 * @param content Content to be written to the file.
	 * @return Returns true if it succeeds writing to the file, false otherwise.
	 */
	public boolean append(String filename, String content) {
		return this.
			setFileContents(filename, this.getFileContents(filename) + content);
	}

	public byte[] getFileContentsInBytes(File file) {
		try {
			Path p = Paths.get(file.getAbsolutePath());
			return Files.readAllBytes(p);
		} catch (IOException ex) {
			Logger.getLogger(FileHandler.class.getName()).
				log(Level.SEVERE, null, ex);
		}

		return null;
	}

	/**
	 * Gets the contents of a file.
	 *
	 * @param filename File path (including the file name).
	 * @return Returns the file content as a string, otherwise returns null.
	 */
	public String getFileContents(String filename) {
		if (filename == null) {
			return null;
		}

		if (!fileExists(filename)) {
			return null;
		}

		try {
			Scanner file = new Scanner(new File(filename), "UTF-8");
			String content = "";

			while (file.hasNextLine()) {
				content += file.nextLine();
			}

			// Remover a última linha em branco
			if (content.length() > 0) {
				//content = content.substring(0, content.length() - 1);
			}

			file.close();
			return content;

		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * Gets the number of lines of the given file.
	 *
	 * @param filename File path (including the file name).
	 * @return Returns the number of lines in the file.
	 */
	public int getNumberOfLines(String filename) {
		try {
			Scanner file = new Scanner(new File(filename), "UTF-8");
			int lines = 0;

			while (file.hasNextLine()) {
				lines++;
				file.nextLine();
			}

			file.close();
			return lines;

		} catch (FileNotFoundException e) {
			return -1;
		}
	}

	/**
	 * Gets the contents of a file.
	 *
	 * @param filename File path (including the file name).
	 * @return Returns an string array containing each line of the file.
	 */
	public String[] getLines(String filename) {
		try {
			Scanner file = new Scanner(new File(filename), "UTF-8");
			String[] content = new String[this.getNumberOfLines(filename)];
			int i = 0;

			while (file.hasNextLine()) {
				content[i] = file.nextLine();
				i++;
			}

			file.close();
			return content;

		} catch (FileNotFoundException e) {
			return null;
		}
	}

	/**
	 * Copies a file to a destination.
	 *
	 * @param from File path (including the file name).
	 * @param to The path to store the copied file.
	 * @param options Copy options.
	 * @return Returns true if it copies successfully, false otherwise.
	 */
	public boolean copy(String from, String to, CopyOption[] options) {
		Path fromPath = (Path) Paths.get(from);
		Path toPath = (Path) Paths.get(to);

		try {
			java.nio.file.Files.copy(fromPath, toPath, options);

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Copies a file to a destination with default options.
	 *
	 * @param from File path (including the file name).
	 * @param to The path to store the copied file.
	 * @return Returns true if it copies successfully, false otherwise.
	 */
	public boolean copy(String from, String to) {
		CopyOption[] options = new CopyOption[]{
			StandardCopyOption.REPLACE_EXISTING,
			StandardCopyOption.COPY_ATTRIBUTES
		};

		return this.copy(from, to, options);
	}

}
