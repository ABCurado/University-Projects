/**
 * Technical documentation regarding the work of the team member (1130303) Rui Freitas during week3.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <h2>2. Use Case/Feature: IPC05.2</h2>
 *
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-64">LPFOURDG-64</a>
 *
 * <h2>3. Requirement</h2>
 * Cleansheets should now use the user name of the system as the basis for the
 * user profile of the chat. The end user should be able to add an icon or a
 * photo to its profile as well as a nickname. Each user should have have a
 * status (i.e., online or offline). Cleansheets should automatically discover
 * all users in the local network. The sidebar window should now have the
 * conversations organized by user. The window should also display the status of
 * the users and their icon and nickname. When a user state is offline it will
 * not receive any messages from other instances of Cleansheets. Profile
 * configuration and message history should be persistent.
 *
 * <p>
 * <b>Use Case "Chat Participants":</b> The user configures her chat profile
 * setting up a photo and a nickname. System shows the chat window displaying
 * other chat users found in the network. The user select one, types a message
 * and then sends it. The system adds the message to the history. Other
 * instances can send messages to the current user. If this happens the message
 * is shown in the chat window and added to the history sidebar.
 *
 *
 * <h2>4. Analysis</h2>
 * The user selects "Send Message" option in the "Chat Application" menu. The
 * system prompts the user to insert a nickname and photo. The user inserts a
 * nickname and selects a photo. The system saves the information and gets the
 * other available instances in the local network, and presents them in a chat
 * window. The user selects which instance to send the message. The system gets
 * the message, and sends them to the targeted instance. The system notifies the
 * other user with a popup with 5 seconds time. The system put the received/sent
 * message in a tree control (sidebar). The user closes the chat. The system
 * notifies all the other instances that it is offline.
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
 * <h4>Send Message proposal analysis</h4>
 * <p>
 * <img src="doc-files/chat_app_analysis_send.png" alt="image">
 *
 * <h4>Receive Message proposal analysis</h4>
 * <p>
 * <img src="doc-files/chat_app_analysis_receive.png" alt="image">
 * <p>
 *
 * From the previous diagram we see that we need to add a thread for each
 * conversation. Using a thread manager we can disable a thread when it´s
 * necessary.
 *
 * <h3>Analysis of Core Technical Problem</h3>
 * The core of communication is expected to communicate in udp and tcp protocols
 * connections.
 * <p>
 * <img src="doc-files/ipc05.2_chat_analysis.png" alt="image">
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Since this feature uses the network framework already implemented any tests
 * needed to be done are done on that level. For the realization of this use
 * case I only use/call this framework methods.
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We
 * will also need to create a subclass of UIExtension. For the sidebar we need
 * to implement a JPanel. In the code of the extension
 * <code>csheets.ext.chatApp</code> we can find examples that illustrate how to
 * implement these technical requirements. The following diagrams illustrate
 * core aspects of the design of the solution for this use case.
 *
 * <h3>Chat send Message</h3>
 * The following diagram shows the setup of the local connection when
 * cleansheets's user select share.
 * <p>
 * <img src="doc-files/ipc05.2_udp_design.png" alt="image">
 *
 *
 * <h3>Receive Message</h3>
 * The following diagram illustrates what happens when a instance of cleansheet
 * receive message.
 * <p>
 * <img src="doc-files/ipc05.2_received_design.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * Global Class Diagram
 * <p>
 * <img src="doc-files/ipc05.2_classdiagram.png" alt="image">
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * Used "Service" classes instead of having the controller directly controlling
 * networking related operations.
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/chatApp/application/package-summary.html">csheets.ext.chatApp.application</a><p>
 * <a href="../../../../csheets/ext/chatApp/ui/package-summary.html">csheets.ext.chatApp.ui</a><p>
 *
 * <h2>7. Integration/Demonstration</h2>
 * Since this feature was the continuation of the IPC05.1 feature I did
 * communicate with the my colleague that was responsable for that UC. I also
 * did a lot of pratical tests to ensure that the UC worked in different
 * scenarios.
 *
 * <h2>8. Final Remarks</h2>
 * As usual, Swing design was the hardest bit on this sprint. Other obstacle
 * that I had was to make sure that my design didn't fall when the application
 * is tested with a weak network infrastructure.
 *
 * <h2>9. Work Log</h2>
 * <p>
 * <b>Monday</b>
 * <p>
 * Today
 * <p>
 * 1. Analysis of the UC and started asking some explanations to the previous
 * colleague.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Today
 * <p>
 * 1. Started to implement my solution. Analysis and Design was made off the
 * record (old fashion paper).
 * <p>
 * Blocking:
 * <p>
 * 1. Swing design!
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Today
 * <p>
 * 1. Continued to implement my solution. Having a hard time to make the GUI
 * pretty and functional.
 * <p>
 * Blocking:
 * <p>
 * 1. Swing design!
 * <p>
 * <b>Thursday</b>
 * <p>
 * Today
 * <p>
 * 1. Finished the implementation in the morning.
 * <p>
 * Blocking:
 * <p>
 * 1. Windows firewall and poor network infrastructure problems!
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>10.1. Design and Implementation:3</h3>
 * I was a little behind in time because I lost a fair amount understanding how
 * the network infrastructure works and also how my previous colleague implement
 * the previous feature. Overall, I think I did a good job: all requirements
 * were done and the GUI final look was pretty and functional.
 * <p>
 * <b>Evidences:</b>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Rui Freitas <1130303@isep.ipp.pt>
 */
package csheets.worklog.n1130303.sprint3;

class _Dummy_ {
}
