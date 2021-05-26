package me.jazzyjake.player;

import me.jazzyjake.ships.Ship;

import java.awt.*;
import java.util.ArrayList;

public class BluePlayer extends Player {
    public BluePlayer() {}

    public BluePlayer(ArrayList<Ship> ships) {
        super(ships);
    }

    @Override
    public PlayerColor getColor() {
        return PlayerColor.BLUE;
    }
}
