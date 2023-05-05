package dev.kou2kkkt.tree;

class MyCalendar {
    // ref: https://leetcode.com/problems/my-calendar-i/
    int L;
    int R;
    MyCalendar left;
    MyCalendar right;

    public MyCalendar() {
        this.L = -1;
        this.R = -1;
        this.left = null;
        this.right = null;
    }

    private boolean within(int start, int end) {
        if (this.L == -1 || this.R == -1) {
            return false;
        }
        // revert without
        return !(end <= this.L || this.R <= start);
    }

    public boolean book(int start, int end) {
        if (this.within(start, end)) {
            return false;
        }
        if (this.L == -1 || this.R == -1) {
            this.L = start;
            this.R = end;
            return true;
        }
        if (start >= this.R) {
            if (this.left == null) {
                this.left = new MyCalendar();
            }
            return this.left.book(start, end);
        }
        if (end <= this.L) {
            if (this.right == null) {
                this.right = new MyCalendar();
            }
            return this.right.book(start, end);
        }
        return false;
    }
}