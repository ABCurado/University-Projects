/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.FormEditor.ui;

import csheets.core.Cell;
import csheets.ui.ctrl.UIController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class FormE implements Serializable {

	/**
	 * Name of the Form
	 */
	private String nameForm;

	private List<Widget> lstWidget;

	private boolean isEditable;

	/**
	 *
	 * @param nameForm lstWidget is a new List
	 */
	public FormE(String nameForm) {
		this.nameForm = nameForm;
		this.lstWidget = new ArrayList<>();
		this.isEditable = true;
	}

	/**
	 *
	 * @return name of the Form
	 */
	public String getNameForm() {
		return nameForm;
	}

	/**
	 *
	 * @return list of Widgets
	 */
	public List<Widget> showLstWidget() {
		return lstWidget;
	}

	/**
	 *
	 * @return cell
	 */
	public Cell cell() {
		return UIController.getUIController().getActiveCell();
	}

	/**
	 *
	 * @param wgt is a received widget
	 */
	public void addWidget(Widget wgt) {
		this.lstWidget.add(wgt);
	}

	/**
	 *
	 * @param activeCell is active Cell on the workbook
	 */
	/*public void setCell(Cell activeCell) {
		this.cell = activeCell;
	}*/
	/**
	 *
	 * @return a boolean to define form editable or not editable
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 *
	 * @param editable is a boolean to define form editable or not editable
	 */
	public void setEditable(boolean editable) {
		this.isEditable = editable;
	}
}
