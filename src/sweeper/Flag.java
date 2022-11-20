package sweeper;

class Flag {
    private Matrix flagMap;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
    }

    Box getFlagMap(Coord coord) {
        return flagMap.getBox(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
    }

    private void setFlaggedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    void toggleFlaggedToBox(Coord coord) {
        switch(flagMap.getBox(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlaggedToBox(coord);
                break;
        }
    }

}
