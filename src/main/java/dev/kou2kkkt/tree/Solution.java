package dev.kou2kkkt.tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
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
}