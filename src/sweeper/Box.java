package sweeper;

import java.awt.Image;

public enum Box {
    // up level
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,

    // down level
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Image image;

    Box nextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }

    int getNumber() {
        int num = ordinal();
        if(num >= Box.NUM1.ordinal() && ordinal() <= Box.NUM8.ordinal()) {
            return num;
        }
        return -1;
    }
}
