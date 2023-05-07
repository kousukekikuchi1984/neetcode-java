package dev.kou2kkkt.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SolutionTest {
    @Test
    void test_subsetsWithDup() {
        // ref: https://leetcode.com/problems/subsets-ii/
        Solution solution = new Solution();
    }

    @Test
    void test_combine() {
        // ref: https://leetcode.com/problems/combinations/
        Solution solution = new Solution();
        Assertions.assertIterableEquals(List.of(List.of(1, 2), List.of(1, 3), List.of(1, 4), List.of(2, 3), List.of(2, 4), List.of(3, 4)), solution.combine(4, 2));
    }

    @Test
    void test_letterCombinations() {
        // ref: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
        Solution solution = new Solution();
        Assertions.assertIterableEquals(List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), solution.letterCombinations("23"));
    }
}