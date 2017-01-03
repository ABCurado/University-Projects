/**
 * Technical documentation regarding the work of the team member (1130523) Ruben
 * Santos during week4.
 *
 * <p>
 * <b>Scrum Master: João Martins</b>
 *
 * <p>
 * <b>Area Leader: Rui Bento</b>
 *
 *
 * <h2>1. Notes</h2>
 *
 *
 * <h2>2. Use Case/Feature: IPC08.1) File Sharing</h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-72">LPFOURDG-72</a>
 *
 * <p>
 * Sub-Task in Jira:
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-375">LPFOURDG-375</a>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-376">LPFOURDG-376</a>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-377">LPFOURDG-377</a>
 * <p>
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-378">LPFOURDG-378</a>
 *
 * <h2>3. IPC08.1) File Sharing</h2>
 *
 * Cleansheets should have a new option to share the files contained in a
 * specific directory. The user should be able to specify the directory to share
 * (output). These files should now be listed on other instances of Cleansheets
 * (in a specific window, for instance, in a sidebar). The list should include
 * the name of the files and its size. It is also required to configure the
 * local directory that will receive the downloaded files (input). The
 * configuration of file sharing should be persistent. For the moment it is not
 * required to implement the download of files, however it is necessary to keep
 * the list of files updated automatically. It is also necessary to update the
 * list of files that where selected for download in the input list. This list
 * should include the name of the file, its size, its source and its status
 * (download in progress, up to date, etc.).
 *
 * <p>
 * <b>Use Case 1 - "Inbox files Reception":</b>
 * <p>
 *
 * The files should now be listed on other instances of Cleansheets (in a
 * specific window, for instance, in a sidebar). The list should include the
 * name of the files and its size. It is also required to configure the local
 * directory that will receive the downloaded files (input). The configuration
 * of file sharing should be persistent.
 *
 *
 * <p>
 * <b>Use Case 2 - "Outbox files Share":</b>
 * <p>
 *
 * Cleansheets should have a new option to share the files contained in a
 * specific directory. The user should be able to specify the directory to share
 * (output). Then the file must be in the outbox with all specific data (name of
 * the file, its size, its source and its status). The configuration of file
 * sharing should be persistent.
 *
 *
 *
 * <h2>4. Analysis - IPC08.1) File Sharing</h2>
 *
 *
 * The purpose of this use case is to create an extension that allows you to
 * share files in the same network . You must receive the network port
 * configuration and the location of the folder to share. Another cleansheets
 * program should receive a list of available files and show it . This requires
 * that at least two programs are running simultaneously on the same network .
 * One will be the client and the other the server. The server will start with
 * the selected folder you want to share is listening waiting for the request of
 * a customer. You'll have to tell the customer the amount of files sending.
 * Once you know this value the customer knows how many files will receive. When
 * you finish this process , closes the connection. The list of files updated
 * automatically. It is also necessary to update the list of files that where
 * selected for download in the input list. This list should include the name of
 * the file, its size, its source and its status (download in progress, up to
 * date, etc.).
 *
 *
 * <h3> First "analysis" sequence diagram - IPC08.1) File Sharing</h3>
 *
 * <p>
 * <img src="doc-files/ipc_file_sharing_analysis_ssd.png" alt="image">
 *
 * <h2>5. Design</h2>
 *
 * <h3>5.1. Functional Tests</h3>
 *
 * I have to do tests for this specific use case of class ShareFilesController.
 * Have the specific method pathDownloads(), pathFiles(), files(). I test if a
 * method stop(), stops all the UDP services, if JtextField in UI shows default
 * path in the seperator files and downloads and if returns a list with all
 * files for a specific path.
 *
 *
 *
 * <h3>5.2. UC Realization</h3>
 *
 * For the devolopment I need to create a subclass of Extension and a subclass
 * of UIExtension. For the sidebar I need implement a JPanel. Will need a button
 * to set the directory with the files that you want to transfer and tab
 * downloads with the list of files received by broadcast You also need a button
 * to download only certain user- selected files. I used some methods of the
 * package Volt Request class to get some file information such as the IP of who
 * sent the files, the port, the size of each file and the file name.
 *
 *
 * <h3>5.3. Classes</h3>
 *
 *
 * <h3>5.4. Design Patterns and Best Practices</h3>
 * <p>
 * Observer: This Pattern is used to notify ShareFilesPanel with new instances
 * in local network.
 *
 * <h2>6. Implementation</h2>
 *
 * <b>Sequence Diagrams</b>
 *
 * <h3>Extension Setup (runtime)</h3>
 * The Sequence diagram shows the setup of the share files extension when
 * cleansheets is runing.
 * <p>
 * <img src="doc-files/ipc_file_sharing_extensions.png" alt="image">
 *
 * <h3>Setup of the extension</h3>
 * <p>
 * The sequence diagram illustrates the creation of the sharefiles extension.
 * All the extensions are loaded dynamically by the ExtensionManager at
 * application startup.
 * <img src="doc-files/ipc_file_sharing_designextension_sd.png" alt="image">
 *
 * <p>
 * The sequence diagram illustrates the creation of the user interface
 * extension. All the UI extensions are loaded by the UIController at
 * application startup.
 * <img src="doc-files/ipc_file_sharing_designextension_sd2.png" alt="image">
 *
 *
 *
 * <p>
 * <b>SD - Share files Reception</b>
 * <p>
 * The sequence diagram illustrates how Share Files UC works
 * <img src="doc-files/ipc_file_sharing_design_sd3.png" alt="image">
 *
 * <p>
 * <b>Created Classes</b>:
 *
 * <p>
 * ShareFilesController, ShareFilesExtension, UIExtensionShareFiles,
 * ShareFilesPanel;
 * <p>
 * <b>Updated Classes/Files</b>:
 *
 * <p>
 * extensions.props
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
 * TEXT
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
 * nothing
 * <p>
 * Today
 * </p>
 * 1. Analysis - IPC08.1) File Sharing 2. Design - IPC08.1) File Sharing
 * <p>
 * Blocking:
 * </p>
 * 1. nothing
 * <p>
 * <b>Tuesday</b>
 * </p>
 * Yesterday I worked on:
 * <p>
 * 1. Analysis - IPC08.1) File Sharing 2. Design - IPC08.1) File Sharing
 * </p>
 * Today
 * <p>
 * 1. Design - IPC08.1) File Sharing Sequence Diagrams 2. Implementation -
 * ShareFilesExtension, controller and UI with a list of files for specific path
 * and information of each file.
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
 * 1. Design - IPC08.1) File Sharing Sequence Diagrams 2. Implementation -
 * ShareFilesExtension, controller and UI with a list of files for specific path
 * and information of each file.
 * </p>
 * Today
 * <p>
 * 1. Design Completetion - IPC08.1) File Sharing Sequence Diagrams 2.
 * Implementation - Some corrections in UI. 3. Unit Tests.
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
 * 1. Design Completetion - IPC08.1) File Sharing Sequence Diagrams 2.
 * Implementation - Some corrections in UI. 3. Unit Tests.
 * </p>
 * Today
 * <p>
 * 1. Apresentation of Sprint 3; 2. Worklog Update
 * <p>
 * Blocking:
 * <p>
 * 1. nothing
 * </p>
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
 * TEXT
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
 * ShareFilesExtension, controller and UI with a list of files for specific path
 * and information of each file.
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/6c66c20da15793c88360abbb44766b1fac0f9ea8">
 * Implementation</a>
 *
 *
 * <p>
 * Some corrections in UI
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/f816b0a2c37292a720207ebe07dbdfd822d4b3d1">
 * Implementation Corrections</a>
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Ruben Santos
 */
package csheets.worklog.n1130523.sprint4;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author ruben
 */
class _Dummy_ {
}
