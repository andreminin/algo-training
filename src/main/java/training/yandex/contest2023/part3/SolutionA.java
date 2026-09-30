package training.yandex.contest2023.part3;

import java.io.*;
import java.util.Arrays;

public class SolutionA {
    /*
        Дан ориентированный взвешенный граф. Найдите кратчайшее расстояние от одной заданной вершины до другой.
        Формат ввода

        В первой строке содержатся три числа: N, S и F (1≤ N ≤ 100, 1 ≤ S, F ≤ N), где N — количество вершин графа, S — начальная вершина, а F — конечная. В следующих N строках вводится по N чисел, не превосходящих 100, – матрица смежности графа, где -1 означает что ребра между вершинами нет, а любое неотрицательное число — наличие ребра данного веса. На главной диагонали матрицы записаны нули.
        Формат вывода

        Выведите искомое расстояние или -1, если пути между указанными вершинами не существует.
        Пример
        Ввод
        Вывод

        3 2 1
        0 1 1
        4 0 1
        2 1 0

        3

       Считывание данных: Первая строка содержит N, S и F. Последующие строки формируют матрицу смежности.

        Обработка матрицы смежности: Заменяем -1 на Integer.MAX_VALUE для удобства обработки в алгоритме.

        Алгоритм Дейкстры: Ищем вершину с наименьшим расстоянием, обновляем расстояния до её соседей. Процесс повторяется для всех вершин.

        Вывод результата: Если путь до конечной вершины существует, выводим его длину, иначе -1.

    Этот подход гарантирует нахождение кратчайшего пути за время O(N^2), что достаточно для ограничения N ≤ 100.
     */

    public static int dijkstraDist(int[][] graph, int start, int finish) {
        int n = graph.length - 1;

        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int i = 1; i <= n; i++) {
            int vertex = -1;
            for (int j = 1; j <= n; j++) {
                if (!visited[j] && (vertex == -1 || dist[j] < dist[vertex])) {
                    vertex = j;
                }
            }

            if (dist[vertex] == Integer.MAX_VALUE) break;
            visited[vertex] = true;

            for (int edge = 1; edge <= n; edge++) {
                if (graph[vertex][edge] != Integer.MAX_VALUE && dist[vertex] + graph[vertex][edge] < dist[edge]) {
                    dist[edge] = dist[vertex] + graph[vertex][edge];
                }
            }
        }

        return dist[finish] == Integer.MAX_VALUE ? -1 : dist[finish];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] tokens = reader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int start = Integer.parseInt(tokens[1]);
            int finish = Integer.parseInt(tokens[2]);

            int[][] graph = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                tokens = reader.readLine().split(" ");
                for (int j = 1; j <= n; j++) {
                    int weight = Integer.parseInt(tokens[j-1]);
                    graph[i][j] = weight == -1 ? Integer.MAX_VALUE : weight;
                }
            }

            int distance = dijkstraDist(graph, start, finish);

            writer.write(String.valueOf(distance));
            writer.newLine();
        }
    }
}
