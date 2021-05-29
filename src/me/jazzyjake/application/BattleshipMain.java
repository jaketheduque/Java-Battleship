package me.jazzyjake.application;

import me.jazzyjake.clients.CommandLineClient;
import me.jazzyjake.game.Game;
import me.jazzyjake.game.MoveResponse;
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
        PlayerColor color = PlayerColor.valueOf(scan.nextLine().toUpperCase());

        ArrayList<Ship> ships = ShipGenerator.generateRandomShips();

        Player player = (Player) color.getPlayerClass().getConstructor(ArrayList.class).newInstance(ships);
        CommandLineClient client = new CommandLineClient(player);

        Game game = new Game(client);

        for (int i = 0 ; i < 5 ; i++) {
            game.nextTurn();
            Thread.sleep(1000L);
        }
    }
}