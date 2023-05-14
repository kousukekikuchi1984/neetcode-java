package dev.kou2kkkt.knapsack;

import java.util.*;

class Solution {
    public boolean canPartition(int[] nums) {
        // ref: https://leetcode.com/problems/partition-equal-subset-sum/
        // total sum must be even when partitioned
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];

        for (int j = 0; j <= target; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }

        return dp[nums.length - 1][target] == target;
    }

    public int findTargetSumWays(int[] nums, int target) {

    }
}