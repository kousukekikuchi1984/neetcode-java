package dev.kou2kkkt.graph;

import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // ref: https://leetcode.com/problems/network-delay-time/
        // adjacent: Map, shortest: Map, maxQueue: queue
        Map<Integer, List<Integer[]>> adjacent = new HashMap<>();
        Map<Integer, Integer> shortest = new HashMap<>();
        Queue<int[]> maxQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // build adjacent
        for (int i = 0; i < n + 1; i++) {
            adjacent.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            int s = time[0], d = time[1], w = time[2];
            adjacent.get(s).add(new Integer[]{d, w});
        }
        maxQueue.add(new int[]{0, k});
        while (!maxQueue.isEmpty()) {
            int[] cur = maxQueue.remove();
            int curNode = cur[1], curWeight = cur[0];
            if (shortest.containsKey(curNode)) {
                continue;
            }
            shortest.put(curNode, curWeight);
            for (Integer[] pair : adjacent.get(curNode)) {
                int nextNode = pair[0], nextWeight = pair[1];
                if (!shortest.containsKey(nextNode)) {
                    maxQueue.add(new int[]{curWeight + nextWeight, nextNode});
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
        class Pair {
            int to;
            double prob;
            Pair(int to, double prob) {
                this.to = to;
                this.prob = prob;
            }
        }

        Map<Integer, List<Pair>> adjacent = new HashMap<>();
        Map<Integer, Double> largest = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjacent.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            double prob = succProb[i];
            int source = edge[0];
            int destination = edge[1];
            Pair toDestination = new Pair(destination, prob);
            adjacent.get(source).add(toDestination);
            Pair toSource = new Pair(source, prob);
            adjacent.get(destination).add(toSource);
        }

        Queue<Pair> maxQueue = new PriorityQueue<>((a, b) -> Double.compare(-a.prob, -b.prob));
        // insert the first queue
        Pair first = new Pair(start, 1.0);
        maxQueue.add(first);

        while (!maxQueue.isEmpty()) {
            Pair cur = maxQueue.remove();
            int curNode = cur.to;
            double curProb = cur.prob;
            if (largest.containsKey(curNode)) {
                if (largest.get(curNode) >= curProb) {
                    continue;
                }
            }
            largest.put(curNode, curProb);
            // get adjacent nodes
            for (Pair pair : adjacent.get(curNode)) {
                // to_node prob
                Pair newPair = new Pair(pair.to, pair.prob * curProb);
                maxQueue.add(newPair);
            }
        }
        if (largest.get(end) == null || Double.isNaN(largest.get(end))) {
            return 0.0;
        }
        return largest.get(end);
    }

    protected int calcCost(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int minCostConnectPoints(int[][] points) {

        // minQueue, seen
        Queue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<Integer> seen = new HashSet<>();
        int totalCost = 0;

        // init 0 -> all
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            int cost = this.calcCost(points[0], point);
            minQueue.add(new int[]{cost, i});
        }
        seen.add(0);

        while (!minQueue.isEmpty() || seen.size() < points.length) {
            int[] cur = minQueue.remove();
            int curCost = cur[0], index = cur[1];
            if (seen.contains(index)) {
                continue;
            }
            totalCost += curCost;
            seen.add(index);

            // add all adjacent
            for (int i = 0; i < points.length; i++) {
                if (seen.contains(i) || i == index) {
                    continue;
                }
                int[] point = points[i];
                int adjCost = this.calcCost(points[index], point);
                minQueue.add(new int[]{adjCost, i});
            }
        }
        return totalCost;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // minQueue: PriorityQueue, seen: HashMap, neighbors: HashMap
        Queue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Map<Integer, Integer> seen = new HashMap<>();
        Map<Integer, List<int[]>> neighbors = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> all = new HashSet<>();
        Set<Integer> pseudoCritical = new HashSet<>();
        // build neighbors mashmap
        for (int i = 0; i < n; i++) {
            neighbors.put(i, new ArrayList<>());
            all.add(i);
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0], to = edge[1], cost = edge[2];
            neighbors.get(from).add(new int[]{cost, to, i});
            neighbors.get(to).add(new int[]{cost, from, i});
        }
        // init minQueue
        for (int[] edge : neighbors.get(0)) {
            minQueue.add(edge);
        }

        // while minQueue is not empty
        while (!minQueue.isEmpty()) {
            int[] cur = minQueue.remove();
            int cost = cur[0], to = cur[1], index = cur[2];
            if (seen.containsKey(to)) {
                // check pseudo-critical
                if (seen.get(to).equals(cost)) {
                    pseudoCritical.add(index);
                }
            }
            seen.put(to, cost);
            for (int[] edge : neighbors.get(to)) {
                if (seen.containsKey(edge[1])) {
                    continue;
                }
                minQueue.add(edge);
            }
        }
        all.removeAll(pseudoCritical);
        result.add(new ArrayList<>(all));
        result.add(new ArrayList<>(pseudoCritical));
        return result;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // https://leetcode.com/problems/course-schedule-ii/
        // adjacents, topologicalSort, visit
        Map<Integer, List<Integer>> adjacents = new HashMap<>();
        List<Integer> topologicalSort = new ArrayList<>();
        Set<Integer> visit = new HashSet<>();
        // build adjacents
        for (int i = 0; i < numCourses; i++) {
            adjacents.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            // build adjacents graph
            int from = prerequisite[1], to = prerequisite[0];
            adjacents.get(from).add(to);
        }
        // find cycle in adjacents
        for (int i = 0; i < numCourses; i++) {
            if (this._hasCycle(i, adjacents, visit)) {
                return new int[]{};
            }
        }
        for (int i = 0; i < numCourses; i++) {
            this._findOrder(i, adjacents, topologicalSort, visit);
        }
        Collections.reverse(topologicalSort);
        return topologicalSort.stream().mapToInt(i -> i).toArray();
    }

    private boolean _hasCycle(int i, Map<Integer, List<Integer>> adjacents, Set<Integer> visit) {
        if (visit.contains(i)) {
            return true;
        }
        visit.add(i);
        List<Integer> neighbors = adjacents.get(i);
        for (Integer neighbor : neighbors) {
            if (this._hasCycle(neighbor, adjacents, visit)) {
                return true;
            }
        }
        visit.remove(i);
        return false;
    }

    private void _findOrder(int i, Map<Integer, List<Integer>> adjacents, List<Integer> topologicalSort, Set<Integer> visit) {
        if (visit.contains(i)) {
            return;
        }
        visit.add(i);
        List<Integer> neighbors = adjacents.get(i);
        for (Integer neighbor : neighbors) {
            this._findOrder(neighbor, adjacents, topologicalSort, visit);
        }
        topologicalSort.add(i);
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {

    }
}