import java.util.Scanner;
/**
 * Class for Solution.
 */
public final class Solution {
  /**
   * Constructs the object.
   */

  private Solution() {
  }
  /**
   * Main function.
   *
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    Scanner scan = new Scanner(System.in);
    Digraph digraph = new Digraph(scan);
    DirectedCycle dcircle = new DirectedCycle(digraph);
    if (dcircle.hasCycle()) {
      System.out.println("Cycle exists.");
    } else {
      System.out.println("Cycle doesn't exists.");
    }
  }
}
