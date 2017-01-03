/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rui Bento
 */
@Entity
public class VersionControl implements Serializable {

	/**
	 * Version Control id
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Last returned version
	 */
	private int lastVersion;

	/**
	 * If this is marked as deleted
	 */
	private boolean deleted;

	protected VersionControl() {
		lastVersion = 0;
	}

	/**
	 * Get Version Control id
	 *
	 * @return id
	 */
	public Long id() {
		return id;
	}

	/**
	 * Set Version Control id
	 *
	 * @param id ID
	 */
	protected void id(Long id) {
		this.id = id;
	}

	/**
	 * Check if versionNum is the last version
	 *
	 * @param versionNum Version Number
	 * @return boolean
	 */
	protected boolean isLastVersion(int versionNum) {
		return lastVersion == versionNum;
	}

	/**
	 * Add new version and return it's value
	 *
	 * @return lastVersion+1
	 */
	protected int addVersion() {
		lastVersion++;
		return lastVersion;
	}

	/**
	 * Check if Version Control is deleted
	 *
	 * @return boolean
	 */
	protected boolean isDeleted() {
		return deleted;
	}

	/**
	 * Mark Version Control as deleted
	 */
	protected void delete() {
		deleted = true;
	}

}
