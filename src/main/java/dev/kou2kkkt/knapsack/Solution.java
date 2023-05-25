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
        int originalLength = s.length() + 1;
        int subLength = t.length() + 1;
        int[][] dp = new int[originalLength][subLength];
        for (int i = 0; i < originalLength; i++) {
            Arrays.fill(dp[i], -1);
        }
        return this._numDistinct(s, t, 0, 0, dp);
    }

    private int _numDistinct(String s, String t, int originalCur, int subCur, int[][] dp) {
        if (dp[originalCur][subCur] != -1) {
            return dp[originalCur][subCur];
        }
        if (subCur == t.length()) {
            return 1;
        }
        if (originalCur == s.length()) {
            return 0;
        }
        int common = this._numDistinct(s, t, originalCur + 1, subCur, dp);
        if (s.charAt(originalCur) == t.charAt(subCur)) {
            common += this._numDistinct(s, t, originalCur + 1, subCur + 1, dp);
        }
        dp[originalCur][subCur] = common;
        return common;
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        return this._minDistance(word1, word2, 0, 0, dp);
    }

    private int _minDistance(String word1, String word2, int index1, int index2, int[][] dp) {
        if (word1.length() == index1) {
            return word2.length() - index2;
        }
        if (word2.length() == index2) {
            return word1.length() - index1;
        }
        if (dp[index1][index2] != 0) {
            return dp[index1][index2];
        }
        int minValue = Integer.MAX_VALUE;
        if (word1.charAt(index1) == word2.charAt(index2)) {
            minValue = this._minDistance(word1, word2, index1 + 1, index2 + 1, dp);
        } else {
            minValue = Math.min(minValue, this._minDistance(word1, word2, index1 + 1, index2 + 1, dp) + 1);
            minValue = Math.min(minValue, this._minDistance(word1, word2, index1 + 1, index2, dp) + 1);
            minValue = Math.min(minValue, this._minDistance(word1, word2, index1, index2 + 1, dp) + 1);
        }
        dp[index1][index2] = minValue;
        return minValue;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        return this._isInterleave(s1, s2, s3, 0, 0, dp);
    }

    private boolean _isInterleave(String s1, String s2, String s3, int i1, int i2, Boolean[][] dp) {
        if (dp[i1][i2] != null) {
            return dp[i1][i2];
        }
        if (i1 + i2 == s3.length()) {
            dp[i1][i2] = true;
            return true;
        }
        boolean result = false;
        if (i1 < s1.length() && s1.charAt(i1) == s3.charAt(i1 + i2)) {
            result = result || this._isInterleave(s1, s2, s3, i1 + 1, i2, dp);
        }
        if (i2 < s2.length() && s2.charAt(i2) == s3.charAt(i1 + i2)) {
            result = result || this._isInterleave(s1, s2, s3, i1, i2 + 1, dp);
        }
        dp[i1][i2] = result;
        return result;
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int [][] dp = new int[l1 + 1][l2 + 1];
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        for (int i1 = 1; i1 <= l1; i1++) {
            for (int i2 = 1; i2 <= l2; i2++) {
                if (s1[i1 - 1] == s2[i2 - 1]) {
                    dp[i1][i2] = dp[i1 - 1][i2 - 1] + 1;
                } else {
                    dp[i1][i2] = Math.max(dp[i1 - 1][i2], dp[i1][i2 - 1]);
                }
            }
        }

        int i1 = l1;
        int i2 = l2;
        StringBuilder str = new StringBuilder();
        while (i1 > 0 && i2 > 0) {
            if (s1[i1 - 1] == s2[i2 - 1]) {
                str.insert(0, s1[i1 - 1]);
                i1--;
                i2--;
            } else if (dp[i1 - 1][i2] < dp[i1][i2 - 1]) {
                str.insert(0, s2[i2 - 1]);
                i2--;
            } else {
                str.insert(0, s1[i1 - 1]);
                i1--;
            }
        }
        if (i1 > 0) {
            str.insert(0, str1.substring(0, i1));
        }
        if (i2 > 0) {
            str.insert(0, str2.substring(0, i2));
        }
        return str.toString();
    }

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        if (length == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return s.substring(0, 1);
            }
        }
        String result = "";
        for (int i = 1; i < length; i++) {
            // odd
            int left  = i - 1;
            int right = i + 1;
            StringBuilder oddPalindrome = new StringBuilder();
            oddPalindrome.append(s.charAt(i));
            while (left >= 0 && right < length) {
                if (s.charAt(left) == s.charAt(right)) {
                    oddPalindrome.append(s.charAt(right));
                    oddPalindrome.insert(0, s.charAt(left));
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            String oddPalindromeString = oddPalindrome.toString();
            if (result.length() < oddPalindromeString.length()) {
                result = oddPalindromeString;
            }

            // even
            left = i - 1;
            right = i;
            StringBuilder evenPalindrome = new StringBuilder();
            while (left >= 0 && right < length) {
                if (s.charAt(left) == s.charAt(right)) {
                    evenPalindrome.append(s.charAt(right));
                    evenPalindrome.insert(0, s.charAt(left));
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            String evenPalindromeString = evenPalindrome.toString();
            if (result.length() < evenPalindromeString.length()) {
                result = evenPalindromeString;
            }
        }
        return result;
    }

    public int countSubstrings(String s) {
        // palindrum
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd
            result++;
            int right = i + 1;
            int left = i - 1;
            while (left >= 0 && right < s.length()) {
                System.out.println(left + " " + right);
                if (s.charAt(left) == s.charAt(right)) {
                    result++;
                    right++;
                    left--;
                } else {
                    break;
                }
            }

            // even
            right = i + 1;
            left = i;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) == s.charAt(right)) {
                    result++;
                    right++;
                    left--;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}