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
}
