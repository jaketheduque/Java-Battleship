package me.jazzyjake.game;

import me.jazzyjake.clients.Client;
import me.jazzyjake.events.TurnEventHandler;
import me.jazzyjake.player.BluePlayer;
import me.jazzyjake.player.Player;
import me.jazzyjake.player.PlayerColor;
import me.jazzyjake.player.RedPlayer;

import java.util.Arrays;

public class Game {
	private TurnEventHandler turnHandler = new TurnEventHandler();

	private final RedPlayer redPlayer;
	private final BluePlayer bluePlayer;

	private Player attacker;
	private Player defender;

	public Game(Client client) {
		if (client.getPlayer() instanceof RedPlayer) {
			this.redPlayer = (RedPlayer) client.getPlayer();
			this.bluePlayer = new BluePlayer();

		} else {
			this.bluePlayer = (BluePlayer) client.getPlayer();
			this.redPlayer = new RedPlayer();
		}

		if (Math.random() > 0.5) {
			attacker = this.redPlayer;
			defender = this.bluePlayer;
		} else {
			attacker = this.bluePlayer;
			defender = this.redPlayer;
		}

		client.getPlayer().setGame(this);

		turnHandler.addPropertyChangeListener(client);
	}

	public Game(Client client1, Client client2) {
		if (Math.random() > 0.5) {
			redPlayer = (RedPlayer) client1.getPlayer();
			bluePlayer = (BluePlayer) client2.getPlayer();

			attacker = this.redPlayer;
			defender = this.bluePlayer;
		} else {
			bluePlayer = (BluePlayer) client1.getPlayer();
			redPlayer = (RedPlayer) client2.getPlayer();

			attacker = this.bluePlayer;
			defender = this.redPlayer;
		}

		client1.getPlayer().setGame(this);
		client2.getPlayer().setGame(this);

		turnHandler.addPropertyChangeListener(client1);
		turnHandler.addPropertyChangeListener(client2);
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

		turnHandler.signalNextTurn(attacker, defender);
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
