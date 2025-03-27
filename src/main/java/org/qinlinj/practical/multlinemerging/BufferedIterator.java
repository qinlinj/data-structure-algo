package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.IOException;

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
