package dev.kou2kkkt.backtracking;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // ref: https://leetcode.com/problems/subsets-ii/
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(nums);
        this._subsetsWithDuplicates(0, nums, subset, subsets);
        return subsets;
    }

    private void _subsetsWithDuplicates(int index, int[] nums, List<Integer> subset, List<List<Integer>> subsets) {
        if (index == nums.length)  {
            subsets.add(new ArrayList<>(subset));
            return;
        }
        subset.add(nums[index]);
        this._subsetsWithDuplicates(index + 1, nums, subset, subsets);
        subset.remove(subset.size() - 1);

        // skip duplicates
        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index++;
        }
        this._subsetsWithDuplicates(index + 1, nums, subset, subsets);
    }

    public List<List<Integer>> combine(int n, int k) {

    }
}