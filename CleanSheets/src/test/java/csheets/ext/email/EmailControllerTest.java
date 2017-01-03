/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.email;

import com.icegreen.greenmail.util.GreenMail;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jvnet.mock_javamail.Mailbox;

/**
 *
 * @author Rui Bastos
 * @author Joao Martins
 */
public class EmailControllerTest {

	EmailController instance;

	public EmailControllerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		instance = new EmailController();
	}

	@After
	public void tearDown() {
	}

	/**
	 * Testing email sending.
	 *
	 * @throws java.lang.Exception
	 */
	@Test
	public void test() throws Exception {

		String email = "lapr4_2dg@outlook.pt";
		String password = "LAPR42dg";
		String server = "smtp.live.com";

		final GreenMail mailServer = new GreenMail();
		mailServer.start();

		Email mail = instance.configureEmail(email, password, server);

		Mailbox mailbox = Mailbox.get(email);
		assertEquals(mailbox.size(), 0);

		instance.sendEmail(mail, email, "", "");

		mailbox = Mailbox.get(email);
		assertEquals(mailbox.size(), 1);

		instance.sendEmail(mail, email, "", "");

		mailbox = Mailbox.get(email);
		assertEquals(mailbox.size(), 2);

		mailServer.stop();
	}

}
