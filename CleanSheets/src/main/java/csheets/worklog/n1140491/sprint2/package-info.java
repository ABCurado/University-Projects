/**
 * Technical documentation regarding the work of the team member (1140491) Rui
 * Bastos during week2.
 *
 * <p>
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
 * 06/05/2016: Started the analysis of the feature
 *
 * <h2>2. Use Case/Feature: IPC04.1</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-60">LPFOURDG-60</a>
 *
 * <h2>3. Requirement</h2>
 * -The responsibility that was assignedto me, was to resolve the feature of
 * import/export a text file.-
 *
 * <p>
 * <b>Use Case "Import/Export Text":</b> A sidebar window that provides
 * functionalities for importing/exporting a text fiel. Each line of this file
 * should be separeted by a special character. The user may choose if the first
 * line of the file is a header or not. He can choose the cells where the
 * content is displayed.
 *
 *
 * <h2>4. Analysis</h2>
 * This feature will be supported in a new extension.
 * <h3>Import File</h3>
 * The user selects the file, choooses the special character to use as a column
 * separator, if the first line is an header or not and the cells to add the
 * file text. The system gets the information and add the text to the cells.
 *
 * <h3>Export File</h3>
 * The user selects the path to export the file. System exports the file to the
 * wanted path.
 *
 * <h3>First "analysis" sequence diagram</h3>
 *
 * <h4>Import file Content proposal analysis</h4>
 * <p>
 * <img src="doc-files/import_file_image.png" alt="image">
 *
 * <h4>Export File proposal analysis</h4>
 * <p>
 * <img src="doc-files/export_file_image.png" alt="image">
 *
 * <h3>Analysis of Core Technical Problem</h3>
 *
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * see: <code>csheets.domain.EventTest</code>
 *
 * <h3>5.2. UC Realization</h3>
 * To realize this user story we will need to create a subclass of Extension. We
 * will also need to create a subclass of UIExtension. For the sidebar we need
 * to implement a JPanel. To import and export the files, is important to use
 * strategies.
 *
 * <h4>Import file Content proposal design</h4>
 * <p>
 * <img src="doc-files/import_file_design_image.png" alt="image">
 *
 * <h4>Export File proposal design</h4>
 * <p>
 * <img src="doc-files/export_file_design_image.png" alt="image">
 *
 *
 * <h3>5.3. Classes</h3>
 *
 * <h3>Class Diagram of the feature</h3>
 * <p>
 * <img src="doc-files/crm01_01_design_class_diagram.png" alt="image">
 * </p>
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 *
 * -Describe new or existing design patterns used in the issue-
 * <p>
 *  * This is issue requires a strategy pattern.
 * </p>
 * <h2>6. Implementation</h2>
 *
 *
 * <code>csheets.ext.importExportData</code> In this package we have all the
 * relevant code of the feature(UI,controller, and parsers)
 *
 * <p>
 * The strategy pattern was implemented similarly to my team's LAPR3 project
 * design.
 * <p>
 * see:
 * <p>
 * <a href="../../../../csheets/persistence/package-summary.html">csheets.ext.comments</a>
 *
 *
 * <h2>7. Integration/Demonstration</h2>
 *
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * <b>Sunday</b>
 * Analysis of the feature.
 * </p>
 *
 * <p>
 * <b>Monday</b>
 * </p>
 * 1. Re-analysis of the problem, some design, ui and parsers implemented
 * <p>
 * Blocking:
 * </p>
 * 1. Nothing.
 * <p>
 * <b>Tuesday</b>
 * </p>
 * <p>
 * <b>Wednesday</b>
 * <p>
 * 1. Nothing.
 *
 * <h2>10. Self Assessment</h2>
 *
 * -Insert here your self-assessment of the work during this sprint.-
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * 3- bom: os testes cobrem uma parte significativa das funcionalidades (ex:
 * mais de 50%) e apresentam código que para além de não ir contra a arquitetura
 * do cleansheets segue ainda as boas práticas da área técnica (ex:
 * sincronização, padrões de eapli, etc.)
 * <p>
 * <b>Evidences:</b>
 * </p>
 * - url of commit: ... - description: this commit is related to the
 * implementation of the design pattern ...-
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * /**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author Rui Bastos
 */
package csheets.worklog.n1140491.sprint2;
