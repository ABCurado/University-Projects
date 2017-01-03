/**
 * Technical documentation regarding the work of the team member (1131190) João
 * Martins during week4.
 *
 * <p>
 * <b>Scrum Master: no</b>
 *
 * <p>
 * <b>Area Leader: no</b>
 *
 * <h2>1. Notes</h2>
 * <p>
 * All the work was done as planned. Initially it was necessary to do some
 * refatoring of some use after case, due to the fact they are not fully
 * functional, affecting the performance of my use case. After being done with
 * my feature I've helped to implement CRM02.1 Address Edition, CRM06.3 Tasks,
 * Reminders, Events, IPC 08.1 File Sharing and also discussing Analysis and
 * Design with my teammates of their respective feature.
 * </p>
 *
 *
 * <h2>2. Use Case/Feature: CRM04.3</h2>
 *
 * <b>Issue in Jira:</b>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-86">LPFOURDG-86</a>
 * <p>
 * <b>Sub-tasks:</b>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-351">LPFOURDG-351</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-352">LPFOURDG-352</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-353">LPFOURDG-353</a></p>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-354">LPFOURDG-354</a></p>
 *
 * <h2>3. Requirement</h2>
 * It should be possible to search for notes (text and lists) within an time
 * interval. The query expression should also allow to search based on the
 * contents of the notes. It should be possible to user regular expressions to
 * search the contents of the notes that are within the time interval. The
 * result should list all the notes that where found. It should be possible to
 * open a specific note by double clicking on it in the result list. It should
 * be possible to export the search results to a range in an worksheet.
 *
 * <h2>4. Analysis</h2>
 * As I had been in the CRM project began facilitated in some aspects such as
 * the question of creating a sidebar, domain model of the application, database
 * and all that was related to the area itself. At first glance the requested
 * requirement, got there first conclusion that I need a new extension, as in
 * the past, my colleagues have joined all the same extent the use cases create
 * notes and lists. Therefore to avoid pollution of code and code overhead in
 * the same class I have decided to create a new extension to facilitate any
 * maintenance in the future if necessary. Thus the window provided the user
 * must follow the methodology of user friendly for easy use and understanding.
 * The user will have the option to search for "various forms", by date, title
 * or content. It will also be possible to export the results of research into a
 * range of cells defined by the user.
 *
 * <p>
 * <b>UIExtensionFind Class: </b>
 * <p>
 * Extends UIExtension. The User Interface known by the MenuBar of Find
 * Notes/List Extension.
 * </p>
 *
 * <b>FindNotesListsExtension Class: </b>
 * <p>
 * Therefore, an extension class should be implemented to support find
 * notes/lists. The class will extend, as all already implemented extensions,
 * the: Extension class (All Inheritance will be available further on this
 * page).
 * </p>
 *
 * <b>FindPanel Class: </b>
 * <p>
 * User interface responsible for providing all the functionality requesitadas
 * user. All your "update" is made by the Observer pattern, where all user
 * actions are being controlled and each is called the update method to update
 * the respective user interface. The search for title or content is made using
 * regular expressions entered by the user.
 * </p>
 *
 * <b>Informations Class: </b>
 * <p>
 * Class used to represent information of a note or list when the user
 * double-clicks on one.
 * </p>
 *
 * <b>Find Controller Class: </b>
 * <p>
 * Class requested by the user interface and responsible for supporting the
 * entire flow if search usage notes / lists, from the time the user enters
 * "something" to search until it receives response.
 * </p>
 *
 * <h2>5. Design</h2>
 *
 * <img src="doc-files/crm04_3_design.png" alt="image">
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * Several tests were made to the Controller in order to be possible to conclude
 * that all the functionality was implemented correctly.
 *
 * <p>
 * Tests with search based on dates such as:
 *
 * <p>
 * public void testSearchListsByStartDate() {
 * System.out.println("testSearchListsByStartDate"); Calendar startDate =
 * DateTime.dateToCalendar(new Date(116, 05, 01)); java.util.List<List> lists =
 * new ArrayList<>(); lists = (java.util.List<List>) this.findController.
 * searchLists(startDate, null, null, false); assertEquals(lists.size(), 3);
 * //All elements must be in the list startDate = DateTime.newCalendar(2016, 6,
 * 21); lists = (java.util.List<List>) this.findController.
 * searchLists(startDate, null, null, false); assertEquals(lists.size(), 2); //2
 * elements must be in the list }
 *
 * <p>
 * Testing research based on content such as:
 *
 * <p>
 * public void testSearchListsByContent() {
 * System.out.println("testSearchListsByContent"); String expression =
 * ".*primeira lista.*"; Set<List> listLists = new HashSet(); listLists =
 * (Set<List>) this.findController. searchLists(null, null, expression, true);
 * assertEquals(listLists.size(), 1); //1 element must be in the list expression
 * = ".*segunda lista.*"; listLists = (Set<List>) this.findController.
 * searchLists(null, null, expression, true); assertEquals(listLists.size(), 1);
 * //1 element must be in the list }
 *
 * <p>
 * Tests with search based on title such as:
 *
 * <p>
 * public void testSearchNotesByTitle() {
 * System.out.println("testSearchNotesByTitle"); String expression = "Nota 1";
 * Set<Note> listLists = new HashSet(); listLists = (Set<Note>)
 * this.findController. searchNotes(null, null, expression, false);
 * assertEquals(listLists.size(), 1); //1 element must be in the list expression
 * = "Nota 2"; listLists = (Set<Note>) this.findController. searchNotes(null,
 * null, expression, false); assertEquals(listLists.size(), 1); //1 element must
 * be in the list expression = "Nota 3"; listLists = (Set<Note>)
 * this.findController. searchNotes(null, null, expression, false);
 * assertEquals(listLists.size(), 1); //1 element must be in the list }
 *
 * <p>
 * In total 11 tests were made, thus covering a wide range of possible errors
 * and bugs that may exist ...
 * </p>
 *
 * <h2>6. Implementation</h2>
 * <p>
 * The implementation went as planned in the analysis and design, there have
 * been no major problems in this regard. There was a slight delay in the
 * completion of that since I encountered a problem where I had to call in
 * outside research to solve. The problem was that the match method of the Java
 * String class did not recognize the content if there \ n or \ r through the
 * text. Researched in several renowned sites such as stackoverflow
 * (http://stackoverflow.com/questions/3445326/regex-in-java-how-to-deal-with-newline)
 * and Oracle
 * (https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html) in
 * an attempt to find a simple and workable solution. After some reflection on
 * the existing problem and some solutions already in hand, I decided to do a
 * line by line analysis, "leaving" so the received text and then applying the
 * regular expression, thus solving the problems of \ n existing in the past.
 * </p>
 *
 * <h2>7. Commit Evidences</h2>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a9f6c9e33c8794dfacbd670a3319934644f03dd4">Commit
 * concerning Analysis</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/35e2731f9918a575118e05bda041e62bd470d751">Commit
 * concerning Design</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b3d80f877d750ad8a331489510a1c269ed022b29">Commit
 * responsible for ensuring time-based research responsibility and regular
 * expressions</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/dad81a069a08c7cff88e14d60e6698ee1a882742">Commit
 * refactoring, and created a new sidebar</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b49f70f297fee8a8dd9b5e125554c1196af52624">Commit
 * implementation done</a>
 * </p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/6bc3d63ffd39684a51d2a319df21ce076193d956">Commit
 * Tests done</a>
 * </p>
 *
 *
 * <h2>8. Work Log</h2>
 *
 * <b>Sunday</b>
 * <p>
 * Analysis and design of the UC
 * <p>
 *
 * <b>Monday</b>
 * <p>
 * Implementation of the UC
 * <p>
 * Helping CRM02.1 Address Edition
 * <p>
 *
 * <b>Tuesday</b>
 * <p>
 * Testing of the UC
 * <p>
 * <p>
 * Helping IPC 08.1 File Sharing
 * <p>
 *
 * <b>Wednesday</b>
 * <p>
 * Helping CRM06.3 Tasks,Reminders, Events
 * <p>
 *
 * <b>Thursday</b>
 * <p>
 * Presentation to the client
 *
 *
 * <h2>9. Self Assessment</h2>
 * <p>
 * All features worked as expected.
 *
 * <h3>9.1. Design and Implementation:</h3>
 *
 * <h3>9.2. Teamwork: ...</h3>
 *
 * <h3>9.3. Technical Documentation: ...</h3>
 *
 * @author João Martins
 */
package csheets.worklog.n1131190.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author João Martins
 */
class _Dummy_ {
}
