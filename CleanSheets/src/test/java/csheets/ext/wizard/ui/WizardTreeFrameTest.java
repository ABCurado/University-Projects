//package csheets.ext.wizard.ui;
//
//import csheets.CleanSheets;
//import csheets.core.Spreadsheet;
//import csheets.core.Workbook;
//import csheets.core.formula.compiler.ExcelExpressionCompiler;
//import csheets.core.formula.compiler.FormulaCompilationException;
//import csheets.ui.ctrl.UIController;
//import csheets.ui.sheet.SpreadsheetTable;
//import javax.swing.JTree;
//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.TreeNode;
//import org.antlr.runtime.tree.CommonTree;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// * Test Class of WizardTreeFrame
// */
//public class WizardTreeFrameTest {
//
//    WizardTreeFrame instance;
//
//    public WizardTreeFrameTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() throws FormulaCompilationException {
//        Workbook wb = new Workbook(1);
//        String[][] content = new String[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                content[i][j] = "";
//            }
//        }
//        wb.addSpreadsheet(content);
//        UIController uiController = new UIController(new CleanSheets());
//        Spreadsheet s = wb.getSpreadsheet(0);
//        SpreadsheetTable sst = new SpreadsheetTable(s, uiController);
//        uiController.focusOwner = sst;
//        uiController.setActiveSpreadsheet(s);
//        uiController.setActiveCell(s.getCell(0, 0));
//        WizardFrame frame = new WizardFrame(uiController);
//        frame.setVisible(false);
//        CommonTree ast = new ExcelExpressionCompiler().compileTree("={POW(2;3)}");
//        instance = new WizardTreeFrame(ast, frame);
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getTree method, of class WizardTreeFrame.
//     * Had to brute force it a bit.
//     */
//    @Test
//    public void ensureTreeIsProperlyConstructed() {
//        boolean verdict = false;
//        JTree tree = new javax.swing.JTree();
//        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Formula");
//        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
//        DefaultMutableTreeNode expResult = (DefaultMutableTreeNode) tree.getModel().getRoot();
//
//        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("{");
//        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("POW");
//        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("2");
//        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("3");
//
//        node2.add(node3);
//        node2.add(node4);
//        node1.add(node2);
//        expResult.add(node1);
//
//        DefaultMutableTreeNode result = (DefaultMutableTreeNode) instance.getTree().getModel().getRoot();
//
//        verdict = (result.getChildCount() == expResult.getChildCount());
//
//        if (verdict) {
//            // Get to the child node "POW"
//            TreeNode resultChild = result.getChildAt(0).getChildAt(0);
//            TreeNode expResultChild = expResult.getChildAt(0).getChildAt(0);
//
//            verdict = resultChild.toString().equals(expResultChild.toString());
//
//            if (verdict) {
//                for (int i = 0; i < resultChild.getChildCount(); i++) {
//                    verdict = resultChild.getChildAt(i).toString().equals(expResultChild.getChildAt(i).toString());
//                    if (!verdict) {
//                        break;
//                    }
//                }
//            }
//        }
//        assertEquals(verdict, true);
//    }
//
//}