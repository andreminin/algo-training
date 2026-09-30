package training.sandbox;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // Node for doubly linked list
    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(-1, -1); // Dummy head
        tail = new Node(-1, -1); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        remove(node);     // Remove from current position
        addToHead(node);  // Move to head (most recently used)
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            remove(node);     // Remove from current position
            addToHead(node);  // Move to head
        } else {
            if (cache.size() == capacity) {
                cache.remove(tail.prev.key); // Remove LRU from cache
                remove(tail.prev);           // Remove from list
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode); // Add new node to head
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1); // Cache: {1=1}
        cache.put(2, 2); // Cache: {1=1, 2=2}
        System.out.println(cache.get(1)); // Returns 1, Cache: {2=2, 1=1}
        cache.put(3, 3); // Evicts key 2, Cache: {1=1, 3=3}
        System.out.println(cache.get(2)); // Returns -1 (not found)
        cache.put(4, 4); // Evicts key 1, Cache: {3=3, 4=4}
        System.out.println(cache.get(1)); // Returns -1
        System.out.println(cache.get(3)); // Returns 3
        System.out.println(cache.get(4)); // Returns 4
    }
}
