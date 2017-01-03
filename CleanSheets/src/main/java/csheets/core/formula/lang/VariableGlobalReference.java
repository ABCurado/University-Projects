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
package csheets.core.formula.lang;

import csheets.core.Cell;
import csheets.core.Value;

/**
 * A reference to a cell in a spreadsheet.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class VariableGlobalReference extends VariableArrayReference {

	/**
	 * Creates a new cell reference to the given address. By default, relative
	 * addressing is used.
	 *
	 * @param cell the cell to which the reference points
	 * @param variable variable
	 */
	public VariableGlobalReference(Cell cell, String variable) {
		super(cell, variable);
	}

	/**
	 * Evaluate method.
	 *
	 * @return Value evaluated.
	 */
	@Override
	public Value evaluate() {
		return this.getCell().getSpreadsheet().getWorkbook().
			getVariableValue(this.getVariable(), this.getPosition());

	}
}
