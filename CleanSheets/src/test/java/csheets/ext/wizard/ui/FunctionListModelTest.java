/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.wizard.ui;

import csheets.core.formula.Function;
import csheets.core.formula.lang.And;
import csheets.core.formula.lang.False;
import csheets.core.formula.lang.For;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author AB-1140280
 */
public class FunctionListModelTest {

    Function f1;
    Function f2;
    Function f3;
    FunctionListModel list;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        f1 = new And();
        f2 = new False();
        f3 = new For();
        ArrayList<Function> temp = new ArrayList();
        temp.add(f1);
        temp.add(f2);
        temp.add(f3);
        list = new FunctionListModel(temp);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSize method, of class FunctionListModel.
     */
    @Test
    public void testGetSize() {
        int expected = 3;
        Assert.assertEquals(expected, list.getSize());
    }

    /**
     * Test of getElementAt method, of class FunctionListModel.
     */
    @Test
    public void testGetElementAt() {
        String expected = f2.getIdentifier();
        Assert.assertEquals(expected, list.getElementAt(1));
    }

    /**
     * Test of getFunctionInfo method, of class FunctionListModel.
     */
    @Test
    public void testGetFunctionInfo() {
        String expected = "={AND(BOOLEAN)}";
        WizardController controller = new WizardController(null);
        Assert.assertEquals(expected, list.getFunctionInfo(0, controller));
    }

    /**
     * Test of getHelp method, of class FunctionListModel.
     */
    @Test
    public void testGetHelp() {
        String expected = f3.getDescription();
        Assert.assertEquals(expected, list.getHelp(2));
    }

    /**
     * Test of getHelp method, of class FunctionListModel.
     */
    @Test
    public void testEquals() {
        ArrayList<Function> temp = new ArrayList();
        temp.add(f1);
        temp.add(f2);
        temp.add(f3);
        FunctionListModel expected = new FunctionListModel(temp);
        assertEquals(expected, list);
    }

    /**
     * Test of getHelp method, of class FunctionListModel.
     */
    @Test
    public void testNotEquals() {
        ArrayList<Function> temp = new ArrayList();
        temp.add(f1);
        temp.add(f2);
        FunctionListModel expected = new FunctionListModel(temp);
        assertNotEquals(expected, list);
    }

}
