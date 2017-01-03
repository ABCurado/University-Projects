/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.domain;

import java.util.Calendar;

/**
 * Represents a Notation domain.
 * ie: It can be a Note, List, ... (etc)
 * @author Rui Bento
 */
interface Notation<E> {
    
    /**
     * Get Notation Title
     * @return title
     */
    public String getTitle();
    
    /**
     * Get note lines in a String
     * @return string
     */
    public String getText();
    
    /**
     * Get Notation contact
     * @return contact
     */
    public Contact getContact();
    
    /**
     * Get Notation version number
     * @return version number
     */
    public int getVersionNumber();
    
    /**
     * Time (Calendar) that notation was created
     * @return time
     */
    public Calendar getTimeCreated();
    
    /**
     * Create new version of the Notation
     * @param title Title
     * @param text Text
     * @return new version
     */
    public E newVersion(String title, String text);
    
    /**
     * Check if Notation is deleted
     * @return boolean
     */
    public boolean isDeleted();
    
    /**
     * Mark Notation as deleted
     */
    public void delete();
    
    /**
     * Check if Notation is the last version
     * @return boolean
     */
    public boolean isLatestVersion();
    
    /**
     * Compare Notations to check if it is the same Notation or another version
     * of the Notation
     * @param notation Notation
     * @return boolean
     */
    public boolean sameNotation(E notation);
}
