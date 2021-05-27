package me.jazzyjake.application;

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
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Choose your player color: (RED, BLUE)");
        PlayerColor color = PlayerColor.valueOf(scan.nextLine().toUpperCase());

        ArrayList<Ship> ships = ShipGenerator.getShipsFromConsoleInput();

        Player player = (Player) color.getPlayerClass().getConstructor(ArrayList.class).newInstance(ships);

        Game game = new Game(player);

        if (game.getAttacker() != player) game.nextTurn();

        game.getDefender().getShips().forEach(System.out::println);

        while (game.getDefender().getShips().size() != 0) {
            System.out.println("Enter shot coordinate: (x, y)");

            String[] params = scan.nextLine().split(",");

            int x = Integer.parseInt(params[0].trim());
            int y = Integer.parseInt(params[1].trim());

            MoveResponse response = game.fireShotAtDefender(x, y);

            System.out.println(response);
        }

        System.out.println("You won!");
    }
}