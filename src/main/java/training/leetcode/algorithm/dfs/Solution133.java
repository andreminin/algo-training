package training.leetcode.algorithm.dfs;

import java.util.*;

public class Solution133 {
    /*
    For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with
    val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

    An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set
     of neighbors of a node in the graph.

    The given node will always be the first node with val = 1. You must return the copy of the given node as a reference
    to the cloned graph.

    Approach

    Breadth-First Search (BFS): We use BFS to traverse the graph starting from the given node. This ensures we process all nodes level by level.

    HashMap for Tracking Clones: We maintain a HashMap to keep track of nodes that have been cloned. The key is the
    original node, and the value is the cloned node. This helps in avoiding cycles and re-cloning the same nodes.

    Cloning Process: For each node dequeued from the BFS queue, we iterate through its neighbors. For each neighbor
    not yet cloned, we create a clone, add it to the HashMap, and enqueue the original neighbor for further processing.
     We then add the cloned neighbor to the current clone's list of neighbors.
     */

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class Solution {
        public Node cloneGraph(Node node) {
            if (node == null) return null;

            Map<Integer, Node> visited = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();

            Node cloneNode = new Node(node.val);
            visited.put(node.val, cloneNode);
            queue.add(node);

            while (!queue.isEmpty()) {
                Node current = queue.poll();
                Node clone = visited.get(current.val);

                for (Node neighbor : current.neighbors) {
                    Node neighborClone = visited.get(neighbor.val);

                    if (neighborClone == null) {
                        neighborClone = new Node(neighbor.val);
                        visited.put(neighbor.val, neighborClone);
                        queue.add(neighbor);
                    }

                    clone.neighbors.add(neighborClone);
                }
            }

            return cloneNode;
        }
    }
}
