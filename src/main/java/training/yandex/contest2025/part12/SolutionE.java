package training.yandex.contest2025.part12;

import java.io.*;

public class SolutionE {
    /*
      На табло было написано число nn. Каждую секунду к числу прибавляется последняя цифра этого числа. Определите, какое число будет отображаться на табло через kk секунд.
        Формат ввода

        В единственной строке записаны два числа nn и kk ( 0≤n,k≤1090≤n,k≤109) — начальное число и количество секунд соответственно.
    Формат вывода

    Выведите одно число xx, которое будет отображаться на табло через kk секунд.
    Пример 1
    Ввод
    Вывод

    1 10



    44

    Пример 2
    Ввод
    Вывод

    5 1



    10

     */

    private static long naive(long number, long cycles) {
        if(cycles != 0 && number != 0) {
            for(int i = 0, lastDigit = (int)(number % 10); i < cycles && lastDigit != 0; i++) {
                number += lastDigit;
                lastDigit = (int)(number % 10);
            }
        }
        return number;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        long number = Long.parseLong(input[0]);
        long cycles = Long.parseLong(input[1]);

        if (cycles == 0 || number == 0) {
            writer.write(String.valueOf(number));
            writer.newLine();
            reader.close();
            writer.close();
            return;
        }

        // Periodical cases
        // 0 - constant, exit
        // 1, 2 - handle as 2
        // 2, 4, 8, 6, 2 - period 4, period value 20
        // 3, 6, 2 - handle as 2
        // 4 - handle as 2
        // 5 = handle as 0
        // 6 - handle as 2
        // 7, 4 - handle as 2
        // 8 - handle as 2
        // 9, 8, 6, 2 - handle as 2

         long reference = naive(number, cycles);

        long lastDigit;
        for (int i = 0; i < cycles; i++) {
            lastDigit = number % 10;
            if (lastDigit == 0) {
                break;
            }

            if (lastDigit == 2 && i + 4 <= cycles) {
                //  2-4-8-6 cycle with period sum 20
                long remainingCycles = cycles - i;
                long fullCycles = remainingCycles / 4;
                number += fullCycles * 20;
                i += fullCycles * 4 - 1; // -1, i increments inside for
            } else {
                number += lastDigit;
            }
        }

        if(number != reference) {
            throw new RuntimeException("Expected "+reference+", result: "+number);
        }

        writer.write(String.valueOf(number));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
