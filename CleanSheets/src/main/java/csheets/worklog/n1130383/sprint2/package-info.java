/**
 * Technical documentation regarding the work of the team member (1130383) Pedro
 * Gomes during week2.
 *
 * <p>
 * <b>Scrum Master:</b> no
 *
 * <p>
 * <b>Area Leader:</b> yes
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * All work was done as planned. After being done with my feature I've helped to
 * implement Core01.2 Auto-description of Extensions and also discussing
 * Analysis and Design with my teammates and their respective feature.</p>
 *
 * <h2>2. Use Case/Feature: Core02.1</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-6">LPFOURDG-6</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-120">LPFOURDG-120</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-121">LPFOURDG-121</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-122">LPFOURDG-122</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-123">LPFOURDG-123</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * A new extension to add sort functionalities to Cleansheets - Sort the
 * contents of a column of cells. It should be possible to select the order:
 * ascending or descending. The interaction with the user can be based solely on
 * menu options. It should be possible to sort columns with numeric and/or text
 * contents.</p>
 *
 * <p>
 * <b>Use Case 1 - "Sort Column Ascending/Descending":</b> The user selects the
 * column and what type of sort he wants (ascending/descending). The Extension
 * proceeds to sort the column as required.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>
 * The Analysis of the ExampleExtension facilitates the understanding of this
 * feature and how to implement this new Sort Extension. As seen, the
 * ExtensionManager should load this (and others) Extension(s) and provide them
 * to the UIController. UIController is in charge for managing the Interface and
 * therefore it returns all UIExtensions from their belonging Extensions to the
 * MenuBar. The ExtensionManager is the link all Extensions and Cleansheets
 * application.</p>
 *
 * <b>ExtensionManager Class:</b>
 * <p>
 * Implements the Singleton pattern to guarantee that there is only one instance
 * running on each application. The Class links itself to a File -
 * "extension.props" - that contains the name reference for all available
 * extensions. When loaded the Extensions are saved into a TreeMap associated
 * with a name reference, a String.</p>
 * <p>
 * CleanSheetps dynamically loads, at Runtime, all Extensions that it finds
 * declared in this files: "res/extensions.props" and "entensions.props" by the
 * ExtensionManager. Both this files must contain the name of the Extension -
 * "csheets.ext.sort.SortExtension" for this specific case.</p>
 *
 * <b>SortExtension Class:</b>
 * <p>
 * Therefore, an extension class should be implemented to support cell sorting.
 * The class will extend, as all already implemented extensions, the: Extension
 * class (All Inheritance will be available further on this page).</p>
 *
 * <b>UIController Class: </b>
 * <p>
 * Provide us a crucial Attribute -uiExtensions : UIExtension[]. It will contain
 * all UIExtensions that extend UIExtension Class.</p>
 *
 * <b>UISort Class: </b>
 * <p>
 * Extends UIExtension. The User Interface known by the MenuBar of Sort
 * Extension.</p>
 *
 * <b>SortMenu Class:</b>
 * <p>
 * Represents the User Interface Menu for Sorting.</p>
 *
 * <b>SortAction Class:</b>
 * <p>
 * This Class will perform the Action, set the SortController to update the
 * Cleansheet application.</p>
 *
 * <b>SortController Class:</b>
 * <p>
 * This Class will be implemented to handle sorting. It will contain a method
 * that will perform the actual sorting of the chosen column. No other elements
 * will be required aside from a column : cell[] and specific order.</p>
 *
 * <b>Update on SortController Class after first Implementation:</b>
 * <p>
 * After spending some time with the Design and trying to imagine what would be
 * the best way to promote Low Coupling High Cohesion, I decided to give this
 * class access to UIController and therefore to the active Spreadsheet.</p>
 * <p>
 * The sorting will be performed on an Arraylist of Values. Before the actual
 * sorting occurs all Cells' Values will be added to this Arraylist to
 * facilitate the procedure. After that, the Controller will use this sorted
 * ArrayList to update the current selected column. On a previous version,
 * instead of an ArrayList of values and ArrayList of Strings was being used
 * because of the habit in using the method "getContent()" accessed by the cell
 * itself. The change was made duo to impossibility of ascending or descending
 * correctly. Example: with two Strings, one being "35" and other "4" the "35"
 * would actually be a "smaller" number when asceding.</p>
 *
 * <b>SortJDialog</b>
 * <p>
 * This window will interact with the user to select the column and the sorting
 * order.</p>
 * <p>
 * Window first Draft:</p>
 * <p>
 * <img src="http://i.imgur.com/N6MGrKJ.png" alt="JDialog Draft"></p>
 *
 *
 * <h3>Class Diagram Analysis</h3>
 * <p>
 * <img src="doc-files/sort_extension_1.png" alt="Class Diagram Analysis"></p>
 *
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * The Core problem lies on the algorithm used to show only the active columns.
 * The only method that already exists and can be helpful in this situation is
 * the "getColumnCount()" provided by the active Spreadsheet accessed by the
 * UIController. Even then, this method returns an int number accountable from
 * the first column to the last "active" column. In a situation where A,B,C
 * columns had cells with content the returned number would be 3 (scenario 1).
 * If only A and C had content the number would be the same(scenario 2). Same as
 * C being the only with content (scenario 3). A possible solution for the 3
 * scenarios above would be to allow the User to select either one of the three
 * columns. So all three cells would be available to select, no matter the
 * scenario. On the other hand the sorting algorithm will only target cells with
 * content so this doesn't represent an actual hard-to-solve problem.</p>
 * <p>
 * There was also the possibility to work with ascII code to operate with the
 * associated decimal, but for now the idea is on hold.</p>
 * <p>
 * Update on current subject:</p>
 * <p>
 * After considering using ascII code I've decided to proceed with this idea.
 * Turns out the solution was quite simple. The implemented code will allow
 * SortJDialog to show to the User all the columns that have content. The user
 * proceeds to select a column. This column references are Char type, this Char
 * will be added to the existing columnComboBox and the selected column index
 * will further be used.</p>
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * Tests will be performed for Class SortController to check if the order occurs
 * as planned.</p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * Final Version of Sort Extension interact window: A smaller and leaner version
 * of the first Analysis and implementation.</p>
 *
 * <p>
 * <img src="http://i.imgur.com/ZDUNUxO.png" alt="JDialog FinalVersion">
 * </p>
 *
 * <p>
 * The following Diagrams are useful to understand the UC Realization:</p>
 *
 * <h3>5.3. Extension Setup</h3>
 *
 * <h3>Extension Manager Interaction</h3>
 * <p>
 * <img src="doc-files/sort_extension_2.png" alt="Sequence Diagram Design"></p>
 * <h3>UICOntroller Interaction</h3>
 * <p>
 * <img src="doc-files/sort_extension_3.png" alt="Sequence Diagram Design"></p>
 * <h3>MenuBar Interaction</h3>
 * <p>
 * <img src="doc-files/sort_extension_4.png" alt="Sequence Diagram Design"></p>
 * <h3>Sequence Diagram</h3>
 * <p>
 * <img src="doc-files/sort_extension_5.png" alt="Sequence Diagram Design"></p>
 *
 * <h3>5.4. Classes</h3>
 * <p>
 * Everything inside *[expression]* was implemented before JDialog
 * implementation. SortAction was directly connected to SortController</p>
 *
 * <h3>Class Diagram</h3>
 *
 * <p>
 * <img src="doc-files/sort_class_diagram.png" alt="Class Diagram Analysis"></p>
 *
 * <h3>5.5. Design Patterns and Best Practices</h3>
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Patterns promoting Low Cowpling - High Cohesion.</p>
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * The file containing the extensions name was the only file required to update
 * - extension.props. All other Classes where implemented.</p>
 * <p>
 * Also, it was found a <b>bug</b> on SpreadsheetImpl in method getColumn(index)
 * - For loop limiter wasn't valid, that leads to the update of the current
 * class also. This bug also exists in the original version of the Author Einar
 * Pehrson where the last update ocurred in 2013-05-02.</p>
 * <p>
 * Update on this subject:</p>
 * <p>
 * There was also another bug in the same method where the size assign to colum
 * (: Cell[]) is the same as "rows" - a specific attribute that is equivalent to
 * the the number of rows in the current Spreadsheet. This variable is getting
 * its result from cells with content. The address of each cell is obtainable by
 * its current position in the spreadsheet matrix[column][row]. The cell "B2"
 * has its position in matrix[1][1]. Therefore, when updating the attribute
 * using B2 "rows" it will be assigned to him the value: 1. When returning a
 * column from the active Spreadsheet the returned array size will be also 1,
 * creating space for only one value that, in this scenario, would be A2 value.
 * B2 value wouldn't be stored. This bug took a while to solve, not because its
 * complexity but because it lead to a in-depth revision of the already
 * implemented code. Also, my analysis to this problem might be wrong, I'm
 * afraid that something was left unchecked. Because a this more time was needed
 * than the previous time planned for the implementation. The current solution
 * was implemented and its seems to be working great. My teammate Rúben Teixeira
 * helped in this fix realization.</p>
 * <p>
 * <b>Current fix:</b></p>
 * <p>
 * <img src="http://i.imgur.com/mQ5jmTq.png" alt="Fix"></p>
 * <p>
 * Commit</p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/f0c55e41e41be9debb9cd4cea981d2b64c9c2717">Commit
 * concerning the fix described.</a></p>
 *
 *
 *
 * <p>
 * Commit Evidences:</p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/3dbf709a0a5f3dc77b3f56d7fb028a66c308db0c">Commit
 * concerning Analysis and Design</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/2c5a95e9c69bd0fe2903809e04a3d65be54d9e45">Commit
 * concerning the fix for the bug, and first steps into Implementation</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a1a78746338c6c0666d8854cb21c4b35f2353465">Commit
 * concerning JDialog implementation and first deployment of Sort
 * Extension</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/f0c55e41e41be9debb9cd4cea981d2b64c9c2717">Commit
 * concerning JDialog re-design, final deployment of Sort Extension.</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/4ff6014ccddc64aafa90c286d337eb7d63b5e682">Commit
 * concerning Auto-description implementation</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e97b257c5c6a04a9d51931be788c650582a80954">Commit
 * concerning Sort Extension Tests implementation.</a></p>
 *
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 * <p>
 * Durante this week, one day before project submission, my colegue in charge of
 * implementing Core01.2 feature - Auto-description of Extensions - was facing
 * major dificulties. I've proceed to implement his previous analysis and design
 * on this subject.</p>
 * <p>
 * How it was implemented:</p>
 * <p>
 * After loading the file "extensions.pros" to ExtensionMangar a Frame will be
 * presented to user (ExtensionManagerFrame). From that frame the user decides
 * what Extensions will load to the application. The current implemented
 * approach to solve this problem implies the edition of the current
 * "extensions.props" file by adding a version and a description next to path.
 * When adding a new path extension the developer must also introduce the
 * version and a short description of what the version current does.</p>
 *
 *
 * <h2>8. Final Remarks</h2>
 *
 * <p>
 * ExtensionManagerFrame will be disabled while implementing features for sprint
 * 3 not only because it's more conveniente but also because it must suffer a
 * crucial update. Sort v1 feature is ready to be extended my other team members
 * on the next sprint.</p>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * Today:
 * <p>
 * 1. Analysis.
 * <p>
 * 2. Design.
 *
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * Today:
 * <p>
 * 1. Updated Analysis.
 * <p>
 * 2. Started and mostly finished Design.
 * <p>
 * 3. Started Implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Today:
 * <p>
 * 1. Feature fully available, finished Implementation but will need
 * refactoring.
 * <p>
 * 2. Altered Design, code refactoring was needed and most likely will be needed
 * again. With it, the previous designed and implemented test solution will too
 * need to change.
 * <p>
 * 3. Altered Diagrams according to SortJDialog implementation.
 * <p>
 * 4. Reunion with Product Owner.
 * <p>
 * 5. Reunion with Manager.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Today:
 * <p>
 * 1. Fixed a bug where the last cell with content wasn't being sorted. The fix
 * was applied to SpreadsheetImpl Class in the method "getColumn". My teammate
 * Rúben Teixeira - 1140780, helped in this fix realization.
 * <p>
 * 2. Reunion with Core Team for this Sprint. Analysis on all to-implement
 * features where discussed.
 * <p>
 * 3. Re-Design of SortJDialog.
 * <p>
 * 4. Reunion with Supervisor.
 * <p>
 * Blocking:
 * <p>
 * 1. nothing.
 *
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Today:</p>
 * <p>
 * 1. Core01.2 Implementation - Auto-description of Extensions.</p>
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Today:</p>
 * <p>
 * 1. Demonstration.</p>
 * <p>
 * 2. Sprint Review.</p>
 * <p>
 * 3. Code Submission - End of sprint.
 *
 * <h2>10. Self Assessment</h2>
 * <p>
 * All features worked as expected.
 *
 * <h3>10.1. Design and Implementation</h3>
 * <p>
 * 5.</p>
 *
 * <h3>10.2. Teamwork:</h3>
 * <p>
 * 5.</p>
 *
 * <h3>10.3. Technical Documentation:</h3>
 * <p>
 * 4.</p>
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
package csheets.worklog.n1130383.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
class _Dummy_ {
}
