/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.controllers;

import csheets.AppSettings;
import csheets.core.Address;
import csheets.core.Cell;
import csheets.core.Spreadsheet;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.NetworkManager;
import csheets.ext.game.domain.Battleship;
import csheets.ext.game.domain.Ship;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StylableSpreadsheet;
import csheets.ext.style.StyleExtension;
import csheets.support.ThreadManager;
import csheets.ui.ctrl.SelectionEvent;
import csheets.ui.ctrl.SelectionListener;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.tcp.TcpClient;
import vendor.volt.protocols.tcp.TcpServer;

/**
 *
 * @author Rui Bento
 */
public class BattleshipController implements SelectionListener, SpecificGameController {

    public static final String GAME_NAME = "Battleship";

    private final char START_CHAR = 'A';
    private final char END_CHAR = 'Z';
    private final String TCP_GAME_SERVER_NAME = "ipc.battleship-tcpServer";
    private final String REQUEST_READY = ":game-ready";
    private final String REQUEST_PLAY = ":game-play";
    private final String REQUEST_RESPONSE = ":game-response";
    private final String READY_MESSAGE = "ready";
    private final String RESPONSE_WIN = "win";
    private final String RESPONSE_SINK = "sink";
    private final String RESPONSE_HIT = "hit";
    private final String RESPONSE_WATER = "water";
    private final String RESPONSE_FAIL = "fail";
    private final int CELL_WIDTH = 30;
    private final int CELL_HEIGHT = 30;
    private final Border cellBorder = BorderFactory.createMatteBorder(1, 1, 1, 1,
            Color.BLACK);

    private Battleship.BoardSize boardsize = Battleship.BoardSize.NORMAL;
    private int titleInfoCellColumn = 1, titleInfoCellRow = 1;
    private int infoCellColumn = 2, infoCellRow = 1;
    private int marginColumn = 2, marginRow = 4;
    private int columnShipStart = marginColumn + boardsize.size() + 1, rowShipStart = marginRow + 1;
    private int marginOwnBoardColumn = marginColumn;
    private int marginOwnBoardRow = marginRow + (boardsize.size() + 1) + 2;

    private UIController uiController;
    private TcpServer server;
    private Spreadsheet sheet;
    private StylableSpreadsheet styleSheet;
    private Battleship game;
    private String connection;
    private Map<Cell, Map.Entry<Ship.ShipType, Integer>> lstShips;
    private Map.Entry<Ship.ShipType, Integer> selectedShip;
    private Cell shipStartCell;
    private boolean turn;
    private boolean readyToPlay;
    private boolean opponentReadyToPlay;

    public BattleshipController(UIController uiController, boolean startTurn,
            String opponentIP) {
        this.uiController = uiController;
        this.turn = startTurn;
        this.connection = opponentIP + ":" + Integer.
                parseInt(AppSettings.instance().
                        get("TCP_PORT"));
        this.readyToPlay = false;
        this.lstShips = new HashMap<>();
    }

    /**
     * Add all playable cells Listenners
     */
    private void addListeners() {
        uiController.addSelectionListener(this);
        /*for (int i = 0; i < boardsize.size(); i++) {
            for (int j = 0; j < boardsize.size(); j++) {
                styleSheet.getCell(i + marginColumn, j + marginRow).addCellListener(this);
            }
        }*/
    }

    /**
     * Remove all playable cells Listenners
     */
    private void removeListeners() {
        uiController.removeSelectionListener(this);
        /*for (int column = 0; column < sheet.getColumnCount(); column++) {
            for (int row = 0; row < sheet.getRowCount(); row++) {
                for (CellListener cellListener : sheet.getCellListeners()) {
                    sheet.getCell(column, row).removeCellListener(cellListener);
                    uiController.removeSelectionListener(this);
                }
            }
        }*/
    }

    @Override
    public void start() {
        game = new Battleship(boardsize, Battleship.BattleshipGameType.TYPE_1);
        startServer();
        createGameSpreedsheet();
        prepareShipsForEdit();
        //removeListeners();
        addListeners();
        createEditBoard();
        //repaintBoard();
    }

