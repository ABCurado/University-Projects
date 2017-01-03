package csheets.ui.legacy.exportXML;

import csheets.core.Spreadsheet;
import csheets.ui.ctrl.UIController;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * A controller for updating the user-specified comment of a cell.
 *
 * @author ruben
 */
public class ExportXMLController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * Creates a new comment controller.
	 *
	 * @param uiController the user interface controller
	 */
	public ExportXMLController(UIController uiController) {
		this.uiController = uiController;
	}

	/**
	 * Method to create a file with format .xml, call method exportWorkbook to
	 * export a workbook and write to file
	 *
	 * @param fileChooser fileChooser
	 * @param tagWorkbook tagWorkbook
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow tagRow
	 * @param tagColumn
	 * @param tagValue tagColumn
	 * @param tagFont tagFont
	 * @param tagBackground tagBackground
	 * @param tagBorder tagBorder
	 * @param tagForeground tagForeground
	 * @param tagHorizontal tagHorizontal
	 * @param tagVertical tagVertical
	 * @param tagComment tagComment
	 * @param tagAuthor tagAuthor
	 * @param tagScript tagScript
	 * @param tagName tagName
	 * @param tagType tagType
	 * @param tagContent tagContent
	 * @param tagSync tagSync
	 * @throws IOException exception
	 */
	public void exportWorkbook(JFileChooser fileChooser, String tagWorkbook,
							   String tagSpreadSheet,
							   String tagRow, String tagColumn, String tagValue,
							   String tagFont,
							   String tagBackground, String tagBorder,
							   String tagForeground, String tagHorizontal,
							   String tagVertical,
							   String tagComment, String tagAuthor,
							   String tagScripts,
							   String tagScript,
							   String tagName,
							   String tagType, String tagContent, String tagSync
	) throws IOException {
		FileWriter file = new FileWriter(fileChooser.getSelectedFile() + ".xml");
		String result = ExportXML.
			exportWorkbook(tagWorkbook, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont, tagBackground, tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, tagScripts, tagScript, tagName, tagType, tagContent, tagSync, uiController.
						   getActiveWorkbook());
		file.write(result);
		file.close();
	}

	/**
	 * Method to create a file with format .xml, call method exportSpreadSheet
	 * to export a spreadsheet and write to file
	 *
	 * @param fileChooser fileChooser
	 * @param tagWorkbook tagworkbook
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
	public void exportSpreadSheet(JFileChooser fileChooser,
								  String tagWorkbook,
								  String tagSpreadSheet,
								  String tagRow, String tagColumn,
								  String tagValue,
								  String tagFont,
								  String tagBackground, String tagBorder,
								  String tagForeground, String tagHorizontal,
								  String tagVertical,
								  String tagComment, String tagAuthor,
								  Spreadsheet spreadsheet) throws IOException {
		FileWriter file = new FileWriter(fileChooser.getSelectedFile() + ".xml");
		String result = ExportXML.
			exportSpreadsheet(tagWorkbook, tagSpreadSheet, tagRow, tagColumn, tagValue, tagFont, tagBackground, tagBorder, tagForeground, tagHorizontal, tagVertical, tagComment, tagAuthor, spreadsheet);
		file.write(result);
		file.close();
	}

	/**
	 *
	 * Method to create a file with format .xml, call method
	 * exportSpreadSheetSelected to export a spreadsheetSelected and write to
	 * file
	 *
	 * @param fileChooser fileChooser
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow row
	 * @param tagColumn column
	 * @throws IOException exception
	 */
	public void exportSpreadSheetSelected(JFileChooser fileChooser,
										  String tagSpreadSheet,
										  String tagRow, String tagColumn) throws IOException {
		FileWriter file = new FileWriter(fileChooser.getSelectedFile() + ".xml");
		String result = ExportXML.
			exportSpreadsheetSelected(tagSpreadSheet, tagRow, tagColumn, uiController);
		file.write(result);
		file.close();
	}
}
