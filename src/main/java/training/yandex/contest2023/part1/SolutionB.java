package training.yandex.contest2023.part1;

import java.io.*;
import java.util.Random;

public class SolutionB {
    /*
         Реализуйте быструю сортировку, используя алгоритм из предыдущей задачи.

        На каждом шаге выбирайте опорный элемент и выполняйте partition относительно него. Затем рекурсивно
        запуститесь от двух частей, на которые разбился исходный массив.
        Формат ввода

        В первой строке входного файла содержится число NN — количество элементов массива ( 0≤N≤1060≤N≤106).
        Во второй строке содержатся NN целых чисел aiai​, разделенных пробелами ( −109≤ai≤109−109≤ai​≤109).
        Формат вывода

        Выведите результат сортировки, то есть NN целых чисел, разделенных пробелами.
        Пример
        Ввод
        Вывод

        5
        1 5 2 4 3
        1 2 3 4 5

     */

    public static int partition(int[] numbers, int from, int to, int pivot) {
        int left = from;
        int right = to;

        while (left <= right) {
            while (left <= right && numbers[left] < pivot) {
                left++;
            }
            while (left <= right && numbers[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = numbers[left];
                numbers[left] = numbers[right];
                numbers[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }

    private final static Random RANDOM = new Random();

    public static void quicksort(int[] numbers, int from, int to) {
        if (from >= to) {
            return;
        }

        int pivotIndex = from + RANDOM.nextInt(to - from + 1);
        int pivot = numbers[pivotIndex];

        int pivotPos = partition(numbers, from, to, pivot);

        quicksort(numbers, from, pivotPos - 1);
        quicksort(numbers, pivotPos, to);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                return;
            }

            int n = Integer.parseInt(line.trim());
            if (n == 0) {
                return;
            }

            String[] parts = reader.readLine().split(" ");
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(parts[i]);
            }

            quicksort(numbers, 0, n - 1);


            for (int i = 0; i < n; i++) {
                writer.write(String.valueOf(numbers[i]));
                if (i < n - 1) {
                    writer.write(" ");
                }
            }
            writer.newLine();
        }
    }
}
