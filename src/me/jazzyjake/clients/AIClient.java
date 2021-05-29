package me.jazzyjake.clients;

import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.player.Player;

public class AIClient extends Client {
    private int[] hitOrigin;
    private int[] lastTriedMove;
    private int[] nextMove;
    private MoveResponse lastTriedMoveResponse;
    private Direction currentDirection;

    private enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    public AIClient(Player player) {
        super(player);
    }

    @Override
    public void onNextTurn(Client newAttacker, Client oldAttacker) {
        if (newAttacker.getPlayer() == player) {
            System.out.println("AI is attacking!");
        } else {
            System.out.println("AI is defending!");
        }

        MoveResponse response = null;

        if (hitOrigin == null) {
            while (response == null || response == MoveResponse.DUPLICATE_SHOT) {
                int x = BattleshipUtil.random();
                int y = BattleshipUtil.random();

                response = getGame().fireShotAtDefender(x, y);

                if (response == MoveResponse.HIT) {

                }
            }
        }
    }
}
