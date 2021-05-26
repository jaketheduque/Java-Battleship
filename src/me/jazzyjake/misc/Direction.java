package me.jazzyjake.misc;

public enum Direction {
	RIGHT, DOWN;
	
	public static Direction randomDirection() {
		double random = Math.random();

		if (random < 0.5) {
			return Direction.RIGHT;
		} else {
			return Direction.DOWN;
		}
	}
}