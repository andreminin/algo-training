package training.yandex.contest2023.part3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SolutionB {
    /*
        Дан ориентированный взвешенный граф. Найдите кратчайший путь от одной заданной вершины до другой.
        Формат ввода

        В первой строке содержатся три числа: N, S и F ( 1≤N≤1001≤N≤100, 1≤S1≤S, F≤NF≤N), где N — количество вершин графа, S — начальная вершина, а F — конечная. В следующих N строках вводится по N чисел, не превосходящих 100, – матрица смежности графа, где -1 означает, что ребра между вершинами нет, а любое неотрицательное число — наличие ребра данного веса. На главной диагонали матрицы записаны нули.
        Формат вывода

        Последовательно выведите все вершины одного (любого) из кратчайших путей, или -1, если пути между указанными вершинами не существует
        Примечания

        Пример ввода:

        3 2 1

        0 1 1

        4 0 1

        2 1 0

        Пример вывода:

        2 3 1
     */

    public static List<Integer> dijkstraPath(int[][] graph, int start, int finish) {
        int n = graph.length - 1;
        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            int vertex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && (vertex == -1 || dist[j] < dist[vertex])) {
                    vertex = j;
                }
            }

            if (vertex == -1 || dist[vertex] == Integer.MAX_VALUE) break;
            visited[vertex] = true;

            for (int edge = 1; edge <= n; edge++) {
                if (graph[vertex][edge] != Integer.MAX_VALUE && dist[vertex] + graph[vertex][edge] < dist[edge]) {
                    dist[edge] = dist[vertex] + graph[vertex][edge];
                    prev[edge] = vertex;
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        if (dist[finish] == Integer.MAX_VALUE) {
            path.add(-1);
        } else {
            for (int v = finish; v != -1; v = prev[v]) {
                path.add(v);
            }
            Collections.reverse(path);
        }

        return path;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] tokens = reader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int start = Integer.parseInt(tokens[1]);
            int finish = Integer.parseInt(tokens[2]);

            int[][] graph = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                tokens = reader.readLine().split(" ");
                for (int j = 1; j <= n; j++) {
                    int weight = Integer.parseInt(tokens[j - 1]);
                    graph[i][j] = (weight == -1) ? Integer.MAX_VALUE : weight;
                }
            }

            List<Integer> path = dijkstraPath(graph, start, finish);

            writer.write(String.join(" ", path.stream().map(String::valueOf).toList()));
            writer.newLine();
        }
    }
}
