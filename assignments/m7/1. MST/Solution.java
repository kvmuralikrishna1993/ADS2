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
     * Main function to handle inputs and deliver outputs.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] inputs = scan.nextLine().split(" ");
            Edge addedge = new Edge(Integer.parseInt(inputs[0]),
                Integer.parseInt(inputs[1]), Double.parseDouble(inputs[2]));
            graph.addEdge(addedge);
        }
        LazyPrimMST lazymst = new LazyPrimMST(graph);
        System.out.printf("%.5f\n", lazymst.weight());
    }
}
