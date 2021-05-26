package me.jazzyjake.exceptions;

import me.jazzyjake.misc.Direction;

public class InvalidOriginException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidOriginException(int x, int y, Direction direction) {
		super("Invalid origin point: [" + x + ", " + y + "] Facing: " + direction);
	}
}
