package me.jazzyjake.exceptions;

import me.jazzyjake.ships.Direction;

public class InvalidOriginException extends RuntimeException {
	public enum InvalidOriginCause {
		OUT_OF_BOUNDS, OVERLAPPING_SPACE
	}

	private static final long serialVersionUID = 1L;
	private InvalidOriginCause cause;
	
	public InvalidOriginException(InvalidOriginCause cause, int x, int y, Direction direction) {
		super("Invalid origin point: Cause: " + cause + " Coords: [" + x + ", " + y + "] Facing: " + direction);

		this.cause = cause;
	}

	public InvalidOriginCause getInvalidOriginCause() {
		return cause;
	}
}
