package org.qinlinj.linear.algo.sort.pratice;

public class _164_MaximumGap {
    class Solution {
        public int maximumGap(int[] nums) {
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

        class Bucket {
            public int min = Integer.MAX_VALUE;
            public int max = Integer.MIN_VALUE;
            public boolean hasData = false;
        }
    }
}
