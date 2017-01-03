/**
 * Technical documentation regarding the work of the team member (1131302) Gabriel Sousa during week3.
 *
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: No</b>
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: IPC 3.3</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-59">LPFOURDG-59</a>
 *
 * <h2>3. Requirement</h2>
 * 
 *
 * <p>
 * <b>Network Search by File Contents:</b> The network search should now be based on a pattern for the name of the workbook (as before) or a pattern of the contents of the workbook (or both). 
 * The search should also include not only the open workbooks but also the workbooks that exist in the remote file systems. 
 * The window with the list of results should be updated automatically as the replies are received.
 *</p>
 *
 * <h2>4. Analysis</h2>
 * <p>
 * Previously it should be possible to search workbooks active on the local network. 
 * Now, should enter the search inactive workbooks. Should be possible to base the search in a pattern contained in the workbook.
 * The results list must be updated automatically.
 *</p>
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Tests</h3>
 * see: <code></code>
 *
 *
 * <p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <h3>Network Search by File Contents:</h3>
 * <p>
 * <img src="" alt="image">
 * </p>
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * <h2>6. Implementation</h2>
 *Basically removed the menu option for the functions window, and for that window put a button.
 *The window had a makeover since the function parameters must now be inserted into specific text boxes and not directly in the formula. Here, with special attention that should be loaded text boxes necessary depending on the selected function.
 *Then I spent the implementation of a method in the controller to replace the parameters entered in the text boxes, the function parameters so that then with this formula you can calculate the result.
 *You can also get a brief help on the selected function by pressing the button "help"
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * 1. Analysis and design.
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * <p>
 * 1.Ajust the UI
 * <b>Evidences:</b>
 * </p>
 * <b>Wednesday:</b>
 * <p>
 * 1.Tests and implementation
 * <b>Evidences:</b>
 * </p>
 * - url of commit: ... - description: this commit is related to the
 * implementation of the design pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * /**
 *
 * @author Gabriel Sousa
 */
package csheets.worklog.n1131302.sprint4;

class _Dummy_ {
}
