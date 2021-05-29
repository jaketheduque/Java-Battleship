package me.jazzyjake.misc;

import me.jazzyjake.exceptions.InvalidOriginException;
import me.jazzyjake.ships.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ShipGenerator {
    public static ArrayList<Ship> generateRandomShips() {
        ArrayList<Ship> ships = new ArrayList<>();
        HashSet<int[]> occupied = new HashSet<>();

        // Adds a Carrier
        Carrier carrier = Carrier.random();
        ships.add(carrier);
        for (int[] coord : carrier.getCoords())
            occupied.add(coord);

        // Adds a Battleship
        Battleship battleship = Battleship.random();

        while (BattleshipUtil.containsCoord(occupied, battleship.getCoords())) {
            battleship = Battleship.random();
        }

        ships.add(battleship);
        for (int[] coord : battleship.getCoords())
            occupied.add(coord);

        // Adds a Cruiser
        Cruiser cruiser = Cruiser.random();

        while (BattleshipUtil.containsCoord(occupied, cruiser.getCoords())) {
            cruiser = Cruiser.random();
        }

        ships.add(cruiser);
        for (int[] coord : cruiser.getCoords())
            occupied.add(coord);

        // Adds a Submarine
        Submarine submarine = Submarine.random();

        while (BattleshipUtil.containsCoord(occupied, submarine.getCoords())) {
            submarine = Submarine.random();
        }

        ships.add(submarine);
        for (int[] coord : submarine.getCoords())
            occupied.add(coord);

        // Adds a Destroyer
        PatrolBoat patrolBoat = PatrolBoat.random();

        while (BattleshipUtil.containsCoord(occupied, patrolBoat.getCoords())) {
            patrolBoat = PatrolBoat.random();
        }

        ships.add(patrolBoat);

        return ships;
    }

    public static ArrayList<Ship> getShipsFromConsoleInput() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Ship> ships = new ArrayList<>();
        HashSet<int[]> occupied = new HashSet<>();
        Scanner scan = new Scanner(System.in);

        for (Class shipType : Ship.SHIP_TYPES) {
            Constructor shipConstructor = shipType.getConstructor(int.class, int.class, Direction.class);

            boolean successful = false;
            do {
                try {
                    System.out.println("Enter " + shipType.getSimpleName() + ": (x, y, direction)");

                    String[] params = scan.nextLine().split(",");

                    int x = Integer.parseInt(params[0].trim());
                    int y = Integer.parseInt(params[1].trim());
                    Direction direction = Direction.valueOf(params[2].trim().toUpperCase());

                    if (x < 1 || x > 10 || y < 1 || y > 10) {
                        throw new InvalidOriginException(InvalidOriginException.InvalidOriginCause.OUT_OF_BOUNDS, x, y, null);
                    }

                    Ship ship = (Ship) shipConstructor.newInstance(x, y, direction);

                    while (BattleshipUtil.containsCoord(occupied, ship.getCoords())) {
                        throw new InvalidOriginException(InvalidOriginException.InvalidOriginCause.OVERLAPPING_SPACE, x, y, direction);
                    }

                    for (int[] coord : ship.getCoords()) occupied.add(coord);

                    ships.add(ship);
                    successful = true;

                    BattleshipUtil.printShips(ships);
                } catch (InvalidOriginException e) {
                    System.out.println("Invalid origin due to: " + e.getInvalidOriginCause());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid direction!");
                } catch (InvocationTargetException e) {
                    System.out.println("Invalid origin due to: " + ((InvalidOriginException) e.getCause()).getInvalidOriginCause());
                } catch (Throwable t) {
                    System.out.println("Error occurred due to: " + t.getClass().getSimpleName());
                }
            } while (!successful);
        }

        scan.close();
        return ships;
    }
}
