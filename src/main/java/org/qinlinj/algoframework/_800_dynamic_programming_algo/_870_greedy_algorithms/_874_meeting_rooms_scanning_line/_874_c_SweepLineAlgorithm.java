package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

/**
 * MEETING ROOMS PROBLEM - SWEEP LINE ALGORITHM IMPLEMENTATION
 * LeetCode 253: Meeting Rooms II
 * <p>
 * Sweep Line Algorithm Concept:
 * Imagine a vertical line sweeping across the timeline from left to right.
 * The line carries a counter that tracks concurrent meetings:
 * - When sweep line hits a meeting START: counter += 1
 * - When sweep line hits a meeting END: counter -= 1
 * - Maximum counter value = minimum meeting rooms needed
 * <p>
 * Key Innovation - Two Pointer Technique:
 * Instead of creating explicit events, we:
 * 1. Extract start times and end times into separate arrays
 * 2. Sort both arrays independently
 * 3. Use two pointers to simulate sweep line movement
 * 4. Compare current start vs current end to determine next event
 * <p>
 * Algorithm Steps:
 * 1. Separate start times and end times from meetings
 * 2. Sort start[] and end[] arrays independently
 * 3. Initialize two pointers i=0, j=0 and counter=0
 * 4. While both pointers are valid:
 * - If start[i] < end[j]: meeting starts, counter++, i++
 * - Else: meeting ends, counter--, j++
 * - Track maximum counter value
 * 5. Return maximum counter value
 * <p>
 * Why This Works:
 * - Sorting preserves chronological order of events
 * - Two pointers efficiently merge sorted sequences
 * - Counter accurately tracks concurrent meetings at each moment
 * - No need to store actual time values or create event objects
 * <p>
 * Edge Case Handling:
 * - Empty input: return 0
 * - Single meeting: return 1
 * - Simultaneous start/end: end events processed first (start[i] >= end[j])
 * <p>
 * Time Complexity: O(n log n) for sorting + O(n) for processing = O(n log n)
 * Space Complexity: O(n) for start/end arrays
 * <p>
 * This elegant algorithm transforms a complex scheduling problem into
 * a simple event-processing problem using fundamental CS techniques.
 */

public class _874_c_SweepLineAlgorithm {
}
