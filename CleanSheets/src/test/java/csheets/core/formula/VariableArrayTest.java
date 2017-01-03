/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula;

import csheets.core.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This Test Class concerns VariableArray Operations. This test verify if the
 * given position changes its current Value.
 *
 * @author Pedro Gomes 1130383@isep.ipp.pt
 */
public class VariableArrayTest {

	private VariableArray variable;
	private Value value;

	/**
	 * Sets up, in the position 5 of the Array, the value of 4.
	 */
	public VariableArrayTest() {
		value = new Value(10);
		variable = new VariableArray("@global", value, 5);
	}

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of addValueToVariable method, of class VariableArray. This tests
	 * checks if the Position updates its values.
	 */
	@Test
	public void testAddValueToVariable() {
		System.out.println("addValueToVariable");
		Value _value = new Value(4);
		int position = 5;
		VariableArray instance = new VariableArray("@temp", _value, position);
		instance.addValueToVariable(this.value, position);
		Assert.assertEquals(variable.getValue(position).toString(), instance.
							getValue(position).toString());
	}
}
