package training.yandex.contest2025.part56;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionF {

   /*
        Напишите программу, которая для двух вершин дерева определяет, является ли одна из них предком другой.
        Формат ввода

        Первая строка входного файла содержит натуральное число nn ( 1⩽n⩽1000001⩽n⩽100000) — количество вершин в дереве. Во второй строке находятся nn чисел, ii-е из которых определяет номер непосредственного родителя вершины с номером ii. Если это число равно нулю, то вершина является корнем дерева.

        В третьей строке находится число mm ( 1⩽m⩽1000001⩽m⩽100000) — количество запросов. Каждая из следующих mm строк содержит два различных числа aa и bb ( 1⩽a,b⩽n1⩽a,b⩽n).
        Формат вывода

        Для каждого из mm запросов выведите на отдельной строке число 1, если вершина aa является одним из предков вершины bb, и 0 в противном случае.
        Пример
        Ввод
        Вывод

        6
        0 1 1 2 3 3
        5
        4 1
        1 4
        3 6
        2 6
        6 5



        0
        1
        1
        0
        0

    */

    static class Node {
        List<Integer> children;
        int value;
        int parent;
        int lowerBound;
        int upperBound;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }


    public static int nodeBounds(Node node, int bound, Map<Integer, Node> nodes) {
        node.lowerBound = ++bound;

        for(Integer childId : node.children) {
            Node child = nodes.get(childId);
            bound = nodeBounds(child, bound, nodes);
        }

        node.upperBound = ++bound;

        return bound;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            String[] parts = reader.readLine().split(" ");
            int[] parents = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parents[i] = Integer.parseInt(parts[i - 1]);
            }

            Map<Integer, Node> nodes = new HashMap<>();

            Node root = new Node(0);
            nodes.put(0, root);
            for(int i = 1; i <= n; i++) {
                Node node = nodes.merge(i, new Node(i), (old,val)->old);
                int parentId = parents[i];
                node.parent = parentId;
                Node parent = nodes.merge(parentId, new Node(parentId), (old,val)->old);
                parent.children.add(i);
            }

            nodeBounds(root, 0, nodes);

            int m = Integer.parseInt(reader.readLine());
            boolean[] answers = new boolean[m];

            for (int i = 0; i < m; i++) {
                parts = reader.readLine().split(" ");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);

                Node nodeA = nodes.get(a);
                Node nodeB = nodes.get(b);

                answers[i] = nodeA.lowerBound <= nodeB.lowerBound && nodeA.upperBound >= nodeB.upperBound;
            }

            for (boolean answer : answers) {
                writer.write(answer ? "1" : "0");
                writer.newLine();
            }
        }
    }
}
