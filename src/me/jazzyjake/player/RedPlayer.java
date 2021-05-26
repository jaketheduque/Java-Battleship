package me.jazzyjake.player;

import me.jazzyjake.ships.Ship;

import java.awt.*;
import java.util.ArrayList;

public class RedPlayer extends Player {
    public RedPlayer() {
        super();
    }

    public RedPlayer(ArrayList<Ship> ships) {
        super(ships);
    }

    @Override
    public PlayerColor getColor() {
        return PlayerColor.RED;
    }
}
