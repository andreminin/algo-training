package training.yandex.contest2025.part12;

import java.io.*;
import java.util.Arrays;

public class SolutionA {
    /*
        Вася и Маша ходили в лес и собрали nn грибов, для каждого гриба известен его вес aiai​. Они выложили их в
        один ряд и решили делить следующим образом: первый гриб берёт Вася, второй — Маша, третий — Вася, четвёртый — Маша и т.д.

        Вася очень любит грибы и не очень любит Машу. Количество радости Васи равно разности суммарного веса грибов,
        доставшихся Васе, и суммарного веса грибов, доставшихся Маше. Т.е. радость вычисляется по формуле: ∑ni=1(−1)i−1⋅ai=a1−a2+a3−…i=1∑n​(−1)i−1⋅ai​=a1​−a2​+a3​−…

        Маша отвлеклась на минутку, и за это время Вася может выбрать любые два гриба и поменять их местами (а может и не менять). Определите максимальную радость Васи, которую можно достичь не более чем одним обменом.
        Формат ввода

        В первой строке содержится одно натуральное число nn — количество грибов ( 2≤n≤1052≤n≤105).

        Во второй строке содержится nn чисел aiai​ — вес грибов ( 1≤ai≤10001≤ai​≤1000).
        Формат вывода

        Выведите максимальную радость Васи.
        Пример 1
        Ввод
        Вывод

        2
        1 2
        1

        Пример 2
        Ввод
        Вывод

        3
        2 2 2
        2

        Пример 3
        Ввод
        Вывод

        11
        4 10 7 5 4 5 3 8 3 2 5
        10

     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();
        int total = Integer.parseInt(input);

        String[] weights = reader.readLine().split(" ");

        int count1 = (total + 1) / 2;
        int count2 = total - count1;

        int[] weights1 = new int[count1];
        int sum1 = 0;
        int[] weights2 = new int[count2];
        int sum2 = 0;
        for (int i = 0, j = 0; i < total; i += 2, j++) {
            weights1[j] = Integer.parseInt(weights[i]);
            sum1 += weights1[j];
        }
        for (int i = 1, j = 0; i < total; i += 2, j++) {
            weights2[j] = Integer.parseInt(weights[i]);
            sum2 += weights2[j];
        }

        Arrays.sort(weights1);
        Arrays.sort(weights2);

        if (weights1[0] < weights2[count2 - 1]) {
            // reduce sums
            sum1 -= weights1[0];
            sum2 -= weights2[count2 - 1];
            // swap
            int min1 = weights1[0];
            weights1[0] = weights2[count2 - 1];
            weights2[count2 - 1] = min1;
            // recalculate sums
            sum1 += weights1[0];
            sum2 += weights2[count2 - 1];
        }

        writer.write(String.valueOf(sum1-sum2));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
