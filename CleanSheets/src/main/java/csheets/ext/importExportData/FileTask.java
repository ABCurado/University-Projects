/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.importExportData;

import csheets.core.Spreadsheet;
import csheets.ext.importExportData.ui.ImportExportTextFileController;
import csheets.notification.Notification;
import csheets.support.Task;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Marcelo Barroso 1131399
 */
public class FileTask extends Task implements Observer {

	private String filePath;
	private String separator;
	private Spreadsheet spreadsheet;
	private ImportExportTextFileController controller;
	private long date = 0;

	public FileTask(ImportExportTextFileController controller,
					String filePath, String separator,
					Spreadsheet spreadsheet) {
		this.filePath = filePath;
		this.separator = separator;
		this.spreadsheet = spreadsheet;
		this.controller = controller;
		Notification.cellInformer().addObserver(this);
	}

	@Override
	public void fire() {
		File file = new File(this.filePath);
		if (file.lastModified() > this.date) {
			this.date = file.lastModified();
			this.controller.
				linkImport(this.getFilePath(), this.getSeparator(), this.
						   getSpreadsheet());
		}
	}

	@Override
	public void update(Observable o, Object object) {
		if (this.spreadsheet != null) {
			this.controller.
				linkExport(this.getFilePath(), this.getSeparator(), this.
						   getSpreadsheet());
		}
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * @param separator the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * @return the spreadsheet
	 */
	public Spreadsheet getSpreadsheet() {
		return spreadsheet;
	}

	/**
	 * @param spreadsheet the spreadsheet to set
	 */
	public void setSpreadsheet(Spreadsheet spreadsheet) {
		this.spreadsheet = spreadsheet;
	}

}
