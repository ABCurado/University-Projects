/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.wizard.ui;

import csheets.core.IllegalValueTypeException;
import csheets.core.Value;
import csheets.core.formula.Formula;
import csheets.core.formula.Function;
import csheets.core.formula.compiler.ExcelExpressionCompiler;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.core.formula.compiler.FormulaCompiler;
import csheets.core.formula.compiler.MonetaryExpressionCompiler;
import csheets.core.formula.lang.Language;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.runtime.tree.CommonTree;

/**
 * Controller of the wizard process to simplify the WizardFrame code and use
 * good practices of code structuring
 *
 * @author AB-1140280
 */
public class WizardController {

    UIController uicontroller;
    //FunctionParameter[] parameters;
    //String formulaSelected;
    int i = 0;

    public WizardController(UIController uicontroller) {
        this.uicontroller = uicontroller;
    }

    public FunctionListModel getFunctions() {
        ArrayList<Function> list = new ArrayList();
        for (Function func : Language.getInstance().getFunctions()) {
            list.add(func);
        }
        return new FunctionListModel(list);
    }

    public String getFunctionInfo(Function func) {
        //parameters = func.getParameters();
        //this.formulaSelected = func.getTemplate();
        return func.getTemplate();
    }

    /*
    public FunctionParameter[] getParametersOfFunctionSelected() {
        return this.parameters;
    }*/
    public String executeFormula(String text) throws FormulaCompilationException, IllegalValueTypeException {
        Value result = null;
        try {
            Formula formula = FormulaCompiler.getInstance().
                    compile(uicontroller.getActiveCell(), text);
            result = formula.evaluate();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid function");
        }
        return result.toString();
    }

    public void setCellsFormula(String formula) throws FormulaCompilationException {
        if (uicontroller != null) {
            uicontroller.getActiveCell().setContent(formula);
        }
    }

    public void buildAST(String formula, WizardFrame frame) throws FormulaCompilationException {
        CommonTree ast = null;

        if (formula.charAt(0) == '=') {
            ast = new ExcelExpressionCompiler().compileTree(formula);
        } else if (formula.charAt(0) == '#') {
            ast = new MonetaryExpressionCompiler().compileTree(formula);
        }

        if (ast != null) {
            new WizardTreeFrame(ast, frame).setVisible(true);
            //System.out.println("AST: " + ast.toStringTree());
        }
    }

    /* Lang04.2
    public String setValuesOnExpression(String text, String text0, String text1, String text2) {
        String s = this.formulaSelected;
        String replaced;
        int x = this.parameters.length;

        switch (x) {
            case 0:
                return s;

            case 1:
                if (text == null || text.isEmpty()) {
                    throw new IllegalArgumentException("tem que preencher o primeiro parametro.");
                }
                ArrayList<FunctionParameter> lst = new ArrayList<>();
                for (FunctionParameter par : parameters) {
                    lst.add(par);
                }
                Iterator<FunctionParameter> it = lst.iterator();
                FunctionParameter fPara = it.next();
                String troca = fPara.getValueType().toString();
                replaced = s.replace(troca, text);
                return replaced;

            case 2:
                if (text == null || text.isEmpty() || text0 == null || text0.isEmpty()) {
                    throw new IllegalArgumentException("tem que preencher os dois primeiros parametros.");
                }
                ArrayList<FunctionParameter> lst1 = new ArrayList<>();
                for (FunctionParameter par : parameters) {
                    lst1.add(par);
                }
                Iterator<FunctionParameter> it1 = lst1.iterator();
                FunctionParameter fPara1 = it1.next();
                String troca1 = fPara1.getValueType().toString();
                String aux = s.replaceFirst(troca1, text);
                fPara1 = it1.next();
                String troca2 = fPara1.getValueType().toString();
                String replaced1 = aux.replaceFirst(troca2, text0);
                return replaced1;

            case 3:
                if (text == null || text.isEmpty() || text0 == null || text0.isEmpty() || text1 == null || text1.isEmpty()) {
                    throw new IllegalArgumentException("tem que preencher todos os parametros.");
                }
                ArrayList<FunctionParameter> lst2 = new ArrayList<>();
                for (FunctionParameter par : parameters) {
                    lst2.add(par);
                }
                Iterator<FunctionParameter> it2 = lst2.iterator();
                FunctionParameter fPara2 = it2.next();
                String troca3 = fPara2.getValueType().toString();
                String aux1 = s.replaceFirst(troca3, text);
                fPara2 = it2.next();
                String troca4 = fPara2.getValueType().toString();
                String aux3 = aux1.replaceFirst(troca4, text0);
                fPara2 = it2.next();
                String troca5 = fPara2.getValueType().toString();
                String replaced2 = aux3.replaceFirst(troca5, text1);
                return replaced2;

            case 4:
                ArrayList<FunctionParameter> lst3 = new ArrayList<>();
                for (FunctionParameter par : parameters) {
                    lst3.add(par);
                }
                Iterator<FunctionParameter> it3 = lst3.iterator();
                FunctionParameter fPara3 = it3.next();
                String tr1 = fPara3.getValueType().toString();
                String auxi = s.replaceFirst(tr1, text);
                fPara3 = it3.next();
                String tr2 = fPara3.getValueType().toString();
                String auxi1 = auxi.replaceFirst(tr2, text0);
                fPara3 = it3.next();
                String tr3 = fPara3.getValueType().toString();
                String auxi2 = auxi1.replaceFirst(tr3, text1);
                fPara3 = it3.next();
                String tr4 = fPara3.getValueType().toString();
                String replaced3 = auxi2.replaceFirst(tr4, text1);
                return replaced3;
        }
        return null;

    }*/
}
