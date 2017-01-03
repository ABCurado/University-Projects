/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.actions;

/**
 *
 * @author Paulo Gandra Sousa
 */
public final class NullAction implements Action {

	// FIXME use lazy holder idiom
	private static final NullAction theInstance = new NullAction();

	public static NullAction instance() {
		return theInstance;
	}

	private NullAction() {
	}

	@Override
	public boolean execute() {
		return false;
	}
}
