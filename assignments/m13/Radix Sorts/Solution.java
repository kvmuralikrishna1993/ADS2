import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for lsd.
 */
class LSD {
    /**.
     * { sort using LSD alg. }
     * {time complexity is O(W*N)}
     * @param      a     { array }
     * @param      w     { string len }
     */
    public void sort(final String[] stringarray, final int stringlength) {
        /**.
         * { size of array }
         */
        int stringcount = stringarray.length;
        /**.
         * { number of characters possible }
         */
        final int total = 256;
        /**.
         * { auxilary array }
         */
        String[] aux = new String[stringcount];
        for (int d = stringlength - 1; d >= 0; d--) {
            int[] count = new int[total + 1];
            for (int i = 0; i < stringcount; i++) {
                count[stringarray[i].charAt(d) + 1]++;
            }
            for (int i = 0; i < total; i++) {
                count[i + 1] += count[i];
            }
            for (int i = 0; i < stringcount; i++) {
                aux[count[stringarray[i].charAt(d)]++] = stringarray[i];
            }
            for (int i = 0; i < stringcount; i++) {
                stringarray[i] = aux[i];
            }
        }
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**.
     * { main }.
     * {time complexity for main O(W*N) +O(N)}.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        String[] array = new String[count];
        for (int i = 0; i < count; i++) {
            array[i] = sc.nextLine();
        }
        LSD lsd = new LSD();
        lsd.sort(array, array[0].length());
        System.out.println(Arrays.toString(array));
    }
}