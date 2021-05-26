package me.jazzyjake.ships;

import me.jazzyjake.exceptions.InvalidOriginException;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.misc.Direction;

public class Carrier extends Ship {
    private Carrier(int x, int y, Direction direction) throws InvalidOriginException {
        super(x, y, direction);
    }

    public static Carrier random() {
        Direction direction = Direction.randomDirection();
        int[] origin = Carrier.randomOrigin(direction);

        return new Carrier(origin[0], origin[1], direction);
    }

    @Override
    boolean originValid(int x, int y, Direction direction) {
        if (direction == Direction.RIGHT && x > 6)
            return false;
        if (direction == Direction.DOWN && y > 6)
            return false;
        return true;
    }

    private static int[] randomOrigin(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case RIGHT:
                x = BattleshipUtil.random(1, 6);
                y = BattleshipUtil.random();
                return new int[]{x, y};
            case DOWN:
                x = BattleshipUtil.random();
                y = BattleshipUtil.random(1, 6);
                return new int[]{x, y};
            default:
                return null;
        }
    }

    @Override
    int[][] fillCoords(int x, int y, Direction direction) {
        switch (direction) {
            case RIGHT:
                return new int[][]{{x, y}, {x + 1, y}, {x + 2, y}, {x + 3, y}, {x + 4, y}};
            case DOWN:
                return new int[][]{{x, y}, {x, y + 1}, {x, y + 2}, {x, y + 3}, {x, y + 4}};
            default:
                return null;
        }
    }
}
