/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.domain;

/**
 * Dummy Class
 *
 * @author João Martins
 */
public class TicTacToe {

	int ROW = 3;
	int COLUMN = 3;

	private String[][] board;

	/**
	 * The workbook to which the spreadsheet belongs
	 */
	public TicTacToe() {
		this.board = new String[ROW][COLUMN];
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				board[i][j] = "";
			}
		}
	}

	public boolean validatePlayerMove(int columnPlay, int rowPlay, String player) {
		int countX = 0;
		int countO = 0;
		if (!board[columnPlay][rowPlay].isEmpty()) {
			return false;
		}
		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board[col].length; row++) {
				if (board[col][row].equalsIgnoreCase("x")) {
					countX++;
				} else if (board[col][row].equalsIgnoreCase("o")) {
					countO++;
				}
			}
		}
		if (countX > countO && player.equalsIgnoreCase("x")) {
			return false;
		}
		if (countO > countX && player.equalsIgnoreCase("o")) {
			return false;
		}
		return true;
	}

	public boolean validateWin(String symbol) {
		if (board[0][0].equalsIgnoreCase(symbol)
			&& board[1][0].equalsIgnoreCase(symbol)
			&& board[2][0].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[0][1].equalsIgnoreCase(symbol)
			&& board[1][1].equalsIgnoreCase(symbol)
			&& board[2][1].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[0][2].equalsIgnoreCase(symbol)
			&& board[1][2].equalsIgnoreCase(symbol)
			&& board[2][2].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[0][0].equalsIgnoreCase(symbol)
			&& board[0][1].equalsIgnoreCase(symbol)
			&& board[0][2].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[1][0].equalsIgnoreCase(symbol)
			&& board[1][1].equalsIgnoreCase(symbol)
			&& board[1][2].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[2][0].equalsIgnoreCase(symbol)
			&& board[2][1].equalsIgnoreCase(symbol)
			&& board[2][2].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[0][0].equalsIgnoreCase(symbol)
			&& board[1][1].equalsIgnoreCase(symbol)
			&& board[2][2].equalsIgnoreCase(symbol)) {
			return true;
		} else if (board[0][2].equalsIgnoreCase(symbol)
			&& board[1][1].equalsIgnoreCase(symbol)
			&& board[2][0].equalsIgnoreCase(symbol)) {
			return true;
		}
		return false;
	}

	public boolean isDraw() {
		for (int col = 0; col < board.length; col++) {
			for (int row = 0; row < board[col].length; row++) {
				if (board[col][row].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	public int getColumnCount() {
		return COLUMN;
	}

	public int getRowCount() {
		return ROW;
	}

	public String getValueAt(int i, int j) {
		return board[i][j];
	}

	public void play(int column, int row, String content) {
		board[column][row] = content;
	}
}
