/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.framework.search;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.ui.ctrl.UIController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class LocalWorkbookSearch {

    /**
     * The list of found workbooks
     */
    private List<Workbook> foundWorkbooks;

    /**
     * The search pattern
     */
    private final String pattern;

    /**
     * The UI controller
     */
    private final UIController uiController;

    /**
     * Hard limit of rows/columns to look for non emtpy cells Which one comes
     * first, breaks the limit
     */
    private static final int MAX_NUMBER_ROWS_OR_COLUMNS = 6;

    private String padrao;
    /**
     * Constructor
     *
     * @param uic The UIController
     * @param pattern Workbook name pattern to match against
     */
    public LocalWorkbookSearch(UIController uic, String pattern) {
        this.uiController = uic;
        if (!pattern.matches(".*\\.cls")) {
            this.padrao = pattern;
            pattern = pattern.concat("\\.cls");
        }
        this.pattern = pattern;
        this.foundWorkbooks = null;
    }

    public List<Workbook> getResults() {
        this.foundWorkbooks = new ArrayList<>();
        Workbook activeWorkbook = uiController.getActiveWorkbook();
        String activeWorkbookName = activeWorkbook.getParentFileName();
        // TODO : matches ao conteudo das celulas
        if (activeWorkbookName.matches(pattern) || searchContent(activeWorkbook, pattern)) {
            if (!foundWorkbooks.contains(activeWorkbookName)) {
                this.foundWorkbooks.add(activeWorkbook);
            }
        }

        /* Not needed atm; based on previous implementation */
        File baseDirectory = new File(System.getProperty("user.home"));
        if (baseDirectory.isDirectory()) {
            search(baseDirectory);
        }
        File currentDir = new File(System.getProperty("user.dir") + File.separator);
        if (currentDir.isDirectory()) {
            search(currentDir);
        }
        /**/
        if (!this.foundWorkbooks.isEmpty()) {
            return this.foundWorkbooks;
        }
        return null;
    }

    /**
     * Search a file in directory (NOT NEEDED YET; based on previous
     * implementation)
     *
     * @param file File Path
     */
    private void search(File file) {

        if (file.isDirectory()) {
            //do you have permission to read this directory?
            if (file.canRead()) {
                if (file.listFiles() == null) {
                    return;
                }
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else if (!this.uiController.getActiveWorkbook().getParentFileName().startsWith("*") &&
                            temp.getName().matches(this.uiController.getActiveWorkbook().getParentFileName())) {
                        continue;
                    } else if (temp.getName().matches(".*\\.cls")) {
                        Workbook tempWB = null;
                        try {
                            this.uiController.getCleanSheets().load(temp);
                            tempWB = this.uiController.getCleanSheets().getWorkbook(temp);
                            this.uiController.getCleanSheets().unload(tempWB);
                        } catch (Exception ex) {
                            continue;
                        }
                        if (tempWB != null) {
                            String aux = pattern.replace(".cls", "");
                            if (tempWB.getParentFileName().matches(pattern)) {
                                if (!foundWorkbooks.contains(tempWB)) {
                                    this.foundWorkbooks.add(tempWB);
                                }
                            }else if(searchContent(tempWB, this.padrao)){
                                if (!foundWorkbooks.contains(tempWB)) {
                                    this.foundWorkbooks.add(tempWB);
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }
    }

    private boolean searchContent(Workbook activeWorkbook, String pattern) {
        for (Spreadsheet spreadsheet : activeWorkbook) {
            for (Cell cell : spreadsheet) {
                if (cell.getContent().matches(pattern) || cell.getValue().toString().matches(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

}
