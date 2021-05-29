package me.jazzyjake.clients;

import me.jazzyjake.player.Player;

import java.beans.PropertyChangeEvent;

public class CommandLineClient extends Client {
    public CommandLineClient(Player player) {
        super(player);
    }

    @Override
    public void onNextTurn(Client newAttacker, Client oldAttacker) {
        // If the new attacker is this client
        if (newAttacker.getPlayer() == player) {
            System.out.println("You're attacking!");
        } else {
            System.out.println("You're defending!");
        }
    }
}
