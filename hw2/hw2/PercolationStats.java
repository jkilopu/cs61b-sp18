package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] x;
    private int T;

    /**
     * Constructor, fills x and T.
     * @param N WorldSize
     * @param T Try times
     * @param pf Used to generate percolation
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Invalid N or T");
        }
        this.T = T;
        x = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int[] pos = generateUnrepeatableRandomNumbers(N * N); // All positions with
            // random order.
            int index = 0;
            while (!p.percolates()) {
                int position = pos[index++];
                int row = position / N, col = position % N;
                p.open(row, col);
            }
            x[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * Generate unrepeatable random numbers.
     * @source https://www.cnblogs.com/happyday56/p/5163264.html
     */
    private int[] generateUnrepeatableRandomNumbers(int range) {
        if (range <= 0) {
            throw new IllegalArgumentException("Range can't be less than or equal to 0");
        }
        int[] source = new int[range];
        for (int i = 0; i < range; i++) {
            source[i] = i;
        }

        int index, len = range;
        for (int i = 0; i < range; i++) {
            index = StdRandom.uniform(len--);
            int tmp = source[index];
            source[index] = source[len];
            source[len] = tmp;
        }
        return source;
    }

    public double mean() {
        return StdStats.mean(x);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }                                // high endpoint of 95% confidence interval
}
