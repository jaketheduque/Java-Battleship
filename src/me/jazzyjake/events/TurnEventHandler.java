package me.jazzyjake.events;

import me.jazzyjake.player.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TurnEventHandler {
    private Player currentAttacker;
    private Player currentDefender;
    private PropertyChangeSupport support;

    public TurnEventHandler() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void signalNextTurn(Player newAttacker, Player newDefender) {
        support.firePropertyChange("currentAttacker", newDefender, newAttacker);
    }
}
