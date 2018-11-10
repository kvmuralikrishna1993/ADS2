import java.util.Scanner;
/**
 * { item_Solution }.
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
        String vertices = scan.nextLine();
        String edges = scan.nextLine();
        EdgeWeightedGraph graph = new EdgeWeightedGraph(
                        Integer.parseInt(vertices));
        EdgeWeightedDigraph graph1 = new EdgeWeightedDigraph(
                        Integer.parseInt(vertices));
        for (int i = 0; i < Integer.parseInt(edges); i++) {
            String[] input = scan.nextLine().split(" ");
            DirectedEdge e = new DirectedEdge(Integer.parseInt(input[0]),
                Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            Edge e1 = new Edge(Integer.parseInt(input[0]),
                Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            graph.addEdge(e1);
            graph1.addEdge(e);
        }
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(vertices + " vertices " + edges + " edges");
            for (int i = 0; i < Integer.parseInt(vertices); i++) {
                Iterable<Edge> adjedges = graph.adj(i);
                System.out.print(i + ": ");
                for (Edge each : adjedges) {
                    System.out.print(each.toString());
                    System.out.print("  ");
                }
                System.out.println();
            }
            break;
        case "DirectedPaths":
            String[] path = scan.nextLine().split(" ");
            DijkstraSP djsp = new DijkstraSP(graph1, Integer.parseInt(path[0]));
            if (!djsp.hasPathTo(Integer.parseInt(path[1]))) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(djsp.distTo(Integer.parseInt(path[1])));
            }
            break;
        case "ViaPaths":
            double d1 = 0.0;
            double d2 = 0.0;
            String[] paths = scan.nextLine().split(" ");
            DijkstraUndirectedSP djspv = new DijkstraUndirectedSP(graph,
                Integer.parseInt(paths[0]));
            if (!djspv.hasPathTo(Integer.parseInt(paths[1]))) {
                System.out.println("No Path Found.");
            } else {
                String way = "";
                d1 = djspv.distTo(Integer.parseInt(paths[1]));
                Iterable<Edge> viaway  = djspv.pathTo(Integer.parseInt(paths[1]));
                for (Edge each: viaway) {
                    way += each.getV() + " ";
                }
                djspv = new DijkstraUndirectedSP(graph, Integer.parseInt(paths[1]));
                if (!djspv.hasPathTo(Integer.parseInt(paths[2]))) {
                    System.out.println("No Path Found.");
                } else {
                    d2 = djspv.distTo(Integer.parseInt(paths[2])) + d1;
                    System.out.println(d2);
                    viaway  = djspv.pathTo(Integer.parseInt(paths[2]));
                    for (Edge each: viaway) {
                        way += each.getV() + " ";
                    }
                    System.out.println(way);
                }
            }
            break;

        default:
            break;
        }

    }
}
