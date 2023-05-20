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
        int sum = Arrays.stream(nums).sum();
        if (target > sum || (sum + target) % 2 != 0 || target < -sum) {
            return 0;
        }
        int columnLength = 2 * sum + 1;
        int[][] dp = new int[nums.length][columnLength];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (i == 0) {
                    if (j == sum + nums[i] || j == sum - nums[i]) {
                        if (nums[i] == 0) {
                            dp[i][j] = 2;
                        } else {
                            dp[i][j] = 1;
                        }

                    } else {
                        dp[i][j] = 0;
                    }
                } else {
                    if (j - nums[i] >= 0) {
                        dp[i][j] += dp[i - 1][j - nums[i]];
                    }
                    if (j + nums[i] < columnLength) {
                        dp[i][j] += dp[i - 1][j + nums[i]];
                    }
                }
            }
        }
        if (target >= 0) {
            return dp[nums.length - 1][target + sum];
        } else {
            int absTarget = Math.abs(target);
            return dp[nums.length - 1][absTarget - sum];
        }
    }

    public int findMaxForm(String[] strs, int m, int n) {
        // ref: https://leetcode.com/problems/ones-and-zeroes/
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int mCount = 0;
            int nCount = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    mCount++;
                } else {
                    nCount++;
                }
            }
            for (int i = m; i >= mCount; i--) {
                for (int j = n; j >= nCount; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - mCount][j - nCount] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int lastStoneWeightII(int[] stones) {
        // dynamic programming
        // ref: https://leetcode.com/problems/last-stone-weight-ii/
        int total = Arrays.stream(stones).sum();
        int[][] dp = new int[stones.length][total + 1];
        return _lastStoneWeightII(stones, 0, 0, 0, dp);
    }

    private int _lastStoneWeightII(int[] stones, int index, int sumLeft, int sumRight, int[][] dp) {
        if (index == stones.length) {
            return Math.abs(sumLeft - sumRight);
        }
        int left = this._lastStoneWeightII(stones, index + 1, sumLeft + stones[index], sumRight, dp);
        int right = this._lastStoneWeightII(stones, index + 1, sumLeft, sumRight + stones[index], dp);
        dp[index][sumLeft] = Math.min(left, right);
        return dp[index][sumLeft];
    }

    public int coinChange(int[] coins, int amount) {
        // ref: https://leetcode.com/problems/coin-change/
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + costs[0]; // 1-day pass for current day

            int j = i - 1;
            while (j >= 0 && days[i - 1] - days[j] < 7) {
                j--;
            }
            dp[i] = Math.min(dp[i], dp[j + 1] + costs[1]); // 7-day pass for current day

            j = i - 1;
            while (j >= 0 && days[i - 1] - days[j] < 30) {
                j--;
            }
            dp[i] = Math.min(dp[i], dp[j + 1] + costs[2]); // 30-day pass for current day
        }

        return dp[n];
    }

    public int change(int amount, int[] coins) {
        // ref: https://leetcode.com/problems/coin-change-2/
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) { // for each coin
            for (int i = coin; i <= amount; i++) { // for each amount
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public int numDistinct(String s, String t) {

    }
}