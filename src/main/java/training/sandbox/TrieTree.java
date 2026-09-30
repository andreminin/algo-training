package training.sandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class TrieTree {
    /*
        Key Use Cases:

        Autocomplete/Autosuggest (search engines, IDEs)

        Spell checking and dictionary implementations

        IP routing (longest prefix matching)

        Word games (Boggle, Scrabble)

        Contact search in mobile apps
     */



    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    public static class TrieMap {
        private final TrieNode root;

        public TrieMap() {
            root = new TrieNode();
        }

        // Insert a word into the trie
        public void insert(String word) {
            TrieNode current = root;

            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }

            current.isEndOfWord = true;
        }

        // Search for a complete word
        public boolean search(String word) {
            TrieNode node = getNode(word);
            return node != null && node.isEndOfWord;
        }

        // Check if any word starts with the given prefix
        public boolean startsWith(String prefix) {
            return getNode(prefix) != null;
        }

        // Get all words with given prefix (autocomplete)
        public List<String> getWordsWithPrefix(String prefix) {
            List<String> results = new ArrayList<>();
            TrieNode node = getNode(prefix);

            if (node != null) {
                dfs(node, new StringBuilder(prefix), results);
            }

            return results;
        }

        // Delete a word from trie
        public boolean delete(String word) {
            return delete(root, word, 0);
        }

        // Helper method to get node for a given string
        private TrieNode getNode(String str) {
            TrieNode current = root;

            for (char c : str.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return null;
                }
                current = current.children.get(c);
            }

            return current;
        }

        // Depth-first search to collect all words
        private void dfs(TrieNode node, StringBuilder prefix, List<String> results) {
            if (node.isEndOfWord) {
                results.add(prefix.toString());
            }

            for (char c : node.children.keySet()) {
                prefix.append(c);
                dfs(node.children.get(c), prefix, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        // Recursive delete
        private boolean delete(TrieNode current, String word, int index) {
            if (index == word.length()) {
                if (!current.isEndOfWord) {
                    return false;
                }
                current.isEndOfWord = false;
                return current.children.isEmpty();
            }

            char c = word.charAt(index);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false;
            }

            boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

            if (shouldDeleteCurrentNode) {
                current.children.remove(c);
                return current.children.isEmpty() && !current.isEndOfWord;
            }

            return false;
        }

        // Get total number of words in trie
        public int countWords() {
            return countWords(root);
        }

        private int countWords(TrieNode node) {
            int count = 0;

            if (node.isEndOfWord) {
                count++;
            }

            for (TrieNode child : node.children.values()) {
                count += countWords(child);
            }

            return count;
        }
    }

    public static void main(String[] args) {
        TrieMap trie = new TrieMap();

        // Insert words
        String[] words = {"apple", "app", "application", "banana", "bat", "batch", "batman"};
        for (String word : words) {
            trie.insert(word);
        }

        System.out.println("=== Trie Operations Demo ===");

        // Search operations
        System.out.println("\n--- Search Operations ---");
        System.out.println("Search 'app': " + trie.search("app"));     // true
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'appl': " + trie.search("appl"));   // false

        // Prefix search
        System.out.println("\n--- Prefix Search ---");
        System.out.println("Starts with 'app': " + trie.startsWith("app")); // true
        System.out.println("Starts with 'cat': " + trie.startsWith("cat")); // false

        // Autocomplete demo
        System.out.println("\n--- Autocomplete Demo ---");
        System.out.println("Words with prefix 'app': " + trie.getWordsWithPrefix("app"));
        System.out.println("Words with prefix 'bat': " + trie.getWordsWithPrefix("bat"));
        System.out.println("Words with prefix 'ba': " + trie.getWordsWithPrefix("ba"));

        // Count words
        System.out.println("\nTotal words in trie: " + trie.countWords());

        // Delete operation
        System.out.println("\n--- Delete Operation ---");
        System.out.println("Delete 'app': " + trie.delete("app"));
        System.out.println("Search 'app' after deletion: " + trie.search("app")); // false
        System.out.println("Search 'apple' after deletion: " + trie.search("apple")); // true
        System.out.println("Words with prefix 'app' after deletion: " + trie.getWordsWithPrefix("app"));

        // Spell checker simulation
        System.out.println("\n--- Spell Checker Simulation ---");
        String[] testWords = {"apl", "apple", "bat", "bath", "cat"};
        for (String word : testWords) {
            if (trie.search(word)) {
                System.out.println("✓ '" + word + "' is spelled correctly");
            } else {
                System.out.println("✗ '" + word + "' is misspelled. Suggestions: " +
                        trie.getWordsWithPrefix(word.substring(0, Math.min(3, word.length()))));
            }
        }
    }
}
