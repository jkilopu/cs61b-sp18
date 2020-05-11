package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] world;
    private int worldSize;
    private int numberOfOpenSites;
    WeightedQuickUnionUF disjointSet;
    private static int top;
    private static int bottom;

    /**
     * Create N-by-N grid, with all sites initially blocked,
     * and additional N * N value for visual top , + 2 for visual bottom.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N can't be less than or equal to 0");
        }
        numberOfOpenSites = 0;
        worldSize = N;
        world = new boolean[worldSize * worldSize];
        disjointSet = new WeightedQuickUnionUF(N * N + 2);
        top = N * N;
        bottom = N * N + 1;
    }

    /**
     * Open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        int position = rowColTo1D(row, col);
        if (!world[position]) {
            numberOfOpenSites += 1;
            world[position] = true;
        }
        /* up */
        if (position / worldSize - 1 >= 0 && world[position - worldSize]) {
            disjointSet.union(position, position - worldSize);
        }
        /* left */
        if (position % worldSize - 1 >= 0 && world[position - 1]) {
            disjointSet.union(position, position - 1);
        }
        /* down */
        if (position / worldSize + 1 < worldSize && world[position + worldSize]) {
            disjointSet.union(position, position + worldSize);
        }
        /* Right */
        if (position % worldSize + 1 < worldSize && world[position + 1]) {
            disjointSet.union(position, position + 1);
        }
        /* Connected with top? */
        if (row == 0) {
            disjointSet.union(position, top);
        }
        /* Connected with bottom? */
        if (row == worldSize - 1) {
            disjointSet.union(position, bottom);
        }
    }

    int rowColTo1D(int row, int col) {
        return row * worldSize + col;
    }

    /**
     * Is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        return world[rowColTo1D(row, col)];
    }

    /**
     * Is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        return disjointSet.connected(rowColTo1D(row, col), top);
    }

    /**
     * Number of open sites.
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return disjointSet.connected(top, bottom);
    }
}
