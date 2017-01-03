package csheets.ext.cellsSharing.ui;

import csheets.core.Cell;
import csheets.core.formula.compiler.FormulaCompilationException;
import csheets.ext.style.StylableCell;
import csheets.ext.style.StyleExtension;
import csheets.ui.ctrl.UIController;
import java.awt.Color;
import java.awt.Font;
import java.util.Map;

public class ShareCellsController {

	/**
	 * The UDP Service.
	 */
	private UdpService udpService;

	/**
	 * The TCP Service.
	 */
	private TcpService tcpService;

	public ShareCellsController() {
	}

	/**
	 * Starts the UDP service.
	 *
	 * @param seconds The number of seconds to execute each request.
	 */
	private void startUdpService(int seconds) {
		if (seconds <= 0) {
			throw new IllegalArgumentException("Invalid seconds number given. It's not possible to register negative or zero seconds.");
		}

		try {
			this.udpService.server();
			this.udpService.client(seconds);
		} catch (IllegalArgumentException e) {
			this.udpService.stop();

			throw e;
		}
	}

	/**
	 * Starts the UDP service.
	 *
	 * @param panel The user interface.
	 * @param seconds The number of seconds to execute each request.
	 */
	public void startUdpService(SharePanel panel, int seconds) {
		if (panel == null) {
			throw new IllegalArgumentException("The user interface cannot be null.");
		}

		this.udpService = new UdpService();

		this.startUdpService(seconds);

		this.udpService.addObserver(panel);
	}

	/**
	 * Starts the TCP service.
	 *
	 * @param port The target port that is defined by the user.
	 */
	private void startTcpService(int port) {
		if (port < 0 || port > 49151) {
			throw new IllegalArgumentException("Invalid port was defined. Please select a valid port.");
		}

		try {
			this.tcpService.server(port);

		} catch (IllegalArgumentException e) {
			this.tcpService.stop();

			throw e;
		}
	}

	/**
	 * Starts the TCP service.
	 *
	 * @param panel The user interface.
	 * @param port The target port that is defined by the user.
	 */
	public void startTcpService(SharePanel panel, int port) {
		if (panel == null) {
			throw new IllegalArgumentException("The user interface cannot be null.");
		}

		this.tcpService = new TcpService(panel);

		this.startTcpService(port);

		this.tcpService.addObserver(panel);
	}

	/**
	 * Restarts both the UDP and TCP services.
	 *
	 * @param port The target port that is defined by the user.
	 * @param seconds The number of seconds to execute each request.
	 */
	public void restartServices(int port, int seconds) {
		this.tcpService.stop();
		this.udpService.stop();

		this.startUdpService(seconds);
		this.startTcpService(port);
	}

	/**
	 * Sends a array of Cells to the targeted host.
	 *
	 * @param target Targeted Host (ip and port)
	 * @param cells Selected Cells
	 */
	public void sendCells(String target, Cell[][] cells) {
		String message = "";

		int lines = cells.length;
		int columns = cells[0].length;

		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cell = cells[i][j];
				message += buildMessage(cell);
			}
		}

		message = message.substring(1);

		new TcpService().client(target, message);
	}

	public String buildMessage(Cell cell) {
		StylableCell stylableCell = (StylableCell) cell.getExtension(
			StyleExtension.NAME);

		String message = "";

		message += ";" + stylableCell.getAddress().getColumn() + ";"
			+ stylableCell.getAddress().getRow() + ";" + stylableCell.getValue().
			getType()
			+ ";" + stylableCell.getContent() + ";" + stylableCell.
			getFont().getName()
			+ ";" + stylableCell.getFont().getStyle() + ";" + stylableCell.
			getFont().getSize()
			+ ";" + stylableCell.getHorizontalAlignment() + ";" + stylableCell.
			getVerticalAlignment()
			+ ";" + stylableCell.getForegroundColor().getRGB() + ";" + stylableCell.
			getBackgroundColor().getRGB();

		return message;
	}

	/**
	 * Updates the active spreadsheet with the received cells.
	 *
	 * @param ui The user interface controller.
	 * @param cells Received cells information.
	 * @throws csheets.core.formula.compiler.FormulaCompilationException Cells
	 * can have the wrong value.
	 */
	public void updateCells(UIController ui, Map<String, String> cells) throws FormulaCompilationException {
		for (Map.Entry<String, String> entry : cells.entrySet()) {
			String[] addressData = entry.getKey().split(":");
			int column = Integer.parseInt(addressData[0]);
			int row = Integer.parseInt(addressData[1]);

			try {
				String value = "";
				Font font = null;
				int hAlignment = 0;
				int vAlignment = 0;
				Color fgColor = null;
				Color bgColor = null;
				String[] data = entry.getValue().split(";");

				if (data.length > 1) {
					value = data[1];
					font = new Font(data[2], Integer.parseInt(data[3]), Integer.
									parseInt(data[4]));
					hAlignment = Integer.parseInt(data[5]);
					vAlignment = Integer.parseInt(data[6]);
					fgColor = new Color(Integer.parseInt(data[7]));
					bgColor = new Color(Integer.parseInt(data[8]));
				} else {
					value = "";
				}

				// Sets the cell's style
				StylableCell cell = (StylableCell) ui.getActiveSpreadsheet().
					getCell(column, row).getExtension(StyleExtension.NAME);
				cell.setContent(value);

				cell.setFont(font);
				cell.setHorizontalAlignment(hAlignment);
				cell.setVerticalAlignment(vAlignment);
				cell.setForegroundColor(fgColor);
				cell.setBackgroundColor(bgColor);

				ui.setWorkbookModified(ui.focusOwner.getSpreadsheet().
					getWorkbook());
				ui.focusOwner.repaint();

			} catch (FormulaCompilationException ex) {
				throw new FormulaCompilationException();
			}
		}
	}

	/**
	 * Sends a Cell to the targeted host.
	 *
	 * @param cell Edited Cell
	 */
	public void continuousSending(Cell cell) {
		String message = "";

		message += buildMessage(cell);

		message = message.substring(1);

		tcpService.continuousSending(message, cell);
	}

	/**
	 * Sets the target instance for a continuous sending of messages.
	 *
	 * @param target Targeted instance
	 */
	public void setContinuousTarget(String target) {
		tcpService.setContinuousTarget(target);
	}

	/**
	 * Stops the continuous sending of messages.
	 */
	public void stopConnection() {
		tcpService.stopContinuousSending();
	}
}
