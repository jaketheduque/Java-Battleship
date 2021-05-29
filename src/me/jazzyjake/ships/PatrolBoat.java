package me.jazzyjake.ships;

import me.jazzyjake.exceptions.InvalidOriginException;
import me.jazzyjake.misc.BattleshipUtil;

public class PatrolBoat extends Ship {
    public PatrolBoat(int x, int y, Direction direction) throws InvalidOriginException {
        super(x, y, direction);
    }

    public static PatrolBoat random() {
        Direction direction = Direction.randomDirection();
        int[] origin = PatrolBoat.randomOrigin(direction);

        return new PatrolBoat(origin[0], origin[1], direction);
    }

    @Override
    boolean originValid(int x, int y, Direction direction) {
        if (direction == Direction.RIGHT && x > 8)
            return false;
        if (direction == Direction.DOWN && y > 8)
            return false;
        return true;
    }

    private static int[] randomOrigin(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case RIGHT:
                x = BattleshipUtil.random(0, 8);
                y = BattleshipUtil.random();

                return new int[]{x, y};
            case DOWN:
                x = BattleshipUtil.random();
                y = BattleshipUtil.random(0, 8);

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
