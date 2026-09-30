package training.leetcode.algorithm.reverselinkedlist;

public class Solution24 {

    /*
    Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without
    modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
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

    public ListNode swapPairs(ListNode head) {

        ListNode headRef = new ListNode(0);
        headRef.next = head;
        ListNode prev = headRef;

        //pair nodes
        ListNode left;
        ListNode right;

        while(head != null && head.next != null) {
            left = head;
            right = head.next;

            //Swap
            prev.next = right;
            left.next = right.next;
            right.next = left;

            prev = left;
            head = left.next;
        }

        return headRef.next;
    }

    // Recursive solution - may be stack overflow on large list
    public ListNode swapPairs_Recursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return swapPair(head, head.next);
    }

    ListNode swapPair(ListNode one, ListNode two) {
        if (two == null) {
            return one;
        }

        ListNode temp = two.next;

        two.next = one;

        one.next = temp == null ? null : swapPair(temp, temp.next);

        return two;
    }
}
