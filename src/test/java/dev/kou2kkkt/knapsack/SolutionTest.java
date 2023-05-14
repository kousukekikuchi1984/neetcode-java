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
        Assertions.assertEquals(5, solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }
}