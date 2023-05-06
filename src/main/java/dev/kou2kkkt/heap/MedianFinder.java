package dev.kou2kkkt.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;

    public MedianFinder() {
        this.small = new PriorityQueue<>(Comparator.reverseOrder());
        this.large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        this.small.add(num);
        if (!this.small.isEmpty() && !this.large.isEmpty() && this.small.peek() > this.large.peek()) {
            int val = this.small.poll();
            this.large.add(val);
        }

        if (this.small.size() > this.large.size() + 1) {
            int val = this.small.poll();
            this.large.add(val);
        }
        if (this.large.size() > this.small.size() + 1) {
            int val = this.large.poll();
            this.small.add(val);
        }
    }

    public double findMedian() {
        if (this.small.size() == this.large.size()) {
            return this.small.peek() / 2.0 + this.large.peek() / 2.0;
        } else if (this.small.size() > this.large.size()) {
            return this.small.peek();
        } else {
            return this.large.peek();
        }
    }
}