package training.yandex.contest2025.part56;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionH {

   /*
    Город Деревянск состоит из nn площадей, некоторые из которых соединены двусторонними дорогами. От любой площади до любой другой можно доехать единственным способом. На ii-й площади живёт aiai​ людей.

    В городе планируется открыть пункт выдачи заказов на одной из площадей. Все жители города сразу же сделают заказы и придут за ними в первый же день. Для жителей площади, на которой откроется пункт выдачи, будет отдельный вход. Также отдельные входы будут построены со стороны каждой дороги, ведущей на какую-либо другую площадь.

    К каждому входу перед открытием выстроится очередь, состоящая из всех жителей, живущих со стороны этой двери. Большие очереди могут расстроить посетителей, поэтому вам предстоит выбрать площадь таким образом, чтобы длина максимальной очереди была минимальна.
    Формат ввода

    В первой строке вводится число площадей nn ( 1≤n≤1000001≤n≤100000).

    Во второй строке заданы nn чисел aiai​ ( 1≤ai≤1091≤ai​≤109) — количество жителей, живущих на ii-й площади.

    Каждая из следующих n−1n−1 строк содержит пару чисел vjvj​, ujuj​ ( 1≤vj,uj≤n1≤vj​,uj​≤n) — номера площадей, соединенных дорогой.
    Формат вывода

    Выведите единственное число — номер площади, на которой нужно построить пункт выдачи. Если ответов несколько, выведите любой.
    Пример 1
    Ввод
    Вывод

    5
    3 3 2 5 1
    1 2
    2 3
    2 4
    4 5



    2

    Пример 2
    Ввод
    Вывод

    3
    1 2 1
    2 3
    1 2



    2


    Построение графа: Граф строится как список смежности, где каждая вершина хранит список своих соседей.

    Обход дерева: С помощью BFS определяется родитель и дети для каждой вершины, что позволяет упорядочить вершины для последующей обработки.

    Вычисление сумм: Для каждой вершины вычисляется сумма весов её поддерева, начиная с листьев и двигаясь к корню.

    Поиск оптимальной вершины: Для каждой вершины определяется максимальная очередь, учитывая её собственный вес и веса поддеревьев. Вершина с минимальным значением этого максимума выбирается в качестве ответа.
    */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            long[] a = new long[n];
            String[] aStr = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(aStr[i]);
            }

            List<Integer>[] graph = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < n - 1; i++) {
                String[] edge = reader.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                graph[u].add(v);
                graph[v].add(u);
            }

            int[] parent = new int[n + 1];
            List<Integer>[] children = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                children[i] = new ArrayList<>();
            }
            List<Integer> order = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            parent[1] = 0;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                order.add(u);
                for (int v : graph[u]) {
                    if (v != parent[u]) {
                        parent[v] = u;
                        children[u].add(v);
                        queue.add(v);
                    }
                }
            }

            long[] sum = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                sum[i] = a[i - 1];
            }

            for (int i = order.size() - 1; i >= 0; i--) {
                int u = order.get(i);
                for (int child : children[u]) {
                    sum[u] += sum[child];
                }
            }

            long S = sum[1];
            long minMax = Long.MAX_VALUE;
            int bestVertex = 1;

            for (int u = 1; u <= n; u++) {
                long currentMax = Math.max(a[u - 1], S - sum[u]);
                for (int child : children[u]) {
                    currentMax = Math.max(currentMax, sum[child]);
                }
                if (currentMax < minMax) {
                    minMax = currentMax;
                    bestVertex = u;
                }
            }

            writer.write(String.valueOf(bestVertex));
            writer.newLine();
        }
    }
}
