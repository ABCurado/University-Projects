/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.legacy.importXML;

import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ui.ctrl.UIController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scarl
 */
public class ImportXMLController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * Creates a new import controller.
	 *
	 * @param uiController the user interface controller
	 */
	public ImportXMLController(UIController uiController) {
		this.uiController = uiController;
	}

	/**
	 * Method to create a file with format .xml, call method exportWorkbook to
	 * export a workbook and write to file
	 *
	 * @param path path of file
	 * @param tagWorkBook tagWorkBook
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow tagRow
	 * @param tagColumn tagColumn
	 * @param tagValue tagValue
	 * @param tagFont tagFont
	 * @param tagBackground tagBackground
	 * @param tagBorder tagBorder
	 * @param tagForeground tagForeground
	 * @param tagHorizontal tagHorizontal
	 * @param tagVertical tagVertical
	 * @param tagComment tagComment
	 * @param tagAuthor tagAuthor
	 * @param tagScripts tagScripts
	 * @param tagScript tagScript
	 * @param tagName tagName
	 * @param tagType tagType
	 * @param tagContent tagContent
	 * @param tagSync tagSync
	 * @throws IOException
	 */
	public void importWorkbook(String path, String tagWorkBook,
							   String tagSpreadSheet,
							   String tagRow, String tagColumn, String tagValue,
							   String tagFont,
							   String tagBackground,
							   String tagBorder,
							   String tagForeground,
							   String tagHorizontal,
							   String tagVertical, String tagComment,
							   String tagAuthor,
							   String tagScripts,
							   String tagScript,
							   String tagName,
							   String tagType,
							   String tagContent,
							   String tagSync) throws IOException {
		try {
			ImportXML.
				importWorkbook(path, tagWorkBook, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont,
							   tagBackground,
							   tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, tagScripts, tagScript, tagName, tagType, tagContent, tagSync, uiController.
							   getActiveWorkbook());
		} catch (FileNotFoundException | FormulaCompilationException ex) {
			Logger.getLogger(ImportXMLController.class.getName()).
				log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * * Method to create a file with format .xml, call method
	 * exportSpreadSheet to export a spreadsheet and write to file
	 *
	 * @param path path of the file
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow tagRow
	 * @param tagColumn tagColumn
	 * @param tagValue tagValue
	 * @param tagFont tagFont
	 * @param tagBackground tagBackground
	 * @param tagBorder tagBorder
	 * @param tagForeground tagForeground
	 * @param tagHorizontal tagHorizontal
	 * @param tagVertical tagVertical
	 * @param tagComment tagComment
	 * @param tagAuthor tagAuthor
	 * @param spreadsheet spreadsheet
	 * @throws IOException
	 */
	public void importSpreadSheet(String path,
								  String tagSpreadSheet,
								  String tagRow, String tagColumn,
								  String tagValue,
								  String tagFont,
								  String tagBackground,
								  String tagBorder,
								  String tagForeground,
								  String tagHorizontal,
								  String tagVertical, String tagComment,
								  String tagAuthor,
								  Spreadsheet spreadsheet) throws IOException {
		try {
			ImportXML.
				importSpreadsheet(path, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont,
								  tagBackground,
								  tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, spreadsheet);
		} catch (FileNotFoundException | FormulaCompilationException ex) {
			Logger.getLogger(ImportXMLController.class.getName()).
				log(Level.SEVERE, null, ex);
		}

	}

	/**
	 *
	 * Method to create a file with format .xml, call method
	 * exportSpreadSheetSelected to export a spreadsheetSelected and write to
	 * file
	 *
	 * @param path path of file
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow row
	 * @param tagColumn column
	 * @throws IOException exception
	 */
	public void importSpreadSheetSelected(String path, String tagSpreadSheet,
										  String tagRow, String tagColumn) throws IOException {
		try {
			ImportXML.
				importSpreadsheetSelected(path, tagSpreadSheet, tagRow, tagColumn, uiController);
		} catch (FileNotFoundException | FormulaCompilationException ex) {
			Logger.getLogger(ImportXMLController.class.getName()).
				log(Level.SEVERE, null, ex);
		}
	}
}
