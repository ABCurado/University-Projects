package csheets.ui.legacy.strategy.exportStrategy;

import java.io.File;

/**
 * Strategy to export data
 *
 * @author Diogo Leite
 */
public interface EncoderStrategy {

	/**
	 * Writes a Object to the given output stream.
	 *
	 * @param file file
	 * @param args object
	 */
	public void write(File file, Object args);

}
