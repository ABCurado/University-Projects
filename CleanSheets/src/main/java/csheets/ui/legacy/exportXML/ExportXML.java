/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.legacy.exportXML;

import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
import csheets.ext.macro_beanshell.Code;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ruben
 */
final public class ExportXML {

	private ExportXML() {
	}

	/**
	 * Export the contents of an workbook to XML file with param tags.
	 *
	 * @param tagWorkbook tagWorkbook
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow tagRow
	 * @param tagColumn tagColumn
	 * @param tagValue tagValue
	 * @param tagFont tagFont
	 * @param tagBackground tagBackground
	 * @param tagBorder tagBorder
	 * @param tagForeground
	 * @param tagHorizontal
	 * @param tagVertical
	 * @param tagComment tagComment
	 * @param tagAuthor tagAuthor
	 * @param tagScript tagScript
	 * @param tagName tagName
	 * @param tagType tagType
	 * @param tagContent tagContent
	 * @param tagSync tagSync
	 * @param workbook workbook
	 * @return toString of workbook
	 */
	static public String exportWorkbook(
		String tagWorkbook,
		String tagSpreadSheet,
		String tagRow, String tagColumn, String tagValue, String tagFont,
		String tagBackground,
		String tagBorder, String tagForeground, String tagHorizontal,
		String tagVertical, String tagComment, String tagAuthor,
		String tagScripts,
		String tagScript,
		String tagName,
		String tagType,
		String tagContent,
		String tagSync,
		Workbook workbook) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		stringBuilder.append("<" + tagWorkbook + ">\n");
		for (int i = 0; i < workbook.getSpreadsheetCount(); i++) {
			Spreadsheet spreadsheet = workbook.getSpreadsheet(i);
			stringBuilder.
				append("\t<" + tagSpreadSheet + " name=\"" + spreadsheet.
					getTitle() + "\" >\n");
			for (int j = 0; j <= spreadsheet.getRowCount(); j++) {
				List<String> list = new ArrayList();
				for (int k = 0; k <= spreadsheet.getColumnCount(); k++) {
					Value value = spreadsheet.getCell(k, j).getValue();

					StylableCell stylableCell = (StylableCell) spreadsheet.
						getCell(k, j).getExtension(
						StyleExtension.NAME);

					CommentableCell cell = (CommentableCell) spreadsheet.
						getCell(k, j).getExtension(
						CommentsExtension.NAME);

					if (value.toString().length() > 0) {
						list.
							add("\t\t\t<" + tagColumn + " index=\"" + k + "\"" + ">\n");
						list.
							add("\t\t\t<" + tagValue + ">" + value + "</" + tagValue + ">\n");

						//<---Font--->
						list.
							add("\t\t\t\t<" + tagFont + ">" + stylableCell.
								getFont().toString() + "</" + tagFont + ">\n");
						list.
							add("\t\t\t\t<" + tagBackground + ">" + stylableCell.
								getBackgroundColor().getRGB() + "</" + tagBackground + ">\n");
						list.
							add("\t\t\t\t<" + tagBorder + ">" + stylableCell.
								getBorder() + "</" + tagBorder + ">\n");
						list.
							add("\t\t\t\t<" + tagForeground + ">" + stylableCell.
								getForegroundColor().getRGB() + "</" + tagForeground + ">\n");
						list.
							add("\t\t\t\t<" + tagHorizontal + ">" + stylableCell.
								getHorizontalAlignment()
								+ "</" + tagHorizontal + ">\n");
						list.
							add("\t\t\t\t<" + tagVertical + ">" + stylableCell.
								getVerticalAlignment()
								+ "</" + tagVertical + ">\n");
						//<!---Font--->

						//<--Comment-->
						for (Comment c : cell.getCommentsList()) {
							list.
								add("\t\t\t\t<" + tagComment + ">\n");
							list.
								add("\t\t\t\t\t<" + tagAuthor + ">" + c.
									userName() + "</" + tagAuthor + ">\n");

							list.add("\t\t\t\t\t<" + tagValue + ">" + c.
								text().toString() + "</" + tagValue + ">\n");
							list.
								add("\t\t\t\t\t<" + tagFont + ">" + c.
									getFont().toString() + "</" + tagFont + ">\n");

							list.
								add("\t\t\t\t\t<" + tagBackground + ">" + c.
									getBackgroundColor().getRGB() + "</" + tagBackground + ">\n");
							list.
								add("\t\t\t\t\t<" + tagBorder + ">" + c.
									getBorder().toString() + "</" + tagBorder + ">\n");
							list.add("\t\t\t\t" + "</" + tagComment + ">\n");
						}

						//<!--Comment-->
						list.add("\t\t\t" + "</" + tagColumn + ">\n");
					}
				}
				if (list.size() > 0) {
					stringBuilder.
						append("\t\t<" + tagRow + " index=\"" + j + "\"" + ">\n");
					for (String value : list) {
						stringBuilder.append(value);
					}
					stringBuilder.append("\t\t</" + tagRow + ">\n");
				}
			}
			stringBuilder.append("\t</" + tagSpreadSheet + ">\n");
		}
		List<Code> scripts = UIController.getUIController().getActiveWorkbook().
			getScripts();
		if (scripts.size() > 0) {
			stringBuilder.append("<" + tagScripts + ">\n");
			List<String> lst = new ArrayList();
			for (Code s : scripts) {
				stringBuilder.append("<" + tagScript + ">\n");
				stringBuilder.
					append("\t<" + tagName + ">" + s.getName().toString() + "</" + tagName + ">\n");
				stringBuilder.
					append("\t<" + tagType + ">" + s.getType().toString() + "</" + tagType + ">\n");
				stringBuilder.append("\t<" + tagContent + ">" + s.getContent().
					toString() + "</" + tagContent + ">\n");
				stringBuilder.
					append("\t<" + tagSync + ">" + s.isSynchronous() + "</" + tagSync + ">\n");
				stringBuilder.
					append("</" + tagScript + ">\n");
			}
			stringBuilder.append("</" + tagScripts + ">\n");
		}
		stringBuilder.append("</" + tagWorkbook + ">\n");

