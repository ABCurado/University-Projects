/**
 * Technical documentation regarding the work of the team member (1140780) Ruben
 * Teixeira during week4.
 *
 * <p>
 * <b>Scrum Master:</b> no
 *
 * <p>
 * <b>Area Leader:</b> no
 *
 * <h2>1. Notes</h2>
 *
 * TODO
 *
 * <h2>2. Use Case/Feature: Lang03.3- Tables and Filters</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-35">LPFOURDG-35</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-317">Analysis</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-318">Design</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-319">Tests</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-320">Implementation</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-321">Worklog</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * Add a new extension to support the concept of 'tables'. A table is
 * essentially a range of cells. The first row of this range of cells can be
 * used as header of the table columns (the contents of these cells become the
 * name of the columns).
 * <p>
 * Once a table is defined it should be possible to filter its contents by using
 * formulas. A formula that is used as a filter of a table is applied to each
 * row of the table. If the result is true, the row is visible, if the result is
 * false, the row should become invisible.</p>
 * <p>
 * To facilitate the writing of such formulas a new special variable should be
 * added to formulas. This new variable should be an array variable that
 * represents the value of the columns of the table for the current row. Lets
 * consider, for instance, that the new variable is called '_col'. For example,
 * it should be possible to use '_col[2]' to get the value of column 2 for the
 * current row. It should also be possible to use the name of the column instead
 * of the index. For instance, if the header of column 2 is 'cidade' it should
 * be possible the get the value of this column for the current row by using
 * '_col[“cidade“]'.</p>
 * <p>
 * An example of a filter for a table could be: '=or(_col[“idade“]>10;
 * _col[3]=123)'. This extension should add a new sidebar window that should be
 * used to edit tables and its filters.</p>
 *
 * <p>
 * <b>Use Case 1 - "Create table":</b> The user selects a range of cells, then
 * defines it as a table. The system validates if all the top cells of the
 * selected range have content and if it does, a new table is defined.</p>
 *
 * <p>
 * <b>Use Case 2 - "Filter table content":</b> The user selects an already
 * existant table, defines an expression in a textbox and applies. The system
 * validates if thje expression can be evaluated as a boolean and if so it will
 * apply the filter to the rows of the table.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>
 * After analysis of the requirements and the ConditionalFormatting
 * implementation the course of action will be to first implement UC1 ("Create
 * table") using the following approach:</p>
 *
 * <p>
 * <b>Create Table:</b></p>
 * <p>
 * <img src="doc-files/lang_03.3_create_table_analysis.png" alt="UC1 Analysis"></p>
 *
 *
 * <p>
 * <b>And the Create filter:</b></p>
 * <p>
 * <img src="doc-files/lang_03.3_create_filter_analysis.png" alt="UC2 Analysis"></p>
 *
 * <h3>Analysis of Core Technical Problem</h3>
 *
 * <p>
 * <b>This is the first approach to the problem after the analysis, in which a
 * new SpreadsheetExtension must be created: <code>SpreadsheetWithTables</code>.
 * </b></p>
 * <p>
 * <b>The plan to <code>_col[i]</code> or <code>_col["string"]</code>
 * interpretation is to perform substitution sometime before dispatching the
 * resulting string as an Expression to evaluation.</b></p>
 *
 *
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * Currently, the plan is to develop test for the expression substitution/
 * evaluation:</p>
 * <p>
 * For instance '_col[2]' being evaluated on the second row should map to
 * B2.</p>
 * <p>
 * Tests for table creation are also in order:</p>
 * <p>
 * After creating a table one should verify if saved cells and spreadsheet are
 * correct.</p>
 *
 *
 * <p>
 * <b>TableTest:</b></p>
 *
 *
 * <p>
 * see:</p>
 * <p>
 * <a href="../../../../../test/ext/tableFilters/TableTest">test.csheets.ext.tableFilters.TableTest</a></p>
 *
 * <ul>
 *	<li><code>testReplaceRelativeReferences()</code>: Tests if '_col[]'
 * references are correctly replaced by matching the result of the method with
 * the expected result.</li>
 *	<li><code>testInValidSizeTableCreation()</code>: Tests if invalid table sizes
 * throws exception as expected.</li>
 *	<li><code>testEmptyHeadersTableCreation()</code>: Tests if table creation
 * with empty headers fails. This ensures there will be no undefined behaviour
 * due to string column references as in '_col["header"]'.</li>
 *
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * The following Diagrams are useful to understand the UC Realization:</p>
 *
 * <p>
 * <b>Create Table SD:</b></p>
 * <p>
 * <img src="doc-files/lang_03.3_create_table_sd.png" alt="Sequence Diagram Design"></p>
 *
 * <p>
 * <b>Add filter to Table SD:</b></p>
 * <p>
 * <img src="doc-files/lang_03.3_create_filter_sd.png" alt="Sequence Diagram"></p>
 *
 *
 *
 * <h3>5.3. Extension Setup</h3>
 * <p>
 * The TableFilters Extention is loaded as per the following:</p>
 * <p>
 * <img src="doc-files/lang03.3_extension_load.png" alt="Extension Load"></p>
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Low Cowpling - High Cohesion.</p>
 * <p>
 * Observer - Implemented on Table & UI.</p>
 *
 *
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * Implementation was performed mainly as designed. The hidding of the rows
 * was accomplished through a 'TableFilter' composed of a 'RowFilter', which
 * for lack of time aren't properly documented but information about what they
 * do can be searched on <a href="http://stackoverflow.com/questions/17854854/jtable-rowfilter-and-rowfilter-entry">here</a> and
 * <a href="http://stackoverflow.com/questions/17854854/jtable-rowfilter-and-rowfilter-entry">here</a>.</p>
 *
 * <p>
 * see:</p>
 * <p>
 * <a href="../../../../csheets/ext/tableFilters/package-summary.html">csheets.ext.tableFilters</a></p>
 * <p>
 * <a href="../../../../csheets/ext/tableFilters/ui/package-summary.html">csheets.ext.tableFilters.ui</a></p>
 *
 * <p>
 * Commit Evidences:</p>
 *
 * <b>Analysis:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/64ef5cec13d278f43c040b96781e481667053242">Commit
 * concerning Analysis</a></p>
 *
 * <b>Design:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/20e225cdec9de5e5a71d42b45ee035c988bf5671">Commit
 * concerning Design</a></p>
 *
 * <b>Tests:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/ef42c927e89d6391c5e1ceac10ebe2fad740c5a8">Commit
 * concerning Tests #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/d122506211f96a22aa95aecaf7162cc487abf004">Commit
 * concerning Tests #2</a></p>
 *
 * <b>Implementation:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/b5e1fcb30035710a6f5ede5b2648fd4ffd7db5e3">Commit
 * #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/90b0def964a697f36d7021b60de21b9e7160ce52">Commit
 * #2</a></p>
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * <p>
 * On this sprint i demonstrated my own feature individually with great success.
 *
 * <h2>8. Final Remarks</h2>
 *
 * <p>
 * As previously, my intentions since the start of this sprint was to code while
 * thinking about future iterations. It was the most difficult feature i got but
 *  I believe my implementation was quite good taking into account that it was
 * accomplished without any changes to the core implementation and architecture
 * leaving no dependencies if disabled/removed.</p>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * 1. Nothing.
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * 1. Nothing.
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * 1. Analysis.
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * 1. Finished Analysis.
 * <p>
 * 2. Started Tests and Design while experimenting with the code...
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * 1. Finished Tests and design.</p>
 * <p>
 * 2. Finished implementation.
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * 1. Improved the feature by colouring the created or existing tables.</p>
 * <p>
 * 2. Demonstrated my Feature increment.</p>
 *
 * <p>
 * <b>Friday</b>
 * <p>
 * 1. Finished this worklog.</p>
 *
 * <h2>10. Self Assessment</h2>
 *
 *
 * <h3>10.1. Design and Implementation</h3>
 *
 * I believe this feature design and implementation was quite good as it is
 * accomplished without messing with core code or architecture.
 *
 * <h3>10.2. Teamwork:</h3>
 *
 * A lot of information exchange between me and my colleague Pedro Gomes. Some
 * pair programming aswell. This was invaluable this sprint.
 *
 * <h3>10.3. Technical Documentation:</h3>
 *
 * <p>
 * This is the final result of this sprint's feature:</p>
 *
 * <p>
 * <img src="http://i.imgur.com/xEtJWAE.jpg" alt="JPanel Final Result"></p>
 *
 *
 * <p>
 * <img src="http://i.imgur.com/KYPcJh3.jpg" alt="Request on target"></p>
 *
 *
 * <p>
 * <img src="http://i.imgur.com/S5ltr2X.jpg" alt="JPanel Final Result"></p>
 *
 *
 * <p>
 * <img src="http://i.imgur.com/nfzrxTi.jpg" alt="JPanel Final Result"></p>
 *
 *
 * <p>
 * <img src="http://i.imgur.com/jydiDP4.jpg" alt="JPanel Final Result"></p>
 *
 *
 *
 * @author Ruben Teixeira 1140780@isep.ipp.pt
 */
package csheets.worklog.n1140780.sprint4;

/**
 *
 *
 * @author Ruben Teixeira 1140780@isep.ipp.pt
 */
class _Dummy_ {
}
