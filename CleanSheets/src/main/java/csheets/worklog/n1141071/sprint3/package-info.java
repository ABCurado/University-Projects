/**
 * <p>Technical documentation regarding the work of the team member (1141071)
 * Rui Bento during week 3.</p>
 *
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 *
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * <p>Seemed pointless to create another UI since the CRM04.1 has a UI similar to
 * the one that was needed. So, the previous UI was modified and improved.</p>
 * <p>Due to uncommitted changes and pulled team changes, i was unable to commit
 * all my changes before Tuesday. I was obliged to clone another project and manually
 * merge all my previous changes.</p>
 * 
 *
 * <h2>2. Use Case/Feature: CRM04.2 - Lists Edition</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-85">LPFOURDG-85</a>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>It should be possible to create, edit and remove list notes. A list note
 * is similar to a textual note but each line is displayed as a check box (that
 * can be checked or unchecked). The first line is also interpreted as the title
 * of the list note. It should be possible to generate a new version of a text
 * note or list based on a old version of it. When this happens Cleansheets
 * should 'open' the new version for edit with the same contents of the old
 * version. This is the only 'trace' that may eventually link to the old
 * version.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>User can create, edit and remove lists. After a modification, the system
 * should create a new version of that list and show later to the user.</p>
 * <p>User can check each line of the list. The active list is always the last
 * version of the list.</p>
 * <p>When editing, user can choose any version of the list to start editing.</p>
 * 
 * <h2>5. Design</h2>
 *
 * <p>Diagram of the functionality of the feature</p>
 * <p>Main UC Diagram</p>
 * <img src="doc-files/crm04_02_design.png" alt="Main UC Diagram">
 * <p>Create List Diagram</p>
 * <img src="doc-files/crm04_02_design_create.png" alt="Create List Diagram">
 * <p>Edit List Diagram</p>
 * <img src="doc-files/crm04_02_design_edit.png" alt="Edit List Diagram">
 * <p>Delete List Diagram</p>
 * <img src="doc-files/crm04_02_design_delete.png" alt="Delete List Diagram">
 * 
 * <h3>5.3. Classes</h3>
 * <ul>
 * <li>List</li>
 * <li>Notation</li>
 * <li>Version</li>
 * <li>ListRepository</li>
 * <li>JpaListRepository</li>
 * <li>InMemoryListRepository</li>
 * <li>NotesLists Extension:
 *      <ul>
 *      <li>Extension</li>
 *      <li>Controller</li>
 *      <li>Panel</li>
 *      <li>UIExtension</li>
 *      </ul>
 * </li>
 * </ul>
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Friday</b>
 * <ul>
 * </ul>
 * 
 * 
 * <b>Saturday</b>
 * <ul>
 * <li>Reading feature requirements understanding how the feature should be.</li>
 * </ul>
 * 
 * 
 * <b>Sunday</b>
 * <ul>
 * <li>Starting UC design. (Creating diagrams and documentation)</li>
 * <li>Preparing the new UI to merge with the previous one.</li>
 * </ul>
 * 
 * 
 * <b>Monday</b>
 * <ul>
 * <li>Clarify doubts about the UC with Project Manager</li>
 * <li>Finish UC design.</li>
 * <li>Adding new features to UI.</li>
 * </ul>
 *
 *
 * <b>Tuesday</b>
 * <ul>
 * <li>Changing UI.</li>
 * <li>Implementing domain.</li>
 * </ul>
 * 
 * 
 * <b>Wednesday</b>
 * <ul>
 * <li>Reformulate UC design.</li>
 * <li>Changing UI.</li>
 * <li>Changes to domain.</li>
 * <li>Implementing persistence.</li>
 * </ul>
 * 
 * 
 * <b>Thursday</b>
 * <ul>
 * <li>Changes to worklog.</li>
 * <li>Changes to diagrams.</li>
 * <li>Implementing tests.</li>
 * </ul>
 * 
 *
 * <h2>10. Self Assessment</h2>
 * -
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * <b>Evidences:</b>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-220">Analysis and requirements</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-221">Design</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-222">Code Implementation</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-223">Test Implementation</a>
 * 
 * 
 * <h3>10.2. Teamwork:</h3>
 * <p>Helped the IPC06.2 understanding the UC.</p>
 * <p>Helped understand the previous code of last sprint</p>
 * 
 * <h3>10.3. Technical Documentation:</h3>
 * <p></p>
 *
 * @author Rui Bento
 */
package csheets.worklog.n1141071.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rui Bento
 */
class _Dummy_ {
}
