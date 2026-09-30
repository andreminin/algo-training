package training.yandex.contest2025.part78;

import java.io.*;
import java.util.TreeSet;

public class SolutionH {
    /*
        Начальник решил выдать премии своим сотрудникам. Для этого он выписал всех сотрудников в порядке возрастания профессионализма, а также выписал для каждого сотрудника количество выходных дней, в которые он работал, для ii-о сотрудника это число aiai​.

        Подсчитать премию для каждого сотрудника решил по следующей формуле: для сотрудника ii он подсчитывает количество таких сотрудников с номерами j<ij<i, что j+aj>ij+aj​>i. Затем он умножает это количество на aiai​ и таким образом вычисляет размер премии ii-о сотрудника.

        Определите суммарный размер премий всех сотрудников.
        Формат ввода

        В первой строке вводится число nn ( 1≤n≤1000001≤n≤100000) — количество сотрудников.

        Вторая строка содержит nn целых чисел aiai​ ( 0≤ai≤n0≤ai​≤n) — количество выходных дней, которые отработали сотрудники.
        Формат вывода

        Выведите суммарный размер премий.
        Пример
        Ввод
        Вывод

        4
        4 2 2 4



        14

        Примечания

        Ответ на тест, приведенный в примере, получается по формуле

        0×4+1×2+2×2+2×4=140×4+1×2+2×2+2×4=14

     */


    static class FenwickTree {
        private final int[] tree;

        public FenwickTree(int size) {
            tree = new int[size];
        }

        public void update(int index, int delta) {
            while (index < tree.length) {
                tree[index] += delta;
                index = index | (index + 1);
            }
        }

        public int get(int index) {
            int sum = 0;

            while (index >= 0) {
                sum += tree[index];
                index = (index & (index + 1)) - 1;
            }

            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];
            String[] parts = reader.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }

            FenwickTree tree = new FenwickTree(2 * n + 2);
            long totalPremium = 0;
            int countLessOrEqual;
            int countGreater;

            for (int i = 0; i < n; i++) {
                countLessOrEqual = tree.get(i);
                countGreater = i - countLessOrEqual;

                totalPremium += (long) countGreater * a[i];
                tree.update(i + a[i], 1);
            }

            writer.write(String.valueOf(totalPremium));
        }
    }

    public static void main2(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];
            String[] parts = reader.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }

            long totalPremium = 0;
            TreeSet<Integer> sortedValues = new TreeSet<>();
            // Храним все значения j + a[j] в отсортированном виде

            for (int i = 0; i < n; i++) {
                int countGreater = sortedValues.tailSet(i + 1).size();
                totalPremium += (long) countGreater * a[i];

                sortedValues.add(i + a[i]);
            }

            writer.write(String.valueOf(totalPremium));
        }
    }
}
