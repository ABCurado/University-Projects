/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Rui Bastos
 */
public class Email {

	/**
	 * Email session
	 */
	private Session session;

	/**
	 * Email constructor
	 *
	 * @param session Email session
	 */
	public Email(Session session) {
		this.session = session;
	}

	/**
	 * Returns the email session
	 *
	 * @return email session
	 */
	public Session session() {
		return this.session;
	}

	/**
	 * Authenticates the email account.
	 *
	 * @param server smtp server
	 * @param email email address
	 * @param password account password
	 * @throws MessagingException when data is incorrect
	 */
	public void connect(String server, String email, String password) throws MessagingException {
		Transport transport = session.getTransport("smtp");
		int port = 587;
		transport.connect(server, port, email, password);
		transport.close();
	}

	/**
	 * Send an email message to another email account.
	 *
	 * @param to email address to receive message
	 * @param subject subject of the message
	 * @param body body of the message
	 * @throws MessagingException when the message fails
	 */
	public void sendMessage(String to,
							String subject, String body) throws MessagingException {
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(this.session.getProperties().
			getProperty("mail.username"))); //Remetente
		message.setRecipients(Message.RecipientType.TO,
							  InternetAddress.
							  parse(to)); //Destinatário(s)
		message.setSubject(subject);//Assunto
		message.
			setText(body);
		/**
		 * Método para enviar a mensagem criada
		 */
		Transport.send(message);
	}

}
