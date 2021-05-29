package me.jazzyjake.clients;

import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.player.Player;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.Scanner;

public class CommandLineClient extends Client {
    public CommandLineClient(Player player) {
        super(player);
    }

    @Override
    public void onNextTurn(Client newAttacker, Client oldAttacker) {
        // If the new attacker is this client
        if (newAttacker == this) {
            System.out.println(player.getClass().getSimpleName() + " player is attacking!");

            Scanner scan = new Scanner(System.in);
            MoveResponse response = null;

            BattleshipUtil.printShots(player.getFiredShots());

            while (response == MoveResponse.DUPLICATE_SHOT || response == null) {
                System.out.println("Enter shot coordinates: (x, y)");
                String[] params = scan.nextLine().split(",");

                int x = Integer.parseInt(params[0].trim()) - 1;
                int y = Integer.parseInt(params[1].trim()) - 1;

                if (x < 0 || y < 0 || x > 10 || y > 10) {
                    System.out.println("Invalid coordinates! Please try again.");
                    continue;
                }

                response = game.fireShotAtDefender(x, y);

                if (response == MoveResponse.DUPLICATE_SHOT) {
                    System.out.println("Duplicate shot! Please try again.");
                    response = null;
                    continue;
                }
            }

            System.out.println("Shot response: " + response);

            System.out.println("Press enter to continue!");
            scan.nextLine();

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
