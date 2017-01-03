/**
 * Technical documentation regarding the work of the team member (1141330)
 * Eduardo Marques during week3.
 *
 *
 * <p>
 * <b>Scrum Master:</b> no</p>
 * <p>
 * <b>Area Leader:</b> no</p>
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * First days dedicated to Analysis and Design, and look at my teammate work,
 * and how we search and preview that search. While that i was looking to the
 * classes that i should work.</p>
 *
 * <h2>2. Use Case/Feature: Ipc02.3</h2>
 *
 * <b>Issue in Jira:</b>
 *
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-56">LPFOURDG-55</a>
 * <b>Sub-tasks:</b><ul>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-391">LPFOURDG-244</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-392">LPFOURDG-245</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-393">LPFOURDG-246</a></li>
 * <li>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-394">LPFOURDG-247</a></li>
 * </ul>
 *
 * <h2>3. Requirement</h2>
 * <p>
 * <b>Use Case - Workbook Search:</b> The user indicates a pattern and a
 * directory to search for workbooks that resembles the pattern. The search
 * should be performed accordingly and while Cleansheets application is fully
 * available, therefore it should be performed as a background search. The user
 * can one-click over a specific Workbook found to see a preview or/and
 * double-click to open the Workbook on Cleansheets application. Now the user
 * can pen de woorkbook search in a window and perfom more than on search.. To
 * the main search the user should say if is an active search.</p>
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <p>
 * Analysis Diagram:</p>
 **<p>
 * <img src="doc-files/ipc02.3_analysis.png" alt="Class Diagram Analysis"></p>
 *
 * The Classes to implement are a little different than the others extension
 * that use the sidebar, this now display in a Window that handle these feature
 * required algorithms.<ul>
 * <li><b>AWSExtension:</b> This class contains data regarding the Extension:
 * Name, Version. It also provides access to this UIExtension, required to load
 * the Extension in the main User Interface of the application, and will extend
 * the Extension Class, as all other Extensions. (All inheritance will be
 * available further on this page).
 * </li>
 * <li><b>AWSUI:</b> Extends UIExtension as all other Extensions UI. It will
 * provide the Windows.</li>
 * <li><b>AWSPanel:</b> This class is the actual JFRAME and will contain all
 * components to interact with the User to perform the implemented features –
 * Searching for a Workbook, open a preview or/and open the Workbook to the
 * current workspace and if is an active window or not. This window, aside from
 * Buttons and Text Fields, has also a DefaultListModel to contain all searched
 * files and a DefaultTableModel to contain all data regarding the Workbook
 * Preview.</li>
 * <li><b>AWSController:</b> Class in charge of handling the Search and
 * providing the results to AWSPanel. It will also communicate with
 * WorkbookPreview and retrieve a preview of the given Workbook.</li>
 * <li><b>WorkbookPreview:</b> This Class builds a preview according the
 * Workbook received from AWSController. The preview is a 5*5 rectangular area
 * delimited by an upper left corner and a lower right corner. The first Cell
 * with content will fulfil the position (0; 0) and will serve as a reference
 * for the other cells. This class works as a <b>service</b> who builds 5*5
 * preview for any non-corrupted Workbook. The size of the preview can easily be
 * changed.</li>
 * </ul>
 *
 * <h3>Analysis of the core problem/functionality:</h3>
 * <p>
 * From the analysis made above, and the set of requirements needed for this
 * feature, the core functionality is to provide the user the ability to not
 * only search for workbooks in a specific directory but also to see a preview
 * of the found workbooks without performing the actual "opening" to the current
 * workspace. This feature will save time and will enhance the experience with
 * the application.</p>
 *
 * <h2>5. Design</h2>
 *
 * <h3>Functional Tests</h3>
 * <h3>UC Realization</h3>
 *
 * <b>Class Diagram:</b>
 *
 * <p>
 * <img src="doc-files/ipc02.3_class_diagram.png" alt="Class Diagram"></p>
 *
 * <b>Sequence Diagram:</b>
 * <p>
 * <img src="doc-files/ipc02.3_sequence_diagram.png" alt="Sequence Diagram"></p>
 *
 *
 * <h3>Design Patterns and Best Practices</h3>
 *
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Low Cowpling - High Cohesion.</p>
 *
 * <h2>6. Implementation</h2>
 *
 * This feature window provides the major interaction with the user and access
 * to its functionalities.The user is able to specify a directory to perform the
 * search and a pattern on how the search is based on. Two <b>Text Labels</b>
 * will be implemented alongside with 2 buttons and 1 check box:<ul>
 * <li><b>"..." button</b> - Opens a Directory Dialog. The user chooses a
 * directory on the machine.</li>
 * <li><b>"Search" button</b> - Starts a background search based on the
 * information provided. At this moment a second thread performs the search
 * while all features are available - asynchronous operation.</li>
 * <li><b>"Active" check box</b> - After clicking the search button if the
 * active check box is true, the app will keep search on the directory for the
 * pattern until the user says that want to stop. The user can stop on the
 * search button, that now becomes on "Cancel" Button.</li>
 * <li><b>A Grid</b> - (DefaultTableModel) will be implemented on the side bar
 * Extension. It will be a preview of one of the selected workbooks showing a
 * group of cells. The list of possible selected workbooks will be displayed
 * below the grid showing all workbooks found.</li>
 * <li>Also a <b>List</b> - (DefaultListModel) that will contain all Files found
 * from the Search</li>
 * </ul>
 * <h2>7. Integration/Demonstration</h2>
 *
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 * <p>
 * <b>Sunday</b>
 * </p>
 * Today:
 * <p>
 * 1. Analysis.
 * </p>
 * 2. Design
 * <p>
 * Blocking:
 * </p>
 * 1. nothing.
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * Yesteday:
 * <p>
 * 1. Analysis and Design developed as expected.</p>
 * <p>
 * Today:</p>
 * <p>
 * 1. Test Solutions Implementation.</p>
 * <p>
 * 2. Reunion with Product Owner.</p>
 * <p>
 * 2. Reunion with Manager.</p>
 * <p>
 * Blocking:</p>
 * <p>
 * 1. nothing.</p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>10.1. Design and Implementation</h3>
 * <p>
 * A more viable TDD approach was used and all tests made cover 100% of the code
 * outside UI.</p>
 *
 * <b>Evidences:</b>
 *
 *
 * <h3>10.2. Teamwork</h3>
 *
 * <h3>10.3. Technical Documentation</h3>
 *
 * @author Eduardo Marques 1141130@isep.ipp.pt
 */
package csheets.worklog.n1141330.sprint4;

class _Dummy_ {
}
