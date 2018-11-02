
import java.util.Scanner;

/**
 * Interface for graph.
 */
interface Graph {
    /**
     * number of v.
     *
     * @return     { description_of_the_return_value }
     */
    int vertices();    /**
     * number of e.
     *
     * @return     { description_of_the_return_value }
     */
    int edges();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * iterable.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * { item_ solution }.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * { function_main }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String method = scan.nextLine();
        int v = Integer.parseInt(scan.nextLine());
        int e = Integer.parseInt(scan.nextLine());
        String[] keys = scan.nextLine().split(",");
        if (method.equals("List")) {
            GraphList list = new GraphList(v);
            for (int i = 0; i < e; i++) {
                String[] tokens = scan.nextLine().split(" ");
                list.addEdge(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]));
            }
            System.out.println(list.display(keys));
        } else if (method.equals("Matrix")){
            GraphMatrix matrix = new GraphMatrix(v);
            for (int i = 0; i < e; i++) {
                String[] tokens = scan.nextLine().split(" ");
                matrix.addEdge(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]));
            }
            System.out.println(matrix);
        }
    }
}