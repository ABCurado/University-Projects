/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.framework.search;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
import java.util.List;

/**
 * This class serves as the assembler to Search Results
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class SearchResultAssembler {

	/**
	 * Returns the SearchResultDTO translation of the given Core objects
	 *
	 * @param spreadSheet the Spreadsheet
	 * @param cell the Cell
	 * @return the SearchResultDTO
	 */
	public static SearchResultDTO getResultInformation(Spreadsheet spreadSheet,
													   Cell cell) {
		String workBookFileName = spreadSheet.getWorkbook().getParentFileName();
		String sheetName = spreadSheet.getTitle().trim().
			replaceAll("\\s+", "  ");
		String cellAddress = cell.getAddress().toString().trim();
		String cellContent = cell.getContent().trim();
		String cellValue = cell.getValue().toString().trim();
		List<Comment> cellComments = ((CommentableCell) cell.
			getExtension(CommentsExtension.NAME)).getCommentsList();
		return new SearchResultDTO(workBookFileName, sheetName, cellAddress,
								   cellContent, cellValue, cellComments);
	}

}
