/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.ext.game.domain;

import csheets.core.Address;
import java.util.ArrayList;
import java.util.List;
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
public class ShipTest {

    private int smallShip = 2;
    private int bigShip = 5;
    private List<Address> lstSmallShipRightAddress;
    private List<Address> lstSmallShipWrongAddress;
    private List<Address> lstBigShipRightAddress;
    private List<Address> lstBigShipWrongAddress;

    public ShipTest() {
        lstSmallShipRightAddress = new ArrayList<>();
        for (int i = 0; i < smallShip; i++) {
            lstSmallShipRightAddress.add(new Address(0, i));
        }

        lstSmallShipWrongAddress = new ArrayList<>();
        for (int i = 0; i < smallShip; i++) {
            lstSmallShipWrongAddress.add(new Address(i, i));
        }

        lstBigShipRightAddress = new ArrayList<>();
        for (int i = 0; i < bigShip; i++) {
            lstBigShipRightAddress.add(new Address(0, i));
        }

        lstBigShipWrongAddress = new ArrayList<>();
        for (int i = 0; i < bigShip; i++) {
            if (i > 2) {
                lstBigShipWrongAddress.add(new Address(1, i));
                continue;
            }
            lstBigShipWrongAddress.add(new Address(0, i));
        }
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
     * Test of isShipType method, of class Ship.
     */
    @Test
    public void testIsShipType() {
        System.out.println("isShipType");
        Ship.ShipType st = Ship.ShipType.AircraftCarrier;
        Ship instance = null;
        instance = new Ship(st);
        boolean result = instance.isShipType(Ship.ShipType.AircraftCarrier);
        assertTrue(result);

        st = Ship.ShipType.Battleship;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.Battleship);
        assertTrue(result);

        st = Ship.ShipType.Cruiser;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.Cruiser);
        assertTrue(result);

