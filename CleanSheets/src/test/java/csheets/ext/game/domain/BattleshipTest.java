/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.domain;

import csheets.core.Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rui Bento
 */
public class BattleshipTest {

    public BattleshipTest() {
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
     * Test of addShip method, of class Battleship.
     */
    @Test
    public void testAddShip() {
        System.out.println("addShip");
        int shipCount = 0;
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
    }

    /**
     * Test of addShip method, of class Battleship.
     */
    @Test(expected = VerifyError.class)
    public void testAddShipWithOverride() {
        System.out.println("addShipWithOverride");
        int shipCount = 0;
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                instance.addShip(entry.getKey(), lstAddress);
            }
        }
    }

    /**
     * Test of addShip method, of class Battleship.
     */
    @Test(expected = VerifyError.class)
    public void testAddShipMoreThanAllowedShip() {
        System.out.println("addShipMoreThanAllowedShip");
        int shipCount = 0;
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                instance.addShip(entry.getKey(), lstAddress);
                shipCount++;
            }
        }
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                instance.addShip(entry.getKey(), lstAddress);
                shipCount++;
            }
        }
    }

    /**
     * Test of isReadyToPlay method, of class Battleship.
     */
    @Test
    public void testIsReadyToPlay() {
        System.out.println("isReadyToPlay");
        int shipCount = 0;
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        boolean result = instance.isReadyToPlay();
        assertTrue(result);
    }

    /**
     * Test of isReadyToPlay method, of class Battleship.
     */
    @Test
    public void testIsNotReadyToPlay() {
        System.out.println("isNotReadyToPlay");
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        boolean result = instance.isReadyToPlay();
        assertFalse(result);
    }

    /**
     * Test of shoot method, of class Battleship.
     */
    @Test
    public void testShoot() {
        System.out.println("shoot");
        int shipCount = 0;
        Map<Ship.ShipType, List<Address>> allShips = new HashMap<>();
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                allShips.put(entry.getKey(), lstAddress);
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        instance.isReadyToPlay();
        for (Map.Entry<Ship.ShipType, List<Address>> entry : allShips.entrySet()) {
            int result;
            for (int i = 0; i < entry.getKey().size() - 1; i++) {
                result = instance.shoot(entry.getValue().get(i), 0, 0);
                assertEquals(result, Battleship.SHOOT_HIT);
            }
            result = instance.shoot(entry.getValue().get(entry.getKey().size() - 1), 0, 0);
            assertEquals(result, Battleship.SHOOT_SINK);
        }
    }

    /**
     * Test of shoot method, of class Battleship.
     */
    @Test(expected = VerifyError.class)
    public void testShootRepeatLocation() {
        System.out.println("shootRepeatLocation");
        int shipCount = 0;
        Map<Ship.ShipType, List<Address>> allShips = new HashMap<>();
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                allShips.put(entry.getKey(), lstAddress);
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        instance.isReadyToPlay();
        for (Map.Entry<Ship.ShipType, List<Address>> entry : allShips.entrySet()) {
            for (int i = 0; i < entry.getKey().size(); i++) {
                instance.shoot(entry.getValue().get(i), 0, 0);
            }
            instance.shoot(entry.getValue().get(entry.getKey().size() - 1), 0, 0);
        }
        for (Map.Entry<Ship.ShipType, List<Address>> entry : allShips.entrySet()) {
            for (int i = 0; i < entry.getKey().size(); i++) {
                instance.shoot(entry.getValue().get(i), 0, 0);
            }
        }
    }
    
    /**
     * Test of shoot method, of class Battleship.
     */
    @Test(expected = VerifyError.class)
    public void testShootOutsideBoard() {
        System.out.println("shootOutsideBoard");
        int shipCount = 0;
        Map<Ship.ShipType, List<Address>> allShips = new HashMap<>();
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                allShips.put(entry.getKey(), lstAddress);
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        instance.isReadyToPlay();
        boolean columnShootOutside = true;
        for (Map.Entry<Ship.ShipType, List<Address>> entry : allShips.entrySet()) {
            for (int i = 0; i < entry.getKey().size(); i++) {
                int column = entry.getValue().get(i).getColumn();
                int row = entry.getValue().get(i).getRow();
                if (columnShootOutside) {
                    column += Battleship.BoardSize.NORMAL.size();
                    columnShootOutside = false;
                } else {
                    row += Battleship.BoardSize.NORMAL.size();
                    columnShootOutside = true;
                }
                instance.shoot(new Address(column, row), 0, 0);
            }
        }
    }

    /**
     * Test of shoot method, of class Battleship.
     */
    @Test
    public void testShootFail() {
        System.out.println("shootFail");
        int shipCount = 0;
        Map<Ship.ShipType, List<Address>> allShips = new HashMap<>();
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                allShips.put(entry.getKey(), lstAddress);
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        instance.isReadyToPlay();
        shipCount = 0;
        for (Map.Entry<Ship.ShipType, List<Address>> entry : allShips.entrySet()) {
            int result;
            for (int i = 0; i < entry.getKey().size(); i++) {
                int rowAfter = entry.getKey().size() + i + entry.getValue().get(i).getRow();
                if(rowAfter < Battleship.BoardSize.NORMAL.size()) {
                    result = instance.shoot(new Address(shipCount, rowAfter), 0, 0);
                    assertEquals(result, Battleship.SHOOT_FAIL);
                }
            }
            shipCount++;
        }
    }

    /**
     * Test of allShipsDestroyed method, of class Battleship.
     */
    @Test
    public void testAllShipsDestroyed() {
        System.out.println("allShipsDestroyed");
        int shipCount = 0;
        Map<Ship.ShipType, List<Address>> allShips = new HashMap<>();
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                allShips.put(entry.getKey(), lstAddress);
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        instance.isReadyToPlay();
        for (Map.Entry<Ship.ShipType, List<Address>> entry : allShips.entrySet()) {
            for (int i = 0; i < entry.getKey().size() - 1; i++) {
                instance.shoot(entry.getValue().get(i), 0, 0);
            }
            instance.shoot(entry.getValue().get(entry.getKey().size() - 1), 0, 0);
        }
        assertTrue(instance.allShipsDestroyed());
    }

    /**
     * Test of allShipsDestroyed method, of class Battleship.
     */
    @Test
    public void testNotAllShipsDestroyed() {
        System.out.println("notAllShipsDestroyed");
        int shipCount = 0;
        Map<Ship.ShipType, List<Address>> allShips = new HashMap<>();
        Battleship instance = new Battleship(Battleship.BoardSize.NORMAL, Battleship.BattleshipGameType.TYPE_3);
        for (Map.Entry<Ship.ShipType, Integer> entry : instance.getShipsType().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                List<Address> lstAddress = new ArrayList<>();
                for (int j = 0; j < entry.getKey().size(); j++) {
                    lstAddress.add(new Address(shipCount, j));
                }
                allShips.put(entry.getKey(), lstAddress);
                Ship result = instance.addShip(entry.getKey(), lstAddress);
                assertNotNull(result);
                shipCount++;
            }
        }
        instance.isReadyToPlay();
        assertFalse(instance.allShipsDestroyed());
    }
}
