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

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // ref: https://leetcode.com/problems/path-with-maximum-probability/
        // adjacent: Map, shortest: Map, minQueue: queue
        class Pair {
            int node;
            double prob;

            Pair(int node, double prob) {
                this.node = node;
                this.prob = prob;
            }
        }

        Map<Integer, List<Pair>> adjacent = new HashMap<>();
        Map<Integer, Double> highest = new HashMap<>();
        Queue<Pair> minQueue = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        Pair first = new Pair(start, 1.0);
        minQueue.add(first);
        // create adjacent map
        for (int i = 0; i < n; i++) {
            adjacent.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int s = edge[0], d = edge[1];
            double prob = succProb[edge[1]];
            Pair pair = new Pair(d, prob);
            adjacent.computeIfAbsent(s, k -> new ArrayList<>()).add(pair);

            double prob2 = succProb[edge[0]];
            Pair pair2 = new Pair(s, prob2);
            adjacent.computeIfAbsent(d, k -> new ArrayList<>()).add(pair2);
        }

        while (!minQueue.isEmpty()) {
            Pair cur = minQueue.poll();
            int curNode = cur.node;
            double curProb = cur.prob;
            System.out.println(curNode + " " + curProb);
            // update highest if curProb is higher than the previous one
            if (highest.containsKey(curNode)) {
                if (curProb < highest.get(curNode)) {
                    continue;
                }
            }
            highest.put(curNode, curProb);
            // get adjacent nodes and add to queue
            List<Pair> pairs = adjacent.get(curNode);
            for (Pair pair : pairs) {
                // if the node is already in the queue, remove it
                if (highest.containsKey(pair.node)) {
                    if (curProb * pair.prob < highest.get(pair.node)) {
                        continue;
                    }
                }
                System.out.println(pair.node + " " + curProb + "*" + pair.prob);
                minQueue.add(new Pair(pair.node, curProb * pair.prob));
            }
            System.out.println(minQueue);
        }
        if (!highest.containsKey(end)) {
            return 0.0;
        }
        return highest.get(end);
    }
}