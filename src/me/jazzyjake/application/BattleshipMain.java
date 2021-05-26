package me.jazzyjake.application;

import me.jazzyjake.game.Game;
import me.jazzyjake.player.BluePlayer;
import me.jazzyjake.player.PlayerColor;
import me.jazzyjake.player.RedPlayer;
import me.jazzyjake.ships.Ship;

public class BattleshipMain {
	public static void main(String[] args) {
		Game b = new Game();
		RedPlayer redPlayer = (RedPlayer) b.getFromPlayerColor(PlayerColor.RED);
		BluePlayer bluePlayer = (BluePlayer) b.getFromPlayerColor(PlayerColor.BLUE);

		System.out.println("Red Ships:");
		for (Ship ship : redPlayer.getShips()) {
			System.out.println(ship);
		}

		System.out.println("\nBlue Ships:");
		for (Ship ship : bluePlayer.getShips()) {
			System.out.println(ship);
		}
	}
}
