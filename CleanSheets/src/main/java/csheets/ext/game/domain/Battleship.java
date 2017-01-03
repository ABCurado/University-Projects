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

/**
 * Dummy class
 *
 * @author Rui Bento
 */
public class Battleship {

    public enum BoardSize {
        GIANT(15),
        BIG(12),
        NORMAL(10),
        SMALL(8);

        private final int size;   // line count (square board)

        BoardSize(int size) {
            this.size = size;
        }

        public int size() {
            return size;
        }
    }

    public enum BattleshipGameType {
        TYPE_1(new Ship.ShipType[]{Ship.ShipType.AircraftCarrier,
            Ship.ShipType.Battleship, Ship.ShipType.Submarine,
            Ship.ShipType.Destroyer, Ship.ShipType.PatrolBoat}),
        TYPE_2(new Ship.ShipType[]{Ship.ShipType.AircraftCarrier,
            Ship.ShipType.Battleship, Ship.ShipType.Submarine,
            Ship.ShipType.Destroyer, Ship.ShipType.SmallDestroyer}),
        TYPE_3(new Ship.ShipType[]{Ship.ShipType.AircraftCarrier,
            Ship.ShipType.Battleship, Ship.ShipType.Submarine,
            Ship.ShipType.Cruiser, Ship.ShipType.PatrolBoat}),
        TYPE_4(new Ship.ShipType[]{Ship.ShipType.AircraftCarrier,
            Ship.ShipType.Battleship, Ship.ShipType.Submarine,
            Ship.ShipType.Cruiser, Ship.ShipType.SmallDestroyer}),
        TYPE_5(new Ship.ShipType[]{Ship.ShipType.AircraftCarrier,
            Ship.ShipType.Battleship, Ship.ShipType.Cruiser,
            Ship.ShipType.SmallDestroyer, Ship.ShipType.SmallDestroyer,
            Ship.ShipType.SmallSubmarine, Ship.ShipType.SmallSubmarine});

        private int totalShipCount;
        private Map<Ship.ShipType, Integer> lstShipTypes;

        BattleshipGameType(Ship.ShipType... shipTypes) {
            lstShipTypes = new HashMap<Ship.ShipType, Integer>();
            totalShipCount = 0;
            for (Ship.ShipType shipType : shipTypes) {
                int num = lstShipTypes.containsKey(shipType) ? lstShipTypes.get(shipType) : 0;
                lstShipTypes.put(shipType, ++num);
                totalShipCount++;
            }
        }

        private int getMaxShipTypeNum(Ship.ShipType st) {
            return lstShipTypes.get(st);
        }
    }

    public static final int SHOOT_SINK = 1;
    public static final int SHOOT_HIT = 0;
    public static final int SHOOT_FAIL = -1;

    private Boolean[][] board;
    private BattleshipGameType gameType;
    private List<Ship> lstShips;

    public List<Ship> getShips() {
        return lstShips;
    }

    public Map<Ship.ShipType, Integer> getShipsType() {
        return gameType.lstShipTypes;
    }

    public Battleship(BoardSize boardSize, BattleshipGameType gameType) {
        this.board = new Boolean[boardSize.size][boardSize.size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = false;
            }
        }
        this.gameType = gameType;
        this.lstShips = new ArrayList<>();
    }

    public Ship addShip(Ship.ShipType shipType, List<Address> positions) {
        Ship newShip = new Ship(shipType);
        newShip.setLocation(positions);
        int shipTypeCounter = 0;
        for (Ship ship : lstShips) {
            if (ship.onTopOf(newShip)) {
                throw new VerifyError("Cannot override ship locations.");
            }
            if (ship.isShipType(shipType)) {
                shipTypeCounter++;
            }
        }
        if (gameType.getMaxShipTypeNum(shipType) <= shipTypeCounter) {
            throw new VerifyError("Exceded this ShipTypes for the selected game"
                    + " type.");
        }
        if (!lstShips.add(newShip)) {
            throw new VerifyError("Error adding newShip");
        }
        return newShip;
    }

    public boolean isReadyToPlay() {
        return lstShips.size() == gameType.totalShipCount;
    }

    public boolean allShipsDestroyed() {
        for (Ship ship : lstShips) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public int shoot(Address address, int marginColumn, int marginRow) {
        int column = address.getColumn() - marginColumn;
        int row = address.getRow() - marginRow;
        if (column < 0
                || column >= board.length
                || row < 0
                || row >= board[0].length) {
            throw new VerifyError("Shoot outside of the board.");
        }
        if (board[column][row]) {
            throw new VerifyError("Already shoot that location.");
        }
        board[column][row] = true;
        for (Ship ship : lstShips) {
            if (ship.hit(address)) {
                if (ship.isDestroyed()) {
                    return SHOOT_SINK;
                }
                return SHOOT_HIT;
            }
        }
        return SHOOT_FAIL;
    }

    /**
     * Battleship designation.
     *
     * @return "Battleship"
     */
    @Override
    public String toString() {
        return "Battleship";
    }

}
