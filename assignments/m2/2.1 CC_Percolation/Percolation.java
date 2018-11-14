/**
 * Class for percolation.
 */
class Percolation {
    /**
     * {Declaring an object of weighted quick union}.
     */
    private Graph graph;
    /**
     * {variable n}.
     */
    private int n;
    /**
     * {Variables size, first, last, count}.
     */
    private int size, first, last, count;
    /**
     * {Declaring an integer array of type boolean}.
     */
    private boolean[] connected;
    /**
     * Constructs the object.
     * Complexity N
     * @param      num    The num
     */
    Percolation(final int num) {
        this.n = num;
        this.size = num * num;
        this.first = size;
        this.last = size + 1;
        this.count = 0;
        connected = new boolean[size];
        graph = new Graph(size + 2);
        for (int i = 0; i < n; i++) {
            graph.addEdge(first, i);
            graph.addEdge(last, size - i - 1);
        }
    }
    /**
     * Searches for the first match.
     *
     * @param      i     {row}
     * @param      j     {column}
     *
     * @return     {index value for 1-Dimensional Array}
     */
    private int indexOf(final int i, final int j) {
        return (n * (i - 1)) + (j - 1);
    }
    /**
     * Links open sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void linkOpenSites(final int row, final int col) {
        if (connected[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
     * {Method to open site (row, col) if it is not open already}.
     * Complexity 1
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        int index = indexOf(row, col);
        connected[index] = true;
        count++;
        int top = index - n;
        int bottom = index + n;
        if (n == 1) {
            graph.addEdge(first, index);
            graph.addEdge(first, index);
        }
        if (bottom < size) {
            linkOpenSites(index, bottom);
        }
        if (top >= 0) {
            linkOpenSites(index, top);
        }
        if (col == 1) {
            if (col != n) {
                linkOpenSites(index, index + 1);
            }
            return;
        }
        if (col == n) {
            linkOpenSites(index, index - 1);
            return;
        }
        linkOpenSites(index, index + 1);
        linkOpenSites(index, index - 1);
    }
    /**
     * Determines if the site is open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return connected[indexOf(row, col)];
    }
    /**
     * {Method to determine the number of open sites}.
     *
     * @return     {Number of open sites}
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * {Method to determine does the system percolate?}.
     *
     * @return     {Boolean value}
     */
    public boolean percolates() {
        ConnectedComponents cc = new ConnectedComponents(graph);
        return cc.connected(first, last);
    }
}
