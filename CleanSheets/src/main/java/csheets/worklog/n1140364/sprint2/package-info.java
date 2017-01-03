/**
 * Technical documentation regarding the work of the team member (1140364) José
 * Barros during week2.
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
 * -In this section you should register important notes regarding your work
 * during the week. For instance, if you spend significant time helping a
 * colleague or if you work in more than a feature-
 *
 * <h2>2. Use Case/Feature: IPC03.1</h2>
 *
 * <b>Issue in Jira:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-57">LPFOURDG-57</a></p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-132">LPFOURDG-132</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-133">LPFOURDG-133</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-134">LPFOURDG-134</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-135">LPFOURDG-135</a></p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * A new extension that makes it possible to search for workbooks in all
 * instances of Cleansheets in the local network</p>
 *
 * <p>
 * <b>Use Case 1 - "Search in Another Instance":</b> It should be possible to
 * send a request for searching workbooks to another instance of Cleansheets.
 * The search should be based on the name of the workbook (a pattern of the
 * name). The search should only include workbooks that are open in the remote
 * instance of Cleansheets. The reply must inform if the workbook was find or
 * not. If the workbook was find then the reply must also include a summary of
 * the contents of the workbook. This summary should include the name of the
 * worksheets and the values of the first non-empty cells of each worksheet.</p>
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <h3>First Analysis sequence diagram</h3>
 *
 * <h3>Send Request</h3>
 * The user selects "Search in another instance" option in the "Search
 * Workbooks" menu. The system gets the other available instances in the local
 * network, and presents. The user selects which instance to communicate and
 * hopes that the other instance accepted the request.
 *
 * <p>
 * <img src="doc-files/ipc_03.1_analysis.png" alt="image">
 *
 * <h3>Search the workbook by name</h3>
 * The user introduces the name of workbook to search in the remote instance of
 * Cleansheets and waits reply of search. If workbook was find the system
 * display a summary of the contents of the workbook. This summary include the
 * name of the worksheets and the values of the first non-empty cells of each
 * worksheet.
 *
 * <p>
 * <img src="doc-files/ipc_03.1_analysis2.png" alt="image">
 *
 *
 * <h3>Analysis of Core Technical Problem</h3>
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * For this user story we can start by coding a unit test that uses a subclass
 * of InstanceWorkbookExtension to see if the other instance receives the
 * request and that in turn responds to the same request. Another test can be
 * verify that the name of the book returned is the same user-entered name. And
 * still prove that it is impossible to find a workbook in another instance
 * without the user has introduces a pattern name for search
 *
 * <p>
 * see: <code>csheets.ext.distributedWorkbook.SearchWorkbookTest</code>
 * </p>
 *
 * <h3>5.2. UC Realization</h3>
 *
 * To realize this user story we will need to create a script manager to
 *
 * <h3>Extension Setup</h3>
 *
 * The following sequence diagram illustrates the creation of the user interface
 * extension. All the UI extensions are loaded by the UIController at
 * application startup.
 *
 * <p>
 * <img src="doc-files/ipc_3.1_extension_image2.png" alt="image">
 *
 * <p>
 * The following sequences diagrams illustrates the creation of the menu
 * extension. All the menu extensions are loaded by the MenuBar at application
 * startup.
 *
 * <p>
 * <img src="doc-files/ipc_3.1_extension_image3.png" alt="image">
 *
 *
 * <p>
 * <img src="doc-files/ipc_3.1_extension_image4.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 *
 * The following diagram shows the setup of the "distributed workbook" extension
 * when cleansheets is run.
 *
 * <p>
 * <img src="doc-files/ipc_3.1_extension_image1.png" alt="image">
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 * <p>
 * -You can also add other artifacts to document the design, for instance,
 * database models or updates to the domain model-
 *
 * <h2>6. Implementation</h2>
 *
 * -Reference the code elements that where updated or added-
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-</p>
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 * -In this section document your contribution and efforts to the integration of
 * your work with the work of the other elements of the team and also your work
 * regarding the demonstration (i.e., tests, updating of scripts, etc.)-
 *
 * <h2>8. Final Remarks</h2>
 *
 * -In this section present your views regarding alternatives, extra work and
 * future work on the issue.-
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Saturday</b>
 * <p>
 * Today: Analysis use case and design.
 * <p>
 * 1.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis use case and design.
 * <p>
 * Today:
 * <p>
 * 1. Implementation: List all instances. Send request for another instance and
 * confirm.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Implementation: List all instances. Send request for another instance and
 * confirm.
 * <p>
 * Today:
 * <p>
 * 1. Search workbook by name in another instance <b>design</b>. Implementation.
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Search workbook by name in another instance <b>design</b>. Implementation.
 * <p>
 * Today:
 * <p>
 * 1. Finish a search of workbook. Display summary. Tests
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. Finish a search of workbook. Display summary. Tests. </p>
 * <p>
 * Today:</p>
 * <p>
 * 1. UC correction. Finish Tests.</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on:
 * <p>
 * 1. UC correction. Finish Tests. </p>
 * <p>
 * Today: Pair progamming with João Martins - "Game connection"</p>
 * <p>
 * 1.</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <h2>10. Self Assessment</h2>
 *
 * <p>
 * Outcome 3 ("Design and Implementation") - 3</p>
 * <p>
 * Outcome 5 ("Teamwork") - 3</p>
 * <p>
 * Outcome 6 ("Technical Documentation") - 3</p>
 *
 * <h3>10.1. Design and Implementation</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * </p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/904e3cafc2a6743538a7bc2a7b7807c117724f07">Analysis</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/2e63959eecc67f4218cc4a683351ca97d4368ef3">Design
 * of extension setup</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/7c4d915025b7c415c4d194c1074df5aa168b17e6">
 * Initial UI. Step "Search Instances"</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/d25e508e0671e94dc2ff982979e366f0cc073f3e">Send
 * Request to another instance. - Implemented</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/137f77bc482ba24aaf8a3670006f060cb9857303">Confirm
 * request by another instance - Implemented</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/9a70ba62a41775ba206fce26817e965c93753a42">
 * Javadoc and corrections in Server</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/612b67b24eab9173ae03ce5b7f526d3f324283ee">"Search
 * workbook" and "Display summary" - implemented</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/16f008cfc0d70ff9cbcbf6e097129dfcbb133870">Pair
 * progamming with João Martins - "UI for games and another changes in use
 * case"</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/86ebb91260be7789893dcb484b18603ede1dd02f">Pair
 * progamming - "Event panel updated"</a></p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/0ae596dbfe063c6ed1d56a51e70d76fcca926ad7">Some
 * tests</a></p>
 *
 * <h3>10.2. Teamwork:</h3>
 *
 * <h3>10.3. Technical Documentation:</h3>
 *
 * @author José Barros 1140364@isep.ipp.pt
 */
package csheets.worklog.n1140364.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author José Barros 1140364@isep.ipp.pt
 */
class _Dummy_ {
}
