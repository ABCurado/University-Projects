package csheets.core;

import org.junit.Test; 

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue;

public class SpreadsheetTest {

	@Test public void testSetTitle() {
		
		// create a workbook with 2 sheets
		Workbook wb=new Workbook(2);
		Spreadsheet s=wb.getSpreadsheet(0);
		
		s.setTitle("title");
		
		assertTrue("title".compareTo(s.getTitle())==0);	
	}	
}
