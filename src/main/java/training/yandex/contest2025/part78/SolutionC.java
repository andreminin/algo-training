package training.yandex.contest2025.part78;

import java.io.*;

public class SolutionC {
    /*
    За забором завода по производству программ выстроилась очередь из кандидатов, желающих устроиться на работу. Исходно в очереди стоит nn человек. Люди в очереди пронумерованы по порядку, начиная с нуля. Номер человека в очереди равен числу людей, которое стоит в очереди перед ним. В конец очереди могут вставать новые кандидаты, а также из начала очереди кандидаты могут проходить на собеседование.

    Вася давно работает в HR, поэтому с одного взгляда умеет оценивать профессионализм кандидата. Профессионализм ii-го человека — целое неотрицательное число aiai​. Во время стояния в очереди профессионализм кандидата не меняется.

    Вася считает кандидата подходящим, если его профессионализм не меньше xx. Если это не так, то кандидат неподходящий.

    Иногда к Васе приходит коллега Маша и спрашивает, сколько подходящих кандидатов среди первых kk человек в очереди. Вася хороший HR, но плохой программист (если бы был хорошим — сам бы встал в очередь), так что помогите ему правильно ответить на запросы Маши.
    Формат ввода

    В первой строке вводятся два числа nn, xx ( 1≤n≤100000,0≤x≤1091≤n≤100000,0≤x≤109) — начальное количество кандидатов в очереди и нижняя граница профессионализма.

    В следующей строке вводятся nn чисел aiai​ — профессионализм людей в очереди ( 0≤ai≤1090≤ai​≤109).

    В третьей строке вводится число mm ( 1≤m≤1000001≤m≤100000) — количество событий, которые происходили с очередью. В следующих mm строках дано описание событий. Событие бывает одного из трёх типов:

        1 aa ( 0≤a≤1090≤a≤109) — в конец очереди приходит человек с профессионализмом, равным aa.

        2 — из начала очереди уходит человек (он имеет номер 0). После этого номера оставшихся в очереди людей уменьшаются на 1.

        3 kk ( 0≤k≤n0≤k≤n) — Маша хочет узнать, сколько подходящих людей среди первых kk человек в очереди.

    Гарантируется, что все запросы корректны: если в очереди никого нет, то операция второго типа не выполняется, а количество человек в очереди всегда будет строго больше kk в запросе третьего типа.
    Формат вывода

    На каждый запрос третьего типа в отдельной строке выведите одно число — количество подходящих кандидатов.
    Пример 1
    Ввод
    Вывод

    1 2
    3
    5
    1 2
    1 1
    3 0
    3 1
    3 2



    0
    1
    2

    Пример 2
    Ввод
    Вывод

    2 2
    1 2
    7
    3 0
    3 1
    2
    3 0
    1 3
    3 0
    3 1



    0
    0
    0
    0
    1

     */

    static class FenwickTree {
        int[] tree;
        int n;

        public FenwickTree(int size) {
            n = size;
            tree = new int[n + 1];
        }

        public void update(int idx, int delta) {
            for (int i = idx + 1; i <= n; i += i & -i) {
                tree[i] += delta;
            }
        }

        public int query(int idx) {
            int s = 0;
            for (int i = idx + 1; i > 0; i -= i & -i) {
                s += tree[i];
            }
            return s;
        }

        public int rangeQuery(int l, int r) {
            if (l > r) return 0;
            return query(r) - query(l - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int x = Integer.parseInt(parts[1]);

        parts = reader.readLine().split(" ");
        int[] initial = new int[n];
        for (int i = 0; i < n; i++) {
            initial[i] = Integer.parseInt(parts[i]);
        }

        int m = Integer.parseInt(reader.readLine());

        int capacity = n + m;
        FenwickTree tree = new FenwickTree(capacity);
        int[] current = new int[capacity];

        int start = 0;
        int end = n - 1;

        for (int i = 0; i < n; i++) {
            if (initial[i] >= x) {
                current[i] = 1;
                tree.update(i, 1);
            }
        }

        for (int i = 0; i < m; i++) {
            String[] event = reader.readLine().split(" ");
            switch (event[0]) {
                case "1" -> {
                    int a = Integer.parseInt(event[1]);
                    end = (end + 1) % capacity;

                    if (current[end] != 0) {
                        tree.update(end, -current[end]);
                        current[end] = 0;
                    }

                    int value = (a >= x) ? 1 : 0;
                    current[end] = value;

                    if (value == 1) {
                        tree.update(end, 1);
                    }
                }
                case "2" -> {
                    if (current[start] != 0) {
                        tree.update(start, -current[start]);
                        current[start] = 0;
                    }

                    start = (start + 1) % capacity;
                }
                case "3" -> {
                    int k = Integer.parseInt(event[1]);

                    if (k == 0) {
                        writer.write("0\n");
                    } else {
                        int r = start + k - 1;
                        int ans;
                        if (r < capacity) {
                            ans = tree.rangeQuery(start, r);
                        } else {
                            ans = tree.rangeQuery(start, capacity - 1) + tree.rangeQuery(0, r % capacity);
                        }
                        writer.write(ans + "\n");
                    }
                }
            }
        }

        writer.flush();
    }
}
