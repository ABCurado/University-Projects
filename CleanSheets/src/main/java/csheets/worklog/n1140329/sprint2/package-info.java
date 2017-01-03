/**
 * Technical documentation regarding the work of the team member (1140329) Rafael Rocha during week2. 
 * 
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
 * On Wednesday, I spent a significant time helping out my collegue on the implementation of IPC07.1.
 *
 * <h2>2. Use Case/Feature: IPC01.2</h2>
 * Issue in Jira: <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-52">LPFOURDG-52: Sharing’s Automatic Update</a>
 * <p>
 * Sub-Tasks in Jira:  <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-152">LPFOURDG-152: Analysis</a>; 
 *                     <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-153">LPFOURDG-153: Design</a>; 
 *                     <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-154">LPFOURDG-154: Tests</a>; 
 *                     <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-155">LPFOURDG-155: Implementation</a>
 * <p>
 * 
 * LPFOURDG-52
 * 
 * Once a connection is stablished between two instances of Cleansheets updates 
 * made in one side must be automatically sent to the other side. 
 * The data shared must include now also the style of the cells. 
 * At the moment it is not necessary to support the sharing of cells with formulas.
 * 
 * <h2>3. Requirement</h2>
 * Once a connection is stablished between two instances of Cleansheets, 
 * it should be possible for updates made in one side to be automatically sent to the other side.
 * The data shared must include now also the style of the cells.
 * 
 * <p>
 * <b>Use Case "Sharing's Automatic Update":</b> Once a connection is stablished
 * between two instances of Cleansheets, it should be possible for updates made
 * in one side to be automatically sent to the other side.
 * 
 *  
 * <h2>4. Analysis</h2>
 * <h3>Automatic Cells Update</h3>
 * The user opens up the "Share Cells" sidebar.
 * The system gets the other available instances in the local network, and presents them in the sidebar window.
 * The user selects which instance to establish a connection to and clicks on the "Connect" button.
 * Once a cell is edited by the user, the system sends it to the targeted instance 
 * and updates the instance's spreadsheet.
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. We call this diagram an "analysis" use case realization because it functions like a draft that we can do during analysis or early design in order to get a previous approach to the design. For that reason we mark the elements of the diagram with the stereotype "analysis" that states that the element is not a design element and, therefore, does not exists as such in the code of the application (at least at the moment that this diagram was created).
 * <h4>Automatic Cells Update proposal analysis</h4>
 * <p>
 * <img src="doc-files/automatic_cell_update_image_analysis.png" alt="image"> 
 * <p>
 * 
 * From the previous diagram we see that we need to add a new functionality to the UI: listening to the active spreadsheet's cells.
 * We also need to implement a constant TCP connection between instances.
 * Therefore, at this point, we need to study how to add this new functionality to the UI and the TCP service.
 * These are the core technical problems regarding this issue.
 * 
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to implement 
 * The following diagrams illustrate core aspects of the design of the solution for this use case.
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical documentation the elements depicted in these design diagrams exist in the code!
 * 
 * <h3>User selects to connect to another instance (Automatic Cell Update)</h3>
 * The following diagram shows the setup of the local connection when the user selects to connect to another instance, and how the updates are made.
 * <p>
 * <img src="doc-files/ipc01_2_design_part2.png" alt="image">
 * 
 * <h3>Extension Setup</h3>
 * The following diagram shows the setup of the "share" extension when cleansheets is running.
 * <p>
 * <img src="doc-files/ipc01_2_design_part1.png" alt="image">
 *  
 * <h3>5.3. Classes</h3>
 * <p>
 * <b>Class Diagram</b>
 * 
 * <p>
 * * <b>Sequence Diagrams</b> illustrating the setup of the extension
 * <p>
 * The following sequence diagram illustrates the creation of the share
 * extension. All the extensions are loaded dynamically by the ExtensionManager
 * at application startup.
 * <img src="doc-files/ipc01_2_design_part1.png" alt="image">
 * <img src="doc-files/ipc01_2_design_part2.png" alt="image">
 *
 * <p>
 * The following sequence diagram illustrates the creation of the user interface
 * extension. All the UI extensions are loaded by the UIController at
 * application startup.
 * <img src="doc-files/ipc_extension_image3.png" alt="image">
 *
 * <p>
 * The following sequence diagram illustrates the creation of the menu
 * extension. All the menu extensions are loaded by the MenuBar at application
 * startup.
 * <img src="doc-files/ipc_extension_image4.png" alt="image">
 * <p>
 * <b>Sequence Diagrams</b> illustrating use cases of the extension
 * <p>
 * <img src="doc-files/ipc_extension_image5.png" alt="image">
 *
 * -Document the implementation with class diagrams illustrating the new and the modified classes-
 * 
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * 
 * -Describe new or existing design patterns used in the issue-
 * 
 * Observer: This Pattern is used to notify SharePanel with new instances in local network and received cells.
 * 
 * <h2>6. Implementation</h2>
 * 
 * -Reference the code elements that where updated or added-
 * 
 * -Also refer all other artifacts that are related to the implementation and where used in this issue. As far as possible you should use links to the commits of your work-
 * 
 * <h2>7. Integration/Demonstration</h2>
 * 
 * -In this section document your contribution and efforts to the integration of your work with the work of the other elements of the team and also your work regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 * 
 * <h2>8. Final Remarks</h2>
 * 
 * -In this section present your views regarding alternatives, extra work and future work on the issue.-
 * 
 * 
 * <h2>9. Work Log</h2> 
 * <b>Friday</b>
 * <p>
 * Today
 * <p>
 * 1. Started analysis of IPC01.2
 * <p>
 * 2. Studied the base code to help the analysis.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Started analysis of IPC01.2
 * <p>
 * 2. Studied the base code to help the analysis.
 * <p>
 * Today
 * <p>
 * 1. Working on analysis of IPC01.2
 * <p>
 * 2. Working on design of IPC01.2
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on: 
 * <p>
 * 1. Working on analysis of IPC01.2
 * <p>
 * 2. Working on design of IPC01.2
 * <p>
 * Today
 * <p>
 * 1. Finishing analysis of IPC01.2
 * <p>
 * 2. Designing IPC01.2
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finishing analysis of IPC01.2
 * <p>
 * 2. Designing IPC01.2
 * <p>
 * Today
 * <p>
 * 1. Finishing design of IPC01.2
 * <p>
 * 2. Implementing IPC01.2
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finishing design of IPC01.2
 * <p>
 * 2. Implementing IPC01.2
 * <p>
 * Today
 * <p>
 * 1. Implementing IPC01.2
 * <p>
 * Blocking:
 * <p>
 * 1. -Managing multiple threads and port addresses.-
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Implementing IPC01.2
 * <p>
 * Today
 * <p>
 * 1. Finishing IPC01.2's implementation.
 * <p>
 * 2. Testing this Use Case.
 * <p>
 * 3. Helping out with IPC07.1's implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. -Managing multiple threads.-
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finishing IPC01.2's implementation.
 * <p>
 * 2. Testing this Use Case.
 * <p>
 * 3. Helping out with IPC07.1's implementation.
 * <p>
 * Today
 * <p>
 * 1. Testing IPC01.2's implementation and doing some final adjustments.
 * <p>
 * 2. Testing and fixing some issues of the other developed Use Cases of IPC.
 * <p>
 * Blocking:
 * <p>
 * 1. -nothing-
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Testing IPC01.2's implementation and doing some final adjustments.
 * <p>
 * 2. Testing and fixing some issues of the other developed Use Cases of IPC.
 * <p>
 * Today
 * <p>
 * Blocking:
 * <h2>10. Self Assessment</h2> 
 * 
 * <h3>10.1. Design and Implementation: 3</h3>
 * 
 * 3- Good. My Use Case's implementation worked out fine but could be severely improved. 
 * The Unit Tests were scarce because I wasn't really sure how to test a Use Case
 * that was very network dependent (broadcasts and TCP connections). Nonetheless
 * there is a test for ThreadManager that addresses a very important part of my
 * use case.
 * 
 * <b>Evidences:</b>
 * <p>
 * - url of commit: ... - description: this commit is related to the implementation of the design pattern ...-
 * 
 * <h3>10.2. Teamwork: Helped out colleague in IPC07.1 (https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/c81b8ce76da4eed4e4cfe4cead9f1bb6f502d0bc)</h3>
 * 
 * <h3>10.3. Technical Documentation: ...</h3>
 * 
 * @author Rafael Rocha
 */

package csheets.worklog.n1140329.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this EMPTY package! Do not remove this class!
 * 
 * @author RafaelRocha
 */
class _Dummy_ {}

