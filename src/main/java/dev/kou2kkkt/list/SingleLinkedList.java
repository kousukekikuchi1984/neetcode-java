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
        // fast and slow pointer
        // if fast is null -> return false
        // matched true
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public static ListNode detectCycle(ListNode head) {
        // fast and slow pointer
        // if fast is null -> return null
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
    public static int findDuplicate(int[] nums) {
        // ref: https://leetcode.com/problems/find-the-duplicate-number/
        int fast = nums[0];
        int slow = nums[0];
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                slow = nums[0];
                while (slow != fast) {
                    slow = nums[slow];
                    fast = nums[fast];
                }
                break;
            }
        }
        return fast;
    }
}