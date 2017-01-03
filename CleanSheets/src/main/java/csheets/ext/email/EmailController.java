/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.email;

import csheets.persistence.PersistenceContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;

/**
 *
 * @author Rui Bastos
 */
public class EmailController {

	private Email mail;

	public Email configureEmail(String email, String password, String server) throws MessagingException, IOException {

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", server); //smtp.live.com
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.quitwait", "false");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.username", email);
		props.put("mail.password", password);

		// or use getDefaultInstance instance if desired...
		Session session = Session.
			getInstance(props, new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(props.
								getProperty("mail.username"), props.
															  getProperty("mail.password"));
						}
					});

		Email mail = new Email(session);
		mail.connect(server, email, password);

		//updateProperties(session.getProperties());
		return mail;
	}

	public void sendEmail(Email email, String to,
						  String subject, String body) throws MessagingException, SendFailedException {

		email.sendMessage(to, subject, body);
	}

	public void updateProperties(Properties props) throws FileNotFoundException, IOException {

		File f = new File("mail.properties");
		OutputStream out = new FileOutputStream(f);
		props.store(out, "Email data");
	}

	/**
	 * Persist the sent email.
	 *
	 * @param sentEmail
	 */
	public void persistSentEmail(InformationEmailSent sentEmail) {
		PersistenceContext.repositories().emails().save(sentEmail);
	}

	/**
	 * List all emails.
	 *
	 * @return all email.
	 */
	public List<InformationEmailSent> allSentEmails() {
		return (List<InformationEmailSent>) PersistenceContext.repositories().
			emails().all();
	}
}
