package training.leetcode.algorithm.linkedlists;

import java.util.*;

public class Solution23 {
    /*
        You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
        Merge all the linked-lists into one sorted linked-list and return it.

        Example 1:
        Input: lists = [[1,4,5],[1,3,4],[2,6]]
        Output: [1,1,2,3,4,4,5,6]
        Explanation: The linked-lists are:
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        merging them into one sorted linked list:
        1->1->2->3->4->4->5->6

        Example 2:
        Input: lists = []
        Output: []

        Example 3:
        Input: lists = [[]]
        Output: []
     */

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        public String toString() { return val+"->"+next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for(ListNode node : lists) {
            while (node != null) {
                int val = node.val;
                freqMap.merge(val, 1, Integer::sum);
                node = node.next;
            }
        }
        List<Integer> values = new ArrayList<>(freqMap.keySet());
        Collections.sort(values);

        ListNode node = new ListNode(-1);
        ListNode dummy = node;

        for(Integer value : values) {
            int count = freqMap.get(value);
            for(int i = 0; i<count; i++) {
                node.next = new ListNode(value);
                node = node.next;
            }
        }

        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }
        if(lists.length == 1) {
            return lists[0];
        }

        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int minIndex;
        int minValue;

        do {
            minIndex = -1;
            minValue = Integer.MAX_VALUE;

            for(int i = 0; i < lists.length; i++) {
                if(lists[i] != null && lists[i].val < minValue) {
                    minValue = lists[i].val;
                    minIndex = i;
                }
            }

            if(minIndex >= 0) {
                current.next = lists[minIndex];
                current = lists[minIndex];
                lists[minIndex] = lists[minIndex].next;
            }
        } while (minIndex >= 0);

        return dummy.next;
    }

    public static void main(String[] args) {
        Solution23 solution = new Solution23();

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        System.out.println(solution.mergeKLists(new ListNode[] {node1, node2, node3}));
    }
}
