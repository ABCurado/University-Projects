/**
 * Technical documentation regarding the work of the team member (1030472) Bruno
 * Silva during week2.
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
 * -In this section you should register important notes regarding your work
 * during the week. For instance, if you spend significant time helping a
 * colleague or if you work in more than a feature-
 *
 * <h2>2. Use Case/Feature: Core01.2</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-2">LPFOURDG-6</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-191">LPFOURDG-120</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-192">LPFOURDG-121</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-193">LPFOURDG-122</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-194">LPFOURDG-123</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * Auto-Description of an extension. Add versions to an existing extension. Also
 * add one description * * to a version of an extension. Show extensions
 * caracteristics before loading them (Name, version, ** * description). The
 * extension is identified by hise name. The user should be able to cancel
 * extension * loading after reading description.</p>
 *
 * <p>
 * <b>Use Case 1 - Auto-Description of an extension:</b> The user selects the
 * extension he wants (regarding version and description).</p>
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
 *
 *
 * <h3>Class Diagram Analysis</h3>
 * <p>
 * <img src="doc-files/auto_description_class_diagram.png" alt="Class Diagram Analysis"></p>
 *
 *
 * <h3>5.3. Extension Setup</h3>
 *
 * <p>
 * <img src="doc-files/autodescription_version_1.png" alt="Sequence Diagram Design"></p>
 *
 *
 * <h2>6. Implementation</h2>
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
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * -In this section document your contribution and efforts to the integration of
 * your work with the work of the other elements of the team and also your work
 * regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 *
 * <h2>8. Final Remarks</h2>
 *
 * -In this section present your views regarding alternatives, extra work and
 * future work on the issue.-
 *
 * <h2>9. Work Log</h2>
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * <p>
 * - url of commit: - description: this commit is related to the implementation
 * of the design pattern-</p>
 *
 * <h3>10.2. Teamwork:</h3>
 *
 * <h3>10.3. Technical Documentation:</h3>
 *
 * @author Bruno Silva 1030472@isep.ipp.pt
 */
package csheets.worklog.n1030472.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Bruno Silva 1030472@isep.ipp.pt
 */
class _Dummy_ {
}
