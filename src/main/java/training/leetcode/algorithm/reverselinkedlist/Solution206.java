package training.leetcode.algorithm.reverselinkedlist;


public class Solution206 {
    /*
        Given the head of a singly linked list, reverse the list, and return the reversed list.
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

    public ListNode reverseList(ListNode head) {
        // Reject last node 5
        if (head == null || head.next == null) {
            return head;
        }
        // Node 4
        ListNode reversedList = reverseList(head.next);
        // reverse child to parent - Node 5 points to Node 4
        head.next.next = head;
        // remove link to child - Node 4 points to null, previous caller will
        // set link to itself
        head.next = null;

        return reversedList;
    }
}
