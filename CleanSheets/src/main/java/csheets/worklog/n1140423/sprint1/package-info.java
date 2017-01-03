/**
 * Technical documentation regarding the work of the team member (1140423) Renato Machado during week1. 
 * 
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 * 
 * <b>Area Leader: -(yes/no)- yes</b>
 * </p>
 * 
 * <h2>1. Notes</h2>
 * 
 * <p>
 * Been setting up the network protocols for instances of Cleansheets to connect to each others.
 * Helped my colleagues to understand the protocol structure and how things connect to each other.
 * </p>
 * 
 * <h2>2. Use Case/Feature: IPC01.1</h2>
 * 
 * <p>
 * Issue in Jira: <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-51">LPFOURDG-51</a>
 * </p>
 * <p>
 * LPFOURDG-51
 * 
 * It should be possible to establish a connection with other instance of
 * Cleansheets in the local network. It should be possible to send the contents
 * of a range of cells to another instance of Cleansheets. The other instance
 * should display the received contents in the same cell address as the original
 * cells. It should be possible to configure the port to be used for network
 * connections. It should be possible to find other instances of Cleansheets
 * available in e local network. These instances should be listed in a new
 * window (sidebar window). The user should be able to select one of the
 * discovered instances to connect to when establishing the connection to send
 * the contents of the range of cells. At the moment it is only required to send
 * the value of the cells.
 * 
 * </p>
 * 
 * <h2>3. Requirement</h2>
 *
 * It should be possible to establish a connection with other Clensheets instances in the local network, and share the values of the selected cells.
 * The connection should be configurable.
 * 
 * <p>
 * <b>Use Case "Start Sharing":</b> The user .
 * 
 *  
 * <h2>4. Analysis</h2>
 * <h3>Send Cells</h3>
 * The user selects "Send Cells" option in the "Share Cells" menu.
 * The system gets the other available instances in the local network, and presents them in a sidebar window.
 * The user selects which instance to send the cells to, and activates the sending.
 * The system gets the selected cells, and sends them to the targeted instance.
 * The system notifies the user that the cells were sent.
 * 
 * <h3>Receive Cells</h3>
 * The user selects "Receive Cells" option in the "Share Cells" menu.
 * The system waits for the cells being sent in the local network.
 * After receiving the cells, the system checks if checks if the received cells are located on an address that already has existing cells.
 * If so, then the system asks the user for permission to change the original cells with the new ones.
 * Otherwise, the system just changes them.
 * The system notifies the user that the cells were changed.
 * 
 * <h3>First "analysis" sequence diagram</h3>
 * The following diagram depicts a proposal for the realization of the previously described use case. We call this diagram an "analysis" use case realization because it functions like a draft that we can do during analysis or early design in order to get a previous approach to the design. For that reason we mark the elements of the diagram with the stereotype "analysis" that states that the element is not a design element and, therefore, does not exists as such in the code of the application (at least at the moment that this diagram was created).
 * <h4>Send Cells proposal analysis</h4>
 * <p>
 * <img src="doc-files/share_cell_send_image.png" alt="image"> 
 * 
 * <h4>Receive Cells proposal analysis</h4>
 * <p>
 * <img src="doc-files/share_cell_receive_image.png" alt="image"> 
 * <p>
 * 
 * From the previous diagram we see that we need to add a new "attribute" to a cell: "comment".
 * Therefore, at this point, we need to study how to add this new attribute to the class/interface "cell". This is the core technical problem regarding this issue.
 * 
 * <h3>Analysis of Core Technical Problem</h3>
 * The core of communication is expected to communicate in udp and tcp protocols connections. 
 * <p>
 * <img src="doc-files/ipc_analysis.png" alt="image"> 
 * 
 * 
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * Basically, from requirements and also analysis, we see that the core functionality of this use case is to be able to add an attribute to cells to be used to store a comment/text. We need to be able to set and get its value.
 * Following this approach we can start by coding a unit test that uses a subclass of <code>CellExtension</code> with a new attribute for user comments with the corresponding method accessors (set and get). A simple test can be to set this attribute with a simple string and to verify if the get method returns the same string.
 * As usual, in a test driven development approach tests normally fail in the beginning. The idea is that the tests will pass in the end. 
 * <p>
 * see: <code>csheets.core.SpreadsheetTest</code>
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We will also need to create a subclass of UIExtension. For the sidebar we need to implement a JPanel. In the code of the extension <code>csheets.ext.style</code> we can find examples that illustrate how to implement these technical requirements.
 * The following diagrams illustrate core aspects of the design of the solution for this use case.
 * <p>
 * <b>Note:</b> It is very important that in the final version of this technical documentation the elements depicted in these design diagrams exist in the code!
 * 
 * <h3>User Share selected Cells</h3>
 * The following diagram shows the setup of the local connection when cleansheets's user select share.
 * <p>
 * <img src="doc-files/ipc01_01_design.png" alt="image">
 * 
 *
 * <h3>Application display shared cells</h3>
 * The following diagram illustrates what happens when a instance of cleansheet receive shared cells.
 * <p>
 * <img src="doc-files/ipc01_01_design1.png" alt="image">
 * 
 * <h3>Extension Setup</h3>
 * The following diagram shows the setup of the "share" extension when cleansheets is run.
 * <p>
 * <img src="doc-files/ipc01_design.png" alt="image">
 *  
 * <h3>5.3. Classes</h3>
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * Global Class Diagram
 * <p>
 * <img src="doc-files/ipc01_classDiagram.png" alt="image"> 
 * <p>
 * Extension Class Diagram
 * <p>
 * <img src="doc-files/ipc_extension_image1.png" alt="image">
 * <p>
 * * <b>Sequence Diagrams</b> illustrating the setup of the extension
 * <p>
 * The following sequence diagram illustrates the creation of the share
 * extension. All the extensions are loaded dynamically by the ExtensionManager
 * at application startup.
 * <img src="doc-files/ipc_extension_image2.png" alt="image">
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
 * <p>
 * -Also refer all other artifacts that are related to the implementation and where used in this issue. As far as possible you should use links to the commits of your work-
 * <p>
 * see:<p>
 * <a href="../../../../csheets/ext/cellsSharing/package-summary.html">csheets.ext.cellsSharing</a><p>
 * <a href="../../../../csheets/ext/cellsSharing/ui/package-summary.html">csheets.ext.cellsSharing.ui</a><p>
 * <a href="../../../../csheets/framework/volt/package-summary.html">csheets.framework.volt</a>
 * 
 * <h2>7. Integration/Demonstration</h2>
 * 
 * -In this section document your contribution and efforts to the integration of your work with the work of the other elements of the team and also your work regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 * 
 * <h2>8. Final Remarks</h2>
 * 
 * -In this section present your views regarding alternatives, extra work and future work on the issue.-
 * <p>
 * As an extra this use case also implements a small cell visual decorator if the cell has a comment. This "feature" is not documented in this page.
 * 
 *  
 * <h2>9. Work Log</h2> 
 * 
 * <p>31/05/2016</p>
 * <b>Tuesday</b>
 * <p>Yesterday:</p>
 * <p>I defined the subtasks for the IPC01.1.</p>
 * <p>Today</p>
 * <p>Started working on the feature. Helped colleagues to understand the problem and recommended a solution.
 * Explained how Volt, a package developed in RCOMP that eases the development of UDP and TCP protocols, works.
 * Implemented Volt in the repository, and the Support package (with the TaskManager, ThreadManager).</p>
 * <p>Blocking</p>
 * <p>Nothing.</p>
 * 
 * <p>01/06/2016</p>
 * <b>Wednesday</b>
 * <p>Yesterday:</p>
 * <p>I've develop a few helper classes to handle some more difficult situations, such as timing events and
 * thread management. I've also develop a super set protocol over TCP/IP to be able to develop my feature.</p>
 * <p>Today</p>
 * <p>Will work on Volt to make the whole process a bit more stable, testing, try to find bugs and fix them.
 * Will help my colleagues if they have any difficulties.</p>
 * <p>Blocking</p>
 * <p>Nothing.</p>
 * 
 * <h2>10. Self Assessment</h2> 
 * 
 * Outcome 3 ("Design and Implementation") - 5
 * Outcome 5 ("Teamwork") - 4
 * Outcome 6 ("Technical Documentation") - 3
 *
 * <h3>10.1. Design and Implementation:3</h3>
 * 
 * The implementation uses Volt, a package developed during the RCOMP course of the current year (2015/2016), which aims to ease the development of
 * communication protocols with a expressive syntax. It has some nice features such as Routing (Route cycling on UDP) and automatic management of multiple UDP packets.
 * 
 * <p>
 * <b>Evidences:</b>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e01bbf726b8a1ba29f3e920f53d8fb10b6c2ed57">Small updates on TcpServer</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/2c14560581f15ead1be0471d8ac39f9e69ce697c">Fixed errors on TcpClient</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/2b4ded30d0b4dfa006a4fa5e9377923bef28fcc7">Added Volt. Developed TaskManager and ThreadManager</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/6d97401a8d12873a37b3184cfb26cb4cf928a616">Refactoring. Added server restart functionality.</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a52ec7a92450b9ace339067a8bf0776f34b4a57f">Hotfix on TcpServer.</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/c7e5c527ca163a1a12d973059cbb15b6b5ae4193">Updated ThreadManager, and added a unit test.</a>
 * </p>
 * 
 * <h3>10.2. Teamwork: ...</h3>
 * 
 * <h3>10.3. Technical Documentation: ...</h3>
 * 
 * @author Renato Machado
 */

package csheets.worklog.n1140423.sprint1;

/**
 * This class is only here so that javadoc includes the documentation about this EMPTY package! Do not remove this class!
 * 
 * @author alexandrebraganca
 */
class _Dummy_ {}

