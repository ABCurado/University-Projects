package csheets.ui.legacy.exportPDF;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import csheets.ui.legacy.strategy.exportStrategy.EncoderStrategy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Leite
 */
public class ExportPDF implements EncoderStrategy {

	/**
	 * showList
	 */
	boolean showList;

	/**
	 * Empty Constructor.
	 */
	public ExportPDF() {
	}

	@Override
	public void write(File file, Object args) {

		try {

			if (args instanceof Workbook) {

				writeWorkbook((Workbook) args, file);
			} else if (args instanceof Spreadsheet) {
				writeSpreadsheet((Spreadsheet) args, file);
			} else {
				writeSelectedCells((Cell[][]) args, file);
			}

		} catch (Exception e) {

		}
	}

	/**
	 *
	 * Method used to write workbook into the PDF file
	 *
	 * @param workbook workbook
	 * @param file file
	 * @throws IOException exception
	 */
	public void writeWorkbook(Workbook workbook, File file) throws IOException {
		Document document = new Document(PageSize.A1);

		try {

			PdfWriter writer = PdfWriter.
				getInstance(document, new FileOutputStream(file));
			document.open();

			PdfPTable listSections = showListSections(document, writer);
			if (listSections.size() != 0) {
				document.add(listSections);
				document.newPage();
			}

			//added title workbook to pdf file
			PdfPTable title = addTitle("Workbook", 18, Element.ALIGN_CENTER);

			title.setSpacingAfter(10);
			document.add(title);

			for (int k = 0; k < workbook.getSpreadsheetCount(); k++) {

				Spreadsheet spreadsheet = workbook.getSpreadsheet(k);

				int row = spreadsheet.getRowCount();
				int columm = spreadsheet.getColumnCount();

				PdfPTable table = new PdfPTable(1);

				//added subTitle Spreadsheet to pdf file
				Anchor target = new Anchor("Spreadsheet title:" + spreadsheet.
					getTitle());

				target.setName("Spreadsheet title:" + spreadsheet.
					getTitle());

				document.add(target);

				//added subTitle Cells to pdf file
				PdfPTable cellTitle = addTitle("Cells", 14, Element.ALIGN_CENTER);

				cellTitle.setSpacingAfter(100);
				document.add(cellTitle);

				//put information into table
				Font f = new Font();
				PdfPCell tableCell;
				for (int i = 0; i < row + 1; i++) {
					for (int j = 0; j < columm + 1; j++) {

						if (!spreadsheet.getCell(j, i).getValue().
							toString().isEmpty()) {
							tableCell = new PdfPCell(new Paragraph(spreadsheet.
								getCell(j, i).getValue().
								toString(), f));

							table.addCell(tableCell);
						}

					}
				}
				document.add(table);
				document.newPage();
			}
			document.close();
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		}

	}

	/**
	 * Method used to write Spreadsheet into the PDF file
	 *
	 * @param spreadsheet Spreadsheet
	 * @param file file
	 * @throws IOException exception
	 */
	public void writeSpreadsheet(Spreadsheet spreadsheet, File file) throws IOException {
		Document document = new Document(PageSize.A1);

		try {

			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			int row = spreadsheet.getRowCount();
			int columm = spreadsheet.getColumnCount();

			PdfPTable table = new PdfPTable(1);

			//added title Spreadsheet to pdf file
			PdfPTable spreadSheetTitle = addTitle("Spreadsheet title:", 18, Element.ALIGN_CENTER);

			document.add(spreadSheetTitle);
			//added subTitle Cells to pdf file
			PdfPTable cellTitle = addTitle("Cells", 16, Element.ALIGN_LEFT);

			document.add(cellTitle);

			//put information into table
			Font f = new Font();
			PdfPCell tableCell;
			for (int i = 0; i < row + 1; i++) {
				for (int j = 0; j < columm + 1; j++) {

					if (!spreadsheet.getCell(j, i).getValue().
						toString().isEmpty()) {
						tableCell = new PdfPCell(new Paragraph(spreadsheet.
							getCell(j, i).getValue().
							toString(), f));

						table.addCell(tableCell);
					}
				}
			}
			document.add(table);

			document.close();
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		}

	}

	/**
	 * Method used to write a range of cells into the PDF file
	 *
	 * @param cells range of cells
	 * @param file File
	 * @throws IOException exception
	 */
	public void writeSelectedCells(Cell[][] cells, File file) throws IOException {
		Document document = new Document(PageSize.A1);

		try {

			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			PdfPTable table = new PdfPTable(1);

			//added title Cells in pdf file
			PdfPTable cellTitle = addTitle("Cells", 18, Element.ALIGN_CENTER);

			document.add(cellTitle);
			int row = cells.length;
			int columm = cells[0].length;

			//put information into table
			Font f = new Font();
			PdfPCell tableCell;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < columm; j++) {

					if (!cells[i][j].
						getValue().toString().isEmpty()) {
						tableCell = new PdfPCell(new Paragraph(cells[i][j].
							getValue().toString(), f));

						table.addCell(tableCell);
					}
				}
			}
			document.add(table);

			document.close();
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		}

	}

	/**
	 * This method will add a title or a sbuTitle to the pdf file
	 *
	 * @param name title name
	 * @param size tile size
	 * @param alignment title alignment
	 * @return Table with the title
	 */
	public PdfPTable addTitle(String name, int size, int alignment) {
		PdfPCell cell;
		PdfPTable title = new PdfPTable(1);
		cell = new PdfPCell(new Paragraph(name, FontFactory.
										  getFont(FontFactory.TIMES_BOLD, size, Font.BOLD, BaseColor.BLACK)));
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(Rectangle.NO_BORDER);
		title.addCell(cell);
		return title;
	}

	/**
	 * This method writes into the document the list of sections if user select
	 * that option
	 *
	 * @param document Document
	 * @param writer writer
	 * @return PdfPTable
	 */
	public PdfPTable showListSections(Document document, PdfWriter writer) {
		PdfPTable listSections = new PdfPTable(1);

		//add list of sections
		if (showList) {
			PdfPTable titleSections = addTitle("List of Sections", 18, Element.ALIGN_CENTER);

			try {
				document.add(titleSections);
			} catch (DocumentException ex) {
				Logger.getLogger(ExportPDF.class.getName()).
					log(Level.SEVERE, null, ex);
			}

			Workbook workbook = UIController.getUIController().
				getActiveWorkbook();
			for (int k = 0; k < workbook.getSpreadsheetCount(); k++) {

				Spreadsheet spreadsheet = workbook.getSpreadsheet(k);
				Paragraph linkToSection = new Paragraph();
				Anchor anchor = new Anchor("Spreadsheet title:" + spreadsheet.
					getTitle());

				anchor.setReference("#Spreadsheet title:" + spreadsheet.
					getTitle());
				linkToSection.add(anchor);
				listSections.addCell(linkToSection);
				listSections.setTotalWidth(700);
				listSections.writeSelectedRows(0, 0, 36, 36, writer.
											   getDirectContent());

			}

			return listSections;
		}
		return listSections;
	}

	/**
	 * This method return a boolean expression to check
	 *
	 * @param showList boolean
	 */
	public void applyList(boolean showList) {
		this.showList = showList;
	}

}
