package dev.kou2kkkt.backtracking;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        // ref: https://leetcode.com/problems/combinations/
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        int[] numbers = IntStream.rangeClosed(1, n).toArray();
        this._combine(0, k, numbers, combination, combinations);
        return combinations;
    }

    private void _combine(int index, int max_size, int[] numbers, List<Integer> combination, List<List<Integer>> combinations) {
        if (combination.size() == max_size) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        if (index == numbers.length) {
            return;
        }
        // choose the value of index
        combination.add(numbers[index]);
        this._combine(index + 1, max_size, numbers, combination, combinations);

        // not choose the value of index
        combination.remove(combination.size() - 1);
        this._combine(index + 1, max_size, numbers, combination, combinations);
    }
}