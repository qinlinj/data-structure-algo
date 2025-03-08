package org.qinlinj.linear.algo.binarysearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * IP Location Parser
 * <p>
 * This class parses IP address ranges from a file and provides functionality
 * to determine the geographic location of an IP address using binary search.
 * <p>
 * The file format is expected to be:
 * [start_ip] [end_ip] [location]
 * <p>
 * Example: 202.101.48.0 202.101.48.255 Shanghai
 * <p>
 * The class uses a binary search algorithm to efficiently find the location
 * of an IP address within the sorted list of IP ranges.
 */
public class IpLocationParser {
    /**
     * List to store IP location information sorted by start IP address.
     * Static to ensure it's loaded only once and shared across all instances.
     */
    private static final ArrayList<IpLocation> sortedIpLocationList = new ArrayList<>();

    /**
     * Static initializer block that loads IP location data from a file when the class is first loaded.
     * The data is read, parsed, converted to numerical representation, and sorted by start IP.
     */
    static {
        try {
            // Open the file containing IP location information
            BufferedReader br = new BufferedReader(new FileReader("data/ip_location.txt"));
            String line = null;

            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Parse the line into components (startIp, endIp, location)
                String[] tmps = line.split(" ");

                // Create a new IpLocation object
                IpLocation ipLocation = new IpLocation();
                // Convert IP strings to numerical values for easier comparison
                ipLocation.startIp = ip2Score(tmps[0]);
                ipLocation.endIp = ip2Score(tmps[1]);
                ipLocation.location = tmps[2];

                // Add to our list
                sortedIpLocationList.add(ipLocation);
            }
        } catch (IOException e) {
            // If file reading fails, throw a runtime exception
            throw new RuntimeException(e);
        }

        // Sort the list by startIp to enable binary search
        sortedIpLocationList.sort(new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                // Compare by startIp
                if (o1.startIp < o2.startIp) return -1;
                else if (o1.startIp > o2.startIp) return 1;
                else return 0;
            }
        });
    }

    /**
     * Main method for demonstration purposes.
     * Queries the location of a specific IP address.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Example: Find the location of this IP address
        System.out.println(getIpLocation("202.101.48.198"));
    }

    /**
     * Converts an IP address string (e.g., "192.168.1.1") to a numerical score.
     * This makes IP address comparison more efficient.
     * <p>
     * The formula treats the IP as a base-256 number:
     * a.b.c.d = a*256³ + b*256² + c*256 + d
     *
     * @param ip The IP address string to convert
     * @return A long integer representing the IP address
     * <p>
     * Example: "192.168.1.1" becomes 3232235777
     */
    private static long ip2Score(String ip) {
        // Split the IP by dots
        String[] tmps = ip.split("\\.");

        // Convert to numerical form using base-256 conversion
        long res = 256 * 256 * 256 * Long.parseLong(tmps[0]) +
                256 * 256 * Long.parseLong(tmps[1]) +
                256 * Long.parseLong(tmps[2]) +
                Long.parseLong(tmps[3]);
        return res;
    }

    /**
     * Determines the location of an IP address using binary search.
     * <p>
     * The algorithm:
     * 1. Converts the IP to a numerical value
     * 2. Performs binary search on the sorted list to find the appropriate range
     * 3. Checks if the IP falls within the range's boundaries
     *
     * @param ip The IP address to locate
     * @return The location string if found, or null if not found
     * <p>
     * Example walkthrough for IP "202.101.48.198":
     * 1. Convert to numerical value: 3389380550
     * 2. Use binary search to find a range where:
     * - target >= range.startIp
     * - target < next_range.startIp (or it's the last range)
     * 3. Then verify target <= range.endIp to confirm it's within the range
     */
    public static String getIpLocation(String ip) {
        // Convert IP to numerical form
        long target = ip2Score(ip);

        // Initialize binary search pointers
        int left = 0;
        int right = sortedIpLocationList.size() - 1;

        // Binary search loop
        while (left <= right) {
            // Calculate middle index
            int mid = left + (right - left) / 2;

            // Current range's start IP is less than or equal to target
            if (target >= sortedIpLocationList.get(mid).startIp) {
                // Check if we've found the right range:
                // Either we're at the last range OR target is less than the next range's start IP
                if (mid == sortedIpLocationList.size() - 1 || target < sortedIpLocationList.get(mid + 1).startIp) {
                    // Verify target is within the range's bounds
                    if (target <= sortedIpLocationList.get(mid).endIp) {
                        // Found! Return the location
                        return sortedIpLocationList.get(mid).location;
                    } else break; // Target is in a gap between ranges
                } else left = mid + 1; // Look in the right half
            } else if (target > sortedIpLocationList.get(mid).endIp) {
                // Current range's end IP is less than target, look right
                left = mid + 1;
            } else {
                // Current range's start IP is greater than target, look left
                right = mid - 1;
            }
        }

        // IP address not found in any range
        return null;
    }

    /**
     * Private inner class to represent an IP address range and its location.
     * <p>
     * Each instance stores:
     * - startIp: The beginning of the IP range in numerical form
     * - endIp: The end of the IP range in numerical form
     * - location: The geographic location associated with this IP range
     */
    private static class IpLocation {
        private long startIp;     // Lower bound of the IP range
        private long endIp;       // Upper bound of the IP range
        private String location;  // Geographic location for this range
    }
}