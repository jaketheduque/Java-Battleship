package me.jazzyjake.misc;

import java.util.HashSet;

public class BattleshipUtil {
	public static int random(int min, int max) {
		return (int) Math.round((Math.random() * (max - min)) + min);
	}
	
	public static int random() {
		return (int) Math.round((Math.random() * (10 - 1)) + 1);
	}
	
	public static boolean containsCoord(HashSet<int[]> occupied, int[][] coords) {
		for (int[] coord : occupied) {
			for (int[] shipCoord : coords) {
				if (coord[0] == shipCoord[0] && coord[1] == shipCoord[1]) return true;
			}
		}
		return false;
	}
}
