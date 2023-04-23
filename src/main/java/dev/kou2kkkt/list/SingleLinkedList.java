package dev.kou2kkkt.list;

public class SingleLinkedList {
    public static ListNode middleNode(ListNode head) {
        // ref: https://leetcode.com/problems/middle-of-the-linked-list/
        // fast slow pointer
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static int pairSum(ListNode head) {

    }
}