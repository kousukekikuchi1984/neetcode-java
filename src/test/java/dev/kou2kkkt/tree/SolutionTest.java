package dev.kou2kkkt.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class SolutionTest {
    @Test
    void test_accounts_merge() {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        List<List<String>> mergedAccounts = Solution.accountsMerge(accounts);

        List<List<String>> expectedMergedAccounts = new ArrayList<>();
        expectedMergedAccounts.add(Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"));
        expectedMergedAccounts.add(Arrays.asList("Mary", "mary@mail.com"));
        expectedMergedAccounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        assertEquals(expectedMergedAccounts, mergedAccounts);
    }

    @Test
    void test_longestConsecutive() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int expected = 4;
        int actual = Solution.longestConsecutive(nums);
        assertEquals(expected, actual);
    }

    @Test
    void test_reconstructQueue() {
        // ref: https://leetcode.com/problems/queue-reconstruction-by-height/
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] expected = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};
        int[][] actual = Solution.reconstructQueue(people);
        assertArrayEquals(expected, actual);
    }

    @Test
    void test_binary_tree_preorder_traversal() {
        // ref: https://leetcode.com/problems/binary-tree-preorder-traversal/
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        List<Integer> actual = Solution.preorderTraversal(root);
        assertEquals(expected, actual);
    }
}