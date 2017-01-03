/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.networkExplorer;

import csheets.ext.networkExplorer.domain.AppInfo;
import csheets.ext.networkExplorer.domain.ExtensionInfo;
import csheets.ext.networkExplorer.domain.SpreadSheetInfo;
import csheets.ext.networkExplorer.domain.WorkbookInfo;
import csheets.ui.ctrl.UIController;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Diogo Azevedo
 */
public class NetworkExplorerController {

	private Map<String, AppInfo> infoMap;
	private UIController uiController;
	/**
	 * The UDP Service.
	 */
	private UdpService udpService;

	public NetworkExplorerController() {
		this.udpService = new UdpService();
		this.udpService.server();
		this.udpService.client(15);
		this.infoMap = new HashMap();
	}

	public Set<AppInfo> appInfoList() {
		Set<AppInfo> list = new HashSet();
		for (AppInfo appInfo : this.infoMap.values()) {
			list.add(appInfo);
		}
		return list;
	}

	public void appInfo(String name, String info) {
		AppInfo app = new AppInfo(name);
		WorkbookInfo workbook = null;
		for (String line : info.split(";")) {
			String[] data = line.split("-_-");
			if (data[0].equals("Extension")) {
				app.
					addExtension(new ExtensionInfo(data[2].equals("true"), data[1], data[3], data[4]));
			} else if (data[0].equals("Workbook")) {
				workbook = new WorkbookInfo(data[1]);
				app.addWorkbooks(workbook);
			} else if (data[0].equals("Spreadsheet")) {
				workbook.addSpreadSheet(new SpreadSheetInfo(data[1]));
			}
		}
		this.infoMap.remove(name);
		this.infoMap.put(name, app);
	}

}
