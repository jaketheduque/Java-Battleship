package me.jazzyjake.application;

import me.jazzyjake.clients.CommandLineClient;
import me.jazzyjake.game.Game;
import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.misc.ShipGenerator;
import me.jazzyjake.player.Player;
import me.jazzyjake.player.PlayerColor;
import me.jazzyjake.player.RedPlayer;
import me.jazzyjake.ships.Ship;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleshipMain {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Choose your player color: (RED, BLUE)");
        PlayerColor color1 = PlayerColor.valueOf(scan.nextLine().toUpperCase());

        Player player1 = (Player) color1.getPlayerClass().getConstructor(ArrayList.class).newInstance(ShipGenerator.generateRandomShips());
        CommandLineClient client1 = new CommandLineClient(player1);

        PlayerColor color2 = color1 == PlayerColor.RED ? PlayerColor.BLUE : PlayerColor.RED;

        Player player2 = (Player) color2.getPlayerClass().getConstructor(ArrayList.class).newInstance(ShipGenerator.generateRandomShips());
        CommandLineClient client2 = new CommandLineClient(player2);

        Game game = new Game(client1, client2);
        System.out.println();

        while (game.getWinner() == null) {
            game.nextTurn();
        }

        System.out.println(game.getWinner().getPlayer().getClass().getSimpleName() + " is the winner!");
    }
}