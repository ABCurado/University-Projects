/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package csheets.core;

import csheets.core.formula.VariableArray;
import csheets.ext.macro_beanshell.Code;
import csheets.ui.FormEditor.ui.FormE;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A workbook which can contain several spreadsheets.
 *
 * @author Einar Pehrson
 */
public class Workbook implements Iterable<Spreadsheet>, Serializable {

	/**
	 * The unique version identifier used for serialization
	 */
	private static final long serialVersionUID = -6324252462576447242L;

	/**
	 * The spreadsheets of which the workbook consists
	 */
	private List<Spreadsheet> spreadsheets = new ArrayList<Spreadsheet>();

	/**
	 * The cell listeners that have been registered on the cell
	 */
	private transient List<WorkbookListener> listeners
		= new ArrayList<>();

	/**
	 * The number of spreadsheets that have been created in the workbook
	 */
	private int createdSpreadsheets;

	private String parentFileName = "";

	/**
	 * Creates a new empty workbook.
	 */
	public Workbook() {
	}

	/**
	 * Creates a new workbook, which initially contains the given number of
	 * blank spreadsheets.
	 *
	 * @param sheets the number of sheets to create initially
	 */
	public Workbook(int sheets) {
		for (int i = 0; i < sheets; i++) {
			spreadsheets.
				add(new SpreadsheetImpl(this, getNextSpreadsheetTitle()));
		}
	}

	/**
	 * Creates a new workbook, using the given content matrix to create
	 * spreadsheets initially.
	 *
	 * @param contents the content matrices to use when creating spreadsheets
	 */
	public Workbook(String[][]... contents) {
		for (String[][] content : contents) {
			spreadsheets.add(new SpreadsheetImpl(this,
												 getNextSpreadsheetTitle(), content));
		}
	}

	/**
	 * Adds a blank spreadsheet to the end of the workbook.
	 */
	public void addSpreadsheet() {
		Spreadsheet spreadsheet = new SpreadsheetImpl(this,
													  getNextSpreadsheetTitle());
		spreadsheets.add(spreadsheet);
		fireSpreadsheetInserted(spreadsheet, spreadsheets.size() - 1);
	}

	/**
	 * Adds a blank Tictactoe spreadsheet to the end of the workbook.
	 */
	public void addTictactoeSpreadsheet() {
		Spreadsheet spreadsheet = new SpreadsheetImpl(this, "TicTacToe");
		spreadsheets.add(spreadsheet);
		fireSpreadsheetInserted(spreadsheet, spreadsheets.size() - 1);
	}

	/**
	 * Adds a new spreadsheet to the workbook, in which cells are initialized
	 * with data from the given content matrix.
	 *
	 * @param content the contents of the cells in the spreadsheet
	 */
	public void addSpreadsheet(String[][] content) {
		Spreadsheet spreadsheet = new SpreadsheetImpl(this,
													  getNextSpreadsheetTitle(), content);
		spreadsheets.add(spreadsheet);
		fireSpreadsheetInserted(spreadsheet, spreadsheets.size() - 1);
	}

	public String getParentFileName() {
		return this.parentFileName;
	}

	public void setParentFileName(String fileName) {
		this.parentFileName = fileName;
	}

	/**
	 * Returns the title to be used for the next spreadsheet added.
	 *
	 * @return the title to be used for the next spreadsheet added
	 */
	private String getNextSpreadsheetTitle() {
		return SpreadsheetImpl.BASE_TITLE + " " + (createdSpreadsheets++ + 1);
	}

	/**
	 * Adds a new blank spreadsheet to the workbook.
	 *
	 * @param spreadsheet spreadsheet
	 */
	public void removeSpreadsheet(Spreadsheet spreadsheet) {
		spreadsheets.remove(spreadsheet);
		// Remove references to the spreadsheet in remaining spreadsheets!
		fireSpreadsheetRemoved(spreadsheet);
	}

	/**
	 * Returns the spreadsheet at the given index.
	 *
	 * @param index the index of the spreadsheet in the workbook
	 * @return the spreadsheet at the given index
	 * @throws IndexOutOfBoundsException if the index is out of range (index
	 * less than 0 or index greater or equal |spreadsheets|)
	 */
	public Spreadsheet getSpreadsheet(int index) throws IndexOutOfBoundsException {
		return spreadsheets.get(index);
	}

	/**
	 * Returns the number of spreadsheets in the the workbook.
	 *
	 * @return the number of spreadsheets in the the workbook
	 */
	public int getSpreadsheetCount() {
		return spreadsheets.size();
	}

	/**
	 * Returns an iterator over the spreadsheets in the workbook.
	 *
	 * @return an iterator over the spreadsheets in the workbook
	 */
	public Iterator<Spreadsheet> iterator() {
		return spreadsheets.iterator();
	}

