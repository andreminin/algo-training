package training.yandex.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Solution_36 {
     /*
       Вам нужно распилить деревянный брус на несколько кусков в заданных местах. Распилочная компания берет K K рублей
        за распил одного бруска длиной K K метров на две части.

        Понятно, что различные способы распила приводят к различной суммарной стоимости заказа. Например, рассмотрим
         брус длиной 10 метров, который нужно распилить на расстоянии 2, 4 и 7 м, считая от одного конца. Это можно сделать
          несколькими способами. Можно распилить сначала на отметке 2 м, потом 4 и, наконец, 7 м. Это приведет к стоимости
           10 + 8 + 6 = 24 10+8+6=24, потому что сначала длина бруса, который пилили, была 10 м, затем она стала 8 м, и,
           наконец, 6 м. А можно распилить иначе: сначала на отметке 4 м, затем 2, затем 7м. Это приведет к стоимости 10+4+6=20, что лучше.

        Определите минимальную стоимость распила бруса на заданные части.
        Формат ввода

        Первая строка входных данных содержит целое число L L ( 2 ≤ L ≤ 1 0 6 2≤L≤106) — длину бруса и целое число N N
        ( 1 ≤ N ≤ 100 1≤N≤100) — количество распилов. Во второй строке записано N N целых чисел С i Сi​ ( 0 < C i <
        L 0<Ci​<L) в строго возрастающем порядке — места, в которых нужно сделать распилы.
        Формат вывода

        Выведите одно натуральное число — минимальную стоимость распила.
        Ограничения

        Ограничение времени
            1 с
        Ограничение памяти
            64 МБ

        Примеры
        Пример 1
        Ввод

        10 3

        2 4 7

        Вывод

        20

        Пример 2
        Ввод

        100 3

        15 50 75

        Вывод

        200
      */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:
        int n = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(n));
        */

        String[] input = reader.readLine().split(" ");
        int length = Integer.parseInt(input[0]);
        int count = Integer.parseInt(input[1]);

        int[] cuts = new int[count];
        input = reader.readLine().split(" ");
        for (int i = 0; i < count; i++) {
            cuts[i] = Integer.parseInt(input[i]);
        }

        int[] points = new int[count + 2];
        points[0] = 0;
        points[count + 1] = length;
        System.arraycopy(cuts, 0, points, 1, count);

        int m = points.length;
        int[][] dp = new int[m][m];

        for (int len = 2; len < m; len++) {
            for (int i = 0; i + len < m; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + points[j] - points[i]);
                }
            }
        }

        writer.write(String.valueOf(dp[0][m - 1]));

        reader.close();
        writer.close();
    }
}
