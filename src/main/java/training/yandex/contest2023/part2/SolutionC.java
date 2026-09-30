package training.yandex.contest2023.part2;

import java.io.*;

public class SolutionC {

    /*
        Дана непустая строка SS, длина которой NN не превышает 106106. Будем считать, что элементы строки нумеруются от 0 до N−1N−1.

        Вычислите z-функцию z[i]z[i] для всех ii от 0 до N−1N−1. z[i]z[i] определяется как максимальная длина подстроки, начинающейся с позиции i и совпадающей с префиксом всей строки. z[0]=0z[0]=0
        Формат ввода

        Одна строка длины NN, 0<N≤1060<N≤106, состоящая из прописных латинских букв.
        Формат вывода

        Выведите NN чисел — значения z-функции для каждой позиции, разделённые пробелом.
        Пример
        Ввод
        Вывод

        abracadabra



        0 0 0 1 0 1 0 4 0 0 1

        Инициализация: Считываем строку s и инициализируем массив z для хранения значений z-функции.

        Обработка символов: Для каждого символа строки, начиная с индекса 1, вычисляем значение z-функции, используя ранее вычисленные значения для оптимизации.

        Обновление отрезка: После вычисления z[i] обновляем границы отрезка [left, right], если текущий отрезок, начинающийся с i, оказывается правее ранее известного.

        Вывод результата: После обработки всех символов выводим значения z-функции для каждой позиции строки.
     */


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();
            int n = s.length();
            int[] z = new int[n];
            z[0] = 0;

            int left = 0, right = 0;
            for (int i = 1; i < n; i++) {
                if (i <= right) {
                    z[i] = Math.min(right - i + 1, z[i - left]);
                } else {
                    z[i] = 0;
                }

                while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                    z[i]++;
                }

                if (i + z[i] - 1 > right) {
                    left = i;
                    right = i + z[i] - 1;
                }
            }

            for (int i = 0; i < n; i++) {
                writer.write(z[i] + " ");
            }
        }
    }
}
