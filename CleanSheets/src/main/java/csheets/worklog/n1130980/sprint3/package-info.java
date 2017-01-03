/**
 * Technical documentation regarding the work of the team member (1130980)
 * Rafael Vilar during week3.
 *
 * * <p>
 * <b>-Note: this is a template/example of the individual documentation that
 * each team member must produce each week/sprint. Suggestions on how to build
 * this documentation will appear between '-' like this one. You should remove
 * these suggestions in your own technical documentation-</b>
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 *
 * -Notes about the week's work.-
 * <p>
 * This week will be developed the Tic-tac-toe game.
 *
 * <h2>2. Use Case/Feature: IPC07.2</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-70">LPFOURDG-70</a>
 * <p>
 * Sub-Task in Jira:</p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-212">Analysis -
 * LPFOURDG-212</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-213">Design -
 * LPFOURDG-213</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-214">Implementation
 * - LPFOURDG-214</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-215">Tests -
 * LPFOURDG-215</a></p>
 *
 * <h2>3. Requirement</h2>
 * This feature Tic-tac-toe is my responsibility
 *
 * <p>
 * <b>Use Case "Tic-tac-toe":</b>
 * <p>
 * The tic-tac-toe game should be implemented. The game should be implemented as
 * described in https://en.wikipedia.org/wiki/Tic-tac-toe . The board of the
 * game should be displayed in a range of cells. Each cell should correspond to
 * a position in the game board. Moves are performed by placing an "X" or an "O"
 * in the cells. The user should only be able to perform a move when its his
 * turn. Boards are updated on both instances. Cleansheets should automatically
 * verify and enforce the rules of the game.
 * </p>
 *
 * <h2>4. Analysis</h2>
 * <p>
 * <img src="doc-files/IPC07_2_Analysis.png" alt="image">
 * </p>
 * <p>
 * The user starts Cleansheets, and right sidebar choose the option Play Games,
 * edit profile picture, then choose an opponent in the Online Users pane of
 * segida choose the TicTacToe game in the Available Games panel, select Options
 * panel the connect option and confirm the link, and start the game with the
 * Play option.</p>
 * <p>
 * After these steps, the game panel is displayed and the option to choose the
 * symbol game one of the players.</p>
 * <p>
 * The player chooses and shows the symbol (X | O) corresponding to each
 * player.</p>
 * <p>
 * The user who escolhhe X is the first to play, and the other sees a message
 * asking to wait your turn. Player X satisfies one of the game board cells with
 * an X and that cell is automatically sent to the player, and so this already
 * You can make your move while the player X is waiting.</p>
 * <p>
 * While the number of moves is less than 9, the system lets you play and when
 * some player has made 3 or more moves checks if any of the players completed a
 * sequence of 3 identical symbols.</p>
 * <p>
 * If any of the players complete the sequence, players are informed of the
 * winner. If no complete a sequence are informed that drew.</p>
 * <p>
 * At the end they are asked whether to make a new game, or leave.</p>
 *
 *
 * <h3>Domain Model</h3>
 *
 *
 *
 * <h2>5. Design</h2>
 * <p>
 * <img src="doc-files/ipc07_2_Design.png" alt="image">
 * </p>
 * <h3>5.1. Functional Tests</h3>
 *
 *
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical
 * documentation the elements depicted in these design diagrams exist in the
 * code!
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 * <p>
 * -You can also add other artifacts to document the design, for instance,
 * database models or updates to the domain model-
 * </p>
 * <h2>6. Implementation</h2>
 *
 * <code>csheets.ext.game.controllers.GameController</code>
 * <code>csheets.ext.game.controllers.SpecificGameController</code>
 * <code>csheets.ext.game.controllers.TicTacToeController</code>
 * <code>csheets.ext.game.domain.TicTacToe</code>
 * <code>csheets.ext.game.ui.GamePanel</code>
 * <code>csheets.ext.game.ui.TcpService</code>
 * <code>csheets.ext.game.ui.UdpService</code>
 * <code>csheets.ext.game.ui.ListOpponents</code>
 * <code>csheets.ext.game.ui.ProfileOpponent</code>
 * <code>csheets.ext.game.ui.UIGameExtension</code>
 * <code>csheets.ext.game.GameExtension</code>
 *
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
 * </p>
 * see:
 * <p>
 * <a href="../../../../csheets/ext/game/package-summary.html">csheets.ext.comments</a>
 * </p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * This week my feature was to implement the tictactoe game. This feature
 * belongs to IPC07.2 area and need to implement network communication so that
 * it can be played on two machines over the network.
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * 1. Analysis use case
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * </p>
 * 1. Design and Starts Implementaton
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Wednesday</b>
 * </p>
 * 1. Implementation and Tests
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * <p>
 * <b>Evidences:</b>
 *
 * <h3>Step 1 - </h3>User Profile<br><br>
 * <img src="http://imgur.com/KjMAiPP.png" alt="connect">
 *
 * <h3>Step 2 - </h3>Choose Opponent<br><br>
 * <img src="http://imgur.com/lU1DKyc.png" alt="connect2">
 *
 * <h3>Step 3 - </h3>Choose TicTacToe Game<br><br>
 * <img src="http://imgur.com/JORXj49.png" alt="choose game">
 *
 * <h3>Step 4 - </h3>Connect Opponent<br><br>
 * <img src="http://imgur.com/QRBRgbW.png" alt="connect">
 *
 * <h3>Step 5 - </h3>Received invitation from opponent<br><br>
 * <img src="http://imgur.com/ThgR6uB.png" alt="connect established">
 *
 * <h3>Step 6 - </h3>Start Game<br><br>
 * <img src="http://imgur.com/4xdMVMv.png" alt="play">
 *
 * <h3>Step 7 - </h3>"O" is ever the first move<br><br>
 * <img src="http://imgur.com/SatOArC.png" alt="game">
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * /**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rafael Vilar
 */
package csheets.worklog.n1130980.sprint3;

class _Dummy_ {
}
