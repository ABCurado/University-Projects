package csheets.ext.importExportData.ui;

import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.SpreadsheetImpl;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.importExportData.FileTask;
import csheets.ext.importExportData.parsers.FileHandler;
import csheets.ext.importExportData.parsers.TxtParser;
import csheets.ext.importExportData.parsers.encoders.TxtEncoder;
import csheets.ext.importExportData.parsers.strategies.ImportTextFileStrategy;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.notification.Notification;
import csheets.support.TaskManager;
import csheets.ui.ctrl.UIController;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ImportExportTextFileController {

    TaskManager tm = new TaskManager();
    TxtParser txtParser = new TxtParser(new FileHandler());
    private long dataAtual;
    boolean option = true;
    String path;
    String separator;
    Cell[][] cells;

    /**
     * filter the file content
     * @param filePath filePath
     * @param separator separator
     * @param spreadsheet spreadsheet
     */
    public void linkImport(String filePath, String separator,
            Spreadsheet spreadsheet) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (spreadsheet == null) {
                spreadsheet = UIController.getUIController().
                        getActiveSpreadsheet();
            }
            Scanner fileScanner = new Scanner(file);
            int row = 0;
            while (fileScanner.hasNextLine()) {
                String[] columns = fileScanner.nextLine().split(separator);
                for (int i = 0; i < columns.length; i++) {
                    spreadsheet.getCell(i, row).setContent(columns[i]);
                }
                row++;
            }
        } catch (Exception ex) {
        }
    }

    /**
     * 
     * @param filePath filePath
     * @param separator  separator
     */
    public void linkImport(String filePath, String separator) {
        this.linkImport(filePath, separator, null);
    }

    /**
     * 
     * @param filePath path of file
     * @param separator olums separator character
     * @param spreadsheet  spreadsheet
     */
    public void linkExport(String filePath, String separator,
            Spreadsheet spreadsheet) {
        try {
            String content = "";
            if (spreadsheet == null) {
                spreadsheet = UIController.getUIController().
                        getActiveSpreadsheet();
            }
            for (int i = 0; i < spreadsheet.getColumnCount(); i++) {
                for (int j = 0; j < spreadsheet.getRowCount(); j++) {
                    content += spreadsheet.getCell(j, i).getValue().toString() + separator;
                }
                content += "\n";
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (Exception ex) {
        }
    }

    /**
     * 
     * @param filePath  path of file
     * @param separator  colums separator character
     */
    public void linkExport(String filePath, String separator) {
        this.linkExport(filePath, separator, null);
    }

    /**
     * Checks if the selected cells are enough to the received content
     *
     * @param path path of the file to import text
     * @param separator colums separator character
     * @param cells selected cells
     * @return true if cells are enough, false otherwise
     */
    public boolean hasEnoughCells(String path, String separator,
            Cell[][] cells) {

        String[] lines = ((String[]) txtParser.use(new ImportTextFileStrategy()).
                with(path).
                parse());

        Cell[][] textCells = nTextCells(lines, separator);

        return textCells.length <= cells.length
                && textCells[0].length <= cells[0].length;
    }

    /**
     * Copies the file content to selected cells
     *
     * @param path path of file
     * @param separator separator column character
     * @param header has header or not
     * @param cells cells to copy from file
     * @return the cells with the text
     * @throws FormulaCompilationException exception
     */
    public Cell[][] parse(String path, String separator, boolean header,
            Cell[][] cells) throws FormulaCompilationException {
        if (cells == null) {
            return null;
        }

        String[] lines = ((String[]) txtParser.use(new ImportTextFileStrategy()).
                with(path).parse());
        if (lines == null) {
            return null;
        }
        Cell[][] textCells = nTextCells(lines, separator);

        int r = cells[0][0].getAddress().getRow();
        int c = cells[0][0].getAddress().getColumn();
        Cell cell = cells[0][0].getSpreadsheet().getCell(c, r);
        for (int i = 0; i < lines.length; i++) {
            String[] col = lines[i].split(separator);
            for (int j = 0; j < col.length; j++) {
                cell.setContent(col[j]);
                textCells[i][j] = cell;
                int column = cell.getAddress().getColumn() + 1;
                int row = cell.getAddress().getRow();
                cell = cell.getSpreadsheet().getCell(column, row);
            }
            cell = cell.getSpreadsheet().getCell(c, cell.getAddress().
                    getRow() + 1);
        }

        if (header) {
            for (int i = 0; i < textCells[0].length; i++) {
                boldHeader(textCells[0][i]);
            }
        }

        return textCells;
    }

    /**
     * Cell character to bold
     *
     * @param cell cell to change
     */
    private void boldHeader(Cell cell) {

        StylableCell stylableCell = (StylableCell) cell.
                getExtension(StyleExtension.NAME);
        stylableCell.setFont(new Font(stylableCell.getFont().getFamily(),
                stylableCell.getFont().getStyle() ^ Font.BOLD, stylableCell.
                getFont().getSize()));

    }

    /**
     * Returns the number of cells needed to copy text from content
     *
     * @param lines lines of content
     * @param separator column separator character
     * @return the cells needed
     */
    private Cell[][] nTextCells(String[] lines, String separator) {
        if (lines == null) {
            return null;
        }
        int maxColumns = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].split(separator).length > maxColumns) {
                maxColumns = lines[i].split(separator).length;
            }
        }
        return new Cell[lines.length][maxColumns];
    }

    /**
     * Creates a file with content of the selected cells
     *
     * @param filename path to new file
     * @param cells selected cells
     * @param separator columns separator character
     * @return true if file was sucessfully created, false otherwise
     */
    public boolean exportFile(String filename, Cell[][] cells, String separator) {
        FileHandler fh = new FileHandler();

        String content = new TxtEncoder().getContent(cells, separator);

        if (fh.createFile(filename)) {
            return fh.append(filename, content);
        }
        return false;
    }

    /**
     *  linking spreadsheet to file
     *
     * @param filePath path to file
     * @param separator columns separator character
     * @param spreadsheet Spreadsheet
     */
    public void linked(String filePath, String separator,
            Spreadsheet spreadsheet) {
        FileTask fileTask = new FileTask(this, filePath, separator, spreadsheet);
        ((SpreadsheetImpl) spreadsheet).setFileTask(fileTask);
        Notification.linkFileInformer().notifyChange(spreadsheet);
    }

    /**
     * Unlinking spreadsheet to file
     * @param spreadsheet Spreadsheet
     */
    public void unlinked(Spreadsheet spreadsheet) {
        ((SpreadsheetImpl) spreadsheet).destroyFileTask();
        Notification.linkFileInformer().notifyChange(spreadsheet);
    }

}
