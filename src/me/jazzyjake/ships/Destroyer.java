package me.jazzyjake.ships;

import me.jazzyjake.exceptions.InvalidOriginException;
import me.jazzyjake.misc.BattleshipUtil;
import me.jazzyjake.misc.Direction;

public class Destroyer extends Ship {
    private Destroyer(int x, int y, Direction direction) throws InvalidOriginException {
        super(x, y, direction);
    }

    public static Destroyer random() {
        Direction direction = Direction.randomDirection();
        int[] origin = Destroyer.randomOrigin(direction);

        return new Destroyer(origin[0], origin[1], direction);
    }

    @Override
    boolean originValid(int x, int y, Direction direction) {
        if (direction == Direction.RIGHT && x > 9)
            return false;
        if (direction == Direction.DOWN && y > 9)
            return false;
        return true;
    }

    private static int[] randomOrigin(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case RIGHT:
                x = BattleshipUtil.random(1, 9);
                y = BattleshipUtil.random();

                return new int[]{x, y};
            case DOWN:
                x = BattleshipUtil.random();
                y = BattleshipUtil.random(1, 9);

                return new int[]{x, y};
            default:
                return null;
        }
    }

    @Override
    int[][] fillCoords(int x, int y, Direction d) {
        switch (d) {
            case RIGHT:
                return new int[][]{{x, y}, {x + 1, y}};
            case DOWN:
                return new int[][]{{x, y}, {x, y + 1}};
            default:
                return null;
        }
    }
}
