package dev.kou2kkkt.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
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

    @Test
    void test_permute() {
        // ref: https://leetcode.com/problems/permutations/
        Solution solution = new Solution();
        List<List<Integer>> expected = List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
        List<List<Integer>> actual = solution.permute(new int[]{1, 2, 3});
        Set<List<Integer>> expectedSet = new HashSet<>(expected);
        Set<List<Integer>> actualSet = new HashSet<>(actual);
        Assertions.assertEquals(expectedSet, actualSet);
    }
}