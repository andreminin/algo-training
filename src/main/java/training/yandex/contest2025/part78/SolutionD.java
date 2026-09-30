package training.yandex.contest2025.part78;

import java.io.*;

public class SolutionD {
   /*
    Вася и Маша участвуют в соревновании по поеданию сырков «Дружба». На nn столах, расставленных в ряд и занумерованных с 11 по nn, лежат сырки, на ii столе расположено aiai​ сырков. По правилам соревнования, если участник подходит к столу, то он должен съесть все сырки с него. Вася начинает слева и будет есть сырки на столах с 11 по ll включительно, а Маша начинает справа и ест сырки на столах с nn по rr включительно ( l<rl<r).

    Победить на соревновании должна дружба — количество съеденных сырков у Васи ( SDVSDV​) и Маши ( SDMSDM​) должно отличаться как можно меньше. Найдите такие l≥1l≥1 и r≤nr≤n, при которых достигается минимум ∣SDV−SDM∣∣SDV​−SDM​∣.
    Формат ввода

    В первой строке вводится число nn — количество столов с сырками ( 2≤n≤1062≤n≤106).

    Во второй строке вводятся nn целых чисел aiai​ — количество сырков на ii-м столе ( 1≤ai≤1091≤ai​≤109).
    Формат вывода

    Выведите три целых числа — минимальное значение ∣SDV−SDM∣∣SDV​−SDM​∣, и значения ll и rr, при которых это значение достигается. Если различных подходящих пар ll и rr несколько, выведите любую из них.
    Пример 1
    Ввод
    Вывод

    5
    5 1 1 1 1



    1 1 2

    Пример 2
    Ввод
    Вывод

    4
    1 2 3 4



    1 2 4

    Примечания

    В первом тесте оптимальным выбором является l=1l=1 и r=2r=2, тогда SDV=5SDV​=5, SDM=4SDM​=4, а ∣SDV−SDM∣=1∣SDV​−SDM​∣=1.

    Во втором тесте оптимальным выбором является l=2l=2 и r=4r=4, тогда SDV=3SDV​=3, SDM=4SDM​=4, а ∣SDV−SDM∣=1∣SDV​−SDM​∣=1.
    */

    public static int search(long[] values, int l, int r, long target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (values[mid] == target) {
                return mid;
            } else if (values[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -l - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        long[] a = new long[n];
        String[] chunks = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(chunks[i]);
        }

        long[] prefix_sum = new long[n + 1];
        prefix_sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + a[i - 1];
        }

        long total = prefix_sum[n];
        long minDiff = Long.MAX_VALUE;
        int bestL = 1;
        int bestR = n;

        for (int l = 1; l < n; l++) {
            long target = total - prefix_sum[l];

            int idx = search(prefix_sum, l, n - 1, target);

            if (idx >= 0) {
                minDiff = 0;
                bestL = l;
                bestR = idx + 1;

                break;
            } else {
                int insertionPoint = -idx - 1;

                if (insertionPoint - 1 >= l && insertionPoint - 1 <= n - 1) {
                    long diff1 = Math.abs(prefix_sum[l] + prefix_sum[insertionPoint - 1] - total);
                    if (diff1 < minDiff) {
                        minDiff = diff1;
                        bestL = l;
                        bestR = insertionPoint;
                    }
                }

                if (insertionPoint >= l && insertionPoint <= n - 1) {
                    long diff2 = Math.abs(prefix_sum[l] + prefix_sum[insertionPoint] - total);
                    if (diff2 < minDiff) {
                        minDiff = diff2;
                        bestL = l;
                        bestR = insertionPoint + 1;
                    }
                }
            }
        }

        writer.write(minDiff + " " + bestL + " " + bestR);
        writer.newLine();
        writer.flush();
    }
}
