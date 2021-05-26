package me.jazzyjake.player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import me.jazzyjake.misc.BattleshipGenerator;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.ships.Ship;

public abstract class Player {
    private ArrayList<Ship> ships;
    private HashSet<int[]> firedShots = new HashSet<>();

    Player() {
        this.ships = BattleshipGenerator.generateShips();
    }

    public boolean checkShot(int x, int y) {
        int[] shot = {x, y};

        for (Ship ship : ships) {
            for (int[] coord : ship.getCoords()) {
                if (Arrays.equals(shot, coord)) return true;
            }
        }

        return false;
    }

    public abstract PlayerColor getColor();

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public HashSet<int[]> getFiredShots() {
        return firedShots;
    }
}
