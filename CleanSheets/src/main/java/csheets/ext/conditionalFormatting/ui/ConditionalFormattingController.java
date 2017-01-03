/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.conditionalFormatting.ui;

import csheets.core.Cell;
import csheets.core.IllegalValueTypeException;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ext.style.ui.BorderChooser;
import csheets.ext.style.ui.FontChooser;
import csheets.ext.style.ui.FormatChooser;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.border.Border;

/**
 *
 * @author Diogo Leite
 */
public class ConditionalFormattingController {

	/**
	 * UI controller
	 */
	private UIController uiController;

	/**
	 * Result
	 */
	private boolean result;

	private Cell cell;

	/**
	 * Constructor of Conditional Formatting Controller
	 *
	 * @param uiController UI controller
	 */
	public ConditionalFormattingController(UIController uiController) {
		this.uiController = uiController;

	}

	public Cell[][] getAllSelectcells() {
		return this.uiController.focusOwner.getSelectedCells();
	}

	/**
	 * This method returns one cell from the created workbook
	 *
	 * @return new cell
	 */
	public Cell createConditionalCell() {
		Workbook workbook = new Workbook(1);
		Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
		this.cell = spreadsheet.getCell(0, 0);
		return this.cell;
	}

	/**
	 * This method returns one StylableCell.After that we can aplly styles at
	 * the created object
	 *
	 * @param nr position of the cell
	 * @return StylableCell
	 */
	public StylableCell createStylableCells(int nr) {
		Workbook workbook = new Workbook(1);
		Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
		return (StylableCell) spreadsheet.getCell(0, nr).getExtension(
			StyleExtension.NAME);
	}

	/**
	 * This method applies the styles that user choose
	 *
	 * @param style StylableCell
	 */
	public void apply(StylableCell style) {

		for (Cell[] row : this.uiController.focusOwner.getSelectedCells()) {
			for (Cell selectedCell : row) {
				StylableCell stylableCell = (StylableCell) selectedCell.
					getExtension(
						StyleExtension.NAME);
				stylableCell.setFont(style.getFont());
				stylableCell.setForegroundColor(style.
					getForegroundColor());
				stylableCell.setBackgroundColor(style.
					getBackgroundColor());
				stylableCell.setBorder(style.
					getBorder());
				stylableCell.setFormat(style.
					getFormat());
				stylableCell.setHorizontalAlignment(style.
					getHorizontalAlignment());
				stylableCell.setVerticalAlignment(style.
					getVerticalAlignment());
			}
		}

		this.uiController.setWorkbookModified(this.uiController.
			getActiveWorkbook());
		this.uiController.focusOwner.repaint();

	}

	public void apply(String expression, StylableCell trueStyle,
					  StylableCell falseStyle) {
		if (expression.charAt(0) != '=') {
			expression = "=" + expression;
		}
		if (!expression.matches(".*_cell.*")) {
			try {
				cell.setContent(expression);
			} catch (FormulaCompilationException ex) {
			}
			if (cell.getValue().isOfType(Value.Type.BOOLEAN)) {
				boolean test = false;
				try {
					test = cell.getValue().toBoolean();
				} catch (IllegalValueTypeException ex) {
					test = false;
				}
				if (test) {
					this.apply(trueStyle);
					return;
				} else {
					this.apply(falseStyle);
					return;
				}
			}
		}
		for (Cell[] row : this.uiController.focusOwner.getSelectedCells()) {
			for (Cell selectedCell : row) {
				if (selectedCell.getValue().isOfType(Value.Type.NUMERIC)) {
					String content = expression.replace("_cell", selectedCell.
														getValue().toString());
					try {
						cell.setContent(content);
						if (cell.getValue().isOfType(Value.Type.BOOLEAN)) {
							boolean test = false;
							try {
								test = cell.getValue().toBoolean();
							} catch (IllegalValueTypeException ex) {
								test = false;
							}
							StylableCell style = null;
							if (test) {
								style = trueStyle;
							} else {
								style = falseStyle;
							}
							StylableCell stylableCell = (StylableCell) selectedCell.
								getExtension(StyleExtension.NAME);
							stylableCell.setFont(style.getFont());
							stylableCell.setForegroundColor(style.
								getForegroundColor());
							stylableCell.setBackgroundColor(style.
								getBackgroundColor());
							stylableCell.setBorder(style.
								getBorder());
							stylableCell.setFormat(style.
								getFormat());
							stylableCell.setHorizontalAlignment(style.
								getHorizontalAlignment());
							stylableCell.setVerticalAlignment(style.
								getVerticalAlignment());
						}
					} catch (FormulaCompilationException ex) {
					}
				}
			}
		}
		this.uiController.setWorkbookModified(this.uiController.
			getActiveWorkbook());
		this.uiController.focusOwner.repaint();

	}

