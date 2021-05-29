package me.jazzyjake.player;

import java.util.ArrayList;
import java.util.Arrays;

import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.ShipGenerator;
import me.jazzyjake.ships.Ship;

public abstract class Player {
    private ArrayList<Ship> activeShips;
    private ArrayList<Ship> sunkShips = new ArrayList<>();
    private MoveResponse[][] firedShots = new MoveResponse[10][10];

    Player() {
        this.activeShips = ShipGenerator.generateRandomShips();
    }

    Player(ArrayList<Ship> ships) {
        this.activeShips = ships;
    }

    public MoveResponse checkShot(int x, int y) {
        int[] shot = {x+1, y+1};

        for (Ship ship : activeShips) {
            for (int[] coord : ship.getCoords()) {
                if (Arrays.equals(shot, coord)) {
                    ship.addHit(shot);

                    if (ship.isSunk()) {
                        activeShips.remove(ship);
                        sunkShips.add(ship);

                        if (activeShips.isEmpty()) return MoveResponse.ALL_SHIPS_SUNK;

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

    public MoveResponse[][] getFiredShots() {
        return firedShots;
    }
}
