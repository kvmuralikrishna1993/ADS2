import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * {main_function}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = Integer.parseInt(sc.nextLine());
        Percolation percolation = new Percolation(size);
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            int a1 = Integer.parseInt(tokens[0]);
            int a2 = Integer.parseInt(tokens[1]);
            percolation.open(a1, a2);
        }
        System.out.println(percolation.percolates() && percolation.numberOfOpenSites() != 0);
    }
}