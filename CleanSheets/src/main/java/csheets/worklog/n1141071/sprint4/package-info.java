/**
 * <p>Technical documentation regarding the work of the team member (1141071)
 * Rui Bento during week 4.</p>
 *
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 *
 * <b>Area Leader: -(yes/no)- yes</b>
 *
 * <h2>1. Notes</h2>
 * <p>All the information about the UC based on the given 
 * <a href="https://en.wikipedia.org/wiki/Battleship_(game)">link</a>.</p>
 * 
 *
 * <h2>2. Use Case/Feature: IPC07.3 - Battleships</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-71">LPFOURDG-71</a>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>The battleships game should be implemented. The game should be implemented
 * as described in <a href="https://en.wikipedia.org/wiki/Battleship_(game)>
 * https://en.wikipedia.org/wiki/Battleship_(game)</a>. The board of the game
 * should be displayed in a range of cells. Each cell should correspond to a
 * position in the game board. Moves are performed by sending cell addresses of
 * the board to the other instance of Cleansheets. The user should only be able
 * to perform a move when its his turn. Cleansheets should automatically verify
 * and enforce the rules of the game.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>After making connection with other player, It should be presented a way to 
 * organize your battleship board.</p>
 * <p>Once both players have set their boards, the game should start, with one 
 * of the players making the first move. After each move, it's send to the other 
 * player the info and should be presented an action corresponding to the Hit or 
 * Water.</p>
 * <p><b>WIN MODE:</b> The first player to sink all the opponent boats wins.</p>
 * 
 * <h2>5. Design</h2>
 *
 * <p>Diagram of the functionality of the feature</p>
 * <p>Main UC Diagram</p>
 * <img src="doc-files/ipc07_03_design.png" alt="Main UC Diagram">
 * 
 * <h3>5.3. Classes</h3>
 * <ul>
 * <li>Battleship</li>
 * <li>Ship</li>
 * <li>BattleshipController</li>
 * </ul>
 *
 * <h2>9. Work Log</h2>
 *
 * <b>Friday</b>
 * <ul>
 * <li>Understand the UC.</li>
 * <li>Read the given link.</li>
 * </ul>
 * 
 * 
 * <b>Saturday</b>
 * <ul>
 * <li>Understanding game extension code.</li>
 * <li>Starting UC design.</li>
 * </ul>
 * 
 * 
 * <b>Sunday</b>
 * <ul>
 * <li>Finish design.</li>
 * <li>Starting implementation.</li>
 * <li>Understanding Area features.</li>
 * </ul>
 * 
 * 
 * <b>Monday</b>
 * <ul>
 * <li>Questioning the PM (Project Manager) about IPC Area UCs.</li>
 * <li>Helping other team area understanding their UC.</li>
 * <li>Continuing the UC implementation.</li>
 * </ul>
 *
 *
 * <b>Tuesday</b>
 * <ul>
 * <li>Helping other team area understanding their UC.</li>
 * <li>Continuing the UC implementation.</li>
 * </ul>
 * 
 * 
 * <b>Wednesday</b>
 * <ul>
 * <li>Helping other team area understanding their UC.</li>
 * <li>Finish UC implementation.</li>
 * <li>Start UC tests implementation.</li>
 * </ul>
 * 
 * 
 * <b>Thursday</b>
 * <ul>
 * <li>Finish UC tests implementation.</li>
 * <li>Update javadoc.</li>
 * <li>Update worklog.</li>
 * </ul>
 * 
 *
 * <h2>10. Self Assessment</h2>
 * <ul>
 * <li>Prepare Virtual Machine for Presentation</li>
 * </ul>
 *
 * <h3>10.1. Design and Implementation:</h3>
 *
 * <b>Evidences:</b>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-71">Main Issue</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-305">Analysis and Requirements</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-306">Design</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-307">Implementation</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-308">Tests</a>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-304">Prepare Virtual Machine for Presentation</a>
 * 
 * 
 * <h3>10.2. Teamwork:</h3>
 * <p>Helped IPC02_03 analysis</p>
 * <p>Helped IPC03_03 analysis and design</p>
 * <p>Helped IPC04_03 analysis and design</p>
 * <p>Helped IPC05_03 analysis and design</p>
 * <p>Helped IPC06_03 analysis, design and implementation</p>
 * <p>Helped IPC08_01 analysis, design and implementation</p>
 * 
 * <h3>10.3. Technical Documentation:</h3>
 * <p></p>
 *
 * @author Rui Bento
 */
package csheets.worklog.n1141071.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rui Bento
 */
class _Dummy_ {
}
