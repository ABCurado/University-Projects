/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData.parsers.strategies;

import csheets.ext.importExportData.parsers.FileHandler;
import csheets.ext.importExportData.parsers.ParserStrategy;

/**
 *
 * @author Rui Bastos
 */
public class ImportTextFileStrategy implements ParserStrategy {

	@Override
	public Object execute(String filepath) {
		FileHandler fh = new FileHandler();
		return fh.getLines(filepath);
	}

}
