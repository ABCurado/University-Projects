/**
 * Technical documentation regarding the work of the team member (1140364) José
 * Barros during week4.
 *
 * <p>
 * <b>Scrum Master:</b> no
 *
 * <p>
 * <b>Area Leader:</b> no
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * -In this section you should register important notes regarding your work
 * during the week. For instance, if you spend significant time helping a
 * colleague or if you work in more than a feature-
 *
 * <h2>2. Use Case/Feature: Core07.3</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-20">LPFOURDG-20:
 * Search and Replace</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-309">LPFOURDG-309:
 * Analysis</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-311">LPFOURDG-311:
 * Design</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-313">LPFOURDG-313:
 * Implementation</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-315">LPFOURDG-315:
 * Tests</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * An extension that adds support for searching the contents of workbooks.</p>
 *
 * <p>
 * <b>Use Case 3 - "Search and Replace":</b> The extension should now include a
 * new option "Search and Replace". This new window should be similar to the
 * search window but with an area to enter the replacing text. When search and
 * replace is launched, when a match is found, the window should display "what"
 * and where the match has occurred and how it will become after the replace.
 * The user should then confirm the replacement or select next (to continue the
 * search). The window should include a button to apply the replacing to all the
 * matches without asking each time. Similarly to the search only option, this
 * option should also have parameters to refine the search, for instance, what
 * type of cells to include in the search (or if it should include formulas or
 * comments).</p>
 *
 *
 * <h2>4. Analysis</h2>
 *
 * The ExtensionManager should load this (and others) Extension(s) and provide
 * them to the UIController. UIController is in charge of managing the Interface
 * and therefore it returns all UIExtensions from their corresponding
 * Extensions. The ExtensionManager is the link to all Extensions and
 * Cleansheets application.
 *
 * <b>ExtensionManager Class:</b>
 * <p>
 * Implements the Singleton pattern to guarantee that there is only one instance
 * running on each application. The Class links itself to a File -
 * "extension.props" - that contains the name reference for all available
 * extensions. When loaded the Extensions are saved into a TreeMap associated
 * with a name reference, a String.</p>
 * <p>
 * CleanSheets dynamically loads, at Runtime, all Extensions that
 * ExtensionManager finds declared in this file: "res/extensions.props". For
 * this specific case "csheets.ext.search.SearchExtension" was added.</p>
 *
 * <b>SearchExtension Class:</b>
 * <p>
 * An extension class should be implemented to support WorkBook search. The
 * class will extend, as all already implemented extensions, the: Extension
 * class.</p>
 *
 * <b>UIController Class: </b>
 * <p>
 * Provide us a crucial Attribute -uiExtensions : UIExtension[]. It will contain
 * all UIExtensions that extend UIExtension Class.</p>
 *
 * <b>UISearch Class: </b>
 * <p>
 * Extends UIExtension. Will provide the needed graphical components, including
 * the sidebar containing the actual functionalitys.</p>
 *
 *
 * <b>SearchController Class:</b>
 * <p>
 * This Class will be implemented to handle searching. It will contain a method
 * that will perform the search.</p>
 *
 * <b>SearchReplaceUI Class:</b>
 * <p>
 * The actual panel that will be added as menu option which contains a text
 * field to search a object, the search tools button, the text field to insert a
 * new object for replace and the search and replace button.</p>
 *
 * <b>SearchResultPanel Class:</b>
 * <p>
 * A search result panel that opens up when exist a match found where it is
 * presented "what" and where the match has occurred and how it will become
 * after the replace. Besides that exist too a button to apply the replacing to
 * all the matches without asking each time, the confirm button and the next
 * button (to continue the search).</p>
 *
 * <b>SearchToolsPanel Class:</b>
 * <p>
 * A helper panel that opens up when the search tools button (in the SearchPanel
 * class) is clicked, which will display a set of check boxes where the user
 * specifies what type of values to search for, and a toggle button that
 * indicates if the cells' comments should be included in the search as
 * well.</p>
 *
 * <b>SearchresultDTO Class:</b>
 * <p>
 * This is the Data Transfer Object which will serve as the translation of core
 * objects to UI specific implementation. Thought for future iterations of this
 * feature.</p>
 *
 * <b>SearchResultAssembler Class:</b>
 * <p>
 * The translator. Should serve as good point to rearrange the way the results
 * are displayed (arranged by sheet/workbook etc)</p>
 *
 * <b>WorkBookSearch Class:</b>
 * <p>
 * This class is responsible for the search behaviour. Any enhancements to the
 * feature with respect to the search logic should be made here.</p>
 *
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the
 * previously described use case. We call this diagram an "analysis" use case
 * realization because it functions like a draft that we can do during analysis
 * or early design in order to get a previous approach to the design. For that
 * reason we mark the elements of the diagram with the stereotype "analysis"
 * that states that the element is not a design element and, therefore, does not
 * exists as such in the code of the application (at least at the moment that
 * this diagram was created).
 *
 * <h4>Search and Replace proposal analysis</h4>
 * <p>
 * <img src="doc-files/core07.3_analysis.png" alt="image">
 * <p>
 *
 * From the previous diagram we see that we need to add a new functionality to
 * the UI and : receiving and searching specific value types, and know if it has
 * to search in a cell's comments aswell. Whenever has a match found, this
 * should be presented in a new panel with all the information of the result.
 * The user should confirm the search and replace. The window should include a
 * button to apply the replacing to all the matches without asking each time.
 *
 * <h3>Analysis of Core Technical Problem</h3>
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * For this user story , we can start by coding a unit test to verify if the
 * search result matches what we wanted seek. Another test can be see if the
 * replace is running correctly. Finally and not least will be to test the
 * option to make the replacing to all the matches.
 *
 * <p>
 * see: <code>csheets.framework.search.WorkBookSearchTest</code>
 * </p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * To realize this user story we will need to implement The following diagrams
 * illustrate core aspects of the design of the solution for this use case.
 * <h3>User starts a search (Global Search)</h3>
 * The following diagram shows how the search is made.
 * <p>
 * <img src="doc-files/core07.3_design.png" alt="image">
 *
 * <h3>Extension Setup</h3>
 *
 * The following sequence diagram illustrates the creation of the user interface
 * extension. All the UI extensions are loaded by the UIController at
 * application startup.
 *
 * <p>
 * <img src="doc-files/core07.3_extension_design_1.png" alt="image">
 *
 * <p>
 * The following sequences diagrams illustrates the creation of the menu
 * extension. All the menu extensions are loaded by the MenuBar at application
 * startup.
 *
 * <p>
 * <img src="doc-files/core07.3_extension_design_2.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 *
 * <p>
 * <b>Extension Setup</b>
 * <p>
 * <img src="doc-files/core07.3_extension_class.png" alt="image">
 *
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * <img src="doc-files/core07.3_class_diagram.png" alt="image">
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Data Transfer Object implemented by SearchResultDTO.</p>
 * <p>
 * Low Cowpling - High Cohesion.</p>
 * <p>
 * Observer implemented by SearchReplaceUI</p>
 *
 * <h2>6. Implementation</h2>
 *
 * Created the SearchReplaceUI and the SearchResultsPanel. Also updated the
 * SearchPanel (shortcut for search replace), SearchController and
 * WorkbookSearch.
 *
 * <p>
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/9a320788f6ee5387292a2eb15cc2eb1b1487fef1</p>
 * <p>
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/33250b97849ce7107ac62f024ac097d4b6ca34bb</p>
 * <p>
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b4eeb5c4b32bd886758b8a059ee038004d7ded99</p>
 * <p>
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/0e3ed0976c56a2ff4b1eafb59ab2f8d15715a099</p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * Updated some tests and added some more:
 * <p>
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/c06dd3e639d0470b213dee30d069063b5248a2bd</p>
 * <p>
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/5b41a5fb28c54a066a7cc88d7adb5d71a0a60595</p>
 *
 * <h2>8. Final Remarks</h2>
 *
 * The function "replace all" although be very well could be adjusted / modified
 * with the creation a unique method for this case.
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * Today:
 * <p>
 * 1. Analysis and design the problem. Start implementation. Initial UI.</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis and design the problem. Start implementation. Initial UI.</p>
 * <p>
 * Today:
 * <p>
 * 1. Creation of UI finalized. Exchange each value founded implemented.</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Creation of UI finalized. Exchange each value founded implemented.
 * Tests.</p>
 * <p>
 * Today:
 * <p>
 * 1. Replacing to all the matches without asking each time</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Creation of UI finalized. Exchange each value founded implemented.
 * Tests.</p>
 * <p>
 * Today:</p>
 * <p>
 * 1. Finish the tests. Another adjustments.</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finish the tests. Another adjustments.</p>
 * <p>
 * Today:</p>
 * <p>
 * 1. Presentation to client.</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * </p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/9a320788f6ee5387292a2eb15cc2eb1b1487fef1">Initial
 * UI</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/94792ed2bfbe986e1049ae7902ed69cadb30c3f5">Creation
 * of UI finalized. Exchange each value founded.</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/33250b97849ce7107ac62f024ac097d4b6ca34bb">Replacing
 * to all the matches without asking each time.</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/0e3ed0976c56a2ff4b1eafb59ab2f8d15715a099">Bug
 * in replace matches fixed. Search controller with more responsibility.</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/c06dd3e639d0470b213dee30d069063b5248a2bd">Tests.</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b4eeb5c4b32bd886758b8a059ee038004d7ded99">Some
 * adjustments in UI.</a></p>
 *
 * <h3>10.2. Teamwork:</h3>
 *
 * <h3>10.3. Technical Documentation:</h3>
 *
 * @author José Barros 1140364@isep.ipp.pt
 */
package csheets.worklog.n1140364.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author José Barros 1140364@isep.ipp.pt
 */
class _Dummy_ {
}
