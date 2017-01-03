/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.core.formula.compiler;

import csheets.AppSettings;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import org.antlr.runtime.tree.CommonTree;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rui Freitas <1130303@isep.ipp.pt>
 */
public class MonetaryExpressionCompilerTest {

    private final static String EXCHANGERATE_POUNDTOEURO = "exchangerate.poundToEuro";
    private final static String EXCHANGERATE_DOLLARTOEURO = "exchangerate.dollarToEuro";
    private final static String EXCHANGERATE_EUROTOPOUND = "exchangerate.euroToPound";
    private final static String EXCHANGERATE_EUROTODOLLAR = "exchangerate.euroToDollar";

    private Cell theCell;

    private String poundToEuro;
    private String dollarToEuro;
    private String euroToPound;
    private String euroToDollar;

    public MonetaryExpressionCompilerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Workbook workbook = new Workbook(1);
        Spreadsheet spreadsheet = workbook.getSpreadsheet(0);
        theCell = spreadsheet.getCell(0, 0);

        /* Read values to store */
        poundToEuro = AppSettings.instance().get(EXCHANGERATE_POUNDTOEURO);
        dollarToEuro = AppSettings.instance().get(EXCHANGERATE_DOLLARTOEURO);
        euroToPound = AppSettings.instance().get(EXCHANGERATE_EUROTOPOUND);
        euroToDollar = AppSettings.instance().get(EXCHANGERATE_EUROTODOLLAR);

        /* Write test values */
        AppSettings.instance().set(EXCHANGERATE_POUNDTOEURO, "1.27");
        AppSettings.instance().set(EXCHANGERATE_DOLLARTOEURO, "0.88");
        AppSettings.instance().set(EXCHANGERATE_EUROTOPOUND, "0.78");
        AppSettings.instance().set(EXCHANGERATE_EUROTODOLLAR, "1.14");
    }

    @After
    public void tearDown() {
        /* Restore values */
        AppSettings.instance().set(EXCHANGERATE_POUNDTOEURO, poundToEuro);
        AppSettings.instance().set(EXCHANGERATE_DOLLARTOEURO, dollarToEuro);
        AppSettings.instance().set(EXCHANGERATE_EUROTOPOUND, euroToPound);
        AppSettings.instance().set(EXCHANGERATE_EUROTODOLLAR, euroToDollar);

    }

    /**
     * Test of compileTree method, of class ExcelExpressionCompiler.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void ensureCompileTreeWorksAsIntended() throws Exception {
        String formula = "#euro{2$+25$}";
        CommonTree ast = new MonetaryExpressionCompiler().compileTree(formula);
        boolean verdict = ast.toStringTree().equals("(euro (+ ($ 2) ($ 25)))");
        assertEquals(true, verdict);
    }

//	/**
//	 * Test of compile method, of class MonetaryExpressionCompiler.
//	 */
//	@Test
//	public void testCompileDollarWithEuroAndDollar() throws Exception {
//		String source = "#dollar{15€+56$}";
//		MonetaryExpressionCompiler instance = new MonetaryExpressionCompiler();
//		Expression result = instance.compile(theCell, source);
//		double res = 73.27;
//
//		assertEquals(res, result.evaluate().toDouble(), 0.1);
//	}
//
//	/**
//	 * Test of compile method, of class MonetaryExpressionCompiler.
//	 */
//	@Test
//	public void testCompilePoundWithDollarAndDollar() throws Exception {
//		String source = "#pound{25$+56$}";
//		MonetaryExpressionCompiler instance = new MonetaryExpressionCompiler();
//		Expression result = instance.compile(theCell, source);
//		double res = 55.59;
//
//		assertEquals(res, result.evaluate().toDouble(), 0.1);
//	}
//
//	/**
//	 * Test of compile method, of class MonetaryExpressionCompiler.
//	 */
//	@Test
//	public void testCompileEuroWithPoundAndDollar() throws Exception {
//		String source = "#euro{15£+56$}";
//		MonetaryExpressionCompiler instance = new MonetaryExpressionCompiler();
//		Expression result = instance.compile(theCell, source);
//		double res = 68.33;
//
//		assertEquals(res, result.evaluate().toDouble(), 0.1);
//	}
}
