package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    
    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }
    
    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYING;
    }
    
    public GameState getState() {
        return state;
    }

    public int getTotalBombs() {
        return bomb.getTotalBombs();
    }

    public int getTotalFlags() {
        return flag.getTotalFlags();
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
        if(!isGameOver()) {
            openBox(coord);
            checkWinner();
        }
    }

    public void pressRightButton(Coord coord) {
        if(!isGameOver()) {
            flag.toggleFlaggedToBox(coord);
        }
    }

    private boolean isGameOver() {
        if(state != GameState.PLAYING) {
            start();
            return true;
        }
        else {
            return false;
        }
    }

    private void checkWinner() {
        if(state == GameState.PLAYING) {
            if(flag.getTotalClosed() == bomb.getTotalBombs()) {
                state = GameState.WIN;
                flag.setFlagsToRemainingBoxes();
            }
        }
    }
    
    private void openBox(Coord coord) {
        switch(flag.getFlagMap(coord)) {
            case OPENED:
                setOpenedAroundNumber(coord);
                break;
            case FLAGED:
                break;
            case CLOSED:
                switch(bomb.get(coord)) {
                    case ZERO:
                        openBoxesAroundZero(coord);
                        break;
                    case BOMB:
                        openBombs(coord);
                        break;
                    default:
                        flag.setOpenedToBox(coord);
                        break;
                }
        }
    }

    private void setOpenedAroundNumber(Coord coord) {
        if(bomb.get(coord) != Box.BOMB) {
            if(bomb.get(coord).getNumber() == flag.getFlagsCountAround(coord)) {
                for(Coord elem: Ranges.getCoordsAround(coord)) {
                    if(flag.getFlagMap(elem) == Box.CLOSED) {
                        openBox(elem);
                    }
                }
            }
        }
    }

    private void openBombs(Coord coord) {
        for(Coord elem: Ranges.getAllCoords()) {
            if(bomb.get(elem) == Box.BOMB) {
                flag.setOpenedtoClosedBox(elem);
            }
            else {
                flag.setNoBomb(elem);
            }
        }
        flag.setBombToBox(coord);
        state = GameState.BOMBED;
    }

    private void openBoxesAroundZero(Coord coord) {
        flag.setOpenedToBox(coord);
        for(Coord elem: Ranges.getCoordsAround(coord)) {
            openBox(elem);
        }
    }

}
