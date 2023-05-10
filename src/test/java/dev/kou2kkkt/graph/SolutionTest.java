package dev.kou2kkkt.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void test_networkDelayTime() {
        // ref: https://leetcode.com/problems/network-delay-time/
        Solution solution = new Solution();
        Assertions.assertEquals(2, solution.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }

    @Test
    void test_swimInWater() {
        // ref: https://leetcode.com/problems/swim-in-rising-water/
        Solution solution = new Solution();
        Assertions.assertEquals(3, solution.swimInWater(new int[][]{{0, 2}, {1, 3}}));
    }

    @Test
    void test_maxProbability() {
        // ref: https://leetcode.com/problems/path-with-maximum-probability/
        Solution solution = new Solution();
        Assertions.assertEquals(0.25000, solution.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));

    }
}