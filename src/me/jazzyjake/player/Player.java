package me.jazzyjake.player;

import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.ShipGenerator;
import me.jazzyjake.ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public abstract class Player {
    private ArrayList<Ship> activeShips;
    private ArrayList<Ship> sunkShips = new ArrayList<>();
    private LinkedHashSet<int[]> firedShots = new LinkedHashSet<>();

    Player() {
        this.activeShips = ShipGenerator.generateRandomShips();
    }

    Player(ArrayList<Ship> ships) {
        this.activeShips = ships;
    }

    public MoveResponse checkShot(int x, int y) {
        int[] shot = {x, y};

        for (Ship ship : activeShips) {
            for (int[] coord : ship.getCoords()) {
                if (Arrays.equals(shot, coord)) {
                    ship.addHit(shot);

                    if (ship.isSunk()) {
                        activeShips.remove(ship);
                        sunkShips.add(ship);

                        return MoveResponse.SHIP_SUNK;
                    }

                    return MoveResponse.HIT;
                }
            }
        }

        return MoveResponse.MISS;
    }

    public abstract PlayerColor getColor();

    public ArrayList<Ship> getShips() {
        return activeShips;
    }

    public LinkedHashSet<int[]> getFiredShots() {
        return firedShots;
    }
}
