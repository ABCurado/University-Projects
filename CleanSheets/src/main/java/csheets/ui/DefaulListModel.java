/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ui;

import javax.swing.DefaultListModel;

/**
 *
 * @author user
 */
public class DefaulListModel extends DefaultListModel {

	public DefaulListModel() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		DefaultListModel listModel = (DefaultListModel) o;

		if (this.getSize() != listModel.getSize()) {
			return false;
		}
		int i = 0;
		for (Object obj : listModel.toArray()) {
			if (!obj.equals(this.getElementAt(i))) {
				return false;
			}
			i++;
		}
		return true;

	}
}
