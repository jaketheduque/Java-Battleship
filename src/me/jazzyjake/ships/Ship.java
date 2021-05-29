package me.jazzyjake.ships;

import me.jazzyjake.exceptions.InvalidOriginException;

import java.util.Arrays;
import java.util.LinkedHashSet;

public abstract class Ship {
	public static final Class[] SHIP_TYPES = {Carrier.class, Battleship.class, Cruiser.class, Submarine.class, PatrolBoat.class};
	private final Direction direction;
	private final int[][] coords;
	private LinkedHashSet<int[]> hits = new LinkedHashSet<>();
	private boolean sunk;

	Ship(int x, int y, Direction direction) throws InvalidOriginException {
		if (!originValid(x, y, direction)) throw new InvalidOriginException(InvalidOriginException.InvalidOriginCause.OUT_OF_BOUNDS, x, y, direction);
		this.direction = direction;
		this.coords = fillCoords(x, y, direction);
	}
	
	@Override
	public String toString() {
		Class<? extends Ship> c = getClass();
		
		return c.getSimpleName() + "[Origin: " + coords[0][0] + ", " + coords[0][1] + " Direction: " + direction + "]";
	}

	public Direction getDirection() {
		return direction;
	}

	public int[][] getCoords() {
		return coords;
	}

	public int[] getOrigin() {
		return coords[0];
	}
	
	abstract boolean originValid(int x, int y, Direction direction);

	// Used to fill out the rest of the ship coordinates based off of the origin
	// point
	abstract int[][] fillCoords(int x, int y, Direction direction);

	public LinkedHashSet<int[]> getHits() {
		return hits;
	}

	public boolean addHit(int[] hit) {
		boolean matchFound = false;

		// Checks to see if the requested hit is a actual ship coordinate
		for (int[] coord : coords) {
			if (Arrays.equals(coord, hit)) {
				matchFound = true;
				break;
			}
		}

		boolean existing = false;
		
		// Checks to see if the requested hit has already been added
		for (int[] coord : hits) {
			if (Arrays.equals(coord, hit)) {
				existing = true;
				break;
			}
		}

		// Returns false if the hit already exists or if there is no matching ship
		// coordinate
		if (!matchFound || existing) {
			return false;
		}

		// Adds the hit
		hits.add(hit);

		// Checks to see if the hits size matches the coords length making the ship sunk
		if (hits.size() == coords.length) {
			sunk = true;
		}

		return true;
	}

	public boolean isSunk() {
		return sunk;
	}
}
