/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.parsers;

/**
 *
 * @author Rui Bastos
 */
public class TxtParser extends Parser {

	/**
	 * Creates a new Parser.
	 *
	 * @param handler File Handler.
	 */
	public TxtParser(FileHandler handler) {
		super(handler);
	}

	/**
	 * Parses the given file. Unless you get the file through the withContents
	 * method, you need to read the file by using the FileHandler associated
	 * with this class in order to have access to the file's contents.
	 *
	 * @return Object containing the parsed contents of the file.
	 */
	@Override
	public Object parse() {
		return this.getStrategy().execute(this.getFile());
	}

}
