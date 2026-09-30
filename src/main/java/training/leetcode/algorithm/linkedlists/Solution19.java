package training.leetcode.algorithm.linkedlists;

import lombok.ToString;

public class Solution19 {
    /*
    Given the head of a linked list, remove the nth node from the end of the list and return its head.
    */

    @ToString
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode[] nodes = new ListNode[30];
        int count = 0;
        while(head != null) {
            nodes[count] = head;
            head = head.next;
            count++;
        }

        int offset = count - n;

        if(offset > 0) {
            nodes[offset-1].next = offset < count -1 ? nodes[offset+1] : null;
            return nodes[0];
        } else {
            return count > 1 ? nodes[1] : null;
        }
    }

    public static void main(String[] args) {
        Solution19 solution = new Solution19();

        ListNode head = new ListNode(5);
        for(int i = 4; i >= 1; i--) {
            ListNode temp = new ListNode(i);
            temp.next = head;
            head = temp;
        }

        System.out.println(solution.removeNthFromEnd(head, 2));
    }
}
