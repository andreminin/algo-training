package training.yandex.contest2021;

import java.io.*;

public class SolutionD {

    /*
        Решите в целых числах уравнение:

        √ax+b=cax+b

        ​=c,

        a, b, c – данные целые числа: найдите все решения или сообщите, что решений в целых числах нет.
        Формат ввода

        Вводятся три числа a, b и c по одному в строке.
        Формат вывода

        Программа должна вывести все решения уравнения в порядке возрастания, либо NO SOLUTION (заглавными буквами), если решений нет. Если решений бесконечно много, вывести MANY SOLUTIONS.
        Пример 1
        Ввод
        Вывод

        1
        0
        0



        0


     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = Integer.parseInt(reader.readLine());
            int b = Integer.parseInt(reader.readLine());
            int c = Integer.parseInt(reader.readLine());

            if (a == 0) {
                if (b < 0) {
                    writer.write("NO SOLUTION");
                } else {
                    if (c < 0) {
                        writer.write("NO SOLUTION");
                    } else if (c * c == b) {
                        writer.write("MANY SOLUTIONS");
                    } else {
                        writer.write("NO SOLUTION");
                    }
                }
                writer.newLine();
                return;
            }

            if (c < 0) {
                writer.write("NO SOLUTION");
                writer.newLine();
                return;
            }

            long discr = (long) c * c - b;

            if (discr % a != 0) {
                writer.write("NO SOLUTION");
                writer.newLine();
                return;
            }

            int x = (int) (discr / a);

            writer.write(String.valueOf(x));
            writer.newLine();
        }
    }
}
