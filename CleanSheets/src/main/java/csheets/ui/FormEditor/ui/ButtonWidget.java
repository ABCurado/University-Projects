/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui.FormEditor.ui;

/**
 *
 * @author João Martins
 */
public class ButtonWidget implements Widget {

	/**
	 * content of the button
	 */
	private String content;

	/**
	 *
	 * @return name of Widget
	 */
	@Override
	public String getName() {
		return "button";
	}

	/**
	 * Set content of the textField
	 *
	 * @param content is a new content of textfield
	 */
	@Override
	public void setContentWidget(String content) {
		this.content = content;
	}

	/**
	 *
	 * @return content of the button widget
	 */
	@Override
	public String getContent() {
		return content;
	}

}
