package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

class Room {
    Size size;
    Position pos; // Left bottom.

    Room (Position pos, Size size) {
        if (size.y <= 2 || size.x <= 2) {
            throw new RuntimeException("Room too small");
        }
        this.pos = pos;
        this.size = size;
    }

    /**
     * Add a room in the world.
     */
    void addRoom (TETile[][] world) {
        Position boundary = new Position(pos.x + size.x - 1, pos.y + size.y - 1);
        /* up and down */
        for (int i = pos.x; i <= boundary.x; i++) {
            world[i][pos.y] = Tileset.WALL;
            world[i][boundary.y] = Tileset.WALL;
        }
        /* left and right */
        for (int i = pos.y; i <= boundary.y; i++) {
            world[pos.x][i] = Tileset.WALL;
            world[boundary.x][i] = Tileset.WALL;
        }
        /* middle */
        for (int i = pos.x + 1; i <= boundary.x - 1; i++) {
            for (int j = pos.y + 1; j <= boundary.y - 1; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }
}
