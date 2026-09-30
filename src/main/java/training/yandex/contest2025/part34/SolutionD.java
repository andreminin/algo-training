package training.yandex.contest2025.part34;

import java.io.*;
import java.util.*;

public class SolutionD {
    /*
       У Васи на клавиатуре не работает клавиша пробел. Поэтому все тексты он теперь набирает слитно. Напишите программу,
       которая будет разделять набранный Васей текст на слова из данного словаря.
        Формат ввода

        Сначала на вход программы поступает текст, введенный Васей — одна строка из не более чем 100100 латинских строчных букв.

        В следующей строке входных данных задается значение NN — количество слов в словаре ( NN — натуральное число, не превосходящее 20002000).

        В следующих NN строках записаны слова из словаря — по одному слову в строке, каждое слово содержит не более 2020
        латинских строчных букв. Слова записаны в алфавитном порядке.
        Формат вывода

        Выведите Васин текст с пробелами между словами (пробел после последнего слова допустим). Если возможно несколько
        вариантов разбиения строки на слова, выведите любой из них. Гарантируется, что хотя бы один способ разбиения строки на словарные слова существует.
        Пример
        Ввод
        Вывод

        whatcanido
        6
        a
        an
        can
        do
        i
        what

     */

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
            }
            node.isWord = true;
        }

        boolean contains(String text, int start, int end) {
            TrieNode node = root;
            for (int i = start; i < end; i++) {
                node = node.children.get(text.charAt(i));
                if (node == null) return false;
            }
            return node.isWord;
        }
    }

    private static String solve(String text, String[] dict) {
        int textLength = text.length();

        if (textLength == 0 || textLength == 1 || dict.length == 0) {
            return text;
        }

        Trie trie = new Trie();
        List<Integer> sortedLengths = new ArrayList<>();
        Set<Integer> lengthSet = new HashSet<>();

        for (String word : dict) {
            trie.insert(word);
            int len = word.length();
            if (lengthSet.add(len)) {
                sortedLengths.add(len);
            }
        }

        sortedLengths.sort((a, b) -> b - a);

        boolean[] dp = new boolean[textLength + 1];
        int[] prev = new int[textLength + 1];
        dp[0] = true;

        for (int i = 1; i <= textLength; i++) {
            for (int len : sortedLengths) {
                if (len > i || !dp[i - len]) continue;

                if (trie.contains(text, i - len, i)) {
                    dp[i] = true;
                    prev[i] = len;
                    break;
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (int pos = textLength; pos > 0; pos -= prev[pos]) {
            result.add(text.substring(pos - prev[pos], pos));
        }
        Collections.reverse(result);

        return String.join(" ", result);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String text = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            String[] dict = new String[n];

            for (int i = 0; i < n; i++) {
                dict[i] = reader.readLine();
            }

            String result = solve(text, dict);

            writer.write(String.join(" ", result));
            writer.newLine();
        }
    }

    public static void test() {
        System.out.println(solve("whatcanido", new String[]{}));
        System.out.println(solve("w", new String[]{"a", "an", "can", "do", "i", "what", "do"}));
        System.out.println(solve("whatcanido", new String[]{"a", "an", "can", "do", "i", "what", "do"}));
        System.out.println(solve("whatcanimake", new String[]{"a", "an", "can", "do", "i", "what", "make"}));
    }

}
