package org.qinlinj.linear.algo.sort.pratice;

public class _164_MaximumGap {
    class Solution {
        public int maximumGap(int[] nums) {
            class Bucket {
                public int min = Integer.MAX_VALUE;
                public int max = Integer.MIN_VALUE;
                public boolean hasData = false;
            }

            if (nums == null || nums.length < 2) {
                return 0;
            }
            int max = nums[0];
            int min = nums[0];
            for (int i : nums) {
                max = Math.max(max, i);
                min = Math.min(min, i);
            }
            if (max == min) return 0;

            int minGap = (int) Math.ceil((double) (max - min) / (nums.length - 1));
            // int minGap = Math.max(1, (max - min) / (nums.length - 1));

            // int bucketNum = (max - min) / minGap + 1;
            int bucketNum = nums.length;

            Bucket[] buckets = new Bucket[bucketNum];
            for (int i = 0; i < bucketNum; i++) {
                buckets[i] = new Bucket();
            }

            for (int num : nums) {
                int bucketId = (num - min) / minGap;
                buckets[bucketId].hasData = true;
                buckets[bucketId].max = Math.max(buckets[bucketId].max, num);
                buckets[bucketId].min = Math.min(buckets[bucketId].min, num);
            }

            int maxGap = 0;
            int prevBucketMax = min;
            for (Bucket bucket : buckets) {
                if (!bucket.hasData) continue;
                maxGap = Math.max(maxGap, bucket.min - prevBucketMax);
                prevBucketMax = bucket.max;
            }
            return maxGap;
        }

        //---------------------------------------
        // Using RadixSorter
        public int maximumGap1(int[] nums) {
            if (nums == null || nums.length < 2)
                return 0;

            new NegativeRadixSorter().sort(nums); // O(n)
            int maxGap = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
            }

            return maxGap;
        }

        class NegativeRadixSorter {
            public void sort(int[] data) {
                if (data == null || data.length <= 1)
                    return;

                int min = data[0];
                int max = data[0];
                for (int i = 1; i < data.length; i++) {
                    min = Math.min(min, data[i]);
                    max = Math.max(max, data[i]);
                }

                int shift = min < 0 ? -min : 0;
                if (shift > 0) {
                    for (int i = 0; i < data.length; i++) {
                        data[i] += shift;
                    }
                    max += shift;
                }

                for (int exp = 1; max / exp > 0; exp *= 10) {
                    countSort(data, exp);
                }

                if (shift > 0) {
                    for (int i = 0; i < data.length; i++) {
                        data[i] -= shift;
                    }
                }
            }

            private void countSort(int[] data, int exp) {
                int[] count = new int[10];
                for (int i = 0; i < data.length; i++) {
                    int digit = (data[i] / exp) % 10;
                    count[digit]++;
                }

                for (int i = 1; i < 10; i++) {
                    count[i] += count[i - 1];
                }

                int[] output = new int[data.length];
                for (int i = data.length - 1; i >= 0; i--) {
                    int digit = (data[i] / exp) % 10;
                    int k = count[digit] - 1;
                    output[k] = data[i];
                    count[digit]--;
                }

                for (int i = 0; i < data.length; i++) {
                    data[i] = output[i];
                }
            }
        }
    }
}
