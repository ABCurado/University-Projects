/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.wizard.ui;

import csheets.core.formula.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;

/**
 *
 * @author AB-1140280
 */
public class FunctionListModel extends DefaultListModel<String> {

    private transient List<Function> listSubjects;

    public FunctionListModel(List<Function> listSubjects) {
        this.listSubjects = listSubjects;
    }

    @Override
    public int getSize() {
        return listSubjects.size();
    }

    @Override
    public String getElementAt(int index) {
        return listSubjects.get(index).getIdentifier();
    }

    public String getFunctionInfo(int index, WizardController controller) {
        return controller.getFunctionInfo(listSubjects.get(index));
    }

    public String getHelp(int selectedIndex) {
        return listSubjects.get(selectedIndex).getDescription();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FunctionListModel other = (FunctionListModel) obj;
        if (this.getSize() != other.getSize()) {
            return false;
        }
        int i = 0;
        for (Function func : listSubjects) {
            if (!func.getIdentifier().equalsIgnoreCase(other.getElementAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

}
