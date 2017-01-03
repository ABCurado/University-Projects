/**
 * Technical documentation regarding the work of the team member (1131190) João
 * Martins during week3.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: yes</b>
 *
 * <h2>1. Notes</h2>
 *
 *
 *
 * <h2>2. Use Case/Feature: IPC01.3</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-53">LPFOURDG-75</a>
 *
 * <h2>3. Requirement</h2>
 * It should be possible to have multiple cell shares active at the same time.
 * Each of the shares should have a unique name. The location (i.e., range
 * address) of the share in each instance of Cleansheets may be different. It
 * should be possible to share ranges that include cells with formulas.
 *
 * <h2>4. Analysis</h2>
 * I began by analyzing the code of the pasts ucs (IPC01.1 and IPC01.2) to see
 * how my colleague had developed the solution. First of all it will be
 * necessary add the ability to share formulas. For this its only necessary
 * update the method of my colleague. I realized that the code already produced
 * has the ability to connect to multiple instances of clean sheets, but also
 * realized that there is an error that must be corrected as it is currently not
 * possible to share the information from a set of selected cells. The shared
 * information is from all cells.
 *
 *
 * <h2>5. Design</h2>
 *
 * <img src="doc-files/ipc01_3_design.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Saturday</b>
 * <p>
 * Reading feature requirements and analysing formulas code in order to
 * understand how antlr and grammars are integratd in the project
 * <p>
 *
 * <b>Monday</b>
 * <p>
 * Analysis and design of the UC
 * <p>
 *
 * <b>Tuesday</b>
 * <p>
 * Implmentation of the UC
 * <p>
 * Helping other collegues
 * <p>
 *
 * <b>Wednesday</b>
 * <p>
 * Tests in the implementation
 * <p>
 * Helping other collegues
 * <p>
 *
 * <b>Thursday</b>
 * <p>
 * Presentation to the client
 *
 *
 * <h2>10. Self Assessment</h2>
 *
 * -
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 *
 * <b>Evidences:</b>
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author João Martins
 */
package csheets.worklog.n1131190.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author João Martins
 */
class _Dummy_ {
}
