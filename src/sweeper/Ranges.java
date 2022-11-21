package sweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranges {

    private static Coord size;
    private static List<Coord> allCoords;
    private static Random random = new Random();

    static void setSize(Coord size) {
        Ranges.size = size;
        allCoords = new ArrayList<>();
        for(int i = 0; i < size.x; i++) {
            for(int j = 0; j < size.y; j++) {
                allCoords.add(new Coord(i, j));
            }
        }
    }

    public static Coord getSize() {
        return size;
    }

    public static List<Coord> getAllCoords() {
        return allCoords;
    }

    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<>();
        for(int x = coord.x - 1; x <= coord.x + 1; x++) {
            for(int y = coord.y - 1; y <= coord.y + 1; y++) {
                if(inRange(around = new Coord(x, y))) {
                    if(!around.equals(coord)) {
                        list.add(around);
                    }
                }
            }
        }
        return list;
    }

    public static int getSquare() {
        return size.x * size.y;
    }

}
