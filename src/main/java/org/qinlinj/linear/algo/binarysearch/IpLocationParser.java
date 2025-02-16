package org.qinlinj.linear.algo.binarysearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class IpLocationParser {
    private static final ArrayList<IpLocation> sortedIpLocationList = new ArrayList<>();

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/ip_location.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tmps = line.split(" ");
                IpLocation ipLocation = new IpLocation();
                ipLocation.startIp = ip2Score(tmps[0]);
                ipLocation.endIp = ip2Score(tmps[1]);
                ipLocation.location = tmps[2];
                sortedIpLocationList.add(ipLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // sort IpLocationList
        sortedIpLocationList.sort(new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                if (o1.startIp < o2.startIp) return -1;
                else if (o1.startIp > o2.startIp) return 1;
                else return 0;
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(getIpLocation("202.101.48.198"));
    }

    private static long ip2Score(String ip) {
        String[] tmps = ip.split("\\.");
        long res = 256 * 256 * 256 * Long.parseLong(tmps[0]) +
                256 * 256 * Long.parseLong(tmps[1]) +
                256 * Long.parseLong(tmps[2]) +
                Long.parseLong(tmps[3]);
        return res;
    }

    public static String getIpLocation(String ip) {
        long target = ip2Score(ip);
        int left = 0;
        int right = sortedIpLocationList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target >= sortedIpLocationList.get(mid).startIp) {
                if (mid == sortedIpLocationList.size() - 1 || target < sortedIpLocationList.get(mid + 1).startIp) {
                    if (target <= sortedIpLocationList.get(mid).endIp) {
                        return sortedIpLocationList.get(mid).location;
                    } else break;
                } else left = mid + 1;
            } else if (target > sortedIpLocationList.get(mid).endIp) {
                left = mid + 1;
            } else right = mid - 1;
        }
        return null;
    }


    private static class IpLocation {
        private long startIp;
        private long endIp;
        private String location;
    }
}
