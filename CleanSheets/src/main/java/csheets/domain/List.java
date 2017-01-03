/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 * List implements the Notation. List is where the user can set a List with
 * Title and note lines. Each of this note lines has associated a boolean that
 * can be checked or not depending on the use of the List.
 *
 * @see Notation
 * @author Rui Bento
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"CONTACT_ID", "TITLE", "VERSIONNUM"})})
public class List implements Notation<List>, Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	private VersionControl version;

	private String title;

	//@ElementCollection
	private java.util.List<ListLine> lines;

	private int versionNum;

	@ManyToOne
	private Contact contact;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Calendar time;

	protected List() {
	}

	public List(String title, String text, Contact contact) {
		this.title = title;
		lines = new ArrayList<>();
		for (String line : text.split("\n")) {
			lines.add(new ListLine(line));
		}
		this.contact = contact;
		this.time = Calendar.getInstance();
		this.version = new VersionControl();
		this.versionNum = version.addVersion();
	}

	public List(String title, String text, Contact contact,
				VersionControl version) {
		this(title, text, contact);
		this.version = version;
		this.versionNum = version.addVersion();
	}

	public VersionControl version() {
		return version;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getText() {
		String text = "";
		for (ListLine line : lines) {
			text += line.getText() + "\n";
		}
		// to remove last "\n"
		return text.substring(0, text.length() - 1);
	}

	public java.util.List<ListLine> getLines() {
		return lines;
	}

	@Override
	public Contact getContact() {
		return contact;
	}

	public void changeState(String text, boolean state) {
		for (ListLine line : lines) {
			if (line.compareTo(text) == 0) {
				if (line.getCheck() != state) {
					if (state) {
						line.check();
					} else {
						line.uncheck();
					}
				}
				return;
			}
		}
	}

	@Override
	public int getVersionNumber() {
		return this.versionNum;
	}

	@Override
	public Calendar getTimeCreated() {
		return time;
	}

	public void changeTime(Calendar time) {
		this.time = time;
	}

	@Override
	public List newVersion(String title, String text) {
		List newList = new List(title, text, this.contact, this.version);
		return newList;
	}

	@Override
	public boolean isDeleted() {
		return this.version.isDeleted();
	}

	@Override
	public void delete() {
		this.version.delete();
	}

	@Override
	public boolean isLatestVersion() {
		return version.isLastVersion(versionNum);
	}

	@Override
	public boolean sameNotation(List l) {
		return l.version().equals(this.version);
	}

	@Override
	public String toString() {
		return title;
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
		List instance = (List) obj;
		return this.equals(instance);
	}

	@Override
	public int hashCode() {
		int hashcode = 29;
		hashcode = hashcode + 11 + (int) this.contact.id();
		hashcode = hashcode + 11 + this.title.hashCode();
		hashcode = hashcode + 11 + this.versionNum;
		return hashcode;
	}

	@Embeddable
	public class ListLine implements Comparable<String>, Serializable {

		private boolean check;
		private String text;

		protected ListLine() {
		}

		protected ListLine(String text) {
			this.text = text;
			this.check = false;
		}

		public String getText() {
			return text;
		}

		public boolean getCheck() {
			return check;
		}

		protected void check() {
			check = true;
		}

		protected void uncheck() {
			check = false;
		}

		@Override
		public int compareTo(String text) {
			return this.text.compareTo(text);
		}
	}
}
