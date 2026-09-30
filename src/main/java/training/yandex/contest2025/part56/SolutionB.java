package training.yandex.contest2025.part56;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class SolutionB {

   /*
    Каждый вечер после работы Вася приходит домой и думает о жизни. Вася описал свои перспективы в виде nn жизненных ситуаций и соединил их n−1n−1 двунаправленными переходами так, что между двумя любыми жизненными ситуациями существовал ровно один простой путь, возможно через промежуточные ситуации. Жизненным тупиком называется жизненная ситуация, из которой существует ровно один переход.

    Помогите Васе найти самый короткий путь из одного жизненного тупика в другой.
    Формат ввода

    В первой строке вводится целое число nn — число жизненных ситуаций ( 2≤n≤1052≤n≤105).

    В следующих n−1n−1 строках заданы по два числа aiai​, bibi​ — номера жизненных ситуаций, между которыми возможен переход ( 1≤ai,bi≤n1≤ai​,bi​≤n).

    Гарантируется, что между любыми двумя жизненными ситуациями существует ровно один простой путь.
    Формат вывода

    Выведите одно число — минимальное количество переходов, которое нужно совершить Васе чтобы попасть из одного жизненного тупика в другой.
    Пример 1
    Ввод
    Вывод

    5
    1 2
    1 3
    2 4
    2 5

    */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());

            List<List<Integer>> graph = new ArrayList<>(n + 1);
            int[] degree = new int[n + 1];
            int[] source = new int[n + 1];

            int[] distance = new int[n + 1];
            Arrays.fill(distance, -1);

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < n - 1; i++) {
                String[] parts = reader.readLine().split(" ");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);

                graph.get(a).add(b);
                graph.get(b).add(a);

                degree[a]++;
                degree[b]++;
            }

            Queue<Integer> queue = new LinkedList<>();

            IntStream.range(0, degree.length)
                    .filter(i -> degree[i] == 1)
                    .forEach(leaf -> {
                        distance[leaf] = 0;
                        source[leaf] = leaf;
                        queue.add(leaf);
                    });

            int minDistance = Integer.MAX_VALUE;

            while (!queue.isEmpty()) {
                int sourceNode = queue.poll();

                for (int targetNode : graph.get(sourceNode)) {
                    // first visit?
                    if (distance[targetNode] == -1) {
                        distance[targetNode] = distance[sourceNode] + 1;
                        source[targetNode] = source[sourceNode];
                        queue.add(targetNode);
                    } else if (source[targetNode] != source[sourceNode]) {
                        // came from another node - sum and find min
                        minDistance = Math.min(minDistance, distance[sourceNode] + distance[targetNode] + 1);
                    }
                }
            }

            writer.write(String.valueOf(minDistance));
            writer.newLine();
        }
    }
}
