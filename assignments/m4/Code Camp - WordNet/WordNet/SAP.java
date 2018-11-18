import java.util.HashSet;
/**
 * Class for sap.
 */
public class SAP {
    /**
     * { var_graph }.
     */
    private Digraph graph;
    /**
     * { var_dist }.
     */
    private Integer dist;
    /**
     * { var_anscestor }.
     */
    private Integer ancestor;
    /**
     * Constructs the object.
     *
     * @param      dig     { parameter_description }
     */
    public SAP(final Digraph dig) {
        this.graph = dig;
        dist = Integer.MAX_VALUE;
    }
    /**
     * { function_length }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final int v, final int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(
            this.graph, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(
            this.graph, w);
        HashSet<Integer> s1 = new HashSet<Integer>();
        HashSet<Integer> s2 = new HashSet<Integer>();
        for (int i = 0; i < this.graph.vertices(); i++) {
            if (bfs1.hasPathTo(i)) {
                s1.add(i);
            }
            if (bfs2.hasPathTo(i)) {
                s2.add(i);
            }
        }
        s1.retainAll(s2);
        for (Integer i : s1) {
            if (dist >= bfs1.distTo(i) + bfs2.distTo(i)) {
                dist = bfs1.distTo(i) + bfs2.distTo(i);
                ancestor = i;
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return dist;
    }
    /**
     * { function_ancestor }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(
            this.graph, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(
            this.graph, w);
        HashSet<Integer> s1 = new HashSet<Integer>();
        HashSet<Integer> s2 = new HashSet<Integer>();
        for (int i = 0; i < this.graph.vertices(); i++) {
            if (bfs1.hasPathTo(i)) {
                s1.add(i);
            }
            if (bfs2.hasPathTo(i)) {
                s2.add(i);
            }
        }
        s1.retainAll(s2);
        for (Integer i : s1) {
            if (dist >= bfs1.distTo(i) + bfs2.distTo(i)) {
                dist = bfs1.distTo(i) + bfs2.distTo(i);
                ancestor = i;
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return ancestor;
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v,
                      final Iterable<Integer> w) {
        for (int i : v) {
            for (int j : w) {
                int tdist = length(i, j);
                if (tdist != -1 && dist >= tdist) {
                    dist = tdist;
                    ancestor = ancestor(i, j);
                }
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return dist;
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final Iterable<Integer> v, final Iterable<Integer> w) {
        for (int i : v) {
            for (int j : w) {
                int tdist = length(i, j);
                if (tdist != -1 && dist >= tdist) {
                    dist = tdist;
                    ancestor = ancestor(i, j);
                }
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return ancestor;
    }
}
