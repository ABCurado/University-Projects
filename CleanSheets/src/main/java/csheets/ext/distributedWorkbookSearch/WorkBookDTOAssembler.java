/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.distributedWorkbookSearch;

import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class WorkBookDTOAssembler {

	/**
	 * Maximum size for the spreadsheet tavle representation
	 */
	private static final int MAX_TABLE_SIZE = 10;

	/**
	 * Return new DTO
	 *
	 * @param workbook The workbook
	 * @return WorkBookDTO
	 */
	public static WorkBookDTO getWorkbookDTO(Workbook workbook) {
		String wbFileName = workbook.getParentFileName();
		List<String> spreadsheets = new ArrayList<>();
		List<String[][]> cells = new ArrayList<>();

		int spreadCount = workbook.getSpreadsheetCount();

		/* For each Spreadsheet */
		for (int i = 0; i < spreadCount; i++) {

			Spreadsheet current_spreadsheet = workbook.getSpreadsheet(i);
			/* Save the spreadsheet title */
			spreadsheets.add(current_spreadsheet.getTitle());

			String[][] matrix = new String[MAX_TABLE_SIZE][MAX_TABLE_SIZE];
			/* For each line */
			for (int j = 0; j < MAX_TABLE_SIZE; j++) {

				/* For each column */
				for (int k = 0; k < MAX_TABLE_SIZE; k++) {

					matrix[j][k] = current_spreadsheet.getCell(k, j).getValue().
						toString();
				}
			}
			cells.add(matrix.clone());
		}

		return new WorkBookDTO(wbFileName, spreadsheets, cells);
	}
}
