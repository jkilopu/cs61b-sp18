package byog.Core;

import java.util.Iterator;

class Position extends Coordinate {
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void up() {
        y++;
    }
    void down() {
        y--;
    }
    void right() {
        x++;
    }
    void left() {
        x--;
    }
}
