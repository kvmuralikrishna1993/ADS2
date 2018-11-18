import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.Socket;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * { item_description }.
 */
public final class In {
    /**
     * { var_description }.
     */
    private static final String CHARSET_NAME = "UTF-8";
    /**
     * { var_description }.
     */
    private static final Locale LOCALE = Locale.US;
    /**
     * { var_description }.
     */
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile(
        "\\p{javaWhitespace}+");
    /**
     * { var_description }.
     */
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");
    /**
     * { var_description }.
     */
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
    /**
     * { var_description }.
     */
    private Scanner scanner;
   /**
     * Initializes an input stream from standard input.
     */
    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in),
            CHARSET_NAME);
        scanner.useLocale(LOCALE);
    }
   /**
    * Constructs the object.
    *
    * @param      socket  The socket
    */
   public In(final Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket argument is null");
        }
        try {
            InputStream is = socket.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open "
                +socket, ioe);
        }
    }
   /**
    * Constructs the object.
    *
    * @param      url   The url
    */
   public In(final URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url argument is null");
        }
        try {
            URLConnection site = url.openConnection();
            InputStream is     = site.getInputStream();
            scanner            = new Scanner(new BufferedInputStream(is),
                CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open "
                + url, ioe);
        }
    }
   /**
    * Constructs the object.
    *
    * @param      file  The file
    */
   public In(final File file) {
        if (file == null) {
            throw new IllegalArgumentException(
            "file argument is null");
        }
        try {
            // for consistency with StdIn, wrap with BufferedInputStream instead of use
            // file as argument to Scanner
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }
   /**
    * Constructs the object.
    *
    * @param      name  The name
    */
   public In(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(
            "argument is null");
        }
        try {
            File file = new File(name);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis),
                    CHARSET_NAME);
                scanner.useLocale(LOCALE);
                return;
            }
            URL url = getClass().getResource(name);
            if (url == null) {
                url = getClass().getClassLoader().getResource(name);
            }
            if (url == null) {
                url = new URL(name);
            }
            URLConnection site = url.openConnection();
            InputStream is     = site.getInputStream();
            scanner            = new Scanner(new BufferedInputStream(is),
                CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + name, ioe);
        }
    }
    /**
     * Constructs the object.
     *
     * @param      scanner  The scanner
     */
    public In(final Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException(
            "scanner argument is null");
        }
        this.scanner = scanner;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public boolean exists()  {
        return scanner != null;
    }
   /**
    * Determines if empty.
    *
    * @return     True if empty, False otherwise.
    */
    public boolean isEmpty() {
        return !scanner.hasNext();
    }
   /**
    * Determines if it has next line.
    *
    * @return     True if has next line, False otherwise.
    */
   public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
    /**
     * Determines if it has next character.
     *
     * @return     True if has next character, False otherwise.
     */
    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }
   /**
    * Reads a line.
    *
    * @return     { description_of_the_return_value }
    */
   public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }
    /**
     * Reads a character.
     *
     * @return     { description_of_the_return_value }
     */
    public char readChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        try {
            String ch = scanner.next();
            assert ch.length() == 1 : "Internal (Std)In.readChar() error!"
                + " Please contact the authors.";
            scanner.useDelimiter(WHITESPACE_PATTERN);
            return ch.charAt(0);
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attempts to read a 'char' value from the input stream, "
                                           + "but no more tokens are available");
        }
    }  
   /**
    * Reads all.
    *
    * @return     { description_of_the_return_value }
    */
   public String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
        return result;
    }
   /**
    * Reads a string.
    *
    * @return     { description_of_the_return_value }
    */
   public String readString() {
        try {
            return scanner.next();
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attempts to read a 'String' value from the input stream, "
                                           + "but no more tokens are available");
        }
    }
   /**
    * Reads an int.
    *
    * @return     { description_of_the_return_value }
    */
   public int readInt() {
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read an 'int' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read an 'int' value from the input stream, "
                + "but no more tokens are available");
        }
    }
   /**
    * Reads a double.
    *
    * @return     { description_of_the_return_value }
    */
   public double readDouble() {
        try {
            return scanner.nextDouble();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'double' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'double' value from the input stream, "
                + "but no more tokens are available");
        }
    }
   /**
    * Reads a float.
    *
    * @return     { description_of_the_return_value }
    */
   public float readFloat() {
        try {
            return scanner.nextFloat();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'float' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'float' value from the input stream, "
                + "but no more tokens are available");
        }
    }
   /**
    * Reads a long.
    *
    * @return     { description_of_the_return_value }
    */
   public long readLong() {
        try {
            return scanner.nextLong();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'long' value from the input stream, "
                                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'long' value from the input stream, "
                                    + "but no more tokens are available");
        }
    }
   /**
    * Reads a short.
    *
    * @return     { description_of_the_return_value }
    */
   public short readShort() {
        try {
            return scanner.nextShort();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'short' value from the input stream, "
                                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'short' value from the input stream, "
                                    + "but no more tokens are available");
        }
    }
   /**
    * Reads a byte.
    *
    * @return     { description_of_the_return_value }
    */
    public byte readByte() {
        try {
            return scanner.nextByte();
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'byte' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'byte' value from the input stream, "
                + "but no more tokens are available");
        }
    }
    /**
     * Reads a boolean.
     *
     * @return     { description_of_the_return_value }
     */
    public boolean readBoolean() {
        try {
            String token = readString();
            if ("true".equalsIgnoreCase(token)) {
                return true;
            }
            if ("false".equalsIgnoreCase(token)) {
                return false;
            }
            if ("1".equals(token)) {
                return true;
            }
            if ("0".equals(token)) {
                return false;
            }
            throw new InputMismatchException(
                "attempts to read a 'boolean' value from the input stream, "
                                + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attempts to read a 'boolean' value from the input stream, "
                                + "but no more tokens are available");
        }
    }
    /**
     * Reads all strings.
     *
     * @return     { description_of_the_return_value }
     */
    public String[] readAllStrings() {
        // we could use readAll.trim().split(), but that's not consistent
        // since trim() uses characters 0x00..0x20 as whitespace
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }
        String[] decapitokens = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++) {
            decapitokens[i] = tokens[i + 1];
        }
        return decapitokens;
    }
    /**
     * Reads all lines.
     *
     * @return     { description_of_the_return_value }
     */
    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[lines.size()]);
    }
    /**
     * Reads all ints.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Integer.parseInt(fields[i]);
        }
        return vals;
    }
    /**
     * Reads all longs.
     *
     * @return     { description_of_the_return_value }
     */
    public long[] readAllLongs() {
        String[] fields = readAllStrings();
        long[] vals = new long[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Long.parseLong(fields[i]);
        }
        return vals;
    }

    /**
     * Reads all doubles.
     *
     * @return     { description_of_the_return_value }
     */
    public double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Double.parseDouble(fields[i]);
        }
        return vals;
    }
   /**
     * Closes this input stream.
     */
    public void close() {
        scanner.close();
    }
    /**
     * Reads ints.
     *
     * @param      filename  The filename
     *
     * @return     { description_of_the_return_value }
     */
    @Deprecated
    public static int[] readInts(final String filename) {
        return new In(filename).readAllInts();
    }
   /**
    * Reads doubles.
    *
    * @param      filename  The filename
    *
    * @return     { description_of_the_return_value }
    */
    @Deprecated
    public static double[] readDoubles(final String filename) {
        return new In(filename).readAllDoubles();
    }
   /**
    * Reads strings.
    *
    * @param      filename  The filename
    *
    * @return     { description_of_the_return_value }
    */
    @Deprecated
    public static String[] readStrings(final String filename) {
        return new In(filename).readAllStrings();
    }
    /**
     * Reads ints.
     *
     * @return     { description_of_the_return_value }
     */
    @Deprecated
    public static int[] readInts() {
        return new In().readAllInts();
    }
   /**
    * Reads doubles.
    *
    * @return     { description_of_the_return_value }
    */
    @Deprecated
    public static double[] readDoubles() {
        return new In().readAllDoubles();
    }
   /**
    * Reads strings.
    *
    * @return     { description_of_the_return_value }
    */
   @Deprecated
    public static String[] readStrings() {
        return new In().readAllStrings();
    }
}