    private void startServer() {
        ThreadManager.create(TCP_GAME_SERVER_NAME, new Thread() {
            @Override
            public void run() {
                server = NetworkManager.tcp();
                server.expect(REQUEST_READY, new Action() {
                    @Override
                    public void run(Request request) {
                        opponentReadyToPlay = request.message().equals(READY_MESSAGE) ? true : false;
                        startGame();
                    }
                });
                server.expect(REQUEST_PLAY, new Action() {
                    @Override
                    public void run(Request request) {
                        String params[] = request.message().split(";");
                        int column = Integer.parseInt(params[0]);
                        int row = Integer.parseInt(params[1]);
                        verifyPlay(column, row);
                        turn = true;
                    }
                });
                server.expect(REQUEST_RESPONSE, new Action() {
                    @Override
                    public void run(Request request) {
                        String args[] = request.message().split(";");
                        int column;
                        int row;
                        try {
                            column = Integer.parseInt(args[0]);
                            row = Integer.parseInt(args[1]);
                        } catch (NumberFormatException ex) {
                            // ignoring
                            //showMessage("Message Received, has error");
                            return;
                        }
                        String response = args[2];
                        switch (response) {
                            case RESPONSE_WIN: {
                                showSink(column, row);
                                showWin();
                                break;
                            }
                            case RESPONSE_SINK: {
                                showSink(column, row);
                                break;
                            }
                            case RESPONSE_HIT: {
                                showHit(column, row);
                                break;
                            }
                            case RESPONSE_WATER: {
                                showWater(column, row);
                                break;
                            }
                            case RESPONSE_FAIL: {
                                repeatPlay(column, row);
                                break;
                            }
                            default: {
                                System.out.println("?!?");
                                repeatPlay(column, row);
                                break;
                            }
                        }
                    }
                });
            }
        });
        ThreadManager.run(TCP_GAME_SERVER_NAME);
    }

    @Override
    public void stopGame() {
        stopThis();
        removeListeners();
    }

    /**
     * Stops all the TCP services.
     */
    private void stopThis() {
        server.shutdown();
        ThreadManager.destroy(TCP_GAME_SERVER_NAME);
    }

    private void prepareShipsForEdit() {
        int shipCount = 0;
        for (Map.Entry<Ship.ShipType, Integer> entry : game.getShipsType().entrySet()) {
            lstShips.put(sheet.getCell(columnShipStart, rowShipStart + shipCount++), entry);
        }
    }

    private void createGameSpreedsheet() {
        uiController.getActiveWorkbook().addSpreadsheet();
        sheet = uiController.getActiveWorkbook().getSpreadsheet(
                uiController.getActiveWorkbook().getSpreadsheetCount() - 1);
        //uiController.setActiveSpreadsheet(sheet);
        uiController.focusOwner.revalidate();
        //styleSheet = new StyleExtension().extend(sheet);
        styleSheet = (StylableSpreadsheet) sheet.getExtension(StyleExtension.NAME);
        //sheet = (StylableSpreadsheet)newSheet;
        sheet.setTitle(BattleshipController.GAME_NAME);
    }

