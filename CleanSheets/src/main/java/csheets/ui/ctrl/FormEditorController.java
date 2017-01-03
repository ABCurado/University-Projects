/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.ctrl;

import csheets.core.Cell;
import csheets.core.CellImpl;
import csheets.core.Value;
import csheets.ui.FormEditor.ui.ButtonWidget;
import csheets.ui.FormEditor.ui.FormE;
import csheets.ui.FormEditor.ui.LabelWidget;
import csheets.ui.FormEditor.ui.TextFieldWidget;
import csheets.ui.FormEditor.ui.Widget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hicham Abahri
 */
public class FormEditorController {

	/**
	 * List with the three types of possible widgets.
	 */
	private List<Widget> widgets = new ArrayList();

	/**
	 * Cell cell.
	 */
	private CellImpl cell;

	private FormE content;

	/**
	 * Form editor controller.
	 *
	 * @param cell Cell
	 */
	public FormEditorController(Cell cell) {
		if (cell instanceof CellImpl) {
			this.cell = (CellImpl) cell;
		}
		this.widgets.add(new LabelWidget());
		this.widgets.add(new TextFieldWidget());
		this.widgets.add(new ButtonWidget());

	}

	/**
	 * Get the content of the cell.
	 *
	 * @param content Content
	 * @return String
	 */
	public String getValue(String content) {
		Value value = this.cell.getVariableValue(content, 1); //BE CARE.
		if (value == null) {
			return content;
		}
		return value.toString();
	}

	/**
	 * Get the type of widget - label, button, textfield.
	 *
	 * @param name name
	 * @return Widget
	 */
	public Widget getWidget(String name) {
		for (Widget widget : widgets) {
			if (widget.getName().equalsIgnoreCase(name)) {
				return widget;
			}
		}
		return null;
	}

	public void setContentWidget(String content) {
		for (Widget wgt : widgets) {
			wgt.setContentWidget(content);
		}
	}
}
