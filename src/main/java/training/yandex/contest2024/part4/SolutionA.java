package training.yandex.contest2024.part4;

import java.io.*;
import java.util.Arrays;

public class SolutionA {

    /*
        Дан массив из NN целых чисел. Все числа от −109−109 до 109109.

        Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения от L до R?”.
        Формат ввода

        Число NN ( 1≤N≤1051≤N≤105). Далее NN целых чисел.

        Затем число запросов KK ( 1≤K≤1051≤K≤105).

        Далее KK пар чисел L,RL,R ( −109≤L≤R≤109−109≤L≤R≤109) — собственно запросы.
        Формат вывода

        Выведите KK чисел — ответы на запросы.
        Пример
        Ввод
        Вывод

        5
        10 1 10 3 4
        4
        1 10
        2 9
        3 4
        2 2


     */


    public static int lowerBound(int[] numbers, int value) {
        int l = 0;
        int r = numbers.length - 1;
        int result = numbers.length;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (numbers[m] >= value) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return result;
    }

    public static int upperBound(int[] numbers, int value) {
        int l = 0;
        int r = numbers.length - 1;
        int result = numbers.length;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (numbers[m] > value) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            String[] parts = reader.readLine().split(" ");
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(parts[i]);
            }
            Arrays.sort(numbers);

            int q = Integer.parseInt(reader.readLine());

            for (int i = 0; i < q; i++) {
                parts = reader.readLine().split(" ");
                int l = Integer.parseInt(parts[0]);
                int r = Integer.parseInt(parts[1]);

                int leftIndex = lowerBound(numbers, l);
                int rightIndex = upperBound(numbers, r);
                int count = Math.max(0, rightIndex - leftIndex);

                writer.write(count + " ");
            }
        }
    }
}
