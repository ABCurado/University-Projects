/**
 * Technical documentation regarding the work of the team member (1140780) Ruben
 * Teixeira during week3.
 *
 * <p>
 * <b>Scrum Master:</b> no
 *
 * <p>
 * <b>Area Leader:</b> no
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * I created an extra task concerning the creation of an Installer for the
 * application found in
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-303">LPFOURDG-303</a></p>
 * <p>
 * It contains a link to the package in the comments section.</p>
 *
 * <h2>2. Use Case/Feature: IPC03.2 - Search in the Network</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-58">LPFOURDG-58</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-274">Analysis</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-275">Design</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-276">Tests</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-277">Implementation</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-273">Worklog</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * It should be possible to broadcast a workbook search request to all the
 * instances of Cleansheets in the same local network. The search should only
 * include the workbooks that are open. Cleansheets should have a sidebar window
 * to display - in a list - the results of the search. This window should be
 * updated as replies as received. The list of results should include the
 * identification of the instance where the workbook was found, the name of the
 * workbook and a summary of the contents of the workbook.</p>
 *
 * <p>
 * <b>Use Case 1 - "Search networked instances for workbook pattern":</b> The
 * user enters a regular expression on a textbox, then presses a button to start
 * the search. The system will then display for each instance in the network
 * that have matching workbook's, its identification as well as a preview of the
 * first non-empty cells.</p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>
 * After analysis of the current implementation, it was found that this feature
 * is implemented following this class diagram design:</p>
 * <p>
 * <img src="doc-files/ipc_03.2_extension_image1.png" alt="Class Diagram"></p>
 *
 * <p>
 * After <code>WorkbookSearchExtension</code> is dinamycally loaded (load flow
 * control analysed in the last sprint, see: see:
 * <a href="../sprint2/package-summary.html">4. Analysis</a>
 * ), it will return to the UI the <code>UIExtensionWorkbookSearch</code> which
 * in turn returns a <code>WorkbookSearchMenu</code> that contains a
 * <code>JMenuItem</code> associated with the <code>WorkbookSearchAction</code>,
 * the latter being the responsible for starting the flow of the Use Case.</p>
 *
 * <b>WorkbookSearchExtension Class:</b>
 * <p>
 * Returns the main UI for this extension.</p>
 *
 * <b>UIExtensionWorkbookSearch Class:</b>
 * <p>
 * Returns the <code>JMenu</code> component added to the main UI.</p>
 *
 * <b>WorkbookSearchMenu Class: </b>
 * <p>
 * The JMenu component added to the main UI.</p>
 *
 * <b>WorkbookSearchAction Class: </b>
 * <p>
 * This Action will be associated with the JMenu component above.</p>
 *
 * <h3>4.1 Current Design</h3>
 *
 * <p>
 * <b>ActionPerformed:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.2_extension_image4.png" alt="Action performed"></p>
 *
 * <p>
 * <b>Since the requirements now specify the need for a Sidebar this will be the
 * updated design:</b></p>
 *
 * <p>
 * <b>Updated diagram of the loading process:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.2_class_diagram_updated.png" alt="CD updated"></p>
 *
 *
 * <p>
 * <b>Search request:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.1_analysis.png" alt="Send Request"></p>
 *
 * <p>
 * <b>For the search request however, re-engineering will be needed as we will
 * now have to request search for all found instances, instead of just the one
 * instance chosen by the User. Aditionally, no input from the client instance
 * user should be necessary until further discussion with the team.</b></p>
 *
 * <p>
 * <b>Updated Search request:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.1_analysis_server_side.png" alt="First Approach SD"></p>
 *
 * <p>
 * <b>This is the first approach to the problem after the analysis, in which the
 * main difference is that all instances found after broadcast and reply
 * received, are then saved in-memory for later request for search.</b></p>
 *
 * <h3>Analysis of Core Technical Problem</h3>
 * <p>
 * The core technical problem is mainly how to refresh the UI with search
 * results which should contain instance identification as well as a preview of
 * their matching workbooks.</p>
 * <p>
 * Perhaps this is time for another Data Transfer object... But first, one
 * should take care of the networking flow of this functional increment.</p>
 * <p>
 * <b>UPDATE:</b></p>
 * <p>
 * After further analysis of the current implementation, one may come to the
 * conclusion that the Volt network implementation might not be the most
 * appropriate solution to this application, as an Object Oriented one, as we
 * quickly come to the conclusion that the Volt Interfaces and protocol don't
 * even support the transfer of objects other than String ones.</p>
 * <p>
 * This almost defeats the purpose of using DTO's as serializable and
 * lightweight objects to send through the network.</p>
 * <p>
 * I will suggest to the original main programmer of Volt (Renato Machado) the
 * addition of generic Object transfer support if possible. Nevertheless, the
 * current goal is to make do with what is currently available as time to
 * deliver is of utmost concern.</p>
 *
 * <p>
 * <b>UPDATE 2:</b></p>
 * <p>
 * After consulting with Volt owner who rejected the suggested changes to his
 * implementation I decided to implement <code>ObjectSerialization</code> class
 * with static methods <code>toString()</code> and <code>fromString()</code> in
 * order to accomplish the same objective by serializing the objects into a
 * Base64 string and the other way around on the other end of the network.
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * Currently, there aren't any acceptable tests for the feature. Since testing
 * against network packet trading can be a cumbersome and fail prone task, the
 * plan is to test the code performed by each side of the communication, so the
 * goal is to write down tests for <code>WorkbookDTOAssemblerTest</code> to
 * ensure valid DTO's are created and <code>ObjectSerializationTest</code> for
 * obvious reasons. <code>LocalWorkbookSearchTest</code> was initially intended
 * too but as it depends on active Workbooks being in the FileSystem i've chosen
 * not to implement it as of this sprint until a proper yet simple way to test
 * it comes to mind.</p>
 *
 *
 * <p>
 * <b>ObjectSerializationtest:</b></p>
 * A simple approach is to create an object, clone it and save the original.
 * Then serialize and de-serialize the clone and make sure it matches the
 * original object.
 * <p>
 * see:</p>
 * <p>
 * <a href="../../../../../test/csheets/framework/ObjectSerializationTest.java">test.csheets.framework.ObjectSerializationTest</a></p>
 *
 * <p>
 * <b>WorkBookDTOAssemblerTest:</b></p>
 * Setup a basic workbook with just 4 cells filled with formulas/values. Create
 * a DTO from that workbook, then instead of testing everything it may test the
 * name of one of the spreadsheets, the resulting <code>value.toString()</code>
 * of two of the cells aswell as make sure the count of the spreadsheets is
 * correct.
 * <p>
 * see:</p>
 * <p>
 * <a href="../../../../../test/csheets/ext/distributedWorkbook/WorkBookDTOAssemblerTest.java">test.csheets.ext.distributedWorkbook.WorkBookDTOAssemblerTest</a></p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * The following Diagrams are useful to understand the UC Realization:</p>
 *
 * <p>
 * <b>Main Flow:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.2_sequence_diagram.png" alt="Sequence Diagram Design"></p>
 *
 * <p>
 * <b>UDPService:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.2_udp.png" alt="Sequence Diagram UDP"></p>
 *
 * <p>
 * <b>TCPService:</b></p>
 * <p>
 * <img src="doc-files/ipc_03.2_tcp.png" alt="Sequence Diagram TCP"></p>
 *
 * <h3>5.3. Extension Setup</h3>
 * <p>
 * The Search Extention is loaded as per the following:</p>
 * <p>
 * <img src="doc-files/ipc03.2_extension_load_sd.png" alt="Extension Load"></p>
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Singleton Pattern implemented by ExtensionManager.</p>
 * <p>
 * Data Transfer Object implemented by WorkbookDTO.</p>
 * <p>
 * Low Cowpling - High Cohesion.</p>
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * Upon implementation, the flow control was changed a little bit as obviously,
 * the UDP and TCP services of search targets must be booted upon the extension
 * loading and not only when a new search is performed.</p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/3d800e058ae429b0ec9c8888ef15a1dcbdd9548d"><b>COMMIT
 * OF THE FIX</b></a>.</p>
 *
 * <p>
 * see:</p>
 * <p>
 * <a href="../../../../csheets/ext/distributedWorkbookSearch/package-summary.html">csheets.ext.distributedWorkbookSearch</a></p>
 * <p>
 * <a href="../../../../csheets/ext/distributedWorkbookSearch/ui/package-summary.html">csheets.ext.distributedWorkbookSearch.ui</a></p>
 *
 * <p>
 * Commit Evidences:</p>
 *
 * <b>Analysis:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/e0d90e5e6eae50f2d858b73217c70878ef0a0a20">Commit
 * concerning Analysis</a></p>
 *
 * <b>Design:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/5907acc36783ed3b2107159614cf6a2ac08cd6bc">Commit
 * concerning Design</a></p>
 *
 * <b>Tests:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/b8f2705b08c33b9fa2f83a1937ff21cfd71f7981">Commit
 * concerning Tests</a></p>
 *
 * <b>Implementation:</b>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/248d53b7970d558fdb99edd6f855e86c0b19a588">Commit
 * #1</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/2d3b9a701f9c8bb948e29e13a5564e15557e2a04">Commit
 * #2</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/1424ad1bbc4eaf8ca425afc7baa7311f3dc1be2c">Commit
 * #3</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/46bfdad2be0f73b9b4820313734f56119fabe495">Commit
 * #4</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/4f9241c071d3abeed96269bc119c034fc5b3f692">Commit
 * #5</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/0f30e2ed87d0411b56627ae01acaa0db7bf098ce">Commit
 * #6</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/3d800e058ae429b0ec9c8888ef15a1dcbdd9548d">Commit
 * #7</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/changeset/5ae73818332aff8d92146c2adea216b4ea4403f8">Commit
 * #8</a></p>
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * <p>
 * On this sprint i demonstrated my own feature individually with great success
 * even thou network related demos can be quite dramatic if the supporting
 * structure does not collaborate. Nevertheless it went perfectly.
 *
 * <h2>8. Final Remarks</h2>
 *
 * <p>
 * As previously, my intentions since the start of this sprint was to code while
 * thinking about future iterations. As such, after understanding the code
 * already present (including volt), evaluating its pros and cons, I took
 * manners in changing what i thought could be improved and structure the code
 * (even class and methods naming) to improve readability and easy access to
 * others, following patterns when I thought it was suited.</p>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * 1. Nothing.
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * 1. Nothing.
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * 1. Analysis.
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * 1. Finished Analysis.
 * <p>
 * 2. Started Tests and Design while experimenting with the code...
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * 1. Finished Tests and design.</p>
 * <p>
 * 2. Did most of the implementation.
 * <p>
 * Blocking: - Volt implementation was undergoing change to support the use of a
 * Request object to abstract from the use of an HashMap representation of a
 * message. Although this was a welcomed fix, it delayed implementation and
 * real-world testing, as i also had to rewrite some of my code.
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * 1. Fixed a few glitches concerning the GUI</p>
 * <p>
 * 2. Improved TCP/UDP services start-up logic.</p>
 * <p>
 * 3. Demonstrated my Feature increment.</p>
 *
 * <p>
 * <b>Friday</b>
 * <p>
 * 1. Finished this worklog.</p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * <p>
 * This sprint I had a lot more difficulties as this time I had to continue work
 * of another colleague. Lack of proper documentation wasn't helping either and
 * since this was my first contact with IPC and Volt, my work was doubled.</p>
 * <p>
 * Given this, i found my perfomance this week to be astonishing since I
 * overcame all the issues, learned a lot and ended up with quite a nice feature
 * presentation and execution.
 *
 * <h3>10.1. Design and Implementation</h3>
 *
 * I believe the code structure could be improved quite a lot. I disagree with
 * the current Volt implementation supporting solely String messaging. This is
 * Object Oriented Programming, String is an object in its own way, so why can
 * it not support generic Object's? Either way I found my design and
 * implementation quite satisfactory given these issues.
 *
 * <h3>10.2. Teamwork:</h3>
 *
 * Teamwork this sprint was very limited due to time constraints. All of the
 * colleagues working on IPC were overwhelmed with work, although I realized
 * that some people from other areas with easier features helped others.
 * Resumably my work as teammate was limited to answering quick questions,
 * handing out some ideas to solve problems/present data on GUI etc.
 *
 * <h3>10.3. Technical Documentation:</h3>
 *
 * <p>
 * I worked relentlessly to keep my worklog updated with rich information,
 * taking into account that this feature was quite hard for me to correctly
 * re-structure and present. I'm quite satisfied with it.</p>
 * <p>
 * This is the final result of this sprint's feature:</p>
 * <p>
 * New Search:</p>
 * <p>
 * <img src="http://i.imgur.com/6VaHlNW.png" alt="JPanel Final Result"></p>
 * <p>
 * Request for search on target PC:</p>
 * <p>
 * <img src="http://i.imgur.com/1I9YFnJ.png" alt="Request on target"></p>
 * <p>
 * Preview the workbook content ( Extra thanks to DTO :) and for the idea i took
 * from Pedro Gomes whom was kind enough to allow me to use):</p>
 * <p>
 * <img src="http://i.imgur.com/JbaW12Y.png" alt="JPanel Final Result"></p>
 *
 * @author Ruben Teixeira 1140780@isep.ipp.pt
 */
package csheets.worklog.n1140780.sprint3;

/**
 *
 *
 * @author Ruben Teixeira 1140780@isep.ipp.pt
 */
class _Dummy_ {
}
