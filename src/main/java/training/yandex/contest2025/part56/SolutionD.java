package training.yandex.contest2025.part56;

import java.io.*;
import java.util.*;

public class SolutionD {

   /*
        Феоктист работает в обменном пункте на границе Флатландии и Байтландии. Каждый день он узнает по радио текущий курс обмена Флатландских флатов на Байтландские биты и вывешивает информацию об обменном курсе на дверях своего пункта.

        В распоряжении Феоктиста есть nn табличек, на которых записаны числа c1,c2,…,cnc1​,c2​,…,cn​. Узнав сегодняшний курс обмена pp, Феоктист выбирает две таблички с значениями cici​ и cjcj​, такими, чтобы значение ci/cjci​/cj​ было как можно ближе к pp, и вывешивает их на двери, формируя таким образом объявление «меняю cici​ флатов на cjcj​ битов». Задача не из легких и Феоктист решил автоматизировать её.

        Помогите Феоктисту по заданному курсе pp найти две соответствующие таблички.
        Формат ввода

        В первой строке входного файла заданы два целых числа nn и pp ( 2≤n≤1000002≤n≤100000, 1≤p≤1091≤p≤109) — число табличек и текущий курс. Вторая строка содержит nn целых чисел cici​ ( 1≤ci≤1091≤ci​≤109) — числа, записанные на табличках.
        Формат вывода

        Выведите два целых числа ii и jj ( 1≤i,j≤n1≤i,j≤n, i≠ji=j) — номера двух табличек, таких что величина ∣∣(ci/cj)−p∣∣∣(ci​/cj​)−p∣ минимальна. Если таких пар несколько, то вы можно вывести любую из них.
        Пример 1
        Ввод
        Вывод

        3 2
        1 6 3



        2 3

        Пример 2
        Ввод
        Вывод

        4 3
        2 3 4 5

    */


    static class ValueWithIndex {
        int value;
        int originalIndex;

        ValueWithIndex(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int p = Integer.parseInt(parts[1]);

        parts = reader.readLine().split(" ");
        ValueWithIndex[] values = new ValueWithIndex[n];
        for (int i = 0; i < n; i++) {
            values[i] = new ValueWithIndex(Integer.parseInt(parts[i]), i + 1);
        }

        Arrays.sort(values, Comparator.comparingInt(v -> v.value));

        int bestI = values[0].originalIndex;
        int bestJ = values[1].originalIndex;
        double bestDiff = Math.abs((double)values[0].value / values[1].value - p);

        for (int i = 0; i < n; i++) {
            long current = values[i].value;
            double targetValue = (double)current / p;

            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (values[mid].value < targetValue) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            for (int offset = -2; offset <= 2; offset++) {
                int j = left + offset;
                if (j >= 0 && j < n && j != i) {
                    double ratio = (double)current / values[j].value;
                    double diff = Math.abs(ratio - p);

                    if (diff < bestDiff
                            || (Math.abs(diff - bestDiff) < 1e-12
                            && (values[i].originalIndex < bestI
                            || (values[i].originalIndex == bestI && values[j].originalIndex < bestJ))))
                    {
                        bestDiff = diff;
                        bestI = values[i].originalIndex;
                        bestJ = values[j].originalIndex;
                    }
                }
            }
        }

        writer.write(bestI + " " + bestJ);
        writer.newLine();
        writer.flush();
    }
}
