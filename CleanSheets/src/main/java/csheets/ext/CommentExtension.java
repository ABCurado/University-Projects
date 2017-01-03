/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext;

import csheets.ext.comments.Comment;

/**
 *
 * @author Carlos Mateus
 */
public abstract class CommentExtension extends Comment {

	/**
	 * The delegate of the extension
	 */
	private Comment delegate;

	/**
	 * The name of the extension to which the cell extension belongs
	 */
	private String name;

	/**
	 * Creates a new comment extention
	 *
	 * @param delegate the delegate of the extension
	 * @param userName the name of the extension to which the comment extension
	 * belongs
	 * @param text the text
	 */
	public CommentExtension(Comment delegate, String userName, String text) {
		super(userName, text);
		this.delegate = delegate;
//		delegate.addCellListener(this);
	}

	/**
	 * Returns the extension's delegate.
	 *
	 * @return the extension's delegate, i.e. the comment to which it belongs
	 */
	public final Comment getDelegate() {
		return delegate;
	}

	/**
	 * Returns the name of the extension to which the comment extension belongs.
	 *
	 * @return the name of the extension to which the comment extension belongs
	 */
	public final String getName() {
		return name;
	}

//	public final Spreadsheet getSpreadsheet() {
//		Spreadsheet spreadsheet = delegate.getSpreadsheet();
//		Spreadsheet extension = spreadsheet.getExtension(name);
//		if (extension != null) {
//			return extension;
//		} else {
//			return spreadsheet;
//		}
//	}
//	public final void setContent(String content) throws FormulaCompilationException {
//		// Disallow?
//		delegate.setContent(content);
//	}
//
//	public void clear() {
//		delegate.clear();
//	}
//
//	public final SortedSet<Cell> getPrecedents() {
//		SortedSet<Cell> cellExts = new TreeSet<Cell>();
//		for (Cell cell : delegate.getPrecedents()) {
//			cellExts.add(cell.getExtension(getName()));
//		}
//		return cellExts;
//	}
//
//	public final SortedSet<Cell> getDependents() {
//		SortedSet<Cell> cellExts = new TreeSet<Cell>();
//		for (Cell cell : delegate.getDependents()) {
//			cellExts.add(cell.getExtension(getName()));
//		}
//		return cellExts;
//	}
//
//	public final void copyFrom(Cell source) {
//		delegate.copyFrom(source);
//	}
//
//	public final void moveFrom(Cell source) {
//		delegate.moveFrom(source);
//	}
//
//	public final void addCellListener(CellListener listener) {
//		delegate.addCellListener(listener);
//	}
//
//	public final void removeCellListener(CellListener listener) {
//		delegate.removeCellListener(listener);
//	}
//
//	public final CellListener[] getCellListeners() {
//		return delegate.getCellListeners();
//	}
//
//	public final Cell getExtension(String name) {
//		return delegate.getExtension(name);
//	}
//
//	public final int compareTo(Cell cell) {
//		return delegate.compareTo(cell);
//	}
	public final String toString() {
		return delegate.toString();
	}

	public void valueChanged(Comment comment) {
	}

	public void contentChanged(Comment comment) {
	}

	public void dependentsChanged(Comment comment) {
	}

	public void cellCleared(Comment comment) {
	}

	public void cellCopied(Comment comment, Comment source) {
	}
}
