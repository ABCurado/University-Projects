/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.controllers;

import csheets.AppSettings;
import csheets.core.Cell;
import csheets.core.CellListener;
import csheets.core.Spreadsheet;
import csheets.core.Workbook;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.NetworkManager;
import csheets.ext.game.domain.TicTacToe;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.support.ThreadManager;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import vendor.volt.Action;
import vendor.volt.Request;
import vendor.volt.protocols.tcp.TcpClient;
import vendor.volt.protocols.tcp.TcpServer;

/**
 *
 * @author AB
 */
public class TicTacToeController implements CellListener, SpecificGameController {

	private TcpServer server;
	UIController uiController;
	Spreadsheet sheet;
	String connection;
	String symbol;
	TicTacToe tictactoe;
	boolean turn;

	public TicTacToeController(UIController uiController, boolean turn,
							   String opponentIP) {
		this.turn = turn;
		if (turn) {
			this.symbol = "O";
		} else {
			this.symbol = "X";
		}
		this.uiController = uiController;
		connection = opponentIP + ":" + Integer.
			parseInt(AppSettings.instance().
				get("TCP_PORT"));
	}

	public void stopGame() {
		stopThis();
		removeListeners();
	}

	public void start() {
		this.tictactoe = new TicTacToe();
		startServer();
		try {
			newSpreadsheet();
		} catch (FormulaCompilationException ex) {
			Logger.getLogger(TicTacToeController.class.getName()).
				log(Level.SEVERE, null, ex);
		}
		addListeners();
		repaintBoard();
	}

	public void newSpreadsheet() throws FormulaCompilationException {
		Workbook activeBook = this.uiController.getActiveWorkbook();
		activeBook.addTictactoeSpreadsheet();
		sheet = activeBook.getSpreadsheet(activeBook.
			getSpreadsheetCount() - 1);
		Border border = BorderFactory.
			createMatteBorder(2, 2, 2, 2, Color.ORANGE);
		Border borderNull = null;
		Font fontTicToe = new Font("Monospaced", Font.BOLD, 20);
		Color fgTac = Color.LIGHT_GRAY;
		Color bgTac = Color.BLUE;
		Color bgTic = Color.LIGHT_GRAY;
		Color fgTic = Color.BLUE;
		Color bgGreen = Color.decode("#006400");
		StylableCell cell;
		int column = 5;
		int rows = 5;
		for (int col = 0; col < column; col++) {
			for (int row = 0; row < rows; row++) {
				if (col == 1 && row == 0) {
					sheet.getCell(1, 0).setContent("TIC");
					styleTicTacToe(activeBook, col, row, fontTicToe, bgTic, fgTic, borderNull);
				} else if (col == 2 && row == 0) {
					sheet.getCell(2, 0).setContent("TAC");
					styleTicTacToe(activeBook, col, row, fontTicToe, bgTac, fgTac, borderNull);
				} else if (col == 3 && row == 0) {
					sheet.getCell(3, 0).setContent("TOE");
					styleTicTacToe(activeBook, col, row, fontTicToe, bgTic, fgTic, borderNull);
				} else if (!(col >= 1 && col <= 3 && row >= 1 && row <= 3)) {
					styleTicTacToe(activeBook, col, row, fontTicToe, bgGreen, Color.BLACK, borderNull);
				} else {
					styleTicTacToe(activeBook, col, row, fontTicToe, Color.WHITE, Color.BLACK, border);
				}
			}
		}
	}

	public void styleTicTacToe(Workbook activebook, int col, int row,
							   Font fontTic,
							   Color bg, Color fg, Border border) {
		StylableCell cell = (StylableCell) uiController.
			getActiveWorkbook().getSpreadsheet(activebook.
				getSpreadsheetCount() - 1).
			getCell(col, row).getExtension(StyleExtension.NAME);
		cell.setForegroundColor(fg);
		cell.setBackgroundColor(bg);
		Font font = new Font("Monospaced", Font.BOLD, 20);
		cell.setFont(font);
		cell.setBorder(border);
	}

	public void addListeners() {
		for (int i = 0; i < tictactoe.getRowCount(); i++) {
			for (int j = 0; j < tictactoe.getColumnCount(); j++) {
				sheet.getCell(i + 1, j + 1).addCellListener(this);
			}
		}
	}

	public void removeListeners() {
		for (int i = 0; i < tictactoe.getRowCount(); i++) {
			for (int j = 0; j < tictactoe.getColumnCount(); j++) {
				sheet.getCell(i + 1, j + 1).removeCellListener(this);
			}
		}
	}

