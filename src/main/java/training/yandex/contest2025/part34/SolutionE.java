package training.yandex.contest2025.part34;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionE {
    /*
       Петя в очередной раз купил себе набор из кубиков. На этот раз он выстроил из них настоящую крепость — последовательность
        из NN столбиков, высота каждого столбика составляет AiAi​ кубиков.

        Вскоре ему стало интересно, насколько его крепость защищена от врагов. Для этого он ввел понятие башни. Башней
        называется любая последовательность из KK столбиков подряд (где KK — любимое число Пети). Защищенность башни
        определяется как суммарная высота всех столбиков этой башни (чем она больше, тем громаднее и ужаснее она кажется),
         умноженная на минимум высоты столбиков башни (т.к. враги, очевидно, будут пытаться проникнуть через самое слабое
         место башни). Неприступность крепости определяется как сумма защищенностей каждой из башен.

        Петя решил как можно скорее посчитать, какова же неприступность его крепости. Однако вскоре он понял, что
        недостаточно знать высоту каждого из столбиков. В зависимости от того, как сгруппировать столбики в башни, получится
         разный результат. Разумеется, Петя выберет то разбиение на непересекающиеся башни, при котором неприступность будет максимальна.

        Петя успешно справился со своей задачей, но теперь Правительство Флатландии решило защитить свой горный курорт.
         Правительство уже построило крепость из кубиков (просто кубики были побольше). Теперь вы должны помочь Правительству
         посчитать неприступность этой крепости. Единственная трудность состоит в том, что у Правительства было очень много денег, и поэтому крепость была построена очень длинной.
        Формат ввода

        В первой строке содержатся число NN — количество столбиков в крепости и число KK‘— любимое число Пети ( 1≤K≤N≤10001≤K≤N≤1000).
         Далее на следующей строке содержатся NN целых чисел, обозначающих AiAi​ ( 1≤Ai≤10001≤Ai​≤1000).
        Формат вывода

        В первой строке выведите число QQ — количество башен в оптимальном разбиении. Далее выведите QQ чисел — номера первых столбиков каждой башни.

        Гарантируется, что в оптимальном разбиении неприступность крепости не превосходит 2×1092×109.
        Пример 1
        Ввод
        Вывод

        1 1
        1

        1
        1

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] params = reader.readLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int k = Integer.parseInt(params[1]);

            int[] alts = new int[n];
            params = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                alts[i] = Integer.parseInt(params[i]);
            }

            long[] dp = new long[n + 1];
            dp[0] = 0;
            boolean[] accepted = new boolean[n + 1];

            long[] prefixSum = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = prefixSum[i - 1] + alts[i - 1];
            }

            for (int i = 1; i <= n; i++) {
                dp[i] = dp[i - 1];
                accepted[i] = false;

                if (i >= k) {
                    int start = i - k;
                    int end = i - 1;
                    long sum = prefixSum[i] - prefixSum[start];
                    int minVal = alts[start];

                    for (int j = start + 1; j <= end; j++) {
                        if (alts[j] < minVal) {
                            minVal = alts[j];
                        }
                    }
                    long value = sum * minVal;

                    if (dp[i] < dp[i - k] + value) {
                        dp[i] = dp[i - k] + value;
                        accepted[i] = true;
                    }
                }
            }

            List<Integer> towers = new ArrayList<>();
            int i = n;
            while (i > 0) {
                if (accepted[i]) {
                    towers.add(i - k + 1);
                    i -= k;
                } else {
                    i--;
                }
            }

            Collections.reverse(towers);

            writer.write(String.valueOf(towers.size()));
            writer.newLine();

            writer.write(towers.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            writer.newLine();
        }
    }
}
