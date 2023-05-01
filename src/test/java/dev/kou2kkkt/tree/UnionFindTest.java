package dev.kou2kkkt.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class UnionFindTest {
    @Test
    void test_accounts_merge() {
        // ref: https://leetcode.com/problems/accounts-merge/
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList(["John", "johnsmith@mail.com", "john_newyork@mail.com"]);
        accounts.add(Arrays.asList(["John", "johnsmith@mail.com", "john00@mail.com"]);
        accounts.add(Arrays.asList(["Mary", "mary@mail.com"]);
        accounts.add(Arrays.asList(["John", "johnnybravo@mail.com"]);
        List<List<string>> actual = UnionFind.accountsMerge(accounts);
        List<List<string>> expected = new ArrayList<>();
        expected.add(Arrays.asList(["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"]);
        expected.add(Arrays.asList(["Mary", "mary@mail.com"]));
        expected.add(Arrays.asList(["John", "johnnybravo@mail.com"]));
        assertEquals(expected, actual);
    }
}