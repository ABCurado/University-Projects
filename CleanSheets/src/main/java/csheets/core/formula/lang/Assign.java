/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.lang;

import csheets.core.CellImpl;
import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.Workbook;
import csheets.core.formula.BinaryOperator;
import csheets.core.formula.Expression;
import csheets.core.formula.VariableArray;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Function where the Left Operand will be assigned the Value of the Right
 * Operand.
 *
 * @author Ruben Teixeira 1140780@isep.ipp.pt
 *
 */
public class Assign implements BinaryOperator {

	/**
	 * Creates a new Assign
	 */
	public Assign() {
	}

	@Override
	public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
		Value value = new Value();
		try {
			if (rightOperand instanceof VariableGlobalReference) {
				VariableGlobalReference var = (VariableGlobalReference) rightOperand;
				value = var.evaluate();
			} else if (rightOperand instanceof VariableLocalReference) {
				VariableLocalReference var = (VariableLocalReference) rightOperand;
				value = var.evaluate();
			} else {
				value = rightOperand.evaluate();
			}
			if (leftOperand instanceof VariableGlobalReference) { //LEFT OPERANT ASSIGNMENT FOR GLOBAL
				VariableGlobalReference var = (VariableGlobalReference) leftOperand;

				Workbook workbook = var.getCell().getSpreadsheet().getWorkbook();
				if (!workbook.variableExist(var.getVariable())) {//if it doens't exist.
					VariableArray newVar = new VariableArray(var.getVariable(), value, var.
															 getPosition()); //creates a new VariableArray.
					workbook.addVariable(newVar);
				} else { // if it exists.
					workbook.updateValue(var.getVariable(), value, var.
										 getPosition());
				}
//				workbook.addVariable(var.getVariable(), value);
			} else if (leftOperand instanceof VariableLocalReference) { //LEFT OPERANT ASSIGNMENT FOR LOCAL

				VariableLocalReference var = (VariableLocalReference) leftOperand;
				CellImpl c = (CellImpl) var.getCell();
				if (!c.variableExist(var.getVariable())) {
					VariableArray newVar = new VariableArray(var.getVariable(), value, var.
															 getPosition());
					((CellImpl) var.getCell()).addVariable(newVar);

				} else {
					((CellImpl) var.getCell()).
						updateValue(var.getVariable(), value, var.getPosition());
				}
//				((CellImpl) var.getCell()).addVariable(var.getVariable(), value);

			} else if (leftOperand instanceof CellReference) {
				CellReference cell = (CellReference) leftOperand;
				((CellImpl) cell.getCell()).setContent(value.toString(), false);
			}
		} catch (Exception ex) {
			Logger.getLogger(Assign.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		return value;
	}

	@Override
	public String getIdentifier() {
		return ":=";
	}

	@Override
	public Value.Type getOperandValueType() {
		return Value.Type.UNDEFINED;
	}

	@Override
	public String toString() {
		return getIdentifier();
	}

}
