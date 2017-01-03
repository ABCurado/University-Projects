package csheets.ext.search;

import csheets.core.Spreadsheet;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.framework.search.SearchResultDTO;
import csheets.framework.search.WorkBookSearch;
import csheets.ui.ctrl.UIController;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.PatternSyntaxException;

/**
 * The SearchController
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 * @author José Barros
 */
public class SearchController {

	/**
	 * Workbook search instance
	 */
	private WorkBookSearch workBookSearch;

	/**
	 * The constructor
	 */
	public SearchController() {
	}

	/**
	 * Searches given Workbook for given pattern
	 *
	 * @param workBooks All the open workbooks
	 * @param searchstring The string to match
	 * @param types Value Types to take in consideration
	 * @param formulas true, if the text in the formulas should be considered
	 * @param comments true, if the text in the comments should be considered
	 * @return result list
	 */
	public List<SearchResultDTO> searchWorkBook(Stack<Workbook> workBooks,
												String searchstring,
												Map<String, Value.Type> types,
												boolean formulas,
												boolean comments) throws PatternSyntaxException {
		this.workBookSearch = new WorkBookSearch(workBooks);
		List<SearchResultDTO> results = workBookSearch.getMatches(searchstring,
																  types, formulas, comments);
		return results;
	}

	/**
	 * Returns the spreadsheet object corresponding to the title passed to
	 * parameter.
	 *
	 * @param spreadsheetTitle title of spreadsheet founded
	 * @return spreadsheet object
	 */
	public Spreadsheet getSpreadSheetObject(String spreadsheetTitle) {

		UIController uiController = UIController.getUIController();

		Spreadsheet spread = null;
		for (int i = 0; i < uiController.getActiveWorkbook().
			getSpreadsheetCount(); i++) {

			if (uiController.getActiveWorkbook().getSpreadsheet(i).getTitle().
				compareTo(spreadsheetTitle) == 0) {
				spread = uiController.getActiveWorkbook().
					getSpreadsheet(i);
			}
		}

		return spread;
	}

	/**
	 * Calls the method replaceMatchContent in workbook search instance to
	 * replace the match content.
	 *
	 * @param result search result
	 * @param newstring new string to replace
	 * @param spread spreadsheet
	 */
	public void replace(SearchResultDTO result, String newstring,
						Spreadsheet spread) {
		this.workBookSearch.replaceMatchContent(result, newstring, spread);
	}

}
