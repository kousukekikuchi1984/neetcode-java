package dev.kou2kkkt.graph;

import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void test_networkDelayTime() {
        // ref: https://leetcode.com/problems/network-delay-time/
        Solution solution = new Solution();
        Assertions.assertEquals(2, solution.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }

    @Test
    void test_swimInWater() {
        // ref: https://leetcode.com/problems/swim-in-rising-water/
        Solution solution = new Solution();
        Assertions.assertEquals(3, solution.swimInWater(new int[][]{{0, 2}, {1, 3}}));
    }

    @Test
    void test_maxProbability() {
        // ref: https://leetcode.com/problems/path-with-maximum-probability/
        Solution solution = new Solution();
        // Assertions.assertEquals(0.25000, solution.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
        int[][] edges = new int[][]{{1, 4}, {2, 4}, {0, 4}, {0, 3}, {0, 2}, {2, 3}};
        double[] succProb = new double[]{0.37, 0.17, 0.93, 0.23, 0.39, 0.04};
        int start = 3;
        int end = 4;
        Assertions.assertEquals(0.2139, solution.maxProbability(5, edges, succProb, start, end));
    }

    @Test
    void test_minCostConnectPoints() {
        // ref: https://leetcode.com/problems/min-cost-to-connect-all-points/
        Solution solution = new Solution();
        Assertions.assertEquals(20, solution.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    @Test
    void test_findCriticalAndPseudoCriticalEdges() {
        // ref: https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
        Solution solution = new Solution();
        int[][] edges = new int[][]{{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}};
        List<List<Integer>> actual = solution.findCriticalAndPseudoCriticalEdges(5, edges);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 1));
        expected.add(Arrays.asList(2, 3, 4, 5));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_findOrder() {
        // ref: https://leetcode.com/problems/course-schedule-ii/
        Solution solution = new Solution();
        // int[][] prerequisites = new int[][]{{1, 0}};
        // int[] actual = solution.findOrder(2, prerequisites);
        // int[] expected = new int[]{0, 1};
        int[][] prerequisites = new int[][]{{0, 1}, {1, 0}};
        int[] actual = solution.findOrder(2, prerequisites);
        int[] expected = new int[]{};
        Assertions.assertArrayEquals(expected, actual);
        System.out.println("test");
    }
}