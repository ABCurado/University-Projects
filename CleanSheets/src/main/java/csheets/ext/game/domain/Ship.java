package csheets.ext.game.domain;

import csheets.core.Address;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rui Bento
 */
public class Ship {

    public enum ShipType {
        AircraftCarrier("Aircraft carrier", null, 5),
        Battleship("Battleship", null, 4),
        Submarine("Submarine", null, 3),
        Cruiser("Cruiser", null, 3),
        Destroyer("Destroyer", null, 3),
        PatrolBoat("Patrol boat", null, 2),
        SmallDestroyer("Destroyer", null, 2),
        SmallSubmarine("Submarine", null, 1);

        private final String name;   // name
        private final byte[] icon;   // image
        private final int size;   // size (lenght of the shiptype)

        ShipType(String name, byte[] icon, int size) {
            this.name = name;
            this.icon = icon;
            this.size = size;
        }

        public boolean isSameType(ShipType st) {
            if (name.equals(st.name)
                    //&& icon.equals(st.icon)
                    && size == st.size) {
                return true;
            }
            return false;
        }

        public int size() {
            return size;
        }
    }

    private ShipType shipType;
    private List<Address> locations;
    private List<Address> hits;

    public Ship(ShipType st) {
        if (st.name == null || st.name.equals("")) {
            throw new IllegalArgumentException("Incorrect ship name");
        }
        if (st.size <= 0) {
            throw new IllegalArgumentException("Incorrect ship name");
        }
        this.shipType = st;
        this.locations = new ArrayList<>();
        this.hits = new ArrayList<>();
    }

    public String name() {
        return shipType.name;
    }

    public byte[] icon() {
        return shipType.icon;
    }

    public int size() {
        return shipType.size;
    }

    public boolean isShipType(ShipType st) {
        return st != null && shipType.isSameType(st);
    }

    public List<Address> getPositions() {
        return locations;
    }

    public boolean setLocation(List<Address> positions) {
        if (positions.size() == 0 || positions.size() != shipType.size) {
            throw new IllegalArgumentException("The ship must have " + shipType.size + " size.");
        }
        int column = positions.get(0).getColumn();
        int row = positions.get(0).getRow();
        int columnCount = 0;
        int rowCount = 0;
        for (Address position : positions) {
            if (position == null) {
                throw new NullPointerException("The location cannot contain null.");
            }
            locations.add(position);
            if (position.getColumn() == column) {
                columnCount++;
            }
            if (position.getRow() == row) {
                rowCount++;
            }
        }
        if (columnCount > 1 && rowCount > 1
                || positions.size() > 1 && columnCount <= 1 && rowCount <= 1) {
            locations.clear();
            throw new IllegalArgumentException("The ship must be in line.");
        }
        return true;
    }

    protected boolean hasLocation() {
        if (locations == null || locations.size() == 0) {
            return false;
        }
        return true;
    }

    public boolean onTopOf(Ship s) {
        for (Address location : s.locations) {
            if (locations.contains(location)) {
                return true;
            }
        }
        return false;
    }

    public boolean hit(Address hit) {
        hasLocation();
        if (hits.contains(hit)) {
            throw new VerifyError("Location already hitted.");
        }
        if (locations.contains(hit)) {
            hits.add(hit);
            return true;
        }
        return false;
    }

    public boolean isDestroyed() {
        return hits.size() == shipType.size;
    }

    @Override
    public String toString() {
        return shipType.name;
    }
}
