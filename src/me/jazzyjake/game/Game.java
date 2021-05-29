package me.jazzyjake.game;

import me.jazzyjake.clients.AIClient;
import me.jazzyjake.clients.Client;
import me.jazzyjake.events.TurnEventHandler;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.player.BluePlayer;
import me.jazzyjake.player.Player;
import me.jazzyjake.player.PlayerColor;
import me.jazzyjake.player.RedPlayer;

import java.util.Arrays;

public class Game {
	private TurnEventHandler turnHandler = new TurnEventHandler();

	private final Client redPlayer;
	private final Client bluePlayer;

	private Client attacker;
	private Client defender;

	private Client winner;

	public Game(Client client) {
		if (client.getPlayer() instanceof RedPlayer) {
			redPlayer = client;
			bluePlayer = new AIClient(new BluePlayer());

		} else {
			bluePlayer = client;
			redPlayer = new AIClient(new RedPlayer());
		}

		this.redPlayer.setGame(this);
		this.bluePlayer.setGame(this);

		turnHandler.addPropertyChangeListener(redPlayer);
		turnHandler.addPropertyChangeListener(bluePlayer);
	}

	public Game(Client client1, Client client2) {
		if (Math.random() > 0.5) {
			redPlayer = client1;
			bluePlayer = client2;
		} else {
			bluePlayer = client1;
			redPlayer = client2;
		}

		client1.setGame(this);
		client2.setGame(this);

		turnHandler.addPropertyChangeListener(redPlayer);
		turnHandler.addPropertyChangeListener(bluePlayer);
	}

	public MoveResponse fireShotAtDefender(int x, int y) {
		if (attacker.getPlayer().getFiredShots()[x][y] != null) {
			return MoveResponse.DUPLICATE_SHOT;
		}

		MoveResponse response = defender.getPlayer().checkShot(x, y);
		attacker.getPlayer().getFiredShots()[x][y] = response;

		if (response == MoveResponse.ALL_SHIPS_SUNK) {
			winner = attacker;
			System.out.println(winner.getPlayer().getClass().getSimpleName() + " is the winner!");
		}

		return response;
	}

	public void nextTurn() {
		// Runs the first time nextTurn is called to initialize attacker and defender fields
		if (attacker == null && defender == null) {
			if (Math.random() > 0.5) {
				attacker = this.redPlayer;
				defender = this.bluePlayer;
			} else {
				attacker = this.bluePlayer;
				defender = this.redPlayer;
			}
		} else {
			Client tempDefender = defender;
			defender = attacker;
			attacker = tempDefender;
		}

		turnHandler.signalNextTurn(attacker, defender);
	}

	public Client getFromPlayerColor(PlayerColor color) {
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

	public Client getRedPlayer() {
		return redPlayer;
	}

	public Client getBluePlayer() {
		return bluePlayer;
	}

	public Client getAttacker() {
		return attacker;
	}

	public Client getDefender() {
		return defender;
	}

	public Client getWinner() {
		return winner;
	}
}
