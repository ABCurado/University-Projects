package csheets.framework.search;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.comments.Comment;
import csheets.ext.comments.CommentableCell;
import csheets.ext.comments.CommentsExtension;
import csheets.ext.search.ui.SearchReplaceUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * This class is responsible for the search behaviour of the application.
 * Further, more advanced search techniques should be implemented/added here.
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 * @author José Barros
 */
public class WorkBookSearch {

	/**
	 * The Workbook in which the search shall be performed
	 */
	private final Stack<Workbook> workBooks;

	/**
	 * The constructor
	 *
	 * @param workBooks All the open wokbooks
	 */
	public WorkBookSearch(Stack<Workbook> workBooks) {
		this.workBooks = workBooks;
	}

	/**
	 * Returns the list of results matching then given pattern NB: Calls private
	 * method
	 *
	 * @param pattern regex pattern
	 * @param types value types to be taken into consideration
	 * @param formulas true, if the text in the formulas should be considered
	 * @param comments true, if the text in the comments should be considered
	 * @return list of matching results
	 */
	public List<SearchResultDTO> getMatches(String pattern,
											Map<String, Value.Type> types,
											boolean formulas, boolean comments) throws PatternSyntaxException {

		// This is a stub, will be needed later and serves as test
		// for the pattern syntax
		Pattern regex = Pattern.compile(pattern);

		List<SearchResultDTO> results = new ArrayList<>();

		for (Workbook workBook : this.workBooks) {
			for (int i = 0; i < workBook.getSpreadsheetCount(); i++) {
				Spreadsheet sheet = workBook.getSpreadsheet(i);
				results.
					addAll(getMatches(pattern, types, formulas, comments, sheet));
			}
		}

		return results;
	}

	/**
	 * Private method, called for each Spreadsheet in the Workbook
	 *
	 * @param pattern regex pattern
	 * @param types value types to be taken into consideration
	 * @param formulas true, if the text in the formulas should be considered
	 * @param comments true, if the text in the comments should be considered
	 * @param sheet the Spreadsheet
	 * @return list of matching results
	 */
	private List<SearchResultDTO> getMatches(String pattern,
											 Map<String, Value.Type> types,
											 boolean formulas, boolean comments,
											 Spreadsheet sheet) {
		List<SearchResultDTO> results = new ArrayList<>();
		int columns = sheet.getColumnCount();
		int rows = sheet.getRowCount();

		for (int i = 0; i <= columns; i++) {
			for (int j = 0; j <= rows; j++) {

				Cell cell = sheet.getCell(i, j);
				String content = cell.getContent();
				String value = cell.getValue().toString();

				if (formulas == true && cell.getFormula() != null) {
					String formula = cell.getFormula().toString();
					if (formula.matches(pattern)) {
						if (!results.contains(SearchResultAssembler.
							getResultInformation(sheet, cell))) {
							results.add(SearchResultAssembler.
								getResultInformation(sheet, cell));
						}
					}
				}

				if (comments == true) {
					CommentableCell commentableCell = (CommentableCell) cell.
						getExtension(CommentsExtension.NAME);

					List<Comment> commentList = commentableCell.
						getCommentsList();

					for (Comment comment : commentList) {
						if (comment.text().matches(pattern)) {
							if (!results.contains(SearchResultAssembler.
								getResultInformation(sheet, cell))) {
								results.add(SearchResultAssembler.
									getResultInformation(sheet, cell));
							}
						}
					}
				}

				// Should we match the empty cells if the pattern
				// allows it?
				if ((!content.isEmpty() && content.matches(pattern))
					|| (!value.isEmpty() && value.matches(pattern))) {

					if (!types.isEmpty()) {
						for (Map.Entry<String, Value.Type> entry : types.
							entrySet()) {
							if (cell.getValue().getType().equals(entry.
								getValue())) {
								if (!results.contains(SearchResultAssembler.
									getResultInformation(sheet, cell))) {
									results.add(SearchResultAssembler.
										getResultInformation(sheet, cell));
								}
							}
						}
					} else if (!results.contains(SearchResultAssembler.
						getResultInformation(sheet, cell))) {
						results.add(SearchResultAssembler.
							getResultInformation(sheet, cell));
					}
				}
			}
		}
		return results;
	}

	/**
	 * Replaces the value of search result.
	 *
	 * @param result search result
	 * @param newstring new string to replace
	 * @param spread spread of search result
	 */
	public void replaceMatchContent(SearchResultDTO result, String newstring,
									Spreadsheet spread) {
		String cellAddress = result.getCell();
		int row = Integer.parseInt(cellAddress.substring(1)) - 1;
		int column = cellAddress.charAt(0) - 'A';

		try {
			spread.getCell(column, row).setContent(newstring);

		} catch (FormulaCompilationException ex) {
			Logger.getLogger(SearchReplaceUI.class.getName()).
				log(Level.SEVERE, null, ex);
		}
	}

}
