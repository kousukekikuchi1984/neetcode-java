package dev.kou2kkkt.graph;

import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // ref: https://leetcode.com/problems/network-delay-time/
        // adjacent: Map, shortest: Map, minQueue: queue
        Map<Integer, List<Integer[]>> adjacent = new HashMap<>();
        Map<Integer, Integer> shortest = new HashMap<>();
        Queue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // build adjacent
        for (int i = 0; i < n + 1; i++) {
            adjacent.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            int s = time[0], d = time[1], w = time[2];
            adjacent.get(s).add(new Integer[]{d, w});
        }
        minQueue.add(new int[]{0, k});
        while (!minQueue.isEmpty()) {
            int[] cur = minQueue.remove();
            int curNode = cur[1], curWeight = cur[0];
            if (shortest.containsKey(curNode)) {
                continue;
            }
            shortest.put(curNode, curWeight);
            for (Integer[] pair : adjacent.get(curNode)) {
                int nextNode = pair[0], nextWeight = pair[1];
                if (!shortest.containsKey(nextNode)) {
                    minQueue.add(new int[]{curWeight + nextWeight, nextNode});
                }
            }
        }
        if (shortest.size() != n) {
            return -1;
        }
        int max = 0;
        for (int weight : shortest.values()) {
            max = Math.max(max, weight);
        }
        return max;
    }

    public int swimInWater(int[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int len = grid.length;

        if (len == 1) {
            return 0;
        }

        var seen = new boolean[len][len];
        seen[0][0] = true;

        var minHeap = new PriorityQueue<Integer[]>((a, b) -> a[0] - b[0]);
        minHeap.add(new Integer[]{grid[0][0], 0, 0});

        int result = 0;

        while (!minHeap.isEmpty()) {
            var curr = minHeap.poll();

            result = Math.max(result, curr[0]);

            if (curr[1] == len - 1 && curr[2] == len - 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = curr[1] + dirs[i][0];
                int y = curr[2] + dirs[i][1];

                if (x < 0 || x >= len || y < 0 || y >= len || seen[x][y]) {
                    continue;
                }

                minHeap.add(new Integer[]{grid[x][y], x, y});
                seen[x][y] = true;
            }
        }

        return result;
    }
}