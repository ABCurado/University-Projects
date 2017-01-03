/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.presentation.console.Menu;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Eduardo
 */
class PublishMenuPrinter implements Visitor<Menu> {

	@Override
	public void visit(Menu visitee) {
		//System.err.println("%-10s%-30s%-4s\n", visitee.title(), visitee.);
	}

	@Override
	public void beforeVisiting(Menu visitee) {
		//nothing to do
	}

	@Override
	public void afterVisiting(Menu visitee) {
		//nothing to do
	}

}