        st = Ship.ShipType.Destroyer;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.Destroyer);
        assertTrue(result);

        st = Ship.ShipType.PatrolBoat;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.PatrolBoat);
        assertTrue(result);

        st = Ship.ShipType.SmallDestroyer;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.SmallDestroyer);
        assertTrue(result);

        st = Ship.ShipType.SmallSubmarine;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.SmallSubmarine);
        assertTrue(result);

        st = Ship.ShipType.Submarine;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.Submarine);
        assertTrue(result);
    }

    /**
     * Test of isShipType method, of class Ship.
     */
    @Test
    public void testIsNotShipType() {
        System.out.println("isNotShipType");
        Ship.ShipType st = Ship.ShipType.AircraftCarrier;
        Ship instance = new Ship(st);
        boolean result = instance.isShipType(Ship.ShipType.Battleship);
        assertFalse(result);

        st = Ship.ShipType.PatrolBoat;
        instance = new Ship(st);
        result = instance.isShipType(Ship.ShipType.AircraftCarrier);
        assertFalse(result);

        st = Ship.ShipType.PatrolBoat;
        instance = new Ship(st);
        result = instance.isShipType(null);
        assertFalse(result);
    }

    /**
     * Test of setLocation method, of class Ship.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        List<Address> positions = lstSmallShipRightAddress;
        Ship instance = new Ship(Ship.ShipType.SmallDestroyer);
        boolean result = instance.setLocation(positions);
        assertTrue(result);

        positions = lstBigShipRightAddress;
        instance = new Ship(Ship.ShipType.AircraftCarrier);
        result = instance.setLocation(positions);
        assertTrue(result);
    }

    /**
     * Test of setLocation method, of class Ship.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetLocationWrongAddressSize() {
        System.out.println("setLocationWrongAddressSize");
        List<Address> positions = lstSmallShipRightAddress;
        Ship instance = new Ship(Ship.ShipType.SmallSubmarine);
        boolean result = instance.setLocation(positions);

        positions = lstBigShipRightAddress;
        instance = new Ship(Ship.ShipType.Cruiser);
        result = instance.setLocation(positions);
    }

    /**
     * Test of setLocation method, of class Ship.
     */
    @Test(expected = NullPointerException.class)
    public void testSetLocationCannotContainNull() {
        System.out.println("setLocationCannotContainNull");
        List<Address> positions = lstSmallShipRightAddress;
        positions.remove(0);
        positions.add(null);
        Ship instance = new Ship(Ship.ShipType.SmallDestroyer);
        boolean result = instance.setLocation(positions);

        positions = lstBigShipRightAddress;
        positions.remove(0);
        positions.add(null);
        instance = new Ship(Ship.ShipType.AircraftCarrier);
        result = instance.setLocation(positions);
    }

    /**
     * Test of setLocation method, of class Ship.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetLocationWrongWhenAddressMustBeInLine() {
        System.out.println("setLocationWrongWhenAddressMustBeInLine");
        List<Address> positions = lstSmallShipWrongAddress;
        Ship instance = new Ship(Ship.ShipType.SmallDestroyer);
        boolean result = instance.setLocation(positions);

        positions = lstBigShipWrongAddress;
        instance = new Ship(Ship.ShipType.AircraftCarrier);
        result = instance.setLocation(positions);
    }

    /**
     * Test of hasLocation method, of class Ship.
     */
    @Test
    public void testHasLocation() {
        System.out.println("hasLocation");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        instance.setLocation(lstBigShipRightAddress);
        boolean result = instance.hasLocation();
        assertTrue(result);
    }

    /**
     * Test of hasLocation method, of class Ship.
     */
    @Test
    public void testDoesntHasLocation() {
        System.out.println("doesntHasLocation");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        boolean result = instance.hasLocation();
        assertFalse(result);
    }

    /**
     * Test of onTopOf method, of class Ship.
     */
    @Test
    public void testOnTopOf() {
        System.out.println("onTopOf");
        Ship s = new Ship(Ship.ShipType.AircraftCarrier);
        s.setLocation(lstBigShipRightAddress);
        Ship instance = new Ship(Ship.ShipType.SmallDestroyer);
        instance.setLocation(lstSmallShipRightAddress);
        boolean result = instance.onTopOf(s);
        assertTrue(result);
    }

    /**
     * Test of onTopOf method, of class Ship.
     */
    @Test
    public void testNotOnTopOf() {
        System.out.println("onTopOf");
        Ship s = new Ship(Ship.ShipType.AircraftCarrier);
        s.setLocation(lstBigShipRightAddress);
        Ship instance = new Ship(Ship.ShipType.SmallDestroyer);
        List<Address> differentLocation = new ArrayList<>();
        for (int i = 0; i < smallShip; i++) {
            differentLocation.add(new Address(3, i));
        }
        instance.setLocation(differentLocation);
        boolean result = instance.onTopOf(s);
        assertFalse(result);
    }

    /**
     * Test of hit method, of class Ship.
     */
    @Test
    public void testHit() {
        System.out.println("hit");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        instance.setLocation(lstBigShipRightAddress);
        for (int i = 0; i < bigShip; i++) {
            boolean result = instance.hit(new Address(0, i));
            assertTrue(result);
        }
    }

    /**
     * Test of hit method, of class Ship.
     */
    @Test
    public void testHitFail() {
        System.out.println("hitFail");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        instance.setLocation(lstBigShipRightAddress);
        for (int i = 0; i < bigShip; i++) {
            boolean result = instance.hit(new Address(2, i));
            assertFalse(result);
        }
    }

    /**
     * Test of hit method, of class Ship.
     */
    @Test(expected = VerifyError.class)
    public void testHitSamePlace() {
        System.out.println("hitSamePlace");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        instance.setLocation(lstBigShipRightAddress);
        for (int i = 0; i < bigShip; i++) {
            boolean result = instance.hit(new Address(0, i));
            result = instance.hit(new Address(0, i));
        }
    }

    /**
     * Test of isDestroyed method, of class Ship.
     */
    @Test
    public void testIsDestroyed() {
        System.out.println("isDestroyed");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        instance.setLocation(lstBigShipRightAddress);
        for (int i = 0; i < bigShip; i++) {
            instance.hit(new Address(0, i));
        }
        boolean result = instance.isDestroyed();
        assertTrue(result);
    }

    /**
     * Test of isDestroyed method, of class Ship.
     */
    @Test
    public void testIsNotDestroyed() {
        System.out.println("isNotDestroyed");
        Ship instance = new Ship(Ship.ShipType.AircraftCarrier);
        instance.setLocation(lstBigShipRightAddress);
        boolean result = instance.isDestroyed();
        assertFalse(result);
    }

}
