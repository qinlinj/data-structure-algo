package org.qinlinj.practical.others.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class TestFileReader {
    public static List<String> readFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }
                String[] lineWords = trimmedLine.split(" ");
                for (String word : lineWords) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}