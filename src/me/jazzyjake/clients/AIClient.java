package me.jazzyjake.clients;

import me.jazzyjake.game.MoveResponse;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;

public class AIClient extends Client {
    private int[] hitOrigin;
    private int[] lastTriedMove;
    private int[] nextMove;
    private MoveResponse lastTriedMoveResponse;
    private Direction currentDirection;

    private enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    public AIClient(Class<Player> playerClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(playerClass.getConstructor().newInstance());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MoveResponse response = null;

        if (hitOrigin == null) {
            while (response == null || response == MoveResponse.DUPLICATE_SHOT) {
                int x = BattleshipUtil.random();
                int y = BattleshipUtil.random();

                response = player.getGame().fireShotAtDefender(x, y);

                if (response == MoveResponse.HIT) {

                }
            }
        }
    }
}
