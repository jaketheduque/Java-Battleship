package me.jazzyjake.misc;

import java.util.ArrayList;
import java.util.HashSet;

import me.jazzyjake.ships.Battleship;
import me.jazzyjake.ships.Carrier;
import me.jazzyjake.ships.Cruiser;
import me.jazzyjake.ships.Destroyer;
import me.jazzyjake.ships.Ship;
import me.jazzyjake.ships.Submarine;

public class BattleshipGenerator {
	public static ArrayList<Ship> generateShips() {
		ArrayList<Ship> ships = new ArrayList<>();
		HashSet<int[]> occupied = new HashSet<>();

		// Adds a Carrier
		Carrier carrier = Carrier.random();
		ships.add(carrier);
		for (int[] coord : carrier.getCoords())
			occupied.add(coord);

		// Adds a Battleship
		Battleship battleship = Battleship.random();

		while (true) {
			if (BattleshipUtil.containsCoord(occupied, battleship.getCoords())) {
				battleship = Battleship.random();
			} else {
				break;
			}
		}

		ships.add(battleship);
		for (int[] coord : battleship.getCoords())
			occupied.add(coord);

		// Adds a Cruiser
		Cruiser cruiser = Cruiser.random();

		while (true) {
			if (BattleshipUtil.containsCoord(occupied, cruiser.getCoords())) {
				cruiser = Cruiser.random();
			} else {
				break;
			}
		}

		ships.add(cruiser);
		for (int[] coord : cruiser.getCoords())
			occupied.add(coord);

		// Adds a Submarine
		Submarine submarine = Submarine.random();

		while (true) {
			if (BattleshipUtil.containsCoord(occupied, submarine.getCoords())) {
				submarine = Submarine.random();
			} else {
				break;
			}
		}

		ships.add(submarine);
		for (int[] coord : submarine.getCoords())
			occupied.add(coord);

		// Adds a Destroyer
		Destroyer destroyer = Destroyer.random();

		while (true) {
			if (BattleshipUtil.containsCoord(occupied, destroyer.getCoords())) {
				destroyer = Destroyer.random();
			} else {
				break;
			}
		}

		ships.add(destroyer);

		return ships;
	}
}
