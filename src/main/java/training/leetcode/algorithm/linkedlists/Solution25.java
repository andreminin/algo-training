package training.leetcode.algorithm.linkedlists;

public class Solution25 {
    /*
    25. Reverse Nodes in k-Group
        Hard
        Topics
        premium lock iconCompanies

        Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

        k is a positive integer and is less than or equal to the length of the linked list. If the number
        of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

        You may not alter the values in the list's nodes, only nodes themselves may be changed
     */

    public static class ListNode {
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

        public String toString() {
            return this == next ? val + "<>" : val + "->" + next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prevTail = dummy;

        while (head != null) {
            ListNode groupStart = head;
            ListNode groupEnd = getGroupEnd(head, k);

            if (groupEnd == null) {
                break;
            }

            ListNode nextGroupStart = groupEnd.next;
            groupEnd.next = null;
            prevTail.next = reverseList(groupStart);
            groupStart.next = nextGroupStart;
            prevTail = groupStart;
            head = nextGroupStart;
        }

        return dummy.next;
    }

    private ListNode getGroupEnd(ListNode head, int k) {
        while (head != null && --k > 0) {
            head = head.next;
        }
        return head;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }

    public static void main(String[] args) {
        Solution25 solution = new Solution25();

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        System.out.println(solution.reverseKGroup(node1, 2));

        node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);
        System.out.println(solution.reverseKGroup(node1, 3));
    }
}
