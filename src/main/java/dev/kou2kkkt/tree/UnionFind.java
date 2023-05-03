package dev.kou2kkkt.tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class UnionFind {

    List<Integer> parent;
    List<Integer> rank;

    public UnionFind(Integer n) {
        this.parent = IntStream.range(0, n).boxed().collect(Collectors.toList());
        this.rank = new ArrayList<>(Collections.nCopies(n, 1));
    }

    public int find(int x) {
        if (this.parent.get(x) == x) {
            return x;
        } else {
            this.parent.set(x, this.find(this.parent.get(x)));
            return this.parent.get(x);
        }
    }

    public boolean union(int x1, int x2) {
        int p1 = this.find(x1);
        int p2 = this.find(x2);
        if (p1 == p2) {
            return false;
        }
        if (this.rank.get(p1) < this.rank.get(p2)) {
            this.parent.set(p1, p2);
            this.rank.set(p2, this.rank.get(p1) + this.rank.get(p2));
        } else {
            this.parent.set(p2, p1);
            this.rank.set(p1, this.rank.get(p1) + this.rank.get(p2));
        }
        return true;
    }
}