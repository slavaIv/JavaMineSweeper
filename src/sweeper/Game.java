package sweeper;

public class Game {

    Matrix bombMap;

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        bombMap.set(new Coord(4, 8), Box.BOMBED);
    }

    public Game(int cols, int rows) {
        Ranges.setSize(new Coord(cols, rows));
    }

    public Box getBox(Coord coord) {
        return bombMap.get(coord);
    }

}
