package byog.Core;

import byog.TileEngine.TETile;

import java.util.Iterator;
import java.util.Random;

class Connection implements Iterable {
    Position[] connections;
    int nCon;
    private static final int MAXNCON = 100;

    Connection() {
        nCon = 0;
        connections = new Position[MAXNCON];
    }

    /**
     * Add a connection which position is limited by s.
     */
    void addConnection(Size s, Random random) {
        if (nCon == MAXNCON) {
            throw new RuntimeException("The number of connections has reached the maximum");
        }
        int x = RandomUtils.uniform(random, 0 , s.x);
        int y = RandomUtils.uniform(random, 0 , s.y);
        Position p = new Position(x, y);
        connections[nCon] = p;
        nCon++;
    }

    @Override
    public Iterator iterator() {
        return new ConIterator();
    }

    /**
     * Iterator for connections.
     */
    private class ConIterator implements Iterator {
        private int ptr;

        public ConIterator() {
            ptr = 0;
        }

        @Override
        public boolean hasNext() {
            return ptr != nCon;
        }
        @Override
        public Position next() {
            Position ret = connections[ptr];
            ptr++;
            return ret;
        }
    }
}
