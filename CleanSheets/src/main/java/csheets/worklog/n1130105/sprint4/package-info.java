/**
 * Technical documentation regarding the work of the team member (1130105)
 * Carlos Mateus during week4.
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
 * <h2>2. Use Case/Feature: Lang08.3- Full XML Import/Export</h2>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-50">LPFOURDG-50</a>
 * <p>
 * Sub-Task in Jira:
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-367">LPFOURDG-367</a>
 * </p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-368">LPFOURDG-368</a>
 * </p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-369">LPFOURDG-369</a>
 * </p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-370">LPFOURDG-370</a>
 * </p>
 *
 *
 * <h2>3. Requirement</h2>
 * <p>
 * A new extension that makes it possible to search for workbooks in the local
 * file system</p>
 *
 * <p>
 * <b>Use Case "Full XML Import/Export":</b>
 * <p>
 * The previous options should now include all the persisting elements of
 * Cleansheets (i.e., all the contents that are normally persisted with the
 * workbook, such as macros, formatting styles, comments, etc.). The import
 * should now ask the user if the file contents should replace or append the
 * existing workbook contents.
 * </p>
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <h3>First "analysis" sequence diagram</h3>
 * <h4>Full XML Import/Export</h4>
 * <h5>Import XML</h5>
 * <img src="doc-files/lang08.3_Import_analysis.png" alt="image">
 *
 * <h5>Export XML</h5>
 * <img src="doc-files/lang08.3_Export_analysis.png" alt="image">
 *
 * <h3>Analysis of Core Technical Problem</h3>
 *
 * <h2>5. Design</h2>
 *
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * The tests verifies if the file is export and imports correctly with the all
 * new tags.
 *
 * <h3>5.2. UC Realization</h3>
 *
 *
 * <h5>Import XML</h5>
 * <img src="doc-files/lang08.3_Import_design.png" alt="image">
 *
 * <h5>Export XML</h5>
 * <img src="doc-files/lang08.3_Export_design.png" alt="image">
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
 * 1. Nothing
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Sunday</b>
 * <p>
 * Yesterday I worked on: nothing</p>
 * <p>
 * <p>
 * Today: nothing
 *
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on: Today: Analyis and Design</p>
 * <p>
 * Today: finish Design, start implementation of ExportXML</p>
 * <p>
 * Blocking:
 * <p>
 * 1. Nothing
 *
 * <p>
 * <b>Tuesday</b>
 * <p>
 * Yesterday I worked on: finish Design, start implementation of ExportXML</p>
 *
 * Today: continue of implementation of ExportXML
 *
 * Blocking:
 * <p>
 * 1. Nothing
 *
 *
 * <p>
 * <b>Wednesday</b>
 * <p>
 * Yesterday I worked on: continue of implementation of ExportXML</p>
 * <p>
 * Today: finish of implementation of ExportXML and implementation of ImportXML
 * and tests with Rui Bastos and Diogo Leite</p>
 *
 * Blocking:
 *
 *
 * <p>
 * <b>Thursday</b>
 * <p>
 * Yesterday I worked on: finish of implementation of ExportXML and
 * implementation of ImportXML and tests
 * </p>
 * <p>
 * Today: finish tests</p>
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
 * Implementation commit
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/56f6a74b1a0f5a2025581afee9a1f6053af75979
 *
 * Test commits
 * https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/f0217dca2df007c4975d92e8f6a3c9205796dd29
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Carlos Mateus
 */
package csheets.worklog.n1130105.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Carlos Mateus
 */
class _Dummy_ {
}
