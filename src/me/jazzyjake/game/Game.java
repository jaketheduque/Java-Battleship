package me.jazzyjake.game;

import me.jazzyjake.player.BluePlayer;
import me.jazzyjake.player.Player;
import me.jazzyjake.player.PlayerColor;
import me.jazzyjake.player.RedPlayer;

import java.util.Arrays;

public class Game {
	private RedPlayer redPlayer = new RedPlayer();
	private BluePlayer bluePlayer = new BluePlayer();

	private Player attacker;
	private Player defender;

	public Game() {
		if (Math.random() > 0.5) {
			attacker = redPlayer;
			defender = bluePlayer;
		} else {
			attacker = bluePlayer;
			defender = redPlayer;
		}
	}

	public MoveResponse fireShotAtDefender(int x, int y) {
		int[] shot = new int[] {x, y};

		for (int[] coord : attacker.getFiredShots()) {
			if (Arrays.equals(coord, shot)) return MoveResponse.DUPLICATE_SHOT;
		}

		attacker.getFiredShots().add(shot);

		MoveResponse response = defender.checkShot(x, y);

		nextTurn();

		return response;
	}

	public void nextTurn() {
		Player tempDefender = defender;
		defender = attacker;
		attacker = tempDefender;
	}

	public Player getFromPlayerColor(PlayerColor color) {
		switch(color) {
			case RED:
				return redPlayer;
			case BLUE:
				return bluePlayer;
		}
		return null;
	}

	public Player getAttacker() {
		return attacker;
	}

	public Player getDefender() {
		return defender;
	}
}
