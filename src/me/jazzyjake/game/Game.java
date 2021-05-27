package me.jazzyjake.game;

import me.jazzyjake.player.BluePlayer;
import me.jazzyjake.player.Player;
import me.jazzyjake.player.PlayerColor;
import me.jazzyjake.player.RedPlayer;

import java.util.Arrays;

public class Game {
	private RedPlayer redPlayer;
	private BluePlayer bluePlayer;

	private Player attacker;
	private Player defender;

	public Game() {
		this.redPlayer = new RedPlayer();
		this.bluePlayer = new BluePlayer();

		if (Math.random() > 0.5) {
			attacker = this.redPlayer;
			defender = this.bluePlayer;
		} else {
			attacker = this.bluePlayer;
			defender = this.redPlayer;
		}
	}

	public Game(Player player) {
		if (player instanceof RedPlayer) {
			System.out.println("RedPlayer Game constructor called!");
			this.redPlayer = (RedPlayer) player;
			this.bluePlayer = new BluePlayer();

		} else {
			System.out.println("BluePlayer Game constructor called!");
			this.bluePlayer = (BluePlayer) player;
			this.redPlayer = new RedPlayer();
		}

		if (Math.random() > 0.5) {
			attacker = this.redPlayer;
			defender = this.bluePlayer;
		} else {
			attacker = this.bluePlayer;
			defender = this.redPlayer;
		}
	}

	public MoveResponse fireShotAtDefender(int x, int y) {
		int[] shot = new int[] {x, y};

		for (int[] coord : attacker.getFiredShots()) {
			if (Arrays.equals(coord, shot)) return MoveResponse.DUPLICATE_SHOT;
		}

		attacker.getFiredShots().add(shot);

		MoveResponse response = defender.checkShot(x, y);

		// For testing purposes the attacker will remain constant
		// nextTurn();

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

	public void setAttacker(PlayerColor color) {
		switch (color) {
			case RED:
				attacker = redPlayer;
				defender = bluePlayer;
				return;
			case BLUE:
				attacker = bluePlayer;
				defender = redPlayer;
				return;
		}
	}

	public void setDefender(PlayerColor color) {
		switch (color) {
			case RED:
				defender = redPlayer;
				attacker = bluePlayer;
				return;
			case BLUE:
				defender = bluePlayer;
				attacker = redPlayer;
				return;
		}
	}

	public Player getAttacker() {
		return attacker;
	}

	public Player getDefender() {
		return defender;
	}
}
