/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.shareFiles;

import csheets.support.ThreadManager;
import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vendor.volt.protocols.udp.UdpServer;

/**
 *
 * @author ruben
 */
public class ShareFilesControllerTest {

	private final ShareFilesController controller;
	private String keyPathDownloads = "ipc.shereFiles-pathDownloads";
	private String keyPathFiles = "ipc.shereFiles-pathFiles";
	private UdpServer server;

	public ShareFilesControllerTest() {
		this.controller = new ShareFilesController();
		ThreadManager.create("ipc.shereFiles-UDPServer", new Thread());
		ThreadManager.create("ipc.shereFiles-UDPClient", new Thread());

	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of pathDownloads method, of class ShareFilesController.
	 */
	@Test
	public void testPathDownloads_String() {
		System.out.println("pathDownloads");
		String path = keyPathDownloads;
		ShareFilesController instance = new ShareFilesController();
		instance.pathDownloads(path);

	}

	/**
	 * Test of pathFiles method, of class ShareFilesController.
	 */
	@Test
	public void testPathFiles_String() {
		System.out.println("pathFiles");
		String path = keyPathFiles;
		ShareFilesController instance = new ShareFilesController();
		instance.pathFiles(path);

	}

	/**
	 * Test of files method, of class ShareFilesController.
	 */
	@Test
	public void testFiles() {
		System.out.println("files");
		ShareFilesController instance = new ShareFilesController();

		instance.pathFiles("files");

		List<File> list = instance.files();
		assertEquals(list.size(), 2);

	}

	/**
	 * Test of server method, of class ShareFilesController.
	 */
	@Test
	public void testServer() {
		System.out.println("server");
		ShareFilesController instance = new ShareFilesController();
		instance.server();

	}

	/**
	 * Test of client method, of class ShareFilesController.
	 */
	@Test
	public void testClient() {
		System.out.println("client");
		int seconds = 0;
		ShareFilesController instance = new ShareFilesController();
		instance.client(seconds);

	}

//	/**
//	 * Test of stop method, of class ShareFilesController.
//	 */
//	@Test
//	public void testStop() {
//		System.out.println("stop");
//		UdpServer server = new UdpServer();
//		server = NetworkManager.udp();
//		ShareFilesController instance = new ShareFilesController();
//
//		instance.stop();
//
//	}
	/**
	 * Test of pathDownloads method, of class ShareFilesController.
	 */
	@Test
	public void testPathDownloads_0args() {
		System.out.println("pathDownloads");
		ShareFilesController instance = new ShareFilesController();
		String expResult = "downloads";
		String result = instance.pathDownloads();
		assertEquals(expResult, result);

	}

	/**
	 * Test of pathFiles method, of class ShareFilesController.
	 */
	@Test
	public void testPathFiles_0args() {
		System.out.println("pathFiles");
		ShareFilesController instance = new ShareFilesController();
		String expResult = "files";
		String result = instance.pathFiles();
		assertEquals(expResult, result);

	}

}
