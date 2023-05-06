package dev.kou2kkkt.heap;
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] ans = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            MedianFinder medianFinder = new MedianFinder();
            for (int j = i; j < i + k; j++) {
                medianFinder.addNum(nums[j]);
            }
            ans[i] = medianFinder.findMedian();
        }
        return ans;
    }
}