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
}