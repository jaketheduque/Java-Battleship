package me.jazzyjake.ships;

import me.jazzyjake.exceptions.InvalidOriginException;
import me.jazzyjake.misc.BattleshipUtil;

public class Battleship extends Ship {
    public Battleship(int x, int y, Direction direction) throws InvalidOriginException {
        super(x, y, direction);
    }

    public static Battleship random() {
        Direction direction = Direction.randomDirection();
        int[] origin = Battleship.randomOrigin(direction);

        return new Battleship(origin[0], origin[1], direction);
    }

    @Override
    boolean originValid(int x, int y, Direction direction) {
        if (direction == Direction.RIGHT && x > 7)
            return false;
        if (direction == Direction.DOWN && y > 7)
            return false;
        return true;
    }

    private static int[] randomOrigin(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case RIGHT:
                x = BattleshipUtil.random(1, 7);
                y = BattleshipUtil.random();
                return new int[]{x, y};
            case DOWN:
                x = BattleshipUtil.random();
                y = BattleshipUtil.random(1, 7);
                return new int[]{x, y};
            default:
                return null;
        }
    }

    @Override
    int[][] fillCoords(int x, int y, Direction d) {
        switch (d) {
            case RIGHT:
                return new int[][]{{x, y}, {x + 1, y}, {x + 2, y}, {x + 3, y}};
            case DOWN:
                return new int[][]{{x, y}, {x, y + 1}, {x, y + 2}, {x, y + 3}};
            default:
                return null;
        }
    }
}
