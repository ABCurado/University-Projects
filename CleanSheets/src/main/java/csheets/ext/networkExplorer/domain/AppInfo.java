/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.networkExplorer.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diogo Azevedo
 */
public class AppInfo {

	private List<ExtensionInfo> extensions;
	private List<WorkbookInfo> workbooks;
	private String name = "default";

	public AppInfo(String name) {
		this.name = name;
		this.extensions = new ArrayList();
		this.workbooks = new ArrayList();
	}

	/**
	 * @return the extensionMap
	 */
	public List<ExtensionInfo> getExtensions() {
		return extensions;
	}

	/**
	 * @return the workbookMap
	 */
	public List<WorkbookInfo> getWorkbooks() {
		return workbooks;
	}

	public void addExtension(ExtensionInfo extesion) {
		extensions.add(extesion);
	}

	public void addWorkbooks(WorkbookInfo newWorkbook) {
		workbooks.add(newWorkbook);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		AppInfo instance = (AppInfo) obj;
		return this.equals(instance);
	}

	@Override
	public int hashCode() {
		int hashcode = 29;
		hashcode = hashcode + 11 + this.name.hashCode();
		return hashcode;
	}
}
