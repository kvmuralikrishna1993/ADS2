import java.util.HashMap;
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
     * Main file to handle inputs and deliver outputs.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] indexs = scan.nextLine().split(" ");
        int vertices = Integer.parseInt(indexs[0]);
        int edges = Integer.parseInt(indexs[1]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String[] inputs = scan.nextLine().split(" ");
        for (int i = 0; i < inputs.length; i++) {
            map.put(inputs[i], i);
        }
        for (int i = 0; i < edges; i++) {
            inputs = scan.nextLine().split(" ");
            Edge link = new Edge(map.get(inputs[0]), map.get(inputs[1]),
                Integer.parseInt(inputs[2]));
            graph.addEdge(link);
        }
        int iter = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < iter; i++) {
            inputs = scan.nextLine().split(" ");
            DijkstraUndirectedSP djsp = new DijkstraUndirectedSP(graph,
                map.get(inputs[0]));
            System.out.println((int) djsp.distTo(map.get(inputs[1])));
        }
    }
}
