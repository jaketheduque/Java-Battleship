package me.jazzyjake.events;

import me.jazzyjake.clients.Client;
import me.jazzyjake.player.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TurnEventHandler {
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

    public void signalNextTurn(Client newAttacker, Client newDefender) {
        support.firePropertyChange("currentAttacker", newDefender, newAttacker);
    }
}
