/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.legacy.exportPDF;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Diogo Leite
 */
public class ExportPDFController {

	/**
	 * The user interface controller
	 */
	private UIController uiController;

	/**
	 * Creates a new ExportPDFController controller.
	 *
	 * @param uiController the user interface controller
	 */
	public ExportPDFController(UIController uiController) {
		this.uiController = uiController;
	}

	/**
	 *
	 * Method to create a file with format .pdf, call method writeWorkbook to
	 * export a workbook and write to file
	 *
	 * @param fileChooser fileChooser
	 * @param showList true if user wants to see list of sections,otherwise
	 * false
	 * @throws IOException exception
	 */
	public void exportWorkbook(JFileChooser fileChooser, boolean showList) throws IOException {

		File file = chooseFile(fileChooser);

		Workbook workbook = uiController.getActiveWorkbook();
		ExportPDF pdf = new ExportPDF();
		pdf.applyList(showList);
		pdf.write(file, workbook);

	}

	/**
	 *
	 * Method to create a file with format .pdf, call method writeSpreadsheet to
	 * export a spreadsheet and write to file
	 *
	 * @param fileChooser fileChooser
	 * @param spreadSheet spreadSheet
	 * @throws IOException exception
	 */
	public void exportSpreadSheet(JFileChooser fileChooser,
								  Spreadsheet spreadSheet) throws IOException {
		File file = chooseFile(fileChooser);

		ExportPDF pdf = new ExportPDF();

		pdf.write(file, spreadSheet);

	}

	/**
	 *
	 * Method to create a file with format .pdf, call method writeSelectedCells
	 * to export a range of cells and write to file
	 *
	 * @param fileChooser fileChooser
	 * @param uiController uiController
	 */
	public void exportSelectedCells(JFileChooser fileChooser,
									UIController uiController
	) {

		File file = chooseFile(fileChooser);
		ExportPDF pdf = new ExportPDF();

		pdf.write(file, uiController.focusOwner.getSelectedCells());

	}

	/**
	 * Method to choose a file
	 *
	 * @param fileChooser JFileChooser
	 * @return new File
	 */
	public File chooseFile(JFileChooser fileChooser) {
		File file = fileChooser.getSelectedFile();
		return new File(file.toString() + ".pdf");  // append .pdf
	}

}
