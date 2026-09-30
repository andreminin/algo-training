package training.yandex.contest2023.part1;

import java.io.*;

public class SolutionA {

    /*
       Базовым алгоритмом для быстрой сортировки является алгоритм partition, который разбивает набор элементов на две
       части относительно заданного предиката.
        По сути элементы массива просто меняются местами так, что левее некоторой точки в нем после этой операции лежат элементы, удовлетворяющие заданному предикату, а справа — не удовлетворяющие ему.
        Например, при сортировке можно использовать предикат «меньше опорного», что при оптимальном выборе опорного элемента может разбить массив на две примерно равные части.

        Напишите алгоритм partition в качестве первого шага для написания быстрой сортировки.
        Формат ввода

        В первой строке входного файла содержится число NN — количество элементов массива ( 0≤N≤1060≤N≤106).
        Во второй строке содержатся NN целых чисел aiai​, разделенных пробелами ( −109≤ai≤109−109≤ai​≤109).
        В третьей строке содержится опорный элемент xx ( −109≤x≤109−109≤x≤109).
        Заметьте, что xx не обязательно встречается среди aiai​.
        Формат вывода

        Выведите результат работы вашего алгоритма при использовании предиката «меньше x»: в первой строке выведите число элементов массива, меньших x, а во второй — количество всех остальных.

        Пример 1
        5
        1 9 4 2 3
        3

        Вывод
        2
        3
     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int m = Integer.parseInt(reader.readLine());
            String[] parts = reader.readLine().split(" ");

            int[] numbers = new int[m];
            for (int i = 0; i < m; i++) {
                numbers[i] = Integer.parseInt(parts[i]);
            }

            int pivot = Integer.parseInt(reader.readLine());

            if(m == 0) {
                writer.write("0");
                writer.newLine();
                writer.write("0");
                writer.newLine();
                return;
            }

            int left = 0;
            int right = m - 1;
            int tmp;
            int count = 0;

            while (left <= right) {
                if(numbers[left] < pivot) {
                    left++;
                    count++;
                } else {
                    if(numbers[right] < pivot) {
                        tmp = numbers[right];
                        numbers[right] = numbers[left];
                        numbers[left] = tmp;
                        left++;
                        count++;
                    }
                    right--;
                }
            }


            writer.write(String.valueOf(count));
            writer.newLine();
            writer.write(String.valueOf(m - count));
            writer.newLine();
        }
    }
}
