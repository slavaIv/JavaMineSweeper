package sweeper;

import java.util.ArrayList;
import java.util.List;

public class Ranges {

    private static Coord size;
    private static List<Coord> allCoords;

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

    public static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

}
