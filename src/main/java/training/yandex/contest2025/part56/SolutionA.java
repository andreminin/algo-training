package training.yandex.contest2025.part56;

import java.io.*;

import static java.lang.Math.sqrt;

public class SolutionA {

   /*
        Вася решил сделать из квадратного куска фанеры прямоугольную полочку для дома.

        Для этого он взял на работе кусок фанеры и отпилил и выбросил от него полосу шириной aa, сделав вертикальный разрез.

        После этого он отрезал полосу высотой bb сделав горизонтальный разрез, и также выбросил её.

        Площадь оставшейся полочки была равна SS.

        После этого у Васи начались неприятности. Во-первых, начальник заметил исчезновение листа фанеры и обязал
         Васю купить за свой счет точно такой же лист. Во-вторых, жена выбросила полочку, так что измерить её Вася не
         может и всё что он помнит — это числа aa, bb и SS.

        В магазине продаются только квадратные листы фанеры с целочисленными сторонами. Помогите Васе определите
         размеры листа, который он использовал, либо определите, что такого не существует.
        Формат ввода

        Вводятся три целых числа aa, bb и SS ( 0≤a,b≤1040≤a,b≤104, 1≤S≤1081≤S≤108).
        Формат вывода

        Если необходимый лист фанеры есть в продаже, то выведите длину его стороны LL.

        Если же такого листа с целочисленными сторонами не может существовать, то выведите «-1».
        Пример 1
        Ввод
        Вывод

        1 1 1
        2

        Пример 2
        Ввод
        Вывод

        2 3 12
        6

        Пример 3
        Ввод
        Вывод

        1 1 2
        -1
    */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int A = Integer.parseInt(parts[0]);
            int B = Integer.parseInt(parts[1]);
            int S = Integer.parseInt(parts[2]);
            // s = (L-a)*(L-b)
            // L^2 - (a+b)*L + (a*b - s) = 0;
            // a = 1; b = -(a+b); c = (a*b -s)
            long a = 1;
            long b = -(A + B);
            long c = (A * B - S);

            long L = -1;
            long d = b * b - 4 * a * c; //  b^2 – 4ac.

            if (d < 0) {
                writer.write("-1");
                writer.newLine();
                return;
            }

            long root = (long) sqrt(1.0 * d);
            if (root * root != d) {
                writer.write("-1");
                writer.newLine();
                return;
            }

            long L1 = (-b - root) / (2 * a);
            long L2 = (-b + root) / (2 * a);

            if ((-b - root) % (2 * a) == 0 && L1 > A && L1 > B) {
                L = L1;
            }

            if ((-b + root) % (2 * a) == 0 && L2 > A && L2 > B) {
                if (L != -1) {
                    writer.write("-1");
                    writer.newLine();
                    return;
                }
                L = L2;
            }

            writer.write(String.valueOf(L));
            writer.newLine();
        }
    }
}
