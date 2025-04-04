package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * BufferedIterator
 * <p>
 * Concept and Principles:
 * This class implements an iterator pattern for reading data from a BufferedReader line by line.
 * It provides a convenient interface to access file data sequentially without loading
 * the entire file into memory at once, which is especially useful for processing large files.
 * <p>
 * The iterator follows a "read-ahead" or "peek" pattern where the next line is pre-fetched
 * into a buffer (head) when hasNext() is called, and then returned when next() is called.
 * <p>
 * Advantages:
 * 1. Memory Efficiency: Reads one line at a time rather than loading the entire file
 * 2. Simplified Interface: Abstracts away the details of BufferedReader and IOException handling
 * 3. Iterator Pattern: Follows the standard iterator pattern (hasNext/next) familiar to developers
 * 4. Resource Management: Provides a close() method to properly release resources
 * <p>
 * Usage Example:
 * ```
 * BufferedReader reader = new BufferedReader(new FileReader("large_file.txt"));
 * BufferedIterator iterator = new BufferedIterator(reader);
 * <p>
 * try {
 * while (iterator.hasNext()) {
 * String line = iterator.next();
 * // Process the line
 * }
 * } finally {
 * iterator.close();
 * }
 * ```
 * <p>
 * Notes:
 * - This implementation always reads a new line when hasNext() is called, which means
 * hasNext() should only be called once before each next() call to avoid skipping lines.
 */
public class BufferedIterator {
    private BufferedReader reader;
    private String head;

    BufferedIterator(BufferedReader reader) {
        this.reader = reader;
    }

    public String next() {
        return head;
    }

    public void close() throws Exception {
        this.reader.close();
    }

    public boolean hasNext() {
        try {
            head = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            head = null;
        }
        return head != null;
    }
}
