package org.qinlinj.nonlinear.highlevel.map;

import org.qinlinj.others.test.TestFileReader;

import java.util.List;

public class TestMap {
    static List<String> words = TestFileReader.readFile("data\\test-data.txt");

    private static double testMap(Map<String, Integer> map) {
        long startTime = System.nanoTime();
        for (String word : words) {
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                map.set(word, count + 1);
            } else {
                map.add(word, 1);
            }
        }
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        Map<String, Integer> llMap = new LinkedListMap<>();
        double time1 = testMap(llMap);
        System.out.println("LinkedListMap: " + time1);

        Map<String, Integer> bstMap = new BSTMap<>();
        double time2 = testMap(bstMap);
        System.out.println("BSTMap: " + time2);

        Map<String, Integer> hashMap = new HashMap<>();
        double time3 = testMap(hashMap);
        System.out.println("HashMap: " + time3);
    }
}

