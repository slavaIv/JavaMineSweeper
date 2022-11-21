package sweeper;

class Flag {
    private Matrix flagMap;
    private int totalFlags;
    
    private int totalClosed;
    
    
    public void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlags = 0;
        totalClosed = Ranges.getSquare();
    }
    
    int getTotalFlags() {
        return totalFlags;
    }
    
    int getTotalClosed() {
        return totalClosed;
    }

    Box getFlagMap(Coord coord) {
        return flagMap.getBox(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        totalClosed --;
    }

    private void setFlaggedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
        totalFlags ++;
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
        totalFlags --;
    }

    void toggleFlaggedToBox(Coord coord) {
        switch(flagMap.getBox(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlaggedToBox(coord);
                break;
            default:
                break;
        }
    }

    void setFlagsToRemainingBoxes() {
        for(Coord elem: Ranges.getAllCoords()) {
            if(flagMap.getBox(elem) == Box.CLOSED) {
                setFlaggedToBox(elem);
            }
        }
    }

    void setBombToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedtoClosedBox(Coord coord) {
        if(flagMap.getBox(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNoBomb(Coord coord) {
        if(flagMap.getBox(coord) == Box.FLAGED) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }

    int getFlagsCountAround(Coord coord) {
        int count = 0;
        for(Coord elem: Ranges.getCoordsAround(coord)) {
            if(flagMap.getBox(elem) == Box.FLAGED) {
                count ++;
            }
        }
        return count;
    }

}
