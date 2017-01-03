/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import csheets.support.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Diogo Azevedo
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"INFO, CONTACT"})})
public class Note implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "NOTEID")
	private List<Note> versions;

	private String title;
	private String noteText;
	private String info;
	private boolean versionState;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Contact contact;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private java.util.Calendar time;

	protected Note() {
	}

	public Note(String noteText, Contact contact, boolean noteState) {
		this.contact = contact;
		this.time = DateTime.now();
		this.versionState = noteState;
		this.edit(noteText);
	}

	public void edit(String text) {
		this.info = text;
		String lines[] = text.split("\\r?\\n");
		this.title = lines[0];
		this.noteText = "";
		for (int i = 1; i < lines.length; i++) {
			this.noteText += lines[i] + "\n";
		}
	}

	public boolean addVersion(Note note) {
		return this.versions.add(note);
	}

	public Contact getContact() {
		return this.contact;
	}

	private void timeStamp(Calendar now) {
		this.time = now;
	}

	public List<Note> versionByNote() {
		return this.versions;
	}

	public boolean noteState() {
		return this.versionState;
	}

	public Calendar date() {
		return time;
	}

	public String getInfo() {
		return info;
	}

	public String getTitle() {
		return title;
	}

	public String getNoteText() {
		return noteText;
	}

	public void changeTime(Calendar time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return this.title;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Note instance = (Note) obj;
		return this.equals(instance);
	}

	@Override
	public int hashCode() {
		int hashcode = 29;
		hashcode = hashcode + 11 + this.contact.hashCode();
		hashcode = hashcode + 11 + this.info.hashCode();
		return hashcode;
	}
}
