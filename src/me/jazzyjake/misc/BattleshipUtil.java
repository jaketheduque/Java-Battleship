package me.jazzyjake.misc;

import java.util.LinkedHashSet;
import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.ships.Ship;

import java.util.ArrayList;

public class BattleshipUtil {
	public static int random(int min, int max) {
		return (int) Math.round((Math.random() * (max - min)) + min);
	}
	
	public static int random() {
		return (int) Math.round((Math.random() * (10 - 1)) + 1);
	}
	
	public static boolean containsCoord(LinkedHashSet<int[]> occupied, int[][] coords) {
		for (int[] coord : occupied) {
			for (int[] shipCoord : coords) {
				if (coord[0] == shipCoord[0] && coord[1] == shipCoord[1]) return true;
			}
		}
		return false;
	}

	public static void printShips(ArrayList<Ship> ships) {
		char[][] grid = new char[10][10];

		for (int y = 0 ; y < 10 ; y++) {
			for (int x = 0 ; x < 10 ; x++) {
				grid[x][y] = 'x';
			}
		}

		for (Ship ship : ships) {
			char shipChar = ship.getClass().getSimpleName().charAt(0);

			for (int[] coord : ship.getCoords()) {
				int x = coord[0] - 1;
				int y = coord[1] - 1;

				grid[x][y] = shipChar;
			}
		}

		System.out.println();
		System.out.println("Your ships:");
		for (int y = 0 ; y < 10 ; y++) {
			for (int x = 0 ; x < 10 ; x++) {
				System.out.print(grid[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printShots(MoveResponse[][] shots) {
		char[][] grid = new char[10][10];

		for (int y = 0 ; y < 10 ; y++) {
			for (int x = 0 ; x < 10 ; x++) {
				if (shots[x][y] != null) {
					grid[x][y] = shots[x][y].name().charAt(0);
				} else {
					grid[x][y] = 'x';
				}
			}
		}

		System.out.println();
		System.out.println("Your shots:");
		for (int y = 0 ; y < 10 ; y++) {
			for (int x = 0 ; x < 10 ; x++) {
				System.out.print(grid[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
