/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula;

import csheets.core.Value;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Variable Array Class. This class represents an actual variable with all is
 * values.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class VariableArray implements Serializable {

	/**
	 * Default Size of the Variable ArrayList.
	 */
	private static final int DEFAULT_SIZE = 50;
	/**
	 * Variales name.
	 */
	private String variable;

	/**
	 * Variables array.
	 */
	private List<Value> array = new ArrayList<>(DEFAULT_SIZE);

	/**
	 * Creates an Variable Array. All variable are Arrays.
	 *
	 * @param variable variable.
	 * @param value value.
	 * @param position position.
	 */
	public VariableArray(String variable, Value value, int position) {
		initializeList();
		this.variable = variable;
		this.array.add(position, value);

	}

	/**
	 * List initialization.
	 */
	private void initializeList() {
		for (int i = 0; i < DEFAULT_SIZE; i++) {
			Value value = new Value(0);
			array.add(value);
		}
	}

	/**
	 * Adds a Value to a specific position of the Variables' array. If that
	 * position is already filles the value is replaced.
	 *
	 * @param value value to insert.
	 * @param position position of the array.
	 */
	public void addValueToVariable(Value value, int position) {

		if (array.get(position) == null) {
			array.add(position, value); //adds.
		} else {
			array.set(position, value); //replace if already occupied.
		}
	}

	/**
	 * Returns a value in a specific position.
	 *
	 * @param position position.
	 * @return Value.
	 */
	public Value getValue(int position) {
		return this.array.get(position);
	}

	/**
	 * Returns the Variable Name.
	 *
	 * @return variable.
	 */
	public String getName() {
		return this.variable;
	}

	/**
	 * Returns all Values associated with the Variable.
	 *
	 * @return array.
	 */
	public List<Value> getArray() {
		return this.array;
	}

	/**
	 * To String.
	 *
	 * @return Variable Name.
	 */
	@Override
	public String toString() {
		return this.variable;
	}
}
