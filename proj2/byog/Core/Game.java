package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    TETile[][] world;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int MAXFAILTIME = 1000;
    private Size size = new Size(WIDTH, HEIGHT);

    /**
     * Constructor
     */
    public Game() {
        createEmptyWorld(size());
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
        ter.initialize(size().x, size().y);
        String input = "";

        ter.showMenu();
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            input += key;
            if (playWithInputString(input) != null) {
                ter.renderFrame(world);
                input = ""; // reset
            }
        }
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
        String sSeed = getSeed(input);
        long seed = 0;
        switch (option) {
            case 0:
                seed = Long.parseLong(sSeed);
                break;
            case 1:
                seed = readSeed();
                break;
            case 2:
                saveSeed(sSeed);
                System.exit(0);
                break;
            case -1:
                return null;
        }
        Random random = new Random(seed);
        TETile[][] finalWorldState = generateWorld(random);
        return finalWorldState;
    }

    /**
     * Generate random world.
     */
    private TETile[][] generateWorld(Random random) {
        initWorld();
        Connection cons = new Connection();
        cons.addFirstCon(size(), random); // Must have a first connection
        int failTimes = 0;
        Position c = cons.connections.poll();
        boolean isFirstConnected = false;
        Position tmp = c; // First connection must be wall later.(Because it is not likely connect modules).
        while (c != null && failTimes < MAXFAILTIME) {
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
            c = cons.connections.poll(); // Next connection.
            if (tmp.equals(c)) {
                isFirstConnected = true;
            }
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
        String regexNew = "(?i).*(N[1-9][0-9]*|0)S.*";
        String regexLoad = "(?i)^L.*";
        String regexSave = "(?i).*(:Q)$";
        if (input.matches(regexNew)) {
            return 0;
        }
        if (input.matches(regexLoad)) {
            return 1;
        }
        if (input.matches(regexSave)) {
            return 2;
        }
        return -1; // Invalid input.
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

    private void saveSeed(String s) {
        try {
            FileWriter fw = new FileWriter("savefile.txt");
            fw.write(s);
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long readSeed() {
        int ch;
        String s = "";
        try {
            FileReader fr = new FileReader("savefile.txt");
            while ((ch = fr.read()) != -1) {
                s += (char) ch;
            }
            fr.close();
        }
        catch (IOException e) {
            System.exit(1);
        }
        return Long.parseLong(s);
    }
}
