/**
 * Technical documentation regarding the work of the team member (1130523) Ruben
 * Santos during week2.
 *
 * <p>
 * <b>Scrum Master: Marcelo Barroso</b>
 *
 * <p>
 * <b>Area Leader: Rui Freitas</b>
 *
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: Lang08.1</h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-48">LPFOURDG-48</a>
 *
 * <p>
 * Sub-Task in Jira:
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-161">LPFOURDG-161</a>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-162">LPFOURDG-162</a>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-163">LPFOURDG-163</a>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-164">LPFOURDG-164</a>
 *
 * <h2>3.1 Requirement for Export XML of Worksheet</h2>
 * It should be possible to export the contents of an worksheet or part of an
 * worksheet to an XML file. As we want to optimize as much as possible the
 * process the solution should not rely on any third party library. Cleansheets
 * should have a window to configure the XML tags to use for each type of
 * element. The export should only include the value of the cells. The export
 * menu option should appear in the File menu.
 *
 *
 * <p>
 * <b>Use Case 1 - "Export the contents of an workbook to XML file":</b>
 * <p>
 * The user inserts all values in the cells and selects File-ExportXML, defines
 * content like workbook, if he wants, defines the tags to see in XML file,
 * assign file title and save it.
 *
 * <p>
 * <b>Use Case 2 - "Export the contents of an Worksheet to XML file":</b>
 * <p>
 * The user inserts all values in the cells and selects File-ExportXML, defines
 * the worksheet to save, if he wants, defines the tags to see in XML file,
 * assign file title and save it.
 *
 * <p>
 * <b>Use Case 3 - "Export part of an worksheet to an XML file":</b>
 * <p>
 * The user inserts all values in the cells and selects File-ExportXML, selects
 * cells to export, the worksheet to save, if he wants, defines the tags to see
 * in XML file, assign file title and save it.
 *
 * <h2>4.2 Analysis - Export XML</h2>
 * The user must have the option to export the worksheet information to a
 * standard xml file with the defined tags (Woorkbook-SpreadSheet-row-column) or
 * set these tags with the names what the user wants. The user can choose one of
 * this options: Export the contents of an workbook to XML file, Export the
 * contents of an Worksheet to XML file and Export part of an worksheet to an
 * XML file.
 *
 *
 * <h3> First "analysis" sequence diagram - Export XML of Worksheetk</h3>
 *
 * <p>
 * <img src="doc-files/lang08.1_Export_xml_sd_analysis.png" alt="image">
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * Basically, for the development and after analysis, we test if the content of
 * the cells of xml file it´s correct for each column and row. I have to test if
 * the tags have been changed according to what the user wants and if show only
 * the columns and rows that had been written.
 *
 *
 * <h3>5.2. UC Realization</h3>
 *
 * I have to manipulate tags xml and create 3 methods on class ExportXML:
 * exportWorkbook - Export the contents of an Worksheet to XML file;
 * exportSpreadsheetSelected - Export part of an worksheet to an XML file;
 * exportSpreadsheet - Export XML of Worksheet; In each method gets the various
 * cleancheets tags per parameter, these variables received, will be written to
 * an XML file. If the user wants can edit these tags and file sets, according
 * to defined tags by user. It´s necessary to create a class ExportXMLAction and
 * ExportXMLController to call all methods to export. Will be using a
 * FileChooser to choose the type of file, StringBuilder to construct the format
 * of XML File and FileWriter to create and write in the file.
 *
 *
 * *
 * <h3>5.3. Classes</h3>
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Implemented Patterns: Low Coupling - High Cohesion.
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * <b>Use Case 1 - "Export the contents of an workbook to XML file"</b>
 *
 * <img src="doc-files/lang08.1_Export_xml_sd_design_1.png" alt="Export_xml_sd_design_3">
 *
 * <p>
 * <b>Use Case 2 - "Export the contents of an Worksheet to XML file"</b>
 *
 * <img src="doc-files/lang08.1_Export_xml_sd_design_3.png" alt="Export_xml_sd_design_3">
 *
 * <p>
 * <b>Use Case 3 - "Export part of an worksheet to an XML file"</b>
 *
 * <img src="doc-files/lang08.1_Export_xml_sd_design_2.png" alt="Export_xml_sd_design_2">
 *
 * <b>Created Classes</b>:
 *
 * <p>
 * ExportXML, ExportXMLAction, ExportXMLController, ExportXMLPanel and
 * ExportMenu
 *
 * <b>Updated Classes/Files</b>:
 *
 * <p>
 * MenuBar
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
 * <p>
 * As an extra this use case also implements a small cell visual decorator if
 * the cell has a comment. This "feature" is not documented in this page.
 * </p>
 *
 * <h2>9. Work Log</h2>
 *
 * -Insert here a log of you daily work. This is in essence the log of your
 * daily standup meetings.-
 * <p>
 * Example
 * </p>
 * <b>Monday</b>
 * <p>
 * Yesterday I worked on:
 * </p>
 * 1. Analysis of Lang08.1 - Export XML 2. Part of Design Lang08.1 - Export XML
 * <p>
 * Today
 * </p>
 * 1. Design Lang08.1 - Export XML, 2. Implementation, 3 - Analysis of Lang08.1
 * - Export XML completed
 * <p>
 * Blocking:
 * </p>
 * 1. nothing
 * <p>
 * <b>Tuesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Design Lang08.1 - Export XML 2. Implementation, 3 - Analysis completed
 * </p>
 * Today
 * <p>
 * 1. Design Lang08.1 - Export XML completed, 2. Implementation
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 * <p>
 * <b>Wednesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Design Lang08.1 - Export XML completed, 2. Implementation
 * </p>
 * Today
 * <p>
 * 1. Update Worklog, 2. Tests
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 * <p>
 * <b>Thursday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Update Worklog, 2. Tests, 3. Helped friends in other use cases
 * </p>
 * Today
 * <p>
 * 1. Sprint 2 Apresentation.
 * <p>
 * 1. nothing
 * </p>
 *
 * <p>
 * <b>Friday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * TEXT
 * </p>
 * Today
 * <p>
 * TEXT
 * </p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * During this sprint, my work was mainly of analysis and study of the
 * application architecture.
 *
 * <h3>10.1. Design and Implementation:</h3>
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
 * Implementation commits (links only open in new windows - select option
 * browser to open in new windows) :
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/d0facf0dad250c4ed017266ddedeacadb4c1e691">Implementation</a>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b978f78ac8f18266a477bc163f23225fada4091c">Implementation
 * Corrections</a>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Ruben Santos
 */
package csheets.worklog.n1130523.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author ruben
 */
class _Dummy_ {
}
