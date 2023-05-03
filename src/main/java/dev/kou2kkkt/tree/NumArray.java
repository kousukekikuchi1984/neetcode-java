package dev.kou2kkkt.tree;

class NumArray {
    int[] tree;
    int[] arr;
    int n;

    public NumArray(int[] nums) {
        this.arr = nums;
        this.n = nums.length;
        this.tree = new int[4 * this.n];
        this.build(1, 0, this.n - 1);
    }

    public void build(int node, int L, int R) {
        if (L == R) {
            this.tree[node] = this.arr[L];
            return;
        }
        int mid = (L + R) / 2;
        this.build(node * 2, L, mid);
        this.build(node * 2 + 1, mid + 1, R);
        this.tree[node] = this.tree[node * 2] + this.tree[node * 2 + 1];
    }

    public void update(int index, int val) {
        this.update(1, 0, this.n - 1, index, val);
    }

    private void update(int node, int L, int R, int index, int val) {
        if (L == R) {
            this.tree[node] = val;
            return;
        }
        int mid = (L + R) / 2;
        if (index <= mid) {
            this.update(node * 2, L, mid, index, val);
        } else {
            this.update(node * 2 + 1, mid + 1, R, index, val);
        }
        this.tree[node] = this.tree[node * 2] + this.tree[node * 2 + 1];
    }

    public int sumRange(int left, int right) {
        return this.sumRange(1, 0, this.n - 1, left, right);
    }

    private int sumRange(int node, int L, int R, int left, int right) {
        if (left > R || right < L) {
            return 0;
        }
        if (L == R) {
            return this.tree[node];
        }
        if (left <= L && R <= right) {
            return this.tree[node];
        }
        int mid = (L + R) / 2;
        int sumLeft = this.sumRange(node * 2, L, mid, left, right);
        int sumRight = this.sumRange(node * 2 + 1, mid + 1, R, left, right);
        return sumLeft + sumRight;
    }
}