/**
 * Technical documentation regarding the work of the team member (1140423)
 * Renato Machado during week2.
 *
 * <p>
 * <b>Scrum Master: -(yes/no)- no</b>
 *
 * <b>Area Leader: -(yes/no)- no</b>
 * </p>
 *
 * <h2>1. Notes</h2>
 *
 * Helped my colleagues throughout the sprint in regards to Volt and
 * network/thread related questions.
 *
 * <h2>2. Use Case/Feature: IPC06.1</h2>
 *
 * <p>
 * Issue in Jira:
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-66">LPFOURDG-66</a>
 * </p>
 * <p>
 * LPFOURDG-66
 *
 * There should be a new mechanism to add secure communications (encrypted
 * communications) between instances of Cleansheets. It is not required at this
 * moment that the cypher should be 'professional', only that it should not be
 * trivial to break it. It should be possible to establish secure and unsecure
 * communications with other instances. Cleansheets should now have a new window
 * that logs all the incoming and outgoing communications. Therefore, when this
 * window is activated it should be possible to see encrypted and unsecure data
 * being exchanged. For testing purposes it should be possible for the user to
 * send simple text messages either unsecure or encrypted.
 *
 * </p>
 *
 * <h2>3. Requirement</h2>
 *
 * It should be possible to secure communications (encrypted messages) between
 * instances of Cleansheets. Users should have the choice to use secure or
 * insecure communications (for testing purposes).
 *
 * <p>
 * <b>Use Case "Secure Communication":</b>
 *
 * The user inputs a message, and chooses between a secure or insecure
 * connection, and finally it sends the message. The other instance should
 * receive a message with the connection chosen from the other instance.
 *
 *
 * <h2>4. Analysis</h2>
 *
 * <h3>Send a Secure Message</h3>
 * <p>
 * In order to secure a message we'll need to encrypt it. To do so, we'll need
 * to have an application key to help us protect our communication protocols.
 * Generally, this key should be generated randomly (in a production
 * environment) in order to maintain security, but for simplicity purposes we
 * will just pre-define it.
 * </p>
 * <img src="doc-files/appkey.png" alt="Application Key protecting the CleanSheets instances from attackers">
 * <p>
 * We'll use AES (Advanced Encryption Standard) which is a symmetric encryption
 * algorithm, where we'll use our application key to encrypt our message. On the
 * instance that is receiving our encrypted message, we'll apply the same
 * application key and decrypt our message.
 * </p>
 * <h4>Analysis diagram:</h4>
 *
 * <img src="doc-files/analysis.png" alt="Analysis">
 *
 * <h3>Send a insecure message</h3>
 *
 * This feature is already supported, and was built on the Feature
 * <a href="http://jira.dei.isep.ipp.pt:8080/browse/LPFOURDG-51">IPC01.1</a>.
 *
 * <h2>5. Design</h2>
 * <p>
 * Since all that it's needed for this feature is for secure communication, we
 * only need to make sure we have our Application Key set through the
 * AppSettings, which will allow to get it anywhere our application.
 * </p>
 * <p>
 * Next, while building our service with Volt we need to declare a Channel,
 * which allows us to filter information on run time when Volt is processing the
 * same information. Volt already supports a MessageDecryptionChannel and a
 * MessageEncryptionChannel, so by default we can use that to secure our
 * messages. (This functionality was built while developing this use case)
 * </p>
 *
 * <p>
 * This means that any extension that currently uses the IPC Protocol Services,
 * should be able to secure their messages, thus allow me to get information on
 * what it's sent.
 * </p>
 *
 * <h3>Sequence Diagram:</h3>
 *
 * <p>
 * Since any feature can use this feature, we can generalize the User Interface
 * class (UI) and the Controller class (Controller). The reason for this is
 * because any other feature can implement their own UI and controller while
 * using the same process to implement secure message transmission.
 * </p>
 *
 * <p>
 * <strong>Important:</strong> Since all network based features must implement
 * the protocols services (such as UdpService or TcpService), this diagram will
 * focus solely on showing on how to trigger secure messaging, instead of
 * showing the whole setting up the service process all over.
 * </p>
 * <p>
 * <strong>Note:</strong> It is also important to note that the Service is
 * abstracted to work with the UDP or TCP protocol, which means that the class
 * represented as the "Service" in the sequence diagram can be either UdpService
 * or TcpService.
 * </p>
 *
 * <h4>Server</h4>
 * <img src="doc-files/general_design_server.png" alt="Server generalization">
 *
 * <h4>Client</h4>
 * <img src="doc-files/general_design_client.png" alt="Client generalization">
 *
 * <b>Update</b>: Since the last few days I've written a unified service to
 * serve our extension needs, I've decided to update the design diagram. It is a
 * bit more complete, but essentially it's nearly identical as we just have to
 * declare the channel Volt is filtrating the message and expect for a route.
 *
 * <img src="doc-files/final_design.png" alt="Final Design">
 *
 * <h3>Tests:</h3>
 * <p>
 * Since we're encrypting a message, that should be tested to check proper
 * encryption and decryption. We are going to manually test the network to see
 * expected results.
 * </p>
 *
 *
 * <h2>6. Implementation</h2>
 *
 * <p>
 * In regards to implementation, most of the feature implementation is done
 * through Volt. Since Volt already supports Channels for Encryption and
 * Decryption and as well as watch who receives and sends messages, the feature
 * is easily implemented.
 *
 * <p>
 * Channels are a way to filter the message input and output in certain points
 * of Volt's execution. The Channels have 2 hook methods which are before() and
 * after(), which allows us to execute actions before and after the Volt
 * execution. This allows for some very powerful data manipulation, yet
 * incredibly simple.</p>
 *
 * <p>
 * As for Encryption, Volt uses AES-128 bits encryption which requires a key to
 * both encrypt and decrypt messages. This class is available in Volt's package.
 * </p>
 * <h2>7. Integration/Demonstration</h2>
 *
 * <h2>8. Final Remarks</h2>
 *
 * <h2>9. Work Log</h2>
 *
 * <p>
 * 04/06/2016</p>
 * <b>Saturday</b>
 * <p>
 * Today</p>
 * <p>
 * Started to plan out Volt new features. Started and completed the first
 * analysis on this feature.</p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing.</p>
 *
 * <p>
 * 06/06/2016</p>
 * <p>
 * Monday</p>
 * <p>
 * Today</p>
 * <p>
 * Implemented Volt new features. Added base design.</p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing</p>
 *
 * <p>
 * 07/06/2016</p>
 * <p>
 * Tuesday</p>
 * <p>
 * Today</p>
 * <p>
 * Going to work a bit on unifying the multiple network services into one
 * service. Going to make some improvements into Volt.</p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing</p>
 *
 * <p>
 * 08/06/2016</p>
 * <p>
 * Wednesday</p>
 * <p>
 * Yesterday</p>
 * <p>
 * Implemented the Volt static class and made some small improvements to it.</p>
 * <p>
 * Today</p>
 * <p>
 * Will finish my use case by implementing the User Interface and the associated
 * controller following the design. Test my use case and update the overall
 * documentation.</p>
 * <p>
 * Blocking</p>
 * <p>
 * Nothing</p>
 *
 * <h2>10. Self Assessment</h2>
 *
 * <p>
 * Outcome 3 ("Design and Implementation") -4</p>
 * <p>
 * Outcome 5 ("Teamwork") - 3</p>
 * <p>
 * Outcome 6 ("Technical Documentation") - 4</p>
 *
 * <h3>10.1. Design and Implementation:3</h3>
 *
 * The implementation uses Volt, a package developed during the RCOMP course of
 * the current year (2015/2016), which aims to ease the development of
 * communication protocols with a expressive syntax. It has some nice features
 * such as Routing (Route cycling on UDP) and automatic management of multiple
 * UDP packets.
 *
 * <p>
 * <b>Evidences:</b>
 * </p>
 *
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/b8cf8e3950952c780501d4618af5b4626b086452">Analysis</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/41c3c827ab8a71cbcf35f881f617e5534537eb84">Base
 * design</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/941200bbd1397d028114571cfaf36d3c0bf80e20">Improved
 * Volt performance and documentation. Added new features to Volt: Channels and
 * Dependencies. Added Encrypter.</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/4f27d5ab684c3fbb892412d6ba8d8ef9887c2811">Added
 * the ability for Volt to handle multiple concurrent services.</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/f1f002968d1da85a6d73db628982edaf9777a861">Updated
 * feature UI and Controller. Updated Volt.</a></p>
 * <p>
 * <a href="https://bitbucket.org/lei-isep/lapr4-2016-2dg/commits/3c6e1ab1a01b3d9e20f8864ed7096c855eabee7e">Added
 * the NetworkManager</a></p>
 *
 *
 * <h3>10.2. Teamwork: ...</h3>
 *
 * <h3>10.3. Technical Documentation: ...</h3>
 *
 * @author Renato Machado
 */
package csheets.worklog.n1140423.sprint2;

/**
 * This class is only here so that javadoc includes the documentation about this
 * EMPTY package! Do not remove this class!
 *
 * @author alexandrebraganca
 */
class _Dummy_ {
}
