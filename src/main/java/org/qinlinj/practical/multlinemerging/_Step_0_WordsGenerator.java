package org.qinlinj.practical.multlinemerging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class _Step_0_WordsGenerator {
    private static Random r = new Random();

    public static void main(String[] args) throws IOException {

        BufferedWriter writer = FileIOUtils.getWriter("data/top100/words.txt");

        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int m = chars.length;

        for (int i = 0; i < 10000; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < r.nextInt(16); j++) {
                line.append(chars[r.nextInt(m)]);
            }
            if (line.length() == 0) continue;
            writer.write(line.toString());
            writer.newLine();
        }

        FileIOUtils.closeWriter(writer);
    }
}
