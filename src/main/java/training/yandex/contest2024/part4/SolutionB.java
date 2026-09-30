package training.yandex.contest2024.part4;

import java.io.*;

public class SolutionB {

    /*
        Поле в игре в одномерный морской бой имеет размеры 1×n1×n. Ваша задача — найти такое максимальное kk, что на поле можно расставить один корабль размера 1×k1×k, два корабля размера 1×(k−1)1×(k−1), ……, kk кораблей размера 1×11×1, причем корабли, как и в обычном морском бое, не должны касаться друг друга и пересекаться.
        Формат ввода

        В единственной строке входных данных дано число nn — количество клеток поля ( 0≤n≤10180≤n≤1018).
        Формат вывода

        Выведите единственное число — такое максимальное kk, что можно расставить корабли, как описано в условии.
        Пример
        Ввод
        Вывод

        7



        2

        Примечания

        Пояснение к примеру: для поля 1×71×7 ответ равен 2. Расставить один корабль размера 1×21×2 и два корабля размера 1×11×1 можно следующим образом:
     */

    private static boolean isValid(long k, long n) {
        if (k == 0) return true;

        //  overflow
        if (k > 2000000) return false;


        long term1 = k * (k + 1);
        if (term1 / k != (k + 1)) {
            // overflow
            return false;
        }

        long numerator = term1 * (k + 5);
        if (numerator / term1 != (k + 5)) {
            // overflow
            return false;
        }

        long value = numerator / 6 - 1;

        return value <= n;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            long n = Long.parseLong(reader.readLine());

            if (n == 0) {
                writer.write("0");
                writer.newLine();
                return;
            }

            long left = 0;
            long right = 2000000; // Upper bound since (2M)^3 ≈ 8e18 > 6*(10^18)
            long answer = 0;

            while (left <= right) {
                long mid = left + (right - left) / 2;

                // Check if mid satisfies the condition: mid(mid+1)(mid+5)/6 - 1 <= n
                if (isValid(mid, n)) {
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            writer.write(String.valueOf(answer));
            writer.newLine();
        }
    }
}
