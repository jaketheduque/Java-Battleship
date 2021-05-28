package me.jazzyjake.clients;

import me.jazzyjake.player.Player;

import java.beans.PropertyChangeListener;

public abstract class Client implements PropertyChangeListener {
    Player player;

    public Client(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
