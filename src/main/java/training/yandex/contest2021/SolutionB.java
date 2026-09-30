package training.yandex.contest2021;

import java.io.*;

public class SolutionB {

    /*
        Даны три натуральных числа. Возможно ли построить треугольник с такими сторонами. Если это возможно, выведите строку YES, иначе выведите строку NO.

        Треугольник — это три точки, не лежащие на одной прямой.
        Формат ввода

        Вводятся три натуральных числа.
        Формат вывода

        Выведите ответ на задачу.
        Пример 1
        Ввод
        Вывод

        3
        4
        5



        YES

        Пример 2
        Ввод
        Вывод

        3
        5
        4




        YES

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = Integer.parseInt(reader.readLine());
            int b = Integer.parseInt(reader.readLine());
            int c = Integer.parseInt(reader.readLine());

            boolean result = (a + b) > c && (a + c) > b && (b + c) > a;

            writer.write(result ? "YES" : "NO");
            writer.newLine();
        }
    }
}
