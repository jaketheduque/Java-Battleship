package me.jazzyjake.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.ShipGenerator;
import me.jazzyjake.ships.Ship;

public abstract class Player {
    private ArrayList<Ship> ships;
    private HashSet<int[]> firedShots = new HashSet<>();

    Player() {
        this.ships = ShipGenerator.generateRandomShips();
    }

    Player(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public MoveResponse checkShot(int x, int y) {
        int[] shot = {x, y};

        for (Ship ship : ships) {
            for (int[] coord : ship.getCoords()) {
                if (Arrays.equals(shot, coord)) return MoveResponse.HIT;
            }
        }

        return MoveResponse.MISS;
    }

    public abstract PlayerColor getColor();

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public HashSet<int[]> getFiredShots() {
        return firedShots;
    }
}
