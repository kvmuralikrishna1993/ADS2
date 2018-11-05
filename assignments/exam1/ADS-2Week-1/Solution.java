import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
/**
 * Class for page rank.
 */
class PageRank {
    HashMap<Integer, Double> pr = new HashMap<>();
    HashMap<Integer, Double> tr = new HashMap<>();
    /**
     * { var_description }
     */
    Digraph graph;
    /**
     * Constructs the object.
     *
     * @param      digraph  The digraph
     */
    PageRank(Digraph digraph) {
        this.graph = digraph;
    }
    /**
     * { function_prget }.
     *
     * @param      v     { parameter_description }
     */
    public void get(int v) {
        Digraph reverse = graph.reverse();
        final int iterate = 1000;
        final Double intial = 1.0/v;
        //intialisation (iteration 1)
        for(int j = 0; j < v; j++) {
            pr.put(j, intial);
        }
        //copy intially to temp.
        for (HashMap.Entry<Integer, Double> entry : pr.entrySet()) {
            tr.put(entry.getKey(), entry.getValue());
        }
        //iterations 999.
        for(int i = 0; i < iterate; i++) {
            //calculating:
            for(int j = 0; j < v; j++) {
                //incoming calls.
                Iterable<Integer> adj = reverse.adj(j);
                double sum = 0.0;
                // all incoming values for each page.
                for(int each : adj) {
                    sum += (tr.get(each)*1.0)/(graph.outdegree(each)*1.0);
                }
                pr.put(j, sum);
            }
            //copying original to temp
            for (HashMap.Entry<Integer, Double> entry : pr.entrySet()) {
                tr.put(entry.getKey(), entry.getValue());
            }
        }
        toString(v);
        return;
    }
    /**
     * Returns a string representation of the object.
     *
     * @param      v     { parameter_description }
     */
    public void toString(int v) {
        System.out.println();
        for(int i = 0; i < v; i++) {
            System.out.println(i+" "+"-"+" "+pr.get(i));
        }
    }
}
/**
 * Class for web search.
 */
class WebSearch {
    
}
/**
 * Class for solution.
 */
public class Solution {
    /**
     * { function_main }.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        // read the first line of the input to get the number of vertices
        Scanner scan = new Scanner(System.in);
        int v = Integer.parseInt(scan.nextLine());
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        Digraph digraph = new Digraph(v);
        for (int i = 0; i < v; i++) {
            String[] tokens = scan.nextLine().split(" ");
            for(int j = 1; j < tokens.length; j++) {
                digraph.addEdge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[j]));
            }
        }
        System.out.println(digraph);
        PageRank page = new PageRank(digraph);
        page.get(v);
        // Create page rank object and pass the graph object to the constructor
        
        // print the page rank object
        
        // This part is only for the final test case
        
        // File path to the web content
        String file = "WebContent.txt";
        
        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
        
    }
}
