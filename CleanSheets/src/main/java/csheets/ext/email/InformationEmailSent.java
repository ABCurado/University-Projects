/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.email;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author João Martins
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NAME"})})
public class InformationEmailSent implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String destination;

	private String subject;

	private String body;

	public InformationEmailSent(String destination, String subject, String body) {
		this.destination = destination;
		this.subject = subject;
		this.body = body;
	}

	protected InformationEmailSent() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

}
