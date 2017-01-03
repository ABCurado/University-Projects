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
public class TextFieldWidget implements Widget {

	/**
	 * content of the textField
	 */
	private String content;

	/**
	 *
	 * @return name of Widget
	 */
	@Override
	public String getName() {
		return "textfield";
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
	 * content of the textField
	 *
	 * @return content
	 */
	@Override
	public String getContent() {
		return content;
	}
}
