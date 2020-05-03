/* First part: Drawing A Single Hexagon. Completed
 * Second part: Drawing A Tesselation of Hexagons. Only know the solution.
 */
package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld { // Is extending TETile better? I believe so.
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final Random RANDOM = new Random();

    /**
     * Subclass only contains coordinate.
     */
    public static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Create a world which contains a hexagon.
     * @param width The width(x) of the world.
     * @param height The height(y) of the world.
     * @param pos Determine the bottom-left of the hexagon.
     * @param size The size of the hexagon
     * @param bg The background of the world.
     * @param t THe tile forms the hexagon
     */
    public static TETile[][] createHexagonWorld(int width, int height, Position pos, int size, TETile bg, TETile t) {
        TETile[][] tiles = new TETile[width][height];
        initWorld(tiles, width, height, bg);
        addHexagon(tiles, pos, size, t);
        return tiles;
    }

    /**
     * Init the world with bg
     */
    public static void initWorld(TETile[][] world, int width, int height, TETile bg) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = bg;
            }
        }
    }

    /**
     * Add one line consists of num tiles t at bottomLeftY.
     */
    public static void addOneLine(TETile[] tiles, int bottomLeftY, int size, int num, TETile t) {
        int startY = bottomLeftY + size - num / 2;
        for (int i = 0; i < num; i++) {
            tiles[startY + i] = t;
        }
    }

    /**
     * Add hexagon according to the size, position and tile.
     */
    public static void addHexagon(TETile[][] tiles, Position topLeft, int size, TETile t) {
        /* Left and right */
        for (int i = 0; i < size - 1; i++) {
            addOneLine(tiles[i + topLeft.x], topLeft.y, size, 2 * (i + 1), t);
            addOneLine(tiles[size * 3 - 3 - i + topLeft.x], topLeft.y, size, 2 * (i + 1), t);
        }
        /* Middle */
        for (int i = 0; i < size; i++) {
            addOneLine(tiles[i + size - 1 + topLeft.x], topLeft.y, size, 2 * size, t);
        }
    }

    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.FLOWER;
            case 1: return Tileset.GRASS;
            case 2: return Tileset.TREE;
            case 3: return Tileset.SAND;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.MOUNTAIN;
        }
    }

    public static void main(String args[]) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Position pos = new Position(6, 12);
        TETile part = randomTile();
        TETile[][] world = createHexagonWorld(WIDTH, HEIGHT, pos, 6, Tileset.NOTHING, part);
        ter.renderFrame(world);
    }

    @Test
    public void testAddOneLine() {
        final int LEN = 10;
        final int SIZE = 4;
        TETile[] testLine = new TETile[LEN];
        for (int i = 0; i < LEN; i++) {
            testLine[i] = Tileset.NOTHING;
        }
        TETile[] exp = {Tileset.NOTHING, Tileset.NOTHING, Tileset.WALL, Tileset.WALL, Tileset.WALL, Tileset.WALL, Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING};
        addOneLine(testLine, 0, SIZE, 4, Tileset.WALL);
        for (int i = 0 ; i < LEN; i++) {
            assertEquals(exp[i].character(), testLine[i].character());
        }
    }
}
