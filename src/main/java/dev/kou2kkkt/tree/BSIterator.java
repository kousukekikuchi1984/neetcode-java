package dev.kou2kkkt.tree;

import java.util.Stack;

class BSTIterator {
    Stack<TreeNode> stack;
    TreeNode cur;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        cur = root; // cur の初期化で誤りがありました
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        if (stack.isEmpty()) {
            return -1;
        }
        cur = stack.pop();
        int val = cur.val;
        cur = cur.right;
        return val;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}