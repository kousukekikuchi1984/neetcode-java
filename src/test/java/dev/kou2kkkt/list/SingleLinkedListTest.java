package dev.kou2kkkt.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class SingleLinkedListTest {
    @Test
    void test_middleNode() {
        // ref: https://leetcode.com/problems/middle-of-the-linked-list/
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Assertions.assertEquals(3, SingleLinkedList.middleNode(head).val);
    }

    @Test
    void test_pairSum() {
        // ref: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(9);
        head.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next = new ListNode(2);
        Assertions.assertEquals(14, SingleLinkedList.pairSum(head));
    }

    @Test
    void test_hasCycle() {
        // ref: https://leetcode.com/problems/linked-list-cycle/
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next = new ListNode(-4);
        head.next.next = head.next;
        Assertions.assertEquals(true, SingleLinkedList.hasCycle(head));
    }

    @Test
    void test_detectCycle() {
        // ref: https://leetcode.com/problems/linked-list-cycle-ii/
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next = new ListNode(-4);
        head.next.next = head.next;
        Assertions.assertEquals(head.next, SingleLinkedList.detectCycle(head));
    }
}