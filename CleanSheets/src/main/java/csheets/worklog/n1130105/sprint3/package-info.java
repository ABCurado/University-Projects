/**
 * Technical documentation regarding the work of the team member (1130105)
 * Carlos Mateus during week3.
 *
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <p>
 * <b>Area Leader: -(yes/no)- no</b>
 *
 * <h2>1. Notes</h2>
 *
 * <p>
 * -In this section you should register important notes regarding your work
 * during the week. For instance, if you spend significant time helping a
 * colleague or if you work in more than a feature.-
 *
 * <h2>2. Use Case/Feature: Core02.3- Rich Comments and History</h2>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-5">LPFOURDG-5</a>
 * <p>
 * Sub-Task in Jira:
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-261">LPFOURDG-261</a>
 * </p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-266">LPFOURDG-266</a>
 * </p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-267">LPFOURDG-267</a>
 * </p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-268">LPFOURDG-168</a>
 * </p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * A new extension that makes it possible to search for workbooks in the local
 * file system</p>
 *
 * <p>
 * <b>Use Case "Rich Comments and History":</b>
 * <p>
 * Cleansheets should support rich content in comments. For instance, the user
 * should be able to apply style and format to the comments. It should also
 * exist an history of changes. The user interface should display the history of
 * changes to a comment and allow the user to make a new version of a comment
 * based on an old one. It also should have a search feature, allowing the user
 * to search for comments based on text patterns (including the history in the
 * search).
 * </p>
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <h3>First "analysis" sequence diagram</h3>
 * <h4>Rich Comments and History</h4>
 * The user enter the name of directory he want to search files, the system
 * shows all the files, user select a file and the workbook is open
 * <p>
 * <img src="doc-files/ipc_analysis_2_3.png" alt="image">
 *
 *
 *
 * <h3>Analysis of Core Technical Problem</h3>
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * The tests verifies if the list is empty, after create a comment and verifies
 * if list have the comment. Next verifies if comment is added to the history
 *
 *
 * <h3>5.2. UC Realization</h3>
 *
 * <p>
 * <img src="doc-files/ipc_02.1_design.png" alt="image">
 *
 * <h3>5.3. Classes</h3>
 *
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 *
 *
 * <h2>6. Implementation</h2>
 *
 * -Reference the code elements that where updated or added-
 * <p>
 * -Also refer all other artifacts that are related to the implementation and
 * where used in this issue. As far as possible you should use links to the
 * commits of your work-
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
 * -Insert here a log of you daily work. This is in essence the log of your
 * daily standup meetings.-
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
 *
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on: Today:
 * <p>
 * 1. Analysis. Start of design - Implementation
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: Analysis and Start of design</p>
 *
 *
 * Today:
 * <p>
 * Finish design and start implementation
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on: Design and start implementation</p>
 * <p>
 * 1.</p>
 * <p>
 * Today: Finish implementation and star tests</p>
 * <p>
 * Blocking:
 * <p>
 * 1.
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on: Finish implementation and star tests</p>
 * <p>
 * 1.</p>
 * <p>
 * Today:</p>
 * <p>
 * 1.</p>
 * <p>
 * Blocking:
 * <p>
 * 1.
 * <p>
 * <b>Friday</b>
 * <p>
 * Yesterday I worked on:</p>
 *
 *
 * <p>
 * Today:</p>
 * <p>
 * 1.</p>
 * <p>
 * Blocking:
 *
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * <p>
 * <b>Evidences:</b>
 * <p>
 * - url of commit: - description: this commit is related to the implementation
 * of the design pattern-</p>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Carlos Mateus
 */
package csheets.worklog.n1130105.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Carlos Mateus
 */
class _Dummy_ {
}
