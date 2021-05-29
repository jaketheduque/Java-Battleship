package me.jazzyjake.clients;

import me.jazzyjake.game.Game;
import me.jazzyjake.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class Client implements PropertyChangeListener {
    Game game;
    Player player;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        onNextTurn((Client) evt.getNewValue(), (Client) evt.getOldValue());
    }

    public abstract void onNextTurn(Client newAttacker, Client oldAttacker);

    Client(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }
}
