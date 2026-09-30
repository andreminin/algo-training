package training.yandex.contest2025.part34;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class SolutionC {
    /*
        Дан набор временных интервалов, у каждого из которых задан вес. Множество интервалов называется совместимым,
        если никакие два интервала в нем не пересекаются. Необходимо найти совместимое подмножество интервалов с максимальным весом.

        Под временными интервалами подразумеваются интервалы, не включающие свои концы.
        Формат ввода

        В первой строке записано количество интервалов NN ( 0≤N≤1050≤N≤105). В последующих NN строках на ii-й строке записана
         информация об ii-м интервале, т.е три действительных числа: начало интервала bibi​, конец интервала eiei​ и
         его вес wiwi​. Известно, что для всех ii верно bi<eibi​<ei​ и wi>0wi​>0.
        Формат вывода

        Выведите максимальный суммарный вес подмножества. Ответ будет зачтен, если он отличается от правильного не более чем 10−410−4.
        Пример
        Ввод
        Вывод

        3
        0 1 1
        0.5 1.5 1.5
        1 2 1

     */

    private static double solve(double[][] intervals) {
        int m = intervals.length;
        if (m == 0) {
            return 0.0;
        }

        if (m == 1) {
            return intervals[0][2];
        }

        // Sort by end time
        Arrays.sort(intervals, Comparator.comparingDouble(item -> item[1]));
        double[] dp = new double[m + 1];
        dp[0] = 0;

        for (int i = 0; i < m; i++) {
            double[] current = intervals[i];
            int left = 0;
            int right = i - 1;
            int prevIdx = -1;

            // Binary search to find the last non-overlapping interval
            while (left <= right) {
                int mid = (left + right) / 2;
                // Compare end time of previous interval with start time of current
                if (intervals[mid][1] <= current[0]) {
                    prevIdx = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            double weight = current[2];
            if (prevIdx != -1) {
                weight += dp[prevIdx + 1];
            }

            double prevWeight = dp[i];
            dp[i + 1] = Math.max(weight, prevWeight);
        }

        return dp[m];
    }

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)))
        {
            String input = reader.readLine();
            int m = Integer.parseInt(input);
            double[][] intervals = new double[m][];
            if(m > 0) {
                for (int i = 0; i < m; i++) {
                    String[] parts = reader.readLine().split(" ");
                    intervals[i] = new double[3];
                    intervals[i][0] = Double.parseDouble(parts[0]);
                    intervals[i][1] = Double.parseDouble(parts[1]);
                    intervals[i][2] = Double.parseDouble(parts[2]);
                }
            }

            double result = solve(intervals);

            writer.write(String.valueOf(result));
            writer.newLine();
        }
    }

    public static void test() {
        System.out.println(solve(new double[][] {
                new double[] {0, 1, 1},
                new double[] {0.5, 1.5, 1.5},
                new double[] {1, 2, 1}
        }));

        System.out.println(solve(new double[][] {
                new double[] {0, 1.1, 1},
                new double[] {0.5, 1.5, 1.5},
                new double[] {1, 2, 1}
        }));
    }
}
