package training.leetcode.algorithm.reverselinkedlist;

public class Solution92 {
    /*
    Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of
    the list from position left to position right, and return the reversed list.

    position starts from 1 (not 0!)

    Input: head = [1,2,3,4,5], left = 2, right = 4
    Output: [1,4,3,2,5]

    The loop runs (right - left) times to reverse the sublist.

    In each iteration, we move the then node to the front of the sublist (right after pre).

    start remains fixed at the first node of the sublist, but its next pointer changes to point to the next node that then will process.

    pre remains fixed at the node before the sublist, and its next pointer is updated to point to the new front of the reversed sublist in each iteration.

    then moves to the next node to be processed (which is always start.next after the pointer adjustments).

     Initial: dummy → 1 → 2 → 3 → 4 → 5

    After iteration 1: dummy → 1 → 3 → 2 → 4 → 5

    After iteration 2: dummy → 1 → 4 → 3 → 2 → 5

     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next;
        ListNode then = start.next;

        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        return dummy.next;
    }
}
