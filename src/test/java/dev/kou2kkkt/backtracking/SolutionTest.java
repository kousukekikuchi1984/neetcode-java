package dev.kou2kkkt.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void test_subsetsWithDup() {
        // ref: https://leetcode.com/problems/subsets-ii/
        Solution solution = new Solution();
        Assertions.assertEquals(List.of(List.of(), List.of(1), List.of(1, 2), List.of(1, 2, 2), List.of(2), List.of(2, 2)), solution.subsetsWithDup(new int[]{1, 2, 2}));
        Assertions.assertEquals(List.of(List.of(), List.of(0)), solution.subsetsWithDup(new int[]{0}));
    }
}