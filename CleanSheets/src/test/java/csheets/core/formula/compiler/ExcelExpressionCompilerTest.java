/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.compiler;

import org.antlr.runtime.tree.CommonTree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael Rocha
 */
public class ExcelExpressionCompilerTest {
    
    public ExcelExpressionCompilerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compileTree method, of class ExcelExpressionCompiler.
     * @throws java.lang.Exception
     */
    @Test
    public void ensureCompileTreeWorksAsIntended() throws Exception {
        String formula = "={POW(2;3)}";
        CommonTree ast = new ExcelExpressionCompiler().compileTree(formula);
        boolean verdict = ast.toStringTree().equals("({ (POW 2 3))");
        assertEquals(true, verdict);
    }
    
}
