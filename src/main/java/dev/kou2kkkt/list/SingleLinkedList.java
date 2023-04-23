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
        // ref: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
        // fast slow pointer
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        int max = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode tmp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = tmp;
        }
        // fast -> out of range
        // slow -> starting from middle to end
        // prev -> starting from middle to start
        while (slow != null && prev != null) {
            max = Math.max(max, slow.val + prev.val);
            slow = slow.next;
            prev = prev.next;
        }
        return max;
    }

    public static boolean hasCycle(ListNode head) {

    }
}