	/*
	 * EVENT HANDLING
	 */
	/**
	 * Registers the given listener on the workbook.
	 *
	 * @param listener the listener to be added
	 */
	public void addWorkbookListener(WorkbookListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes the given listener from the workbook.
	 *
	 * @param listener the listener to be removed
	 */
	public void removeWorkbookListener(WorkbookListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Returns the listeners that have been registered on the workbook.
	 *
	 * @return the listeners that have been registered on the workbook
	 */
	public WorkbookListener[] getWorkbookListeners() {
		return listeners.toArray(new WorkbookListener[listeners.size()]);
	}

	/**
	 * Notifies all registered listeners that a spreadsheet has been inserted.
	 *
	 * @param spreadsheet the spreadsheet that was inserted
	 * @param index the index at which the spreadsheet was inserted
	 */
	private void fireSpreadsheetInserted(Spreadsheet spreadsheet, int index) {
		for (WorkbookListener listener : listeners) {
			listener.spreadsheetInserted(spreadsheet, index);
		}
	}

	/**
	 * Notifies all registered listeners that a spreadsheet has been removed.
	 *
	 * @param spreadsheet the spreadsheet that was removed
	 */
	private void fireSpreadsheetRemoved(Spreadsheet spreadsheet) {
		for (WorkbookListener listener : listeners) {
			listener.spreadsheetRemoved(spreadsheet);
		}
	}

	/**
	 * Notifies all registered listeners that a spreadsheet has been renamed.
	 *
	 * @param spreadsheet the spreadsheet that was renamed
	 */
	@SuppressWarnings("unused")
	private void fireSpreadsheetRenamed(Spreadsheet spreadsheet) {
		for (WorkbookListener listener : listeners) {
			listener.spreadsheetRenamed(spreadsheet);
		}
	}

	/*
	 * GENERAL
	 */
	/**
	 * Customizes deserialization by recreating the listener list.
	 *
	 * @param stream the object input stream from which the object is to be read
	 * @throws IOException If any of the usual Input/Output related exceptions
	 * occur
	 * @throws ClassNotFoundException If the class of a serialized object cannot
	 * be found.
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
		listeners = new ArrayList<WorkbookListener>();
	}

	/**
	 * Variables dedicated code. This code assembles all Variables associated
	 * operations.
	 *
	 * @author Pedro Gomes 1130383@isep.ipp.pt
	 */
	private List<VariableArray> variables = new ArrayList<>();

	/**
	 * Clears all Variables associated with this Workbook.
	 */
	public void clearVariables() {
		this.variables.clear();
	}

	/**
	 * Returns the Variable by providing its name.
	 *
	 * @param name variable name.
	 * @return VariableArray.
	 */
	public VariableArray getVariable(String name) {
		for (VariableArray var : variables) {
			if (var.getName().equals(name)) {
				return var;
			}
		}
		return null;
	}

	/**
	 * Returns a specific Value providing the variables' name and a position.
	 *
	 * @param name name of variable
	 * @param position position of variable
	 * @return value of variable
	 */
	public Value getVariableValue(String name, int position) {
		for (VariableArray var : variables) {
			if (var.getName().equals(name)) {
				return var.getValue(position);
			}
		}
		return null;
	}

	/**
	 * Checks if Variable exists by providing its name.
	 *
	 * @param name name of variable
	 * @return True if variable exists.
	 */
	public boolean variableExist(String name) {
		for (VariableArray var : variables) {
			if (var.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new Variable to the Workbook.
	 *
	 * @param newVariable new Variable.
	 */
	public void addVariable(VariableArray newVariable) {
		if (!variableExist(newVariable.getName())) {
			this.variables.add(newVariable);
		} else {
		}

	}

	public void addVariableByName(String name, Value value, int position) {
		if (!variableExist(name)) {
			VariableArray newVar = new VariableArray(name, value, position);
			this.variables.add(newVar);
		} else {
			getVariable(name).addValueToVariable(value, position);
		}
	}

	/**
	 * Updates a Value from the Variable.
	 *
	 * @param variable variable.
	 * @param value value.
	 * @param position position.
	 */
	public void updateValue(String variable, Value value, int position) {
		for (VariableArray var : variables) {
			if (var.getName().equals(variable)) {
				var.addValueToVariable(value, position);
			}
		}
	}

	/**
	 *
	 * @return all Variables from Workbook.
	 */
	public List<VariableArray> getAllVariables() {
		return this.variables;
	}

	/**
	 * End of Variables related code.
	 */
	/**
	 * Map of the FormE
	 */
	private Map<String, FormE> lstFormE = new HashMap();

	/**
	 * Clear all Forms of Workbook
	 */
	public void clearForms() {
		this.lstFormE.clear();
	}

	/**
	 *
	 * @param name name of form
	 * @return Form Map
	 */
	public FormE getForm(String name) {
		return lstFormE.get(name);
	}

	/**
	 * Add FormE to Map
	 *
	 * @param name name of form
	 * @param formE the formE
	 */
	public void addFormE(String name, FormE formE) {
		lstFormE.put(name, formE);
	}

	/**
	 * End of Variable associated Code.
	 */
	//
	//
	/**
	 * The list of workbook scripts
	 */
	private List<Code> scripts = new ArrayList<>();

	/**
	 * Clears all scripts of workbook
	 */
	public void clearScripts() {
		this.scripts.clear();
	}

	/**
	 * Returns the corresponding script to the name
	 *
	 * @param name Script name
	 * @return code of script
	 */
	public Code getScript(String name) {
		for (Code code : scripts) {
			if (code.getName().equals(name)) {
				return code;
			}
		}
		return null;
	}

	/**
	 * Returns a script list
	 *
	 * @return list of scripts
	 */
	public List<Code> getScripts() {
		return this.scripts;
	}

	/**
	 * Adds a script to the list
	 *
	 * @param code Code of script
	 */
	public void addScript(Code code) {
		this.scripts.add(code);
	}

}
