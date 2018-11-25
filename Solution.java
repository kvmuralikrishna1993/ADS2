import java.util.Scanner;
public class Solution {
private Solution() {

}
public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    //vertices and edges
    int vertices = Integer.parseInt(scan.nextLine());
    int edges = Integer.parseInt(scan.nextLine());
    //intialising graph
    EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
    //intialising Binary ST
    BinaryST<Double, Integer> bst = new BinaryST<Double, Integer>();
    //adding edges to Binary ST and creating graph by adding edges and weights
    System.out.println("Graph:");
    System.out.println("-------------------------------------------");
    for (int i = 0; i < edges; i++) {
        String[] inputs = scan.nextLine().split(" ");
        Edge addedge = new Edge(Integer.parseInt(inputs[0]),
            Integer.parseInt(inputs[1]), Double.parseDouble(inputs[2]));
            graph.addEdge(addedge);
            bst.put(Double.parseDouble(inputs[2]),i);
            System.out.println(Integer.parseInt(inputs[0])+" <-> "+Integer.parseInt(inputs[1])+" : "+Double.parseDouble(inputs[2]));
        }
    System.out.println();
    KruskalMST mst = new KruskalMST(graph);
    Iterable<Edge> mstedges = mst.edges();
    MaxPQ maxpq = new MaxPQ();
    System.out.println("Mst Tree:");
    System.out.println("-------------------------------------------");
    for (Edge each : mstedges) {
        maxpq.insert(each.weight());
        System.out.println(each);
        //System.out.println(each.weight());
    }
    System.out.println();
    String max = maxpq.max().toString();
    System.out.println("Longest MST edge: "+max);
    int lessedges = bst.getRank(Double.parseDouble(max));
    System.out.println("Number of edges lower than "+max+" : "+lessedges);
    System.out.println();
    }
}