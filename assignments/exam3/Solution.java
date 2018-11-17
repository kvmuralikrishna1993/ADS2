import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}
			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
        In in = new In(file);
        String[] dictionary = in.readAllStrings();
        String[] dictionarylowcase = new String[dictionary.length];
        for (int i = 0; i < dictionary.length; i++) {
        	dictionarylowcase[i] = dictionary[i].toLowerCase();
        }
    	for (String word : dictionarylowcase) {
        	if (st.contains(word)) {
            	st.put(word, st.get(word) + 1);
        	} else {
            	st.put(word, 1);
        	}
    	}
		return st;
	}

}

class T9 {
	TST table;
	BinarySearchST<String, Integer> st;
	public T9(BinarySearchST<String, Integer> st1) {
		// your code goes here
	table = new TST();
	this.st = st1;
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		Iterable keys = st.keys();
		for ( Object key : keys) {
			String word  = key.toString();
			table.put(word,st.get(word));
		}
		return table.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> list =new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		map.put(2, list);
		list =new ArrayList<String>();
		list.add("d");
		list.add("e");
		list.add("f");
		map.put(3, list);
		list =new ArrayList<String>();
		list.add("g");
		list.add("h");
		list.add("i");
		map.put(4, list);
		list =new ArrayList<String>();
		list.add("j");
		list.add("k");
		list.add("l");
		map.put(5, list);
		list =new ArrayList<String>();
		list.add("m");
		list.add("n");
		list.add("o");
		map.put(6, list);
		list =new ArrayList<String>();
		list.add("p");
		list.add("q");
		list.add("r");
		list.add("s");
		map.put(7, list);
		list =new ArrayList<String>();
		list.add("t");
		list.add("u");
		list.add("v");
		map.put(8, list);
		list =new ArrayList<String>();
		list.add("w");
		list.add("x");
		list.add("y");
		list.add("z");
		map.put(9, list);
		ArrayList<String> check =new ArrayList<String>();
		for (char ch : t9Signature.toCharArray()) {
			for (String each : map.get(Character.getNumericValue(ch))) {

			}
		}
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		BinarySearchST<String, Integer> b2 = new BinarySearchST<String, Integer>();
		//each word prefix
		for (String each : words) {
			Iterable<String> list = table.keysWithPrefix(each);
			MaxPQ maxpq = new MaxPQ(k);
			maxpq.insert(each);
		}

			String[] array = new String[k];
			for (int i = 0; i < k; i++) {
				array[i] = b2.max().toString();
				b2.deleteMax();
			}
			return Arrays.asList(array);
		}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
