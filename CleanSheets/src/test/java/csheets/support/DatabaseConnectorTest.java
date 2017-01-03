/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.support;

import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Ordering testing order so the table wont be deleted before data is stored
 * since method sorters works with testing names called testZDropDatabase
 *
 * @author ab
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseConnectorTest {

    String[][] content;
    String tableName = "DatabaseConnectorTest";
    DatabaseConnector con;

    public DatabaseConnectorTest() {
        con = DatabaseConnector.getInstance();
        con.setConnection("org.h2.Driver",
                "jdbc:h2:./db/csheets",
                "", "");
        content = new String[2][3];
        content[0][0] = "PK1";
        content[0][1] = "vazio";
        content[0][2] = "nada aconteceu";
        content[1][0] = "PK2";
        content[1][1] = "vazio";
        content[1][2] = "111111";
    }

    /**
     * Test of getInstance method, of class DatabaseConnector.
     */
    @Test
    public void testGetInstance() {
        assertNotNull(DatabaseConnector.getInstance());
    }

    /**
     * Test of getInstance method, of class DatabaseConnector.
     */
    @Test
    public void testGetInstance2() {
        assertEquals(con, DatabaseConnector.getInstance());
    }

    /**
     * Test of isConnected method, of class DatabaseConnector.
     */
    @Test
    public void testIsConnected() {
        assertEquals(true, con.isConnected());
    }

    /**
     * Test of createTable method, of class DatabaseConnector.
     */
    @Test
    public void testCreateTable() throws SQLException {
        String header[] = new String[3];
        header[0] = "PK";
        header[1] = "vazio";
        header[2] = "oups";
        boolean result = con.createTable(tableName, header);
        assertEquals(true, result);
    }

    /**
     * Test of createTable method, of class DatabaseConnector.
     */
    @Test
    public void testDoesTableExist() throws SQLException {
        boolean result = con.doesTableExist(tableName);
        assertEquals(true, result);
    }

    /**
     * Test of saveData method, of class DatabaseConnector.
     */
    @Test
    public void testSaveData() throws SQLException {
        boolean result = con.saveData(tableName, content);
        assertEquals(true, result);
    }

    /**
     * Test of getTableData method, of class DatabaseConnector.
     */
    @Test
    public void testGetTableData() throws SQLException {
        int j = 0;
        boolean result = true;
        ResultSet rs = con.getTableData(tableName);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (!rs.getString(i).equals(content[j][i])) {
                    result = false;
                }
            }
            j++;
        }
        assertEquals(true, result);
    }

    /**
     * Test of dropTable method, of class DatabaseConnector.
     */
    @Test
    public void testZDropTable() throws SQLException, InterruptedException {
        sleep(5);
        con.dropTable(tableName);
        boolean result = con.doesTableExist(tableName);
        assertEquals(false, result);
    }

}
