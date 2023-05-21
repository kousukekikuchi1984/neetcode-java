package dev.kou2kkkt.knapsack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class SolutionTest {
    @Test
    public void test_canPartition() {
        // ref: https://leetcode.com/problems/partition-equal-subset-sum/
        Solution solution = new Solution();
        Assertions.assertTrue(solution.canPartition(new int[]{1, 5, 11, 5}));
        Assertions.assertFalse(solution.canPartition(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void test_findTargetSumWays(){
        // ref: https://leetcode.com/problems/target-sum/
        Solution solution = new Solution();
        // Assertions.assertEquals(5, solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        // Assertions.assertEquals(0, solution.findTargetSumWays(new int[]{1}, 2));
        Assertions.assertEquals(1, solution.findTargetSumWays(new int[]{1000}, -1000));
    }

    @Test
    public void test_findMaxForm() {
        // ref: https://leetcode.com/problems/ones-and-zeroes/
        Solution solution = new Solution();
        // Assertions.assertEquals(4, solution.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        // Assertions.assertEquals(2, solution.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
        // ["0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11","01","00","01111","0011","1","1000","0","11101","1","0","10","0111"] caase

    }

    @Test
    public void test_lastStoneWeightII() {
        // ref: https://leetcode.com/problems/last-stone-weight-ii/
        Solution solution = new Solution();
        Assertions.assertEquals(1, solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
    }

    @Test
    public void test_coinChange() {
        // ref: https://leetcode.com/problems/coin-change/
        Solution solution = new Solution();
        //Assertions.assertEquals(3, solution.coinChange(new int[]{1, 2, 5}, 11));
        Assertions.assertEquals(20, solution.coinChange(new int[]{186, 419, 83, 408}, 6249));
    }

    @Test
    public void test_mincostTickets() {
        // ref: https://leetcode.com/problems/minimum-cost-for-tickets/
        Solution solution = new Solution();
        Assertions.assertEquals(11, solution.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    }

    @Test
    public void test_change() {
        // ref: https://leetcode.com/problems/coin-change-ii/
        Solution solution = new Solution();
        Assertions.assertEquals(4, solution.change(5, new int[]{1, 2, 5}));
    }

    @Test
    public void test_numDistinct() {
        // ref: https://leetcode.com/problems/distinct-subsequences
        Solution solution = new Solution();
        Assertions.assertEquals(3, solution.numDistinct("rabbbit", "rabbit"));
    }

    @Test
    public void test_minDistance() {
        // ref: https://leetcode.com/problems/edit-distance/
        Solution solution = new Solution();
        Assertions.assertEquals(3, solution.minDistance("horse", "ros"));
    }
}