package dev.kou2kkkt.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class NumArrayTest {
    @Test
    void test_sumRange() {
        // ref: https://leetcode.com/problems/range-sum-query-immutable/
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        Assertions.assertEquals(1, numArray.sumRange(0, 2));
        Assertions.assertEquals(-1, numArray.sumRange(2, 5));
        Assertions.assertEquals(-3, numArray.sumRange(0, 5));
    }
}