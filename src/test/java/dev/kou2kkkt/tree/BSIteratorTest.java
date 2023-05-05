package dev.kou2kkkt.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class BSIteratorTest {
    @Test
    void test_next() {
        // ref: https://leetcode.com/problems/binary-search-tree-iterator/
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSIterator iterator = new BSIterator(root);
        Assertions.assertEquals(3, iterator.next());
        Assertions.assertEquals(7, iterator.next());
        Assertions.assertEquals(true, iterator.hasNext());
        Assertions.assertEquals(9, iterator.next());
        Assertions.assertEquals(true, iterator.hasNext());
        Assertions.assertEquals(15, iterator.next());
        Assertions.assertEquals(true, iterator.hasNext());
        Assertions.assertEquals(20, iterator.next());
        Assertions.assertEquals(false, iterator.hasNext());
    }
}