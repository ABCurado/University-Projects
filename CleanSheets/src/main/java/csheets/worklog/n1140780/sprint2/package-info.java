/**
 * Technical documentation regarding the work of the team member (1140780) Ruben
 * Teixeira during week2.
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
 * Helped Pedro Gomes reproduce/fix a bug:
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/f0c55e41e41be9debb9cd4cea981d2b64c9c2717">Commit of the fix</a></p>
 * <p>
 * Also spent a whole afternoon and night on wednesday helping revamp the
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-4">Core02.2- Tooltip and User Associated to Comment</a> feature.
 * <p>
 * Resumably my work in the latter was redesigning the UI, implement the Value
 * Object class <code>Comment</code> (see:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-18">colleague's commit</a>
 * ) and later at night i fixed a few bugs and performed some aesthetic
 * improvements which I then logged and commited on it's own
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-209">sub-task.</a></p>
 *
 * <h2>2. Use Case/Feature: Core07.1</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-18">LPFOURDG-18</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-187">Analysis</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-188">Design</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-190">Tests</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-189">Implementation</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-209">Help Implement LPFOURDG-149 - Core02.2- Tooltip and User Associated to Comment</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-207">Worklog</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * An extension that adds support for searching the contents of workbooks. - The
 * extension should provide a new sidebar window for searching the contents of
 * the active workbook. The window should be composed of two parts. The first
 * part (upper part of the window) should contain a textbox for the user to
 * enter a regular expression to be the basis for the search. This part should
 * also contain a button to launch the search. The second part (lower part of
 * the window) should be used to display the search results (cell coordinates
 * and value or contents). The search should include no only the content of the
 * cell (i.e., the text entered by the user) but also its value (that could have
 * been calculated by a formula).</p>
 *
 * <p>
 * <b>Use Case 1 - "Search the contents of the active WorkBook":</b> The user
 * enters a regular expression on a textbox, then presses a button to start the
 * search. The system will then display the cell coordinates and content/value
 * of the search results</p>
 *
 * <p>
 * Window first Draft:</p>
 * <p>
 * <img src="http://i.imgur.com/Lij7lsd.png" alt="JPanel_draft"></p>
 *
 * <h2>4. Analysis</h2>
 * <p>
 * The ExtensionManager should load this (and others) Extension(s) and provide
 * them to the UIController. UIController is in charge of managing the Interface
 * and therefore it returns all UIExtensions from their corresponding
 * Extensions. The ExtensionManager is the link to all Extensions and
 * Cleansheets application.</p>
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
 * <b>SearchPanel Class:</b>
 * <p>
 * The actual panel that will be added as sidebar which contains the search box,
 * search button and results list.</p>
 *
 * <b>SearchresultDTO Class:</b>
 * <p>
 * This is the Data Transfer Object which will serve as the translation of core
 * objects to UI specific implementation. Thought for future iterations of this
 * feature.</p>
 *
 * <b>SearchresultAssembler Class:</b>
 * <p>
 * The translator. Should serve as good point to rearrange the way the results
 * are displayed (arranged by sheet/workbook etc)</p>
 *
 * <b>WorkBookSearch Class:</b>
 * <p>
 * This class is responsible for the search behaviour. Any enhancements to the
 * feature with respect to the search logic should be made here.</p>
 *
 * <h3>Class Diagram</h3>
 * <p>
 * <img src="doc-files/search_class_diagram.png" alt="Class Diagram"></p>
 *
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * The core problem is mainly the way the resulting information should be
 * presented. The goal, at first, was to aggregate each result on the
 * corresponding spreadsheet as per the draft shown upwards. That requires
 * either full access to core objects on the UI, or a Data Transfer Object.</p>
 * <p>
 * Although the decision was made to use a DTO, for simplicity and time concerns
 * the results won't be aggregated per sheet as of this sprint.</p>
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * Tests were performed for Class WorkBookSearch to check if the search works as
 * intended and for SearchResultAssembler to ensure the proper information was
 * being gathered and displayed.</p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * The following Diagrams are useful to understand the UC Realization:</p>
 * <p>
 * <img src="doc-files/search_extension_sd.png" alt="Sequence Diagram Design"></p>
 *
 * <h3>5.3. Extension Setup</h3>
 * <p>
 * The Search Extention is loaded as per the following:</p>
 * <p>
 * <img src="doc-files/core07_01_design.png" alt="Sequence Diagram Design"></p>
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Data Transfer Object implemented by SearchResultDTO.</p>
 * <p>
 * Low Cowpling - High Cohesion.</p>
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * The file containing the extensions name was the only file required to update
 * - extension.props. All other Classes where implemented.</p>
 * <p>
 * see:</p>
 * <p>
 * <a href="../../../../csheets/ext/search/package-summary.html">csheets.ext.search</a></p>
 * <p>
 * <a href="../../../../csheets/ext/search/ui/package-summary.html">csheets.ext.search.ui</a></p>
 * <p>
 * <a href="../../../../csheets/framework/search/package-summary.html">csheets.framework.search</a></p>
 *
 * <p>
 * Commit Evidences:</p>
 *
 * <b>Analysis:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/30579cf346e35642e71fa2edc643e9e7b64e6953">Commit concerning Analysis</a></p>
 *
 * <b>Design:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/aa9c04974805b955243d24db03128bbe37a30d5f">Commit concerning Design #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/d5d87b1bfd6da7e2f8631fe71b50005ec8d5ffc9">Commit concerning Design #2</a></p>
 *
 * <b>Tests:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/e65d6a279a51145e5ce49d3f1206adbddaf3a93d">Commit concerning Tests</a></p>
 *
 * <b>Implementation:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/7e0c63311c05ef5e39e1d562e8b52222927b660f">Commit concernation Implementation #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/24e3490725066d2a8eeacddb2a319e8a62bacf3d">Commit concernation Implementation #2</a></p>
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * <p>
 * On this sprint i demonstrated my own feature individually and helped prepare
 * the demo for
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-4">Core02.2- Tooltip and User Associated to Comment</a>
 * as well as help final testing of
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-6">Core03.1- Column Sort</a></p>
 *
 * <h2>8. Final Remarks</h2>
 *
 * <p>
 * During the design of this feature i always tried to make sure the next
 * iteration would be the more effortless as possible with small changes to
 * design and/or implementation.</p>
 * <p>
 * It was also implemented the class <code>SearchResultAssembler</code> which
 * for its simplicity is not documented here.</p>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * Today:
 * <p>
 * 1. Nothing.
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * Today:
 * <p>
 * 1. Nothing.
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Today:
 * <p>
 * 1. Analysis.
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Today:
 * <p>
 * 1. Finished Analysis.
 * <p>
 * 2. Started Design and resolved it.
 * <p>
 * 3. Started Tests.
 * <p>
 * 4. Started Implementation and resolved it.
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Today:</p>
 * <p>
 * 1. Finished Tests</p>
 * <p>
 * 2. Helped colleague. See Notes on top.
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Today:</p>
 * <p>
 * 1. Helped prepare the presentations on my Area</p>
 * <p>
 * 2. Did my presentation</p>
 *
 *
 * <p>
 * <b>Friday</b>
 * <p>
 * Today:</p>
 * <p>
 * 1. Finished this worklog.</p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * This sprint i consider my participation exponetially better as i felt more
 * confortable with the architecture and the new area to work on.
 *
 * <h3>10.1. Design and Implementation</h3>
 *
 * <p>
 * <b>Evidences:</b>
 *
 * <p>
 * Commit Evidences:</p>
 *
 * <b>Analysis:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/30579cf346e35642e71fa2edc643e9e7b64e6953">Commit concerning Analysis</a></p>
 *
 * <b>Design:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/aa9c04974805b955243d24db03128bbe37a30d5f">Commit concerning Design #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/d5d87b1bfd6da7e2f8631fe71b50005ec8d5ffc9">Commit concerning Design #2</a></p>
 *
 * <b>Tests:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/e65d6a279a51145e5ce49d3f1206adbddaf3a93d">Commit concerning Tests</a></p>
 *
 * <b>Implementation:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/7e0c63311c05ef5e39e1d562e8b52222927b660f">Commit concernation Implementation #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/24e3490725066d2a8eeacddb2a319e8a62bacf3d">Commit concernation Implementation #2</a></p>
 *
 * <h3>10.2. Teamwork:</h3>
 *
 * I believe the teamwork on this sprint was great as i learned a bit more by
 * helping other colleagues.
 *
 * <h3>10.3. Technical Documentation:</h3>
 *
 * <p>
 * I improved my documentation on this sprint as obviously i had more time to
 * prepare it aswell has i didn't had the learning curve associated with the
 * first sprint.</p>
 * <p>
 * This is the final result:</p>
 * <p>
 * <img src="http://i.imgur.com/TGO7a52.png" alt="JPanel Final Result"></p>
 * <p>
 * <img src="http://i.imgur.com/8uMRGXd.png" alt="JPanel Final Result"></p>
 * <p>
 * <img src="http://i.imgur.com/j0ao6vL.png" alt="JPanel Final Result"></p>
 *
 * @author Ruben Teixeita 1140780@isep.ipp.pt
 */
package csheets.worklog.n1140780.sprint2;

/**
 *
 *
 * @author Ruben Teixeira 1140780@isep.ipp.pt
 */
class _Dummy_ {
}
