class SingleLinkedListTest {
    @Test
    void test_middleNode() {
        // ref: https://leetcode.com/problems/middle-of-the-linked-list/
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

    }
}