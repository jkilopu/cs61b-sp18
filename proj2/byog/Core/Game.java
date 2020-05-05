package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    TETile[][] world;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 160;
    public static final int HEIGHT = 60;
    private Size size = new Size(WIDTH, HEIGHT);

    /**
     * Constructor
     */
    Game() {
        createEmptyWorld(size());
        initWorld();
    }

    void createEmptyWorld(Size size) {
        this.size = new Size(size.x, size.y);
        world = new TETile[size().x][size().y];
        initWorld();
    }

    private void initWorld() {
        for (int x = 0; x < size().x; x++) {
            for (int y = 0; y < size().y; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        int option = getOption(input);
        long seed = Long.parseLong(getSeed(input));
//        switch (option) {
//            case 1:
//                saveSeed(seed);
//                break;
//            case 2:
//                seed = getSeed(file);
//                break;
//        }
        Random random = new Random(seed);
        TETile[][] finalWorldFrame = generateWorld(random);
        return finalWorldFrame;
    }

    private TETile[][] generateWorld(Random random) {
        Connection cons = new Connection();
        cons.addFirstCon(size(), random); // Must have a first connection
        int failTimes = 0;
        Position c = cons.connections.poll();
        boolean isFirstConnected = false;
        Position tmp = c; // First connection must be wall later.(Because it is not likely connect modules).
        while (c != null && failTimes < 5) {
            Room room = new Room();
            Position[] news = room.addRandomRoom(random, c, world);
            if (news == null) { // Fail to add new room.
                failTimes++;
            }
            else {
                for (int i = 0; i < news.length; i++) {
                    cons.connections.offer(news[i]); // More connections!
                }
            }
            if (c == tmp) {
                isFirstConnected = true;
            }
            c = cons.connections.poll(); // Next connection.
        }
        if (!isFirstConnected) {
            world[tmp.x][tmp.y] = Tileset.WALL;
        }
        return world;
    }

    Size size() {
        return size;
    }

    /**
     * Return option specified in input.
     *
     * @return 0 means "new game", 1 means "load", 2 means "save"
     */
    static int getOption(String input) {
        String regexLoad = "(?i)^L.*";
        String regexSave = "(?i).*(:Q)$";
        if (input.matches(regexLoad))
            return 1;
        if (input.matches(regexSave))
            return 2;
        return 0; // default: new game;
    }

    /**
     * Return the seed found in input.
     */
    static String getSeed (String input) {
        String regexSplit = "(?i)N|S";
        String []ret = input.split(regexSplit);
        String regexFind = "[1-9][0-9]*|0";
        for (String s : ret) {
            if (s.matches(regexFind))
                return s;
        }
        return "";
    }

    private void saveSeed() {
        ;
    }

    private long getSeed() {
        return 0;
    }
}
