/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.comments;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * Value object that represents a Comment
 *
 * @author Rafael
 */
public class Comment implements Serializable {

	/**
	 * The username that made the comment
	 */
	private String userName;

	/**
	 * The text of the comment
	 */
	private String text;

	/**
	 * List of Comments
	 */
	private List<Comment> history;

	/**
	 * The default font
	 */
	public static final Font FONT = UIManager.getFont("Table.font");

	/**
	 * The default background color
	 */
	public static final Color BACKGROUND = UIManager.
		getColor("Table.background");

	/**
	 * The default empty border
	 */
	public static final Border BORDER
		= BorderFactory.createEmptyBorder(1, 1, 1, 1);

	/**
	 * The font used when rendering the StylableComment content
	 */
	private Font font;

	/**
	 * The background color of the StylableComment
	 */
	private Color bgColor;

	/**
	 * The border of the comment
	 */
	private Border border;

	/**
	 * Construct an instance of Comment that receive userName and text
	 *
	 * @param userName userName of comment
	 * @param text text of comment
	 */
	public Comment(String userName, String text) {
		this.userName = userName;
		this.text = text;
		this.history = new ArrayList<>();
		history.add(this);
		resetStyle();
	}

	/**
	 * Construct an instance o Comment that receives userName, text, font,
	 * fgColor, bgColor and border.
	 *
	 * @param userName username of comment
	 * @param text text of comment
	 * @param font font of comment
	 * @param bgColor BackgroundColor of comment
	 * @param border border of comment
	 */
	public Comment(String userName, String text, Font font,
				   Color bgColor, Border border) {
		this.userName = userName;
		this.text = text;
		this.font = font;
		this.bgColor = bgColor;
		this.border = border;
		this.history = new ArrayList<>();
		history.add(this);

	}

	/**
	 * Returns the userName of comment
	 *
	 * @return the userName of comment
	 */
	public String userName() {
		return userName;
	}

	/**
	 * Return the text of comment
	 *
	 * @return the text of comment
	 */
	public String text() {
		return text;
	}

	/**
	 * Returns the font used when rendering the StylableComment content.
	 *
	 * @return the font used when rendering the StylableComment content
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Returns the background color of the StylableComment.
	 *
	 * @return the background color of the StylableComment.
	 */
	public Color getBackgroundColor() {
		return bgColor;
	}

	/**
	 * Returns the border of the StylableComment.
	 *
	 * @return the border of the StylableComment
	 */
	public Border getBorder() {
		return border;
	}

	/**
	 * Sets the font used when rendering the StylableComment content.
	 *
	 * @param font the font used when rendering the StylableComment content
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	/**
	 * Sets the background color of the comment.
	 *
	 * @param bgColor the background color of the comment
	 */
	public void setBackgroundColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * Sets the border of the comment.
	 *
	 * @param border the border of the comment
	 */
	public void setBorder(Border border) {
		this.border = border;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Resets the style of the comment.
	 */
	public void resetStyle() {
		this.font = FONT;
		this.bgColor = BACKGROUND;
		this.border = BORDER;

	}

	/**
	 * Removes the style from the StylableComment.
	 *
	 * @param comment comment was cleared.
	 */
	public void commentCleared(Comment comment) {
		if (this.equals(comment)) {
			resetStyle();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Comment other = (Comment) obj;
		return Objects.equals(this.userName, other.userName);
	}

	/**
	 * Method that modifies the comment
	 *
	 * @param comment Comment
	 */
	public void setComment(Comment comment) {
		Comment version = new Comment(this.userName(), this.text(), this.
									  getFont(), this.getBackgroundColor(), this.
									  getBorder());
		history.add(version);
		this.font = comment.getFont();
		this.border = comment.getBorder();
		this.bgColor = comment.getBackgroundColor();
		this.text = comment.text();
	}

	public List<Comment> commentHistory() {
		return this.history;
	}
}
