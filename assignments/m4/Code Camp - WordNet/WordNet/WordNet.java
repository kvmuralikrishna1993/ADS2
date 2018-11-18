import java.util.HashMap;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * { item_description }.
     */
    private HashMap<Integer, Bag<String>> synset;
    /**
     * { item_description }.
     */
    private HashMap<String, Bag<Integer>> synset1;
    /**
     * { var_description }.
     */
    private Digraph graph;
    /**
     * { var_description }.
     */
    private SAP sap;
    // constructor takes the name of the two input files

    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     * @throws     Exception { exception_description }
     */
    public WordNet(final String synsets,
                   final String hypernyms) throws Exception {
        In syn = new In(synsets);
        In hyp = new In(hypernyms);
        this.synset = new HashMap<Integer, Bag<String>>();
        this.synset1 = new HashMap<String, Bag<Integer>>();
        for (String line : syn.readAllLines()) {
            String[] temp = line.split(",");
            int id = Integer.parseInt(temp[0]);
            synset.putIfAbsent(id, new Bag<String>());
            for (String each : temp[1].split(" ")) {
                synset.get(id).add(each);
                synset1.putIfAbsent(each, new Bag<Integer>());
                synset1.get(each).add(id);
            }
        }
        graph = new Digraph(synset.size());
        for (String line : hyp.readAllLines()) {
            String[] temp = line.split(",");
            for (int i = 1; i < temp.length; i++) {
                graph.addEdge(Integer.parseInt(temp[0]),
                    Integer.parseInt(temp[i]));
            }
        }
        int count = 0;
        for (int i = 0; i < graph.vertices(); i++) {
            if (graph.outdegree(i) == 0) {
                count++;
            }
        }
        if (count != 1) {
            throw new Exception("Multiple roots");
        }
        DirectedCycle directedCycle = new DirectedCycle(graph);
        if (directedCycle.hasCycle()) {
            throw new Exception("Cycle detected");
        }
    }

    // returns all WordNet nouns

    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> nouns() {
        return this.synset1.keySet();
    }

    // is the word a WordNet noun?

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return this.synset1.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)

    /**
     * { function_description }.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        sap = new SAP(this.graph);
        int dist = sap.length(synset1.get(nounA), synset1.get(nounB));
        return dist;
    }
    /**
     * Gets the graph.
     *
     * @return     The graph.
     */
    public Digraph getGraph() {
        return this.graph;
    }
    /**
     * { function_description }.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public String sap(final String nounA, final String nounB) {
        sap = new SAP(this.graph);
        int id = sap.ancestor(synset1.get(nounA), synset1.get(nounB));
        String ances = "";
        for (String node : synset.get(id)) {
            ances = node + " " + ances;
        }
        return ances.trim();
    }
}
