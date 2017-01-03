/**
 * Technical documentation regarding the work of the team member (1140280) António Estêvão during week3.
 *
 *
 *
 * <b>Scrum Master: -(yes/no)- yes</b>
 *
 *
 * <b>Area Leader: -(yes/no)- yes</b>
 *
 * <h2>1. Notes</h2>
 *
 * -Notes about the week's work.
 * <p>
 * Finished my FI in tuesday spend the rest of the sprint helping in the
 * implementation of the IPC tictactoe which in the end was functional
 * </p>
 * <h2>2. Use Case/Feature: CRM5.2</h2>
 *
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-88">LPFOURDG-88</a>
 * <p>
 * Cleansheets should now have a 'special' window to display the agenda of a
 * contact. It should be possible to select the contact from a dropbox. The
 * window should display the agenda of the selected contact. The window should
 * also have a field to enter a date in the format DD-MM-YYYY. The window should
 * display the agenda of the selected contact for the selected day. The window
 * should display the day of the week for the given day. The window should also
 * have two buttons: one to move to the next day and the other to move to the
 * previous day. The window should display in a simple list box the events for
 * the select day (for all the calendars).</p>
 * <h2>3. Requirement</h2>
 * <p>
 * When a contact is selected it shoud be displayed his browsable daily agenda,
 * it should be browsable either be a next and previous button or a by
 * specifying a given date
 * </p>
 *
 *
 * <h2>4. Analysis</h2>
 * <p>
 * Mainly a visual use case, after reading how the code was structured found
 * that most of the work was already done, just need to add agenda UI to
 * interact with the user and querie persistence to get results for the a given
 * contact and date, it is also important to add a calendar library to ease the
 * search for a particular day After some search I found jCalendar the best
 * choice since it can be added to the netbeans pallete
 * </p>
 *
 * <h3>Analysis sequence diagram</h3>
 * <p>
 * <img src="doc-files/analysis_CRM_5_2.png" alt="image">
 * </p>
 *
 * <h2>5. Design</h2>
 *
 * <h3>Design sequence diagram</h3>
 * <p>
 * <img src="doc-files/design_CRM_5_2.png" alt="image">
 * <p>
 *
 * <h3>5.1. Functional Tests</h3>
 * <p>
 * For unit tests regarding this is feature are basically to test if the
 * controller returns the correct events given a data and a contact, since this
 * has nothing to change in the domain model most of the testing will be manual,
 * also some unit tests can test if next and previous button are returning the
 * correct date
 * </p>
 *
 * <h3>5.2. UC Realization</h3>
 * Uc completed as the design and tests were pointing, main setbacks were:
 * Understanding how to add a new jar to the maven project Understand how to
 * work with jCalendar and integrate it with the neatbeans pallette Understand
 * how to querie the database correcly(learning about jpql since before i was
 * using native sql queries)
 *
 *
 * <h2>6. Implementation</h2>
 * Besides the UI and controller simple methods this is the most important
 * design option, since I choose to get the values every time the user hits a
 * different date I needed to be fast so I implemented a jpql querie so it would
 * be fast  <code>
 *
 * @Override public Iterable<Event> eventsContactPerDay(Contact contact,
 * Calendar date) { final Query q = entityManager().createQuery("SELECT e " +
 * "FROM Event e " + "JOIN e.calendar cc " + "JOIN cc.contact c " + "where
 * c.name=:name and :date between e.startDate and e.endDate", Event.class);
 * q.setParameter("name", contact.name()); q.setParameter("date", date); return
 * (List<Event>) q.getResultList(); }
 * </code>
 * <h2>9. Work Log</h2>
 *
 * <b>Friday</b>
 * Today
 * <p>
 * 1. Started analysis adn requirements, analysing the use case a learning what
 * code has been done Concluded only controller and Ui methods will be needed,
 * decided to crete a new menu on the extention menu since the requirements ash
 * for a "special" window
 * </p>
 * Blocking:
 * <p>
 * -nothing-
 * </p>
 * <b>Saturday</b>
 * <p>
 * Yesterday I worked on: Analysis
 * </p>
 * Today
 * <p>
 * 1. Stared and finished desiging the new window 2. Learned about jCalendar and
 * added jar to maven project 3. Started sprint 3 (Moving tasks from backlog to
 * sprint scope, atributing tasks and start it) 4. Started tests but had some
 * trouble on how to implement UI and list model tests
 * </p>
 * <p>
 * Blocking:
 * <p>
 * 1. trouble on how to implement UI and list model tests
 *
 * <b>Sunday</b>
 * <p>
 * Uc implementation completed as the design and tests were pointing, main
 * setbacks were: Understand how to work with jCalendar Understand how to querie
 * the database correcly
 * </p>
 * <p>
 * Blocking: 1. How to implement unit test on queries to the database
 * </p>
 * <p>
 * Tomorrow: 1. Ask specialist on how to implement unit test on queries to the
 * database 2. Help all members having problems with their feature
 * </p>
 * <b>Monday</b>
 * <p>
 * Ask specialist on how to implement unit test on queries to the database and
 * several other quesrtion regarding project managing and scrum master tasks.
 * Helped Lang4.2 to understand the feature since last sprint I worked on
 * Lang4.1 Spoke with team about this sprint and other topics. Finishing
 * unfinished tests but didnt commited yet
 * </p>
 * <p>
 * Tomorrow: Help the team
 * <b>Tuesday</b>
 * <p>
 * Spent the whole afternoon helping the tictactoe feature, after design
 * delibterations was decided to split use implementatoin part in 2 and tomorrow
 * integrate Also finished testing the whole use case,was pretty happy with the
 * final tests results
 * </p>
 * <p>
 * Tomorrow: Keep working on IPC tictactoe feature
 * <b>Wednsday</b>
 * <p>
 * Workning on the tictactoe implementation reading on IPC implementation and
 * understanding how it should be done Programming the game logic implementing
 * cell listeners and ipc connection
 * </p>
 * <p>
 * Tomorrow: Test and debug, scrum master related tasks
 * </p>
 * <b>Wednsday</b>
 * <p>
 * Testing the implementation and correcting bugs found Finishing version,
 * ensuring everything was ready for presentation
 * </p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * <h3>10.1. Design and Implementation:</h3>
 * <p>
 * Após a penalizaçao no Sprint 1 por nao tomar uma metodoliga TDD tentei
 * alterar a ordem de trabalho e pensar e implementar os testes antes de começar
 * a implemetaçao do caso de use em si Caso de uso funcional sem bugs detatados
 * 100% de coverage em metodos de controller a tirar duvidas para ter 100% em
 * todo o codigo á exepção da UI
 * </p>
 * <b>Evidences:</b>
 * Some commits were pushed referencing wrong issues Im manualy adding all
 * relevant commits in here and commenting Jira issue with a link to the commits
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/a1b927d93083c0cd659998bb5e2fea40855698dc">Analysis</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/66f63fe1bc2a074dac5f8bf7d78986cd56c31496">Analysis
 * fixing</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/4936b3d267dbf70ea7b9c758ef2b9336d6d2dac2">Design</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/e9b9270ae849aaefb2c8368d94dd74fdb3b9c35b">Design
 * error fixing</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/d87b0801e0c543832e82899227e2ecb34fa75d02">Testing</a>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/03f69d028a60bd8d4a0359167f48879b7be60211">Implementation</a>
 * A lot of other commits made to the IPC tictactoe use case
 * </p>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * Helped on the dedign of IPc tictactoe Done some of the implementation of IPc
 * tictactoe
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author AB-1140280
 */
package csheets.worklog.n1140280.sprint3;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author AB-1140280
 */
class _Dummy_ {
}
