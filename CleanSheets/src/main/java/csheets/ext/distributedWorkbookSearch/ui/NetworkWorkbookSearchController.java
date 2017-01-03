/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.distributedWorkbookSearch.ui;

import csheets.core.Workbook;
import csheets.ext.distributedWorkbookSearch.WorkBookDTO;
import csheets.ext.distributedWorkbookSearch.WorkBookDTOAssembler;
import csheets.framework.search.LocalWorkbookSearch;
import csheets.ui.ctrl.UIController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Based on previous implementation by José Barros
 *
 * @author Rúben Teixeira 1140780@isep.ipp.pt
 */
public class NetworkWorkbookSearchController {

	/**
	 * The UDP Service.
	 */
	private UdpService udpService;

	/**
	 * The TCP Service.
	 */
	private TcpService tcpService;

	/**
	 * The constructor
	 */
	public NetworkWorkbookSearchController() {
	}

	/**
	 * Performs a new search for workbooks
	 *
	 * @param uiController the UIController
	 * @param search the search pattern
	 * @return list of WorkbookDTO
	 */
	public List<WorkBookDTO> newLocalSearch(UIController uiController,
											String search) {
		LocalWorkbookSearch localSearch = new LocalWorkbookSearch(uiController, search);
		List<Workbook> foundWorkbooks = localSearch.getResults();
		if (foundWorkbooks == null) {
			return null;
		}
		List<WorkBookDTO> resultList = new ArrayList<>();
		for (Workbook foundWorkbook : foundWorkbooks) {
			resultList.add(WorkBookDTOAssembler.getWorkbookDTO(foundWorkbook));
		}
		return resultList;
	}

	/**
	 * Initiates Udp broadcast
	 *
	 * @param seconds Time in seconds to send another request.
	 */
	public void discoverInstances(int seconds) {
		try {
			this.udpService.client(seconds);
		} catch (Exception ex) {
			// do nothing
		}
	}

	/**
	 * Requests the user permission of a discovered instance.
	 *
	 * @param target the target host
	 * @param permissionMessage the message the permission request shall carry
	 */
	public void requestPermission(String target, String permissionMessage) {
		this.tcpService.requestPermission(target, permissionMessage);
	}

	/**
	 * Requests search results from an instance that previously gave searching
	 * permission
	 *
	 * @param target the target host
	 * @param pattern the Workbook name pattern to search for
	 */
	public void initiateSearch(String target, String pattern) {
		this.tcpService.searchWorkbookOnTarget(target, pattern);
	}

	/**
	 * Restarts the UDP service.
	 *
	 * @param ui UI of Workbook Search
	 * @param seconds The number of seconds to execute each request.
	 */
	public void restartUdpService(NetworkWorkbookSearchPanel ui, int seconds) {
		if (this.udpService == null) {
			startUdpService(ui, seconds);
		} else {
			if (!this.udpService.isActive()) {
				startUdpService(seconds);
			}
		}

	}

	/**
	 * Restarts the TCP service.
	 *
	 * @param ui UI of Workbook Search
	 */
	public void restartTcpService(NetworkWorkbookSearchPanel ui) {
		if (this.tcpService == null) {
			startTcpService(ui);
		} else {
			if (!this.tcpService.isActive()) {
				startTcpService();
			}
		}
	}

	/**
	 * Starts the UDP service
	 *
	 * @param seconds The number of seconds to execute each request.
	 */
	private void startUdpService(int seconds) {
		if (seconds <= 0) {
			throw new IllegalArgumentException("Invalid seconds. It's not possible to register negative or zero seconds.");
		}

		try {
			this.udpService.server();
			//this.udpService.client(seconds);  Start the broadcasts only when
			// new search is initiated
		} catch (IllegalArgumentException e) {
			this.udpService.stop();

			throw e;
		}
	}

	/**
	 * Starts the UDP service.
	 *
	 * @param ui UI of Workbook Search
	 * @param seconds The number of seconds to execute each request.
	 */
	public void startUdpService(
		NetworkWorkbookSearchPanel ui, int seconds) {
		if (ui == null) {
			throw new IllegalArgumentException("The user interface cannot be null.");
		}

		this.udpService = new UdpService();

		this.startUdpService(seconds);

		this.udpService.addObserver(ui);
	}

	/**
	 * Starts the TCP service.
	 *
	 * @param pattern the Workbook name pattern.
	 */
	private void startTcpService() {
		try {
			this.tcpService.server();

		} catch (IllegalArgumentException e) {
			this.tcpService.stop();

			throw e;
		}
	}

	/**
	 * Starts the TCP service.
	 *
	 * @param ui Workbook Search ui
	 */
	public void startTcpService(NetworkWorkbookSearchPanel ui) {
		if (ui == null) {
			System.out.println("error");
			throw new IllegalArgumentException("The user interface cannot be null.");
		}

		this.tcpService = new TcpService();

		this.startTcpService();

		this.tcpService.addObserver(ui);
	}

	/**
	 * Stop both the UDP and TCP services.
	 */
	public void stopServices() {
		this.tcpService.stop();
		this.udpService.stop();
	}

	/**
	 * Sends a Workbook search result to a target host
	 *
	 * @param search search pattern
	 * @param result The string representation of the result
	 */
	void sendSearchResult(String target, String result) throws NullPointerException {
		tcpService.sendSearchResult(target, result);

	}
}
