package org.qinlinj.practical.multlinemerging;

import java.io.*;

// @formatter:off
/**
 * File IO Utilities
 *
 * Concept and Principles:
 * This utility class provides convenient methods for creating and closing file readers and writers.
 * It abstracts away the details of setting up IO streams and handles exceptions in a consistent way,
 * converting checked exceptions (IOException) into unchecked exceptions (RuntimeException).
 *
 * Advantages:
 * 1. Simplified API: Reduces boilerplate code for file operations
 * 2. Consistent Exception Handling: Converts checked exceptions to runtime exceptions
 * 3. Resource Management: Provides helper methods for properly closing resources
 * 4. Separation of Concerns: Centralizes file IO creation logic in one place
 *
 * Usage Example:
 * ```
 * // Reading from a file
 * BufferedReader reader = null;
 * try {
 *     reader = FileIOUtils.getReader("input.txt");
 *     String line;
 *     while ((line = reader.readLine()) != null) {
 *         // Process line
 *     }
 * } finally {
 *     FileIOUtils.closeReader(reader);
 * }
 *
 * // Writing to a file
 * BufferedWriter writer = null;
 * try {
 *     writer = FileIOUtils.getWriter("output.txt");
 *     writer.write("Hello, World!");
 *     writer.newLine();
 * } finally {
 *     FileIOUtils.closeWriter(writer);
 * }
 * ```
 *
 * Note: In modern Java, it's recommended to use try-with-resources instead,
 * but these utilities can still be useful for creating the readers/writers.
 */
public class FileIOUtils {
    /**
     * Creates and returns a BufferedReader for the specified file.
     *
     * This method sets up the proper stream chain:
     * FileInputStream → InputStreamReader → BufferedReader
     *
     * The InputStreamReader uses the default character encoding.
     * For custom encoding, a separate method would be needed.
     *
     * @param name The path/name of the file to read from
     * @return A BufferedReader ready to read from the specified file
     * @throws RuntimeException If an IOException occurs (file not found, permissions issues, etc.)
     */
    public static BufferedReader getReader(String name) {
        try {
            FileInputStream inputStream = new FileInputStream(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            return br;
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    /**
     * Creates and returns a BufferedWriter for the specified file.
     *
     * This method sets up the proper stream chain:
     * FileOutputStream → OutputStreamWriter → BufferedWriter
     *
     * The OutputStreamWriter uses the default character encoding.
     * For custom encoding, a separate method would be needed.
     *
     * Note: This will create a new file or overwrite an existing file.
     * For appending to existing files, a separate method would be needed.
     *
     * @param name The path/name of the file to write to
     * @return A BufferedWriter ready to write to the specified file
     * @throws RuntimeException If an IOException occurs (permissions issues, disk full, etc.)
     */
    public static BufferedWriter getWriter(String name) {
        try {
            FileOutputStream outputStream = new FileOutputStream(name);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            return bw;
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    /**
     * Safely closes a Reader (or its subclasses like BufferedReader).
     *
     * This method handles the null check and exception handling, making it
     * convenient to use in finally blocks for proper resource cleanup.
     *
     * @param reader The Reader to close, can be null
     * @throws RuntimeException If an IOException occurs during closing
     */
    public static void closeReader(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    /**
     * Safely closes a Writer (or its subclasses like BufferedWriter).
     *
     * This method handles the null check and exception handling, making it
     * convenient to use in finally blocks for proper resource cleanup.
     *
     * Important: Closing a writer typically flushes any buffered output before closing.
     * If the close operation fails, data may be lost.
     *
     * @param writer The Writer to close, can be null
     * @throws RuntimeException If an IOException occurs during closing
     */
    public static void closeWriter(Writer writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
}