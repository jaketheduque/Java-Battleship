package me.jazzyjake.clients;

import me.jazzyjake.player.Player;

import java.beans.PropertyChangeEvent;

public class CommandLineClient extends Client {
    public CommandLineClient(Player player) {
        super(player);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // If the new attacker is this client
        if (evt.getNewValue() == player) {
            System.out.println("Your turn!");
        }
    }
}