	private void startServer() {
		ThreadManager.create("ipc.tictactoe-tcpServer", new Thread() {
							 @Override
							 public void run() {
								 server = NetworkManager.tcp();

								 server.expect(":game-play", new Action() {
											   @Override
											   public void run(Request request) {
												   String cellString = request.
													   message();
												   String params[] = cellString.
													   split(";");
												   int column = Integer.
													   parseInt(params[0]);
												   int row = Integer.
													   parseInt(params[1]);
												   String content = params[2];
												   tictactoe.
													   play(column - 1, row - 1, content);
												   try {
													   sheet.getCell(1, 6).
														   setContent("É a tua vez");
													   repaintBoard();
												   } catch (FormulaCompilationException ex) {
													   Logger.
														   getLogger(TicTacToeController.class.
															   getName()).
														   log(Level.SEVERE, null, ex);
												   }
												   repaintBoard();
												   turn = true;
											   }
										   });
								 server.expect(":game-lost", new Action() {
											   @Override
											   public void run(Request request) {
												   String cellString = request.
													   message();
												   String params[] = cellString.
													   split(";");
												   int column = Integer.
													   parseInt(params[0]);
												   int row = Integer.
													   parseInt(params[1]);
												   String content = params[2];
												   tictactoe.
													   play(column - 1, row - 1, content);
												   repaintBoard();
												   diplayLoss();
												   stopGame();
											   }
										   });
								 server.expect(":game-draw", new Action() {
											   @Override
											   public void run(Request request) {
												   String cellString = request.
													   message();
												   String params[] = cellString.
													   split(";");
												   int column = Integer.
													   parseInt(params[0]);
												   int row = Integer.
													   parseInt(params[1]);
												   String content = params[2];
												   tictactoe.
													   play(column - 1, row - 1, content);
												   repaintBoard();
												   diplayDraw();
												   stopGame();
											   }
										   });

							 }
						 });

		ThreadManager.run("ipc.tictactoe-tcpServer");
	}

	/**
	 * Stops all the TCP services.
	 *
	 * @param row that was edited
	 * @param column that was edited
	 * @param content is a symbol
	 * @return true if player win or false if not win
	 */
	public boolean winningPlay(int row, int column, String content) {
		if (!tictactoe.validateWin(symbol)) {
			return false;
		}
		String message = column + ";" + row + ";" + content;
		new TcpClient(0).send(":game-lost", connection, message);
		diplayVictory();
		stopGame();
		return true;
	}

	/**
	 * Stops all the TCP services.
	 *
	 * @param row that was edited
	 * @param column that was edited
	 * @param content is a symbol
	 * @return boolean
	 */
	public boolean draw(int row, int column, String content) {
		if (!tictactoe.isDraw()) {
			return false;
		}
		String message = column + ";" + row + ";" + content;
		new TcpClient(0).send(":game-draw", connection, message);
		diplayDraw();
		stopGame();
		return true;
	}

	/**
	 * Stops all the TCP services.
	 */
	public void stopThis() {
		server.shutdown();
		ThreadManager.destroy("ipc.tictactoe-tcpServer");
	}

	int i = 0;

	private void diplayLoss() {
		JOptionPane.showMessageDialog(null, "Perdeste");
	}

	private void diplayVictory() {
		JOptionPane.showMessageDialog(null, "Ganhaste");
	}

	private void diplayDraw() {
		JOptionPane.showMessageDialog(null, "Empate!");
	}

	private boolean validate() {
		return true;
	}

	public void repaintBoard() {
		for (int i = 0; i < tictactoe.getRowCount(); i++) {
			for (int j = 0; j < tictactoe.getColumnCount(); j++) {
				try {
					String value = tictactoe.getValueAt(i, j);
					sheet.getCell(i + 1, j + 1).setContent(value);
				} catch (FormulaCompilationException ex) {
					Logger.getLogger(TicTacToeController.class.getName()).
						log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	@Override
	public void contentChanged(Cell cell) {
		if (!cell.getContent().equalsIgnoreCase(symbol)) {
			repaintBoard();
			return;
		}
		if (turn) {
			int column = cell.getAddress().getColumn();
			int row = cell.getAddress().getRow();
			if (tictactoe.validatePlayerMove(column - 1, row - 1, symbol)) {
				String message = column + ";" + row + ";" + cell.getContent();
				tictactoe.play(column - 1, row - 1, cell.getContent());
				if (winningPlay(column, row, cell.getContent())) {
					return;
				}
				if (draw(column, row, cell.getContent())) {
					return;
				}
				new TcpClient(0).send(":game-play", connection, message);
				turn = false;
				repaintBoard();
				try {
					sheet.getCell(1, 6).
						setContent("Esperando pela outra resposta");
					repaintBoard();
				} catch (FormulaCompilationException ex) {
					Logger.getLogger(TicTacToeController.class.getName()).
						log(Level.SEVERE, null, ex);
				}
			} else {
				try {
					sheet.getCell(1, 6).setContent("A tua jogada nao é valida");
					repaintBoard();
				} catch (FormulaCompilationException ex) {
					Logger.getLogger(TicTacToeController.class.getName()).
						log(Level.SEVERE, null, ex);
				}
			}

		}
		repaintBoard();
	}

	@Override
	public void dependentsChanged(Cell cell
	) {
	}

	@Override
	public void cellCleared(Cell cell
	) {
	}

	@Override
	public void cellCopied(Cell cell, Cell source
	) {
	}

	@Override
	public void valueChanged(Cell cell
	) {
	}

}