	/**
	 * This method change the Font of the StylableCell
	 *
	 * @param theCell StylableCell
	 */
	public void changeFont(StylableCell theCell) {

		Font font = FontChooser.showDialog(
			null,
			"Choose Font",
			StylableCell.FONT);

		if (font != null) {
			theCell.setFont(font);
		}
	}

	/**
	 * This method change the Foreground of the StylableCell
	 *
	 * @param theCell StylableCell
	 */
	public void changeForeground(StylableCell theCell) {

		Color color = JColorChooser.showDialog(
			null,
			"Choose Foreground Color",
			StylableCell.FOREGROUND);

		if (color != null) {
			theCell.setForegroundColor(color);
		}
	}

	/**
	 * This method change the Background of the StylableCell
	 *
	 * @param theCell StylableCell
	 */
	public void changeBackground(StylableCell theCell) {

		Color color = JColorChooser.showDialog(
			null,
			"Choose Foreground Color",
			StylableCell.BACKGROUND);

		if (color != null) {
			theCell.setBackgroundColor(color);
		}
	}

	/**
	 * This method change the Border of the StylableCell
	 *
	 * @param theCell StylableCell
	 */
	public void changeBorder(StylableCell theCell) {

		Border border = BorderChooser.showDialog(
			null,
			"Choose Border",
			StylableCell.BORDER);

		if (border != null) {
			theCell.setBorder(border);
		}
	}

	/**
	 * This method change the Format of the StylableCell
	 *
	 * @param theCell StylableCell
	 */
	public void changeFormat(StylableCell theCell) {
		Format format = null;
		Date date = new Date();

		try {
			date = Value.parseDateValue("12/01/1996").toDate();
		} catch (ParseException | IllegalValueTypeException ex) {
			Logger.getLogger(ConditionalFormattingUI.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		if (this.uiController.getActiveCell().getValue().getType() == Value.Type.NUMERIC) {
			format = new FormatChooser(
				StylableCell.NUMBER_FORMAT, 2
			).showDialog(null, "Choose Format");
		} else if (this.uiController.getActiveCell().getValue().getType() == Value.Type.DATE) {
			format = new FormatChooser(
				StylableCell.DATE_FORMAT, date
			).showDialog(null, "Choose Format");
		}

		if (format != null) {
			theCell.setFormat(format);
		}
	}

	/**
	 * This method change the Horizontal Align of the StylableCell
	 *
	 * @param theCell StylableCell
	 * @param swing SwingConstants
	 */
	public void changeHorizontalAlign(StylableCell theCell, int swing) {

		theCell.setHorizontalAlignment(swing);

	}

	/**
	 * This method change the Vertical Align of the StylableCell
	 *
	 * @param theCell StylableCell
	 * @param swing SwingConstants
	 */
	public void changeVerticalAlign(StylableCell theCell, int swing) {

		theCell.setVerticalAlignment(swing);

	}

	/**
	 * This method checks if the cell content is a comparison
	 *
	 * @param cell Cell
	 * @return true if cell content is a comparison,otherwise return false
	 */
	public boolean isExpressionComparison(Cell cell) {
		return (cell.getValue().
			isOfType(Value.Type.BOOLEAN));
	}

	/**
	 * This method evaluate if exression is true or false
	 *
	 * @param cell Cell
	 * @return boolean expression
	 * @throws IllegalValueTypeException exception value illegal
	 */
	public boolean evaluateExpression(Cell cell) throws IllegalValueTypeException {
		return (cell.getValue().toBoolean());
	}
}
