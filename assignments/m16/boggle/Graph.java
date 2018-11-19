import java.util.Scanner;

/**
 * Class for graph l.
 */
class Graph {
    /**
     * No.of Vertices.
     */
    private int vertices;
    /**
     * No.of Edges.
     */
    private int edges;
    /**
     * Scanner Object.
     */
    private Scanner sca;
    /**
     * String Array.
     */
    private String[] input;
    /**
     * Bag Array.
     */
    private Bag<Integer>[] bag;
    /**
     * COunt of the Edges.
     */
    private int count;
    /**
     * Constructs the object.
     *
     * @param      vert  The vertical
     */
    Graph(final int vert) {
        vertices = vert;
        bag = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            bag[i] = new Bag();
        }
        count = 0;
    }

    /**
     * Constructs the object.
     *
     * @param      sc    The scanner
     */
    Graph(final Scanner sc) {
        sca = sc;
        vertices = Integer.parseInt(sca.nextLine());
        edges = Integer.parseInt(sca.nextLine());
        input = sca.nextLine().split(",");
        bag = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            bag[i] = new Bag();
        }
        count = 0;
    }

    /**
     * Vertices Method.
     *
     * @return     { returns no.of Vertices }
     */
    public int vert() {
        return vertices;
    }

    /**
     * Edges Method.
     *
     * @return     { returns no.of Edges }
     */
    public int edge() {
        return edges;
    }

    /**
     * Adds an edge, connects two vertices.
     * In worst case the complexity is O(N).
     *
     * @param      vert1  The vertical 1
     * @param      vert2  The vertical 2
     */
    public void addEdge(final int vert1, final int vert2) {
        if (!hasEdge(vert1, vert2)) {
            count++;
        }
        bag[vert1].add(vert2);
        bag[vert2].add(vert1);
    }

    /**
     * Iterable to iterate no.of keys.
     * Complexity is O(N).
     * It iterates through the Bag.
     *
     * @param      v     { Integer Value }
     *
     * @return     { Returns Iterable Integer }
     */
    public Iterable<Integer> adj(final int v) {
        return bag[v];
    }

    /**
     * Determines if it has edge.
     * Complexity of hasEdge is O(N).
     * It iterates through bag in worst case.
     *
     * @param      v     { Vertex Index 1 }
     * @param      w     { Vertex Index 2 }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        Bag bagobj = bag[v];
        return bagobj.contains(w);
    }

    /**
     * To String Method to Print List.
     * Complexity is O(N ^ 2).
     *
     * @return     { Returns String }
     */
    public String tostring() {
        String str = vert() + " vertices, " + count + " edges" + "\n";
        if (count == 0) {
            str += "No edges";
            return str;
        }
        int i = 0;
        for (i = 0; i < vert() - 1; i++) {
            str += input[i] + ": ";
            for (int word : adj(i)) {
                str += input[word] + " ";
            }
            str += "\n";
        }
        str += input[i] + ": ";
        for (int word : adj(i)) {
            str += input[word] + " ";
        }
        return str;
    }
}
