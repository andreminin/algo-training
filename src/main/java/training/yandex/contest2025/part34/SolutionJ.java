package training.yandex.contest2025.part34;

import java.io.*;

public class SolutionJ {

    /*
      По случаю введения больших новогодних каникул устраивается великий праздничный бал-маскарад. До праздника остались
      считанные дни, поэтому срочно нужны костюмы для участников. Для пошивки костюмов требуется LL метров ткани. Ткань
      продается в NN магазинах, в которых предоставляются скидки оптовым покупателям. В магазинах можно купить только целое
      число метров ткани. Реклама магазина номер ii гласит «Мы с радостью продадим Вам метр ткани за PiPi​ бурлей, однако
      если Вы купите не менее RiRi​ метров, то получите прекрасную скидку — каждый купленный метр обойдется Вам всего в QiQi​ бурлей».
      Чтобы воплотить в жизнь лозунг «экономика страны должна быть экономной», правительство решило потратить на закупку ткани для
       костюмов минимальное количество бурлей из государственной казны. При этом ткани можно купить больше, чем нужно, если
       так окажется дешевле. Ответственный за покупку ткани позвонил в каждый магазин и узнал, что:

         реклама каждого магазина содержит правдивую информацию о ценах и скидках;

             магазин номер ii готов продать ему не более FiFi​ метров ткани.

        Ответственный за покупку очень устал от проделанной работы и поэтому поставленную перед ним задачу «закупить ткань за
        минимальные деньги» переложил на своих помощников. Напишите программу, которая определит, сколько ткани нужно купить
         в каждом из магазинов так, чтобы суммарные затраты были минимальны.
        Формат ввода

        В первой строке входного файла записаны два целых числа NN и LL ( 1≤N≤1001≤N≤100, 0≤L≤1000≤L≤100).

        В каждой из последующих NN строк находится описание магазина номер ii — 4 целых числа PiPi​, RiRi​, QiQi​, FiFi​ ( 1≤Qi≤Pi≤10001≤Qi​≤Pi​≤1000, 1≤Ri≤1001≤Ri​≤100, 0≤Fi≤1000≤Fi​≤100).
        Формат вывода

        Первая строка выходного файла должна содержать единственное число — минимальное необходимое количество бурлей.

        Во второй строке выведите NN чисел, разделенных пробелами, где ii-ое число определяет количество метров ткани, которое
         нужно купить в ii-ом магазине. Если в ii-ом магазине ткань покупаться не будет, то на ii-ом месте должно стоять число 00.
          Если вариантов покупки несколько, выведите любой из них.

        Если ткани в магазинах недостаточно для пошивки костюмов, выходной файл должен содержать единственное число −1−1.
        Пример 1
        Ввод
        Вывод

        2 14
        7 9 6 10
        7 8 6 10



        88
        10 4

        Пример 2
        Ввод
        Вывод

        1 20
        1 1 1 1
        -1


    Overflow Protection:

        Added && newCost >= 0 check to detect potential overflow

    Edge Cases Handled:

        L = 0: Buy 0 fabric from all shops with cost 0

        Insufficient total fabric: Output -1 immediately

        Single shop with enough fabric

        Multiple shops with various constraints

Why Long is Necessary:

    Maximum possible cost: 100 shops × 100 meters/shop × 1000 price/meter = 10,000,000

    While this fits in int (max ~2.1 billion), using long prevents any potential overflow during intermediate calculations

    The multiplication k * p[i-1] where k ≤ 100 and p[i-1] ≤ 1000 gives max 100,000, but accumulation across multiple shops could approach limits

Performance:

    Time Complexity: O(N × totalMax × max(F_i)) = O(100 × 10,000 × 100) = O(100,000,000) operations

    Space Complexity: O(N × totalMax) = O(100 × 10,000) = O(1,000,000) elements
     */

    static final long MAX = Long.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            int l = Integer.parseInt(parts[1]);

            // Edge case
            if (l == 0) {
                writer.write("0");
                writer.newLine();
                for (int i = 0; i < n; i++) {
                    writer.write("0");
                    if (i < n - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
                return;
            }

            int[] p = new int[n];
            int[] r = new int[n];
            int[] q = new int[n];
            int[] f = new int[n];

            int totalMax = 0;
            for (int i = 0; i < n; i++) {
                parts = reader.readLine().split(" ");
                p[i] = Integer.parseInt(parts[0]);
                r[i] = Integer.parseInt(parts[1]);
                q[i] = Integer.parseInt(parts[2]);
                f[i] = Integer.parseInt(parts[3]);
                totalMax += f[i];
            }

            // Edge case
            if (totalMax < l) {
                writer.write("-1");
                writer.newLine();
                return;
            }

            long[][] dp = new long[n + 1][totalMax + 1];
            int[][] prev = new int[n + 1][totalMax + 1];

            for (int j = 0; j <= totalMax; j++) {
                dp[0][j] = MAX;
            }
            dp[0][0] = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= totalMax; j++) {
                    dp[i][j] = MAX;
                }

                for (int j = 0; j <= totalMax; j++) {
                    if (dp[i - 1][j] == MAX) {
                        continue;
                    }

                    for (int k = 0; k <= f[i - 1]; k++) {
                        int newJ = j + k;
                        if (newJ > totalMax) continue;

                        long cost;
                        if (k < r[i - 1]) {
                            cost = (long) k * p[i - 1];
                        } else {
                            cost = (long) k * q[i - 1];
                        }

                        long newCost = dp[i - 1][j] + cost;
                        if (newCost < dp[i][newJ] && newCost >= 0) {
                            dp[i][newJ] = newCost;
                            prev[i][newJ] = k;
                        }
                    }
                }
            }

            long minCost = MAX;
            int bestJ = -1;
            for (int j = l; j <= totalMax; j++) {
                if (dp[n][j] < minCost) {
                    minCost = dp[n][j];
                    bestJ = j;
                }
            }

            if (minCost == MAX) {
                writer.write("-1");
                writer.newLine();
            } else {
                writer.write(String.valueOf(minCost));
                writer.newLine();

                int[] res = new int[n];
                int currentJ = bestJ;

                for (int i = n; i >= 1; i--) {
                    res[i - 1] = prev[i][currentJ];
                    currentJ -= prev[i][currentJ];
                }

                for (int i = 0; i < n; i++) {
                    writer.write(String.valueOf(res[i]));
                    if (i < n - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
        }
    }
}
