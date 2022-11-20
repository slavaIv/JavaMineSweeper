package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
    }

    public Box getBoxGame(Coord coord) {
        if(Box.OPENED == flag.getFlagMap(coord)) {
            return bomb.get(coord);
        }
        else {
            return flag.getFlagMap(coord);
        }
    }

    public void pressLeftButton(Coord coord) {
        flag.setOpenedToBox(coord);
    }

    public void pressRightButton(Coord coord) {
        flag.toggleFlaggedToBox(coord);
    }

}
