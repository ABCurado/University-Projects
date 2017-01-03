/**
 * Technical documentation regarding the work of the team member (1140263) Joao Martins during week2
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 *
 * -Notes about the week's work.-
 *
 *
 * <h2>2. Use Case/Feature: IPC07.1 - Choose Game and Partner</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-69">LPFOURDG-69</a>
 * <p>
 * Sub-Task in Jira:
 * <p>
 * Analysis:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-124">LPFOURDG-124</a>
 * <p>
 * Design:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-125">LPFOURDG-125</a>
 * Tests:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-126">LPFOURDG-126</a>
 * Implementation:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-127">LPFOURDG-127</a>
 *
 *
 * <h2>3. Requirements</h2>
 * Implement extension to choose game. Establish a connection to play games.
 * List all the users in the local network. Invite/send request to play a game
 * (one of two games - not available). Define the profile of the user - username
 * (username from the system) and photo. Display all the available users to
 * play. User should be able to play more than one game at the same time.
 * Sidebar to display all the active games and all the online users. Should be
 * possible to end a game.
 *
 *
 * <h2>4. Analysis</h2>
 * Some of the requirements are already implemented (week1). I need to
 * use/implement udp/tcp protocols - List all "online" users (UDP) , Send
 * request to an other player TCP etc. It's necessary to implement the user
 * profile requirement. In the sidebar i will put the 2 options of games - the
 * games are not implemented yet in this week - only for demonstration and
 * starting point to my colleagues. To end a game it is necessary to stop the
 * service - connection established between the "online" clients.
 *
 * Its necessary implement an user interface with these options. First of all i
 * need to offer to the user the profile definition. Seach and select a photo
 * from the local machine. Second search for all the online users in a
 * particular network. After the selection of the user to connect it's important
 * to select a game (two options - tic tac toe and battleship). This games are
 * dummy classes only for demonstration. I also offer three options - connection
 * ; play game (dummy) ; end connection (from a list with the games and
 * partner).
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Test the broadcast - check if the online instances are added to the list.
 * Check if the own computer dont appear in the list of instances. Connection
 * between 2 instances. Close connection.
 *
 * <h3>5.2. </h3>
 *
 * <b>Sequence Diagram - Get Instance and select/send request to another
 * instance</b>:
 * <img src="doc-files/ip07.1_getInstances.png" alt="SD">
 *
 * <b>Sequence Diagram - Main Sequence</b>:
 * <img src="doc-files/ip07.1_main_sequence.png" alt="SD">
 *
 * <p>
 * System Sequence Diagram: 1. Set profile photo 2. Broadcast (Udp) - search for
 * "online" instances 3. Select instance to connect/play game 4. Select game 5.
 * Connect to other instance - connect 6. Play game - not implemented in this
 * feature
 *
 * Note: It is possible to end an established game.
 *
 *
 * <h3>5.3. Classes</h3>
 * <p>
 * 1.GamePanel 2.Battleships and TicTacToe - Dummy classes 3. GameExtension 4.
 * ContactCardPanel - not used yet 5. GameController 6. ProfileOpponent not used
 * yet 7. TcpService 8. UdpService 9. UiGameExtension - not used in this way of
 * implementation
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Concepts from RCOMP. TCP and UDP protocols. Layers - UI; Controller; Domain
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * <b>Created Classes</b>: 1. GamePanel 2. Battleships and TicTacToe - Dummy
 * classes 3. GameExtension 4. ContactCardPanel - not used yet 5. GameController
 * 6. ProfileOpponent not used yet 7. TcpService 8. UdpService 9.
 * UiGameExtension - not used in this way of implementation
 * <p>
 *
 * <b>Updated Classes/Files</b>: 1. UiController
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * Pair Programming last day with Jose Barros - 1140364
 *
 * <h2>8. Final Remarks</h2>
 * The feature its tested and funcional. The core of the problem is set and
 * functional. "The name of the users and icon should be displayed in the list
 * of available users to play with." - not implemented yet; cause: No time do to
 * the task.
 *
 * Two instances can establish connection. It is possible to edit own profile
 * photo. The username and host its automatically set by the program. One
 * instance select other instance + game then press button connect for establish
 * connection between users. It is also possible to end connection/game between
 * users.
 *
 *
 * <h2>9. Work Log</h2>
 *
 * -Insert here a log of you daily work. This is in essence the log of your
 * daily standup meetings.-
 * <p>
 * <b>Weekend</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Create the worklog for the second week of work. 2. Analyze the problem.
 * <p>
 * Today
 * <p>
 * 1. Did the requirements. 2. Did the analyses. 3. Clarified some doubts of the
 * first analyses with my colleagues.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Did the requirements. 2. Did the analyses. 3. Clarified some doubts of the
 * first analyses with my colleagues.
 * <p>
 * Today
 * <p>
 * 1. Design. 2. Implement the extension.
 * <p>
 * Blocking:
 * <p>
 * 1.nothing.
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1.Design. 2. Implement the extension.
 * <p>
 * Today
 * <p>
 * 1. Implementation and corrections.
 * <p>
 * Blocking:
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Huge implementation and corrections.
 * <p>
 * Today
 * <p>
 * 1. Implementation 2. "Adapt" design - cause: fixes.
 * <p>
 * Blocking:
 * <p>
 * 1. ----
 *
 * <h2>10. Self Assessment</h2>
 * Week 2 - i have done a lot of work and effort in my feature. It was a
 * productive week.
 *
 * <h3>10.1. Design and Implementation:</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * <p>
 * - url of commit: ... - description: this commit is related to the
 * implementation of the design pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author joao martins
 */
package csheets.worklog.n1140263.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
