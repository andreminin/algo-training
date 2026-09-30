package training.yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution_62 {
    /*
      62. Количество различных чисел
        Не решалась
        Лёгкая

        Дан список чисел, который может содержать до 100000 чисел. Определите, сколько в нём встречается различных чисел.
        Формат ввода

        Вводится список целых чисел. Все числа списка находятся на одной строке.
        Формат вывода

        Выведите ответ на задачу.
        Ограничения

        Ограничение времени
            1 с
        Ограничение памяти
            64 МБ

        Примеры
        Пример 1
        Ввод

        1 2 3 2 1

        Вывод

        3

        Пример 2
        Ввод

        1 2 3 4 5 6 7 8 9 10

        Вывод

        10

        Пример 3
        Ввод

        1 2 3 4 5 1 2 1 2 7 3

        Вывод

        6

     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        String input = reader.readLine();
        String[] numbers = input.split(" ");

        int count = Arrays.stream(numbers).filter(str -> str != null && !str.trim().isEmpty()).collect(Collectors.toSet()).size();

        writer.write(String.valueOf(count));

        reader.close();
        writer.close();
    }
}
