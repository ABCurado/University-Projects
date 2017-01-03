/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.networkExplorer;

import csheets.core.Workbook;
import csheets.ext.Extension;
import csheets.ext.networkExplorer.domain.AppInfo;
import csheets.ext.networkExplorer.domain.ExtensionInfo;
import csheets.ext.networkExplorer.domain.WorkbookInfo;
import csheets.ext.networkExplorer.ui.NetworkExplorerPanel;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;
import java.util.Iterator;
import java.util.Map;
	

/**
 *
 * @author Diogo Azevedo
 */
public class NetworkExplorerController {
        private Map<String,AppInfo> infoMap;
        private final String LOCALHOST="localHost";
        private UIController uiController;
        /**
	 * The UDP Service.
	 */
	private UdpService udpService;
        
    	/**
	 * Starts the UDP service.
	 *
	 * @param seconds The number of seconds to execute each request.
	 */
	private void startUdpService(int seconds) {
		if (seconds <= 0) {
			throw new IllegalArgumentException("Invalid seconds number given. It's not possible to register negative or zero seconds.");
		}

		try {
			this.udpService.server();
			this.udpService.client(seconds);
		} catch (IllegalArgumentException e) {
			this.udpService.stop();
			throw e;
		}
	}

	/**
	 * Starts the UDP service.
	 *
	 * @param panel The user interface.
	 * @param seconds The number of seconds to execute each request.
	 */
	public void startUdpService(NetworkExplorerPanel panel, int seconds) {
		if (panel == null) {
			throw new IllegalArgumentException("The user interface cannot be null.");
		}

		this.udpService = new UdpService();
		this.startUdpService(seconds);
		this.udpService.addObserver(panel);
	}
        public void receiveCleansheets(String target){
            new TcpService().client(target,"receive");
        }
        
        public void infoExplorer(){
            findExtension(LOCALHOST);
            findWorkbooks(LOCALHOST);
        }

    private void findExtension(String id) {
        for (UIExtension uiExtension : uiController.getExtensions()) {
            Extension extension = uiExtension.getExtension();
            infoMap.get(id).addExtension(extension.getName(),new ExtensionInfo(extension.isEnabled(), extension.getName(), extension.getVersion(), extension.getDescription()));
        }
    }

    private void findWorkbooks(String id) {
        for (Workbook workbook : uiController.workbooks()) {
            int index = uiController.workbooks().indexOf(workbook);
            WorkbookInfo newWorkbook = new WorkbookInfo(index);
            infoMap.get(id).addWorkbooks(index, newWorkbook);
            findSpreadSheets(index, workbook);
        }
    }

    private void findSpreadSheets(int id, Workbook workbook) {
        Iterator iterator = workbook.iterator();
        while(iterator.hasNext()){
            //infoMap.get(id).addSpreadSheets(id,iterator.next());
        }
    }
}
    

