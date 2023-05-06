package dev.kou2kkkt.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
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
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int total_capital = w;
        PriorityQueue<int[]> maxProfits = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        PriorityQueue<int[]> minCapital = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < profits.length; i++) {
            minCapital.add(new int[]{capital[i], profits[i]});
        }

        for (int i = 0; i < profits.length; i++) {
            if (k == 0) {
                break;
            }
            while (!minCapital.isEmpty() && minCapital.peek()[0] <= total_capital) {
                maxProfits.add(minCapital.poll());
            }
            if (maxProfits.isEmpty()) {
                break;
            }
            total_capital += maxProfits.poll()[1];
            k--;
        }

        return total_capital;
    }
}