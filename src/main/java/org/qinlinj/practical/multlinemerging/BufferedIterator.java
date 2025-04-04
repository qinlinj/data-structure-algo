package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.IOException;

// @formatter:off
/**
 * BufferedIterator
 *
 * Concept and Principles:
 * This class implements an iterator pattern for reading data from a BufferedReader line by line.
 * It provides a convenient interface to access file data sequentially without loading
 * the entire file into memory at once, which is especially useful for processing large files.
 *
 * The iterator follows a "read-ahead" or "peek" pattern where the next line is pre-fetched
 * into a buffer (head) when hasNext() is called, and then returned when next() is called.
 *
 * Advantages:
 * 1. Memory Efficiency: Reads one line at a time rather than loading the entire file
 * 2. Simplified Interface: Abstracts away the details of BufferedReader and IOException handling
 * 3. Iterator Pattern: Follows the standard iterator pattern (hasNext/next) familiar to developers
 * 4. Resource Management: Provides a close() method to properly release resources
 *
 * Usage Example:
 * ```
 * BufferedReader reader = new BufferedReader(new FileReader("large_file.txt"));
 * BufferedIterator iterator = new BufferedIterator(reader);
 *
 * try {
 *     while (iterator.hasNext()) {
 *         String line = iterator.next();
 *         // Process the line
 *     }
 * } finally {
 *     iterator.close();
 * }
 * ```
 *
 * Notes:
 * - This implementation always reads a new line when hasNext() is called, which means
 *   hasNext() should only be called once before each next() call to avoid skipping lines.
 */
public class BufferedIterator {
    /**
     * The underlying BufferedReader that this iterator wraps around.
     * This reader provides efficient buffered reading from character-input streams.
     */
    private BufferedReader reader;

    /**
     * Holds the current line that has been read ahead and will be returned by next().
     * This field acts as a buffer that holds the "peeked" line.
     */
    private String head;

    /**
     * Constructs a new BufferedIterator that wraps around the provided BufferedReader.
     *
     * @param reader The BufferedReader to read lines from
     */
    BufferedIterator(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Returns the current line that was previously read by hasNext().
     * <p>
     * Important: This method does not advance to the next line. It only returns
     * the line that was already read by a previous call to hasNext().
     * Always call hasNext() before calling next() to ensure head is populated.
     * <p>
     * Time Complexity: O(1) - simply returns the cached line
     *
     * @return The current line from the reader, or null if no line has been read yet
     */
    public String next() {
        return head;
    }

    /**
     * Closes the underlying BufferedReader to release system resources.
     * This method should be called when done with the iterator to prevent resource leaks.
     *
     * @throws Exception If an error occurs when closing the reader
     */
    public void close() throws Exception {
        this.reader.close();
    }

    /**
     * Checks if there is another line available to read and advances the internal
     * position to the next line.
     * <p>
     * This method reads the next line from the BufferedReader and stores it in the
     * head field for later retrieval by next(). It catches and handles any IOException
     * that might occur during reading.
     * <p>
     * Time Complexity: O(L) where L is the length of the next line
     *
     * @return true if there is another line available, false if the end of the stream has been reached
     */
    public boolean hasNext() {
        try {
            // Read the next line and store it in head
            head = this.reader.readLine();
        } catch (IOException e) {
            // If an exception occurs, print the stack trace and set head to null
            e.printStackTrace();
            head = null;
        }
        // Return true if a line was successfully read, false otherwise
        return head != null;
    }
}