package org.qinlinj.practical.multlinemerging;

import java.io.*;

public class FileIOUtils {
    public static BufferedReader getReader(String name) {
        try {
            FileInputStream inputStream = new FileInputStream(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            return br;
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }

    public static BufferedWriter getWriter(String name) {

    }

    public static void closeReader(Reader reader) {

    }

    public static void closeWriter(Writer writer) {


    }

}