    private void createEditBoard() {
        for (Cell cell : styleSheet.getCells()) {
            cell.clear();
        }
        try {
            styleSheet.getCell(infoCellColumn, infoCellRow).setContent("READY ?");
        } catch (FormulaCompilationException ex) {
            //Logger.getLogger(BattleshipController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addCellConfig((StylableCell) styleSheet.getCell(titleInfoCellColumn,
                titleInfoCellRow), true);
        showBoard(marginColumn, marginRow);
        showShips();
    }

    private void showBoard(int startColumn, int startRow) {
        char charTitle = START_CHAR;
        StylableCell cell = (StylableCell) styleSheet.getCell(startColumn - 1,
                startRow - 1);
        cell.setBackgroundColor(Color.BLACK);
        //System.out.println(styleSheet.getRowHeight(startRow));
        for (int row = startRow, count = 1; count - 1 < boardsize.size(); row++, count++) {
            //uiController.focusOwner.setRowHeight(row, CELL_HEIGHT);
            //uiController.focusOwner.setColumnHeight(startColumn - 1, CELL_WIDTH);
            try {
                cell = (StylableCell) styleSheet.getCell(startColumn - 1, row);
                addCellConfig(cell, true);
                cell.setContent(String.valueOf(count));
            } catch (FormulaCompilationException ex) {
                //Logger.getLogger(BattleshipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int column = startColumn, count = 1; count - 1 < boardsize.size(); column++, count++, charTitle++) {
            //uiController.focusOwner.setRowHeight(startRow - 1, CELL_HEIGHT);
            //uiController.focusOwner.setColumnHeight(column, CELL_WIDTH);
            try {
                cell = (StylableCell) styleSheet.getCell(column, startRow - 1);
                addCellConfig(cell, true);
                cell.setContent(String.valueOf(charTitle));
            } catch (FormulaCompilationException ex) {
                //Logger.getLogger(BattleshipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int column = startColumn; column < boardsize.size() + startColumn; column++) {
            for (int row = startRow; row < boardsize.size() + startRow; row++) {
                cell = (StylableCell) styleSheet.getCell(column, row);
                addCellConfig(cell, false);
            }
        }
    }

    private void addCellConfig(StylableCell cell, boolean bold) {
        cell.setBorder(cellBorder);
        cell.setHorizontalAlignment(SwingConstants.CENTER);
        cell.setVerticalAlignment(SwingConstants.CENTER);
        if (bold) {
            cell.setFont(cell.getFont().deriveFont(Font.BOLD));
        }
    }

    private void removeCellConfig(StylableCell cell) {
        cell.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,
                Color.WHITE));
        cell.setHorizontalAlignment(SwingConstants.LEFT);
        cell.setVerticalAlignment(SwingConstants.LEFT);
        cell.setFont(cell.getFont().deriveFont(Font.PLAIN));
    }

    private void showShips() {
        try {
            sheet.getCell(columnShipStart, rowShipStart - 1).setContent("SHIPS");
            sheet.getCell(columnShipStart + 1, rowShipStart - 1).setContent("REMAINING");
            sheet.getCell(columnShipStart + 2, rowShipStart - 1).setContent("SIZE");
            for (Map.Entry<Cell, Map.Entry<Ship.ShipType, Integer>> entry : lstShips.entrySet()) {
                entry.getKey().setContent(entry.getValue().getKey().name());
                sheet.getCell(entry.getKey().getAddress().getColumn() + 1,
                        entry.getKey().getAddress().getRow()).
                        setContent(entry.getValue().getValue().toString());
                sheet.getCell(entry.getKey().getAddress().getColumn() + 2,
                        entry.getKey().getAddress().getRow()).
                        setContent(String.valueOf(entry.getValue().getKey().size()));
            }
        } catch (FormulaCompilationException ex) {
            System.out.println("Cannot write to cell. " + ex.toString());
        }
    }

    private void repaintBoard() {
        /*for (int i = 0; i < tictactoe.getRowCount(); i++) {
            for (int j = 0; j < tictactoe.getColumnCount(); j++) {
                try {
                    String value = tictactoe.getValueAt(i, j);
                    styleSheet.getCell(i + 1, j + 1).setContent(value);
                } catch (FormulaCompilationException ex) {
                    Logger.getLogger(TicTacToeController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }*/
    }

    private void verifyPlay(int column, int row) {
        Cell cell = sheet.getCell(column, row);
        String message = column + ";" + row;
        int myColumn = column + marginOwnBoardColumn - marginColumn;
        int myRow = row + marginOwnBoardRow - marginRow;
        int shoot;
        System.out.println("\n\n\n");
        try {
            shoot = game.shoot(cell.getAddress(), marginColumn, marginRow);
        } catch (VerifyError ex) {
            new TcpClient(0).send(REQUEST_RESPONSE, connection, message + ";" + RESPONSE_FAIL);
            System.out.println("SEND FAIL");
            System.out.println("\n\n\n");
            return;
        }
        if (shoot == Battleship.SHOOT_SINK) {
            String playMessage = "Opponent sink a boat. ";
            showSink(myColumn, myRow);
            if (game.allShipsDestroyed()) {
                new TcpClient(0).send(REQUEST_RESPONSE, connection, message + ";" + RESPONSE_WIN);
                System.out.println("SEND WIN");
                System.out.println("\n\n\n");
                showLose();
                return;
            }
            new TcpClient(0).send(REQUEST_RESPONSE, connection, message + ";" + RESPONSE_SINK);
            System.out.println("SEND SINK");
            System.out.println("\n\n\n");
            playMessage += "Your turn ...";
            showMessage(playMessage);
            return;
        }
        if (shoot == Battleship.SHOOT_HIT) {
            new TcpClient(0).send(REQUEST_RESPONSE, connection, message + ";" + RESPONSE_HIT);
            System.out.println("SEND HIT");
            System.out.println("\n\n\n");
            showHit(myColumn, myRow);
            showMessage("Opponent hit a boat. Your turn ...");
            return;
        }
        if (shoot == Battleship.SHOOT_FAIL) {
            new TcpClient(0).send(REQUEST_RESPONSE, connection, message + ";" + RESPONSE_WATER);
            System.out.println("SEND WATER");
            System.out.println("\n\n\n");
            showWater(myColumn, myRow);
            showMessage("Opponent fail, he found water. Your turn ...");
            return;
        }
        //repaintBoard();
    }

    private void showMessage(String message) {
        try {
            JOptionPane.showMessageDialog(null, message);
            //styleSheet.getCell(infoCellColumn, infoCellRow).setContent(message);
        } catch (/*FormulaCompilation*/Exception ex) {
            Logger.getLogger(BattleshipController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void selectionChanged(SelectionEvent event) {
        if (!event.getSpreadsheet().equals(sheet)
                || event.getCell().equals(sheet.getCell(0, 0))) {
            return;
        }
        //uiController.setActiveCell(sheet.getCell(0, 0));
        //uiController.focusOwner.setCellSelectionEnabled(false);
        Cell cell = event.getCell();
        if (cell.getValue() == null) {
            return;
        }
        if (isInsideBoard(cell)) {
            doBoardAction(cell);
            return;
        }
        if (isShip(cell)) {
            selectShip(cell);
            return;
        }
        if (isReadyCell(cell)) {
            setReadyToPlay(cell);
            return;
        }
    }

    private boolean isInsideBoard(Cell cell) {
        return cell.getAddress().getColumn() >= marginColumn
                && cell.getAddress().getColumn() < marginColumn + boardsize.size()
                && cell.getAddress().getRow() >= marginRow
                && cell.getAddress().getRow() < marginRow + boardsize.size();
    }

    private boolean isShip(Cell cell) {
        return !readyToPlay && lstShips.containsKey(cell);
    }

    private boolean isReadyCell(Cell cell) {
        return cell.getAddress().getColumn() == titleInfoCellColumn
                && cell.getAddress().getRow() == titleInfoCellRow
                && !readyToPlay;
    }

    private void doBoardAction(Cell cell) {
        if (!readyToPlay && selectedShip != null) {
            if (shipStartCell == null) {
                showPossibleEndOfShip(cell);
                return;
            }
            try {
                List<Address> allSelectedCells = getAddressInLineBetween(shipStartCell, cell);
                Ship newShip = game.addShip(selectedShip.getKey(), allSelectedCells);
                selectedShip.setValue(selectedShip.getValue() - 1);
                removePaintPossibleEndOfShip(cell);
                paintShipPostions(newShip);
            } catch (VerifyError ex) {
                showMessage(ex.getMessage());
            } catch (IllegalArgumentException ex) {
                showMessage(ex.getMessage());
            }
            shipStartCell = null;
            removeSelectedShip();
            return;
        }
        if (!readyToPlay) {
            showMessage("It's required to select the ship to place before choosing the location.");
            return;
        }
        if (!opponentReadyToPlay || !readyToPlay) {
            return;
        }
        if (turn) {
            shootOpponent(cell);
            uiController.focusOwner.revalidate();
            return;
        }
        showMessage("It's not your turn");
    }

    private void paintShipPostions(Ship ship) {
        for (Address address : ship.getPositions()) {
            paintShip((StylableCell) sheet.getCell(address).getExtension(StyleExtension.NAME));
        }
    }

    private void paintShip(StylableCell cell) {
        cell.setBackgroundColor(Color.GRAY);
    }

    private void removeSelectedShip() {
        for (Map.Entry<Cell, Map.Entry<Ship.ShipType, Integer>> entry : lstShips.entrySet()) {
            if (entry.getValue().getKey().isSameType(selectedShip.getKey())) {
                try {
                    sheet.getCell(entry.getKey().getAddress().getColumn() + 1,
                            entry.getKey().getAddress().getRow()).
                            setContent(entry.getValue().getValue().toString());
                } catch (FormulaCompilationException ex) {
                    //Logger.getLogger(BattleshipController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
        selectedShip = null;
    }

    private List<Address> getAddressInLineBetween(Cell startCell, Cell endCell) {
        int startColumn = startCell.getAddress().getColumn();
        int endColumn = endCell.getAddress().getColumn();
        int startRow = startCell.getAddress().getRow();
        int endRow = endCell.getAddress().getRow();
        if (startColumn == endColumn) {
            if (startRow > endRow) {
                return findAddressBetween(endRow, startRow, startColumn, true);
            }
            return findAddressBetween(startRow, endRow, startColumn, true);
        }
        if (startRow == endRow) {
            if (startColumn > endColumn) {
                return findAddressBetween(endColumn, startColumn, startRow, false);
            }
            return findAddressBetween(startColumn, endColumn, startRow, false);
        }
        throw new VerifyError("The selected cells must be in line.");
    }

    private List<Address> findAddressBetween(int start, int end, int fixed, boolean columnFixed) {
        List<Address> lstAddress = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (columnFixed) {
                lstAddress.add(sheet.getCell(fixed, i).getAddress());
                continue;
            }
            lstAddress.add(sheet.getCell(i, fixed).getAddress());
        }
        return lstAddress;
    }

    private void showPossibleEndOfShip(Cell cell) {
        /*boolean show = false;
        int column = cell.getAddress().getColumn();
        int row = cell.getAddress().getRow();
        Cell topCell = sheet.getCell(column, row - selectedShip.getKey().size());
        Cell leftCell = sheet.getCell(column - selectedShip.getKey().size(), row);
        Cell rightCell = sheet.getCell(column + selectedShip.getKey().size(), row);
        Cell bottomCell = sheet.getCell(column, row + selectedShip.getKey().size());
        if(isInsideBoard(topCell)){
            paintPossibleEndOfShip(topCell);
            show = true;
        }
        if(isInsideBoard(leftCell)){
            paintPossibleEndOfShip(leftCell);
            show = true;
        }
        if(isInsideBoard(rightCell)){
            paintPossibleEndOfShip(rightCell);
            show = true;
        }
        if(isInsideBoard(bottomCell)){
            paintPossibleEndOfShip(bottomCell);
            show = true;
        }
        if(!show){
            showMessage("The selected ship doesn't fit anywhere starting on this location.");
            return;
        }*/
        shipStartCell = cell;
    }

    private void paintPossibleEndOfShip(Cell c) {
        StylableCell cell = (StylableCell) sheet.getCell(c.getAddress());
        cell.setBackgroundColor(Color.BLUE);
    }

    private void removePaintPossibleEndOfShip(Cell cell) {
        //StylableCell sCell = (StylableCell)cell;
        //sCell.setBackgroundColor(Color.BLUE);
    }

    private void shootOpponent(Cell cell) {
        try {
            String message = cell.getAddress().getColumn() + ";" + cell.getAddress().getRow();
            new TcpClient(0).send(REQUEST_PLAY, connection, message);
            this.turn = false;
        } catch (VerifyError ex) {
            showMessage(ex.getMessage());
        }
    }

    private void selectShip(Cell cell) {
        selectedShip = lstShips.get(cell);
    }

    private void setReadyToPlay(Cell cell) {
        if (!game.isReadyToPlay()) {
            JOptionPane.showMessageDialog(null, "You are not ready to play. "
                    + "Place the missing ships to start the game.", "CANNOT BE "
                    + "READY", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            cell.setContent("X");
            this.readyToPlay = true;
            new TcpClient(0).send(REQUEST_READY, connection, READY_MESSAGE);
            // START WATING
            startGame();
        } catch (FormulaCompilationException ex) {
            System.out.println("Unable to change cell content. " + ex);
        }
    }

    private void startGame() {
        if (!readyToPlay || !opponentReadyToPlay) {
            return;
        }
        // END WAITING
        for (Cell cell : sheet.getCells()) {
            cell.clear();
        }
        showBoard(marginColumn, marginRow);
        showBoard(marginOwnBoardColumn, marginOwnBoardRow);
        showMyShips(marginOwnBoardColumn - marginColumn, marginOwnBoardRow - marginRow);
    }

    private void showMyShips(int columnMargin, int rowMargin) {
        for (Ship ship : game.getShips()) {
            for (Address address : ship.getPositions()) {
                paintShip((StylableCell) sheet.getCell(address.getColumn()
                        + columnMargin, address.getRow()
                        + rowMargin).getExtension(StyleExtension.NAME));
            }
        }
    }

    private void showWin() {
        showMessage("YOU WON THE GAME");
        stopGame();
    }

    private void showLose() {
        showMessage("You lost, play again ...");
        stopGame();
    }

    private void showSink(int column, int row) {
        showHit(column, row);
    }

    private void showHit(int column, int row) {
        StylableCell scell = (StylableCell) sheet.getCell(column, row).getExtension(StyleExtension.NAME);
        Cell cell = sheet.getCell(column, row);
        //cell.setImage(new ImageIcon(GameExtension.class.getResource("ext/game/explosion.png")));
        scell.setBackgroundColor(Color.RED);
        try {
            cell.setContent("X");
        } catch (FormulaCompilationException ex) {
            //Logger.getLogger(BattleshipController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showWater(int column, int row) {
        StylableCell cell = (StylableCell) sheet.getCell(column, row).getExtension(StyleExtension.NAME);
        cell.setBackgroundColor(Color.BLUE);
    }

    private void repeatPlay(int column, int row) {
        //verifyPlay(column, row);
        System.out.println("Column: " + column + "\nRow: " + row);
        showMessage("Please repeat your play ...");
        this.turn = true;
    }
}
