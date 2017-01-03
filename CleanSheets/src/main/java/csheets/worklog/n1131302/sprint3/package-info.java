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
 * <h2>2. Use Case/Feature: Lang04.2</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-37">LPFOURDG-37</a>
 *
 * <h2>3. Requirement</h2>
 * 
 *
 * <p>
 * <b>Insert Function Intermediate Wizard:</b> The wizard window should display an edit box for each parameter of the selected function. 
 * The user should use these edit boxes to enter the values for each parameter of the function. As the user enters the values the wizard should display (in a new region of the window)
 * the result of the execution of the formula or a message explaining the problem. 
 * The function list should now include also the operators as well as the functions that are dynamically loaded from java.lang.Math. 
 * The wizard should be now launched from an icon or button located in the formula bar, between the label of the active cell and the edit box of the formula/value of the current cell.
 * The menu option should be removed.
 *</p>
 *
 * <h2>4. Analysis</h2>
 * <p>
 * The user initiates the process by pressing a button of the formula bar, and not a menu, because this should be removed.  
 * You should see a new window with the user edit boxes for each parameter of the selected function.
 * To the extent that you are entering values in the parameters, it should be updated the result. Or display a message explaining the problem.
 * The result is displayed.
 *</p>
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Tests</h3>
 * see: <code>WizardControllerTest</code>
 *
 *
 * <p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <h3>Use Functions Intermediate Wizard:</h3>
 * <p>
 * <img src="doc-files/lang04_02_design1.png" alt="image">
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
package csheets.worklog.n1131302.sprint3;

class _Dummy_ {
}
