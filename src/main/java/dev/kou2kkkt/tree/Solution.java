package dev.kou2kkkt.tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Solution {
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // ref: https://leetcode.com/problems/accounts-merge/
        UnionFind uf = new UnionFind(accounts.size());
        Map<String, Integer> emailToName = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            String name = account.get(0);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (emailToName.containsKey(email)) {
                    uf.union(i, emailToName.get(email));
                } else {
                    emailToName.put(email, i);
                }
            }
        }
        HashMap<Integer, List<String>> leaderToEmails = new HashMap<>();
        emailToName.forEach((email, name_idx) -> {
            int leader = uf.find(name_idx);
            if (leaderToEmails.containsKey(leader)) {
                leaderToEmails.get(leader).add(email);
            } else {
                List<String> emails = new ArrayList<>();
                emails.add(email);
                leaderToEmails.put(leader, emails);
            }
        });
        List<List<String>> res = new ArrayList<>();
        leaderToEmails.forEach((leader, emails) -> {
            Collections.sort(emails);
            String name = accounts.get(leader).get(0);
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            res.add(account);
        });
        return res;
    }

    public static int longestConsecutive(int[] nums) {
        UnionFind uf = new UnionFind(nums.length);
        Map<Integer, Integer> numToIdx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numToIdx.containsKey(num)) {
                continue;
            }
            numToIdx.put(num, i);
            if (numToIdx.containsKey(num - 1)) {
                uf.union(i, numToIdx.get(num - 1));
            }
            if (numToIdx.containsKey(num + 1)) {
                uf.union(i, numToIdx.get(num + 1));
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int leader = uf.find(i);
            int length = uf.rank.get(leader);
            res = Math.max(res, length);
        }
        return res;
    }

    public static int[][] reconstructQueue(int[][] people) {
        // ref: https://leetcode.com/problems/queue-reconstruction-by-height/
        // person -> height, higher than or equal to person's height, in front of person
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][]);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Boolean> visits = new Stack<>();
        List<Integer> res = new ArrayList<>();

        stack.push(root);
        visits.push(false);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            boolean visited = visits.pop();
            if (cur == null) {
                continue;
            }
            if (visited) {
                res.add(cur.val);
            } else {
                stack.push(cur);
                visits.push(true);
                if (cur.right != null) {
                    stack.push(cur.right);
                    visits.push(false);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                    visits.push(false);
                }
            }
        }
        return res;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                cur = cur.left;
            } else {
                cur = stack.pop();
            }
        }
        return res;
    }
}