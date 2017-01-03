/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.FormEditor.ui;

import java.io.Serializable;

/**
 *
 * @author João Martins
 */
public interface Widget extends Serializable {

	/**
	 * Get name widget.
	 *
	 * @return Widget Name
	 */
	public String getName();

	/**
	 * Get content of the widget
	 *
	 * @return content of the widget
	 */
	public String getContent();

	/**
	 * Set content of the widget
	 *
	 * @param content is a new content of widget
	 */
	public void setContentWidget(String content);
}
