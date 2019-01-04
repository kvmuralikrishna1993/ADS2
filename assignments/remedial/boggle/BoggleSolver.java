import java.util.HashSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * { path check matrix }.
     */
    private boolean[][] marked;
    /**
     * { var_rows }.
     */
    private int rows;
    /**
     * { var_columns }.
     */
    private int columns;
    /**
     * { tst object }.
     */
    private TST<Integer> tst;
    /**
     * { var_count }.
     */
    private int count;
    /**
     * { set }.
     */
    private HashSet<String> set;
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        count = 0;
        set = new HashSet<String>();
        tst = new TST<Integer>();
        for (String word : dictionary) {
            tst.put(word, count++);
        }
    }
    /**
     * { checks everyletter in board }.
     *
     * @param      board  The board
     */
    private void check(final BoggleBoard board) {
        rows = board.rows();
        columns = board.cols();
        marked = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                marked = new boolean[rows][columns];
                dfs("" + board.getLetter(i, j), board, i, j);
            }
            marked = new boolean[rows][columns];
        }
    }
    /**
     * { declaring score }.
     *
     * @param      str   The string
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String str) {
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int eleven = 11;
        if (str.length() >= 0 && str.length() <= 2) {
            return 0;
        } else if (str.length() > 2 && str.length() <= four) {
            return 1;
        } else if (str.length() == five) {
            return 2;
        } else if (str.length() == six) {
            return three;
        } else if (str.length() == seven) {
            return five;
        } else if (str.length() >= eight) {
            return eleven;
        }
        return 0;
    }
    /**
     * { checking not to enter out of index}.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final int i, final int j) {
        return (i >= 0 && i < rows && j >= 0 && j < columns);
    }
    /**
     * Determines if it has prefix.
     *
     * @param      str   The string
     *
     * @return     True if has prefix, False otherwise.
     */
    private boolean hasPrefix(final String str) {
        return tst.keyWithPrefix(str);
    }
    /**
     * Determines if word.
     *
     * @param      str   The string
     *
     * @return     True if word, False otherwise.
     */
    private boolean isWord(final String str) {
        return (tst.get(str) != null);
    }
    /**
     * { dfs of each letter in recursive }.
     *
     * @param      string   The string
     * @param      b     { parameter_description }
     * @param      s     { parameter_description }
     * @param      m     { parameter_description }
     */
    private void dfs(final String string, final BoggleBoard b,
                                            final int s, final int m) {
        String str = string;
        // Q --> conidtion.
        if (check(s, m)) {
            if (str.charAt(str.length() - 1) == 'Q') {
                    str += "U";
            }
            // marking every check box.
            marked[s][m] = true;
            // basic condition for valid word.
            if (str.length() > 2 && isWord(str)) {
                set.add(str);
            }
                //maximum possible ways for checking each box --> 8.
                //2 3 4
                //5 6 7  6 -->> has eight possible neighbours
                //8 9 0         {2,3,4,5,7,8,9,0}
            //---------------------------------------------------------->> 1
                if (check(s - 1, m - 1) && !marked[s - 1][m - 1]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s - 1, m - 1), b, s - 1, m - 1);
                    marked[s - 1][m - 1] = false;
                }
            //---------------------------------------------------------->> 2
                if (check(s - 1, m) && !marked[s - 1][m]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s - 1, m), b, s - 1, m);
                    marked[s - 1][m] = false;
                }
            //---------------------------------------------------------->> 3
                if (check(s - 1, m + 1) && !marked[s - 1][m + 1]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s - 1, m + 1), b, s - 1, m + 1);
                    marked[s - 1][m + 1] = false;
                }
            //---------------------------------------------------------->> 4
                if (check(s, m - 1) && !marked[s][m - 1]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s, m - 1), b, s, m - 1);
                    marked[s][m - 1] = false;
                }
            //---------------------------------------------------------->> 5
                if (check(s, m + 1) && !marked[s][m + 1]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s, m + 1), b, s, m + 1);
                    marked[s][m + 1] = false;
                }
            //---------------------------------------------------------->> 6
                if (check(s + 1, m - 1) && !marked[s + 1][m - 1]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s + 1, m - 1), b, s + 1, m - 1);
                    marked[s + 1][m - 1] = false;
                }
            //---------------------------------------------------------->> 7
                if (check(s + 1, m + 1) && !marked[s + 1][m + 1]
                    && hasPrefix(str)) {
                    dfs(str + b.getLetter(s + 1, m + 1), b, s + 1, m + 1);
                    marked[s + 1][m + 1] = false;
                }
            //---------------------------------------------------------->> 8
                if (check(s + 1, m) && !marked[s + 1][m] && hasPrefix(str)) {
                    dfs(str + b.getLetter(s + 1, m), b, s + 1, m);
                    marked[s + 1][m] = false;
                }
        }
    }
    /**
     * Gets all valid words.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        check(board);
        return set;
    }
}
