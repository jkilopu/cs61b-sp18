package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import static org.junit.Assert.*;

public class testRoom {

    @Test
    /* ####
     * #  #
     * #  #
     * ####
     */
    public void testAddRoom() {
        Position p = new Position(0 , 0);
        Position con = new Position(0, 1);
        Position[] connections = {con};
        Size size = new Size(4, 4);
        Size worldSize = new Size(4, 4);
        Room r = new Room(p, size, connections);

        TETile f = Tileset.FLOOR;
        TETile w = Tileset.WALL;
        TETile n = Tileset.NOTHING;
        TETile[][] exp = {{w, f, w, w}, {w, f, f, w}, {w, f, f, w}, {w, w, w, w}};

        TETile[][] world = Game.createEmptyWorld(worldSize);
        r.addRoom(world);

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                assertEquals(exp[i][j].character(), world[i][j].character());
            }
        }
    }
}