		return stringBuilder.toString();
	}

	/**
	 * Export the contents of an Spreadsheet to XML file with param tags.
	 *
	 * @param tagWorkbook tagWorkbook
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
	 * @return toString of Spreadsheet
	 */
	static public String exportSpreadsheet(
		String tagWorkbook,
		String tagSpreadSheet,
		String tagRow, String tagColumn, String tagValue, String tagFont,
		String tagBackground,
		String tagBorder, String tagForeground, String tagHorizontal,
		String tagVertical, String tagComment, String tagAuthor,
		Spreadsheet spreadsheet) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		stringBuilder.
			append("<" + tagSpreadSheet + " name=\"" + spreadsheet.
				getTitle() + "\" >\n");
		for (int j = 0; j <= spreadsheet.getRowCount(); j++) {
			List<String> list = new ArrayList();
			for (int k = 0; k <= spreadsheet.getColumnCount(); k++) {
				Value value = spreadsheet.getCell(k, j).getValue();

				StylableCell stylableCell = (StylableCell) spreadsheet.
					getCell(k, j).getExtension(
					StyleExtension.NAME);

				CommentableCell cell = (CommentableCell) spreadsheet.
					getCell(k, j).getExtension(
					CommentsExtension.NAME);

				if (value.toString().length() > 0) {
					list.
						add("\t\t\t<" + tagColumn + " index=\"" + k + "\"" + ">\n");
					list.
						add("\t\t\t<" + tagValue + ">" + value + "</" + tagValue + ">\n");

					//<---Font--->
					list.
						add("\t\t\t\t<" + tagFont + ">" + stylableCell.
							getFont().toString() + "</" + tagFont + ">\n");
					list.
						add("\t\t\t\t<" + tagBackground + ">" + stylableCell.
							getBackgroundColor().getRGB() + "</" + tagBackground + ">\n");
					list.
						add("\t\t\t\t<" + tagBorder + ">" + stylableCell.
							getBorder() + "</" + tagBorder + ">\n");
					list.
						add("\t\t\t\t<" + tagForeground + ">" + stylableCell.
							getForegroundColor().getRGB() + "</" + tagForeground + ">\n");
					list.
						add("\t\t\t\t<" + tagHorizontal + ">" + stylableCell.
							getHorizontalAlignment()
							+ "</" + tagHorizontal + ">\n");
					list.
						add("\t\t\t\t<" + tagVertical + ">" + stylableCell.
							getVerticalAlignment()
							+ "</" + tagVertical + ">\n");
					//<!---Font--->

					//<--Comment-->
					for (Comment c : cell.getCommentsList()) {
						list.
							add("\t\t\t\t<" + tagComment + " index=\"" + k + "\"" + ">\n");
						list.
							add("\t\t\t\t\t<" + tagAuthor + ">" + c.
								userName() + "</" + tagAuthor + ">\n");

						list.add("\t\t\t\t\t<" + tagValue + ">" + c.
							text().toString() + "</" + tagValue + ">\n");
						list.
							add("\t\t\t\t\t<" + tagFont + ">" + c.
								getFont().getFontName() + "</" + tagFont + ">\n");

						list.
							add("\t\t\t\t\t<" + tagBackground + ">" + c.
								getBackgroundColor().getRGB() + "</" + tagBackground + ">\n");
						list.
							add("\t\t\t\t\t<" + tagBorder + ">" + c.
								getBorder().toString() + "</" + tagBorder + ">\n");
						list.add("\t\t\t\t" + "</" + tagComment + ">\n");
					}

					//<!--Comment-->
					list.add("\t\t\t" + "</" + tagColumn + ">\n");
				}
			}
			if (list.size() > 0) {
				stringBuilder.
					append("\t<" + tagRow + " index=\"" + j + "\"" + ">\n");
				for (String value : list) {
					stringBuilder.append(value);
				}
				stringBuilder.append("\t</" + tagRow + ">\n");
			}
		}
		stringBuilder.append("</" + tagSpreadSheet + ">\n");
		return stringBuilder.toString();
	}

	/**
	 * Export part of an worksheet to an XML file
	 *
	 * @param tagSpreadSheet tagSpreadSheet
	 * @param tagRow tagRow
	 * @param tagColumn tagColumn
	 * @param uiController uiController
	 * @return toString of SpreadsheetSelected
	 */
	static public String exportSpreadsheetSelected(
		String tagSpreadSheet,
		String tagRow,
		String tagColumn,
		UIController uiController) {
		Cell[][] cells = uiController.focusOwner.getSelectedCells();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		stringBuilder.
			append("<" + tagSpreadSheet + " name=\"" + uiController.
				getActiveSpreadsheet().getTitle() + "\" >\n");
		for (int j = 0; j < cells.length; j++) {
			List<String> list = new ArrayList();
			Address address = null;
			for (int i = 0; i < cells[0].length; i++) {
				Value value = cells[j][i].getValue();
				address = cells[j][i].getAddress();

				if (value.toString().length() > 0) {
					list.
						add("\t\t<" + tagColumn + " index=\"" + address.
							getColumn() + "\"" + ">" + value + "</" + tagColumn + ">\n");
				}
			}
			if (list.size() > 0) {
				stringBuilder.
					append("\t<" + tagRow + " index=\"" + address.getRow() + "\"" + ">\n");
				for (String value : list) {
					stringBuilder.append(value);
				}
				stringBuilder.append("\t</" + tagRow + ">\n");
			}
		}
		stringBuilder.append("</" + tagSpreadSheet + ">\n");
		return stringBuilder.toString();
	}
}
