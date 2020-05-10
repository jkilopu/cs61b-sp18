package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

/**
 * The door connects floor to nothing.
 * Position rules: Around the pos(in four basic directions),
 * there are exactly two walls, one nothing and one floor.
 */
class Door {
    Position pos;
    boolean locked;

    /**
     * Create a door initially locked.
     */
    Door() {
        pos = new Position();
        locked = true;
    }

    /**
     * Set the door at a proper and random position in world
     */
    void setRandomDoor(TETile[][] world, Random random) {
        pos = getRandomDoorPos(world, random);
        world[pos.x][pos.y] = Tileset.LOCKED_DOOR;
    }

    /**
     * Return the random, proper position int world.
     */
    private Position getRandomDoorPos(TETile[][] world, Random random) {
        List<Position> pList = getValidPosList(world);
        int i = random.nextInt(pList.size());
        return pList.get(i);
    }

    /**
     * Get all the valid position for door in world.
     */
    private List<Position> getValidPosList(TETile[][] world) {
        List<Position> pList = new ArrayList<>();
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                Position p = new Position(i , j);
                if (canBeDoor(world, p)) {
                    pList.add(p);
                }
            }
        }
        return pList;
    }

    /**
     * Return true if p in world can be a door.
     */
    private boolean canBeDoor(TETile[][] world, Position p) {
        Map<TETile, Integer> tileMap = cntSurroundings(world, p);
        // The position rule for surroundings of a door.
        if (tileMap.get(Tileset.WALL) == 2 && tileMap.get(Tileset.NOTHING) == 1 &&
                tileMap.get(Tileset.FLOOR) == 1) {
            return true;
        }
        return false;
    }

    /**
     * Count objects in four basic directions.
     * @Return return a map only have keys: Tileset.NOTHING, Tileset.WALL, Tileset.FLOOR,
     * corresponding value is its amount.
     */
    private Map<TETile, Integer> cntSurroundings(TETile[][] world, Position p) {
        // Create and init the map.
        Map<TETile, Integer> tileMap = new HashMap<>() { {
                put(Tileset.NOTHING, 0);
                put(Tileset.WALL, 0);
                put(Tileset.FLOOR, 0);
            }
        };
        // The p can't be at the edge of the map.
        if (p.x > 0 && p.x < world.length - 1 && p.y > 0 && p.y < world[0].length - 1) {
            Position newPos = p.up();
            cntValue(tileMap, world, newPos);
            newPos = p.down();
            cntValue(tileMap, world, newPos);
            newPos = p.left();
            cntValue(tileMap, world, newPos);
            newPos = p.right();
            cntValue(tileMap, world, newPos);
        }
        return tileMap;
    }

    /**
     * If the tile at p in world contains in tileMap, count++.
     */
    private void cntValue(Map<TETile, Integer> tileMap, TETile[][] world, Position p) {
        TETile key = world[p.x][p.y];
        Integer cnt = tileMap.get(key);
        if (tileMap.containsKey(key)) {
            tileMap.put(key, cnt + 1);
        }
    }
}
