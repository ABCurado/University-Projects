/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.networkExplorer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diogo Azevedo
 */
public class WorkbookInfo {
    private String name;
    private List<SpreadSheetInfo> sheets;

    public WorkbookInfo(String name) {
        this.name = name;
        this.sheets = new ArrayList();
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sheetsMap
     */
    public List<SpreadSheetInfo> getSheetsMap() {
        return sheets;
    }
    
    @Override
    public String toString() {
        return name;
    }

    public void addSpreadSheet(SpreadSheetInfo spreadsheet) {
       sheets.add(spreadsheet);
    }
}


