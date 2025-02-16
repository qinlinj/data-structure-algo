package org.qinlinj.linear.algo.binarysearch;

import org.qinlinj.linear.array.ArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class IpLocationParser {
    private static ArrayList<IpLocation> sortedIpLocationList = new ArrayList<>();

    static {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("data\\ip_location.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static class IpLocation {
        private long startIp;
        private long endIp;
        private int location;
    }
}
