/**
 * <p>Technical documentation regarding the work of the team member (1141071)
 * Rui Bento during week2.</p>
 *
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 *
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 * <p>Since the Macro feature is not being developed at the moment, it will
 * be possibly to create any BeanShell script but not using an example with
 * macro as it was required. Although, it will be easily to implement macros
 * once they are ready (available).</p>
 * <p>Helped the LANG06.1 understanding and with interpretation of UC.</p>
 * <p>Helped the IPC02.1 understanding and with interpretation of UC.
 * Participation on discussions about the code design and implementation.</p>
 * <p>Helped the CRM04.1 with the UI implementation.</p>
 * <p>Helped the CRM05.1 with the UI design.</p>
 * 
 *
 * <h2>2. Use Case/Feature: LANG07.1 - BeanShell Window</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-45">LPFOURDG-45</a>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>Cleansheets should have an extension to support scripts in the BeanShell
 * scripting language. Macros and BeanShell should be in the same extension.
 * The extension should add a menu option that will launch a window to edit and
 * execute a BeanShell script. BeanShell and Macros should share the same
 * editing window (please see Lang05). The idea is that the user will be able
 * to select (in this window) the type of language he wants to use to customize
 * Cleansheets. In this iteration, and to test BeanShell support, if the user
 * selects the BeanShell language, the edit window should automatically be
 * filled with a script that: (1) opens a new workbook, (2) creates a macro,
 * (3) run the created macro and (4) displays a window with the result of the
 * invocation of the macro. Similarly to macros, the result of a BeanShell
 * script is the last value (result of the last instruction). The result of
 * executing the BeanShell script should be displayed in the same area of the
 * edit window that also displays the results of the execution of macros.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>Create an extension to allow to create BeanShell scripts, sharing a window
 * with macro feature. Once selected the option BeanShell, is shown an
 * example of a script.
 * If the script is correct, show the result. But if something is wrong it
 * will be shown an error.</p>
 * 
 * <h2>5. Design</h2>
 *
 * <p>Diagram of the functionality of the feature</p>
 *
 * <img src="doc-files/lang07_01_design1.png" alt="image">
 * 
 * <h3>5.3. Classes</h3>
 * <ul>
 * <li>BeanShell</li>
 * <li>Script</li>
 * <li>MacroBeanShell :
 *      <ul>
 *      <li>Extension</li>
 *      <li>Controller</li>
 *      <li>Panel</li>
 *      <li>UIExtension</li>
 *      </ul>
 * </li>
 * <li>CirclePanel</li>
 * </ul>
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Friday</b>
 * <ul>
 * <li>Reading feature requirements understanding how the feature should be.</li>
 * <li>Preparing questions for PM (Project Manager).</li>
 * </ul>
 * 
 * 
 * <b>Saturday</b>
 * <ul>
 * <li>Questioning the PM and finishing UC analysis.</li>
 * <li>Reading about BeanShell syntax.</li>
 * </ul>
 * 
 * 
 * <b>Sunday</b>
 * <ul>
 * <li>Starting UC design. (Creating diagrams and documentation)</li>
 * <li>Preparing some tests.</li>
 * </ul>
 * 
 * 
 * <b>Monday</b>
 * <ul>
 * <li>Clarify doubts about the UC with Project Manager</li>
 * <li>Finish UC design.</li>
 * <li>Starting the UI design.</li>
 * </ul>
 *
 *
 * <b>Tuesday</b>
 * <ul>
 * <li>Finish the UI design.</li>
 * <li>Complete implementation of UC code.</li>
 * </ul>
 * 
 * 
 * <b>Wednesday</b>
 * <ul>
 * <li>Bug fixes.</li>
 * <li>Unit Tests created</li>
 * <li>JavaDoc</li>
 * </ul>
 * 
 * 
 * <b>Thursday</b>
 * <ul>
 * <li>Presentation of UC.</li>
 * </ul>
 * 
 *
 * <h2>10. Self Assessment</h2>
 * -
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 *
 * <b>Evidences:</b>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-165">Analysis and requirements</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-166">Design</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-167">Code Implementation</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-168">Test Implementation</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/37146d64be29c04258493b829a2884ded1a4e977">CirclePanel Implementation</a>
 * 
 * <h3>10.2. Teamwork: ...</h3>
 * 
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Rui Bento
 */
package csheets.worklog.n1141071.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rui Bento
 */
class _Dummy_ {
}
