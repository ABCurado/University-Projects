/**
 * Technical documentation regarding the work of the team member (1140260) Diogo
 * Leite during week3.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <b>Area Leader: -(yes/no)- no</b>
 * </p>
 *
 * <h2>1. Notes</h2>
 * <p>
 * My computer was disable for a few time so a ask for help of my class mates
 * and i made commits in two of my class mates.In bitbucket everything is
 * correct but in JIRA appears the names of my class mates The commit reference
 * was:
 * </p>
 * <p>
 * Commit in bitbucket:
 * <a href="2efdb46">commit 1</a>
 * </p>
 *
 *
 * <p>
 * Commit in bitbucket:
 * <a href="01bbf16">commit 2</a>
 * </p>
 *
 * <h2>2. Use Case/Feature: IPC06.2</h2>
 * <p>
 * alterar</p>
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-67">LPFOURDG-67</a>
 * </p>
 * <p>
 * LPFOURDG-67
 *
 * Cleansheets should now have a new sidebar window that displays a real time
 * chart of all incoming and outgoing network traffic. There should be four
 * columns: unsecure incoming; secure incoming; unsecure outgoing; secure
 * outgoing. The chart should automatically adjust the units used:
 * bytes;kilobytes; megabytes and gigabytes. The unit used should be the one
 * that results in a chart that is more adjusted to the size of the window.
 *
 * </p>
 *
 * <h2>3. Requirement</h2>
 *
 * It should be possible to display a real time chart that automatically adjust
 * the units used :bytes; kilobytes; megabytes and gigabytes.
 *
 * <p>
 * <b>Use Case "Network Analizer":</b>
 *
 * The user inputs a message, and chooses between a secure or insecure
 * connection, and finally it sends the message. The other instance should
 * receive a message with the connection chosen from the other instance.In this
 * process a real time chart is automatically updated
 *
 *
 * <h2>4. Analysis</h2>
 *
 *
 * <h3>Analysis diagram:</h3>
 *
 * <img src="doc-files/analysis.png" alt="Analysis">
 *
 * <h3>Network Analizer</h3>
 * <p>
 * We'll use JFreechart to display data in real time,to do that we use threads.
 * </p>
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>Sequence Diagram:</h3>
 * <img src="doc-files/final_design.png" alt="Final Design">
 *
 * <p>
 * <strong>Note:</strong> It is also important to note that the Service is
 * abstracted to work with the UDP or TCP protocol, which means that the class
 * represented as the "Service" in the sequence diagram can be either UdpService
 * or TcpService.
 * </p>
 *
 * <h3>Tests:</h3>
 * <p>
 * We're displaying a real time chart so,we are going to manually test the
 * network to see expected results.
 * </p>
 *
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * In regards to implementation, most of the feature implementation is done
 * through Volt.
 *
 *
 * <p>
 * As for displaying a real time char, Volt uses JFreechart
 * </p>
 * <h2>7. Integration/Demonstration</h2>
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Saturday</b>
 *
 * <p>
 * Reading feature requirements and analysing Volt
 *
 * <p>
 * Blocking :Nothing
 * </p>
 *
 * <b>Sunday</b>
 *
 * <p>
 * Analysis and begginng of Design
 *
 * <p>
 * Blocking :Nothing
 * </p>
 *
 *
 * <b>Monday</b>
 *
 * <p>
 * Start implemmentation UI and update on Design
 *
 * <p>
 * Blocking :Trying to understand JFreeChart
 * </p>
 *
 *
 * <b>Tuesday</b>
 *
 * <p>
 * Update on Design and continue on implementation
 *
 * <p>
 * Blocking :Still trying to work with JFreeChart
 * </p>
 *
 *
 *
 * <b>Wednesday</b>
 *
 * <p>
 * Ending implementation and small changes
 *
 * <p>
 * Blocking : Small changes in VOLT delayed a bit the project implementation
 * </p>
 *
 *
 *
 *
 * <b>Thursday</b>
 *
 * <p>
 * Minor changes,so the project can be more clean
 *
 * <p>
 * Blocking : nothing
 * </p>
 *
 *
 *
 *
 * <h2>10. Self Assessment</h2>
 * 4
 *
 * <h3>10.1. Design and Implementation:3</h3>
 * <p>
 * The implementation uses Volt, a package developed during the RCOMP course of
 * the current year (2015/2016), which aims to ease the development of
 * communication protocols with a expressive syntax.
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Diogo Leite
 */
package csheets.worklog.n1140260.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
