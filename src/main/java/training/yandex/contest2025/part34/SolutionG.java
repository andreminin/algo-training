package training.yandex.contest2025.part34;

import java.io.*;

public class SolutionG {
    /*
        Лесенкой называется набор кубиков, в котором каждый горизонтальный слой содержит меньше кубиков, чем слой под ним. В нижнем слое может быть любое количество кубиков.

        Необходимо подсчитать количество различных лесенок, которые могут быть построены из NN кубиков.
        Формат ввода

        Вводится одно число NN ( 1≤N≤1501≤N≤150).
        Формат вывода

        Выведите число лесенок.
        Пример 1
        Ввод   1
        Вывод  1


        Пример 2
        Ввод   3
        Вывод  2

        We need to count the number of different "ladders" (staircases) that can be built from exactly N cubes, where:

            Each horizontal layer must have fewer cubes than the layer below it

            The bottom layer can have any number of cubes

            All N cubes must be used

        This is equivalent to counting the number of partitions of N into distinct positive integers.
        Examples

            N = 1: Only [1] → 1 way

            N = 3: [3] and [2,1] → 2 ways

            N = 5: [5], [4,1], [3,2] → 3 ways

            N = 6: [6], [5,1], [4,2], [3,2,1] → 4 ways

        State Definition

            dp[i] = number of ways to form sum i using distinct integers

        Base Case

            dp[0] = 1: There's exactly 1 way to make sum 0 - use no numbers

        Transition

        For each possible layer size k (from 1 to N):

            We iterate backwards from N down to k

            dp[i] += dp[i - k] means:

                If we can make sum (i - k) in dp[i - k] ways

                Then by adding layer of size k, we can make sum i

        Why Iterate Backwards?

            Iterating backwards ensures we don't use the same k multiple times

            This guarantees all integers in the partition are distinct
     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());

            int[] dp = new int[n + 1];
            dp[0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = n; j >= i; j--) {
                    dp[j] += dp[j - i];
                }
            }

            writer.write(String.valueOf(dp[n]));
         }
    }
}
