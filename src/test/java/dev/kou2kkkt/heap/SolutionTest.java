package dev.kou2kkkt.heap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class SolutionTest {
    @Test
    void test_medianSlidingWindow() {
        // ref: https://leetcode.com/problems/sliding-window-median/
        Solution solution = new Solution();
        Assertions.assertArrayEquals(new double[]{1.00000, -1.00000, -1.00000, 3.00000, 5.00000}, solution.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
        Assertions.assertArrayEquals(new double[]{2147483647.00000}, solution.medianSlidingWindow(new int[]{2147483647, 2147483647}, 2));
    }
}