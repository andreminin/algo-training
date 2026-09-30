package training.yandex.contest2023.part2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionD {

    /*
            Привидение Петя любит играть со своими кубиками. Он любит выкладывать их в ряд и разглядывать свое творение.
            Недавно друзья решили подшутить над Петей и поставили в его игровой комнате зеркало. Известно, что привидения
            не отражаются в зеркале, а кубики отражаются. Теперь Петя видит перед собой N цветных кубиков, но не знает,
            какие из этих кубиков настоящие, а какие — отражение в зеркале. Выясните, сколько кубиков может быть у Пети.
             Петя видит отражение всех кубиков в зеркале и часть кубиков, которая находится перед ним. Часть кубиков
             может быть позади Пети, их он не видит.

             Формат ввода

            Первая строка входного файла содержит число N ( 1 ≤ N ≤ 1000000 ) и количество различных цветов, в
             которые могут быть раскрашены кубики — M ( 1 ≤ M ≤ 1000000 ). Следующая строка содержит N целых чисел от 1 до M — цвета кубиков.
            Формат вывода

            Выведите в выходной файл все такие K, что у Пети может быть K кубиков
            Пример
            Ввод
            Вывод

            6 2
            1 1 2 2 1 1

            3 5 6

     */

    private static final long MOD = 1_000_000_007L;
    private static final int BASE = 1_000_001;

    public static long[] prefix(int[] data) {
        int n = data.length;
        long[] pref = new long[n + 1];
        pref[0] = 0L;

        for (int i = 0; i < n; ++i) {
            pref[i + 1] = (pref[i] * BASE + data[i]) % MOD;
        }

        return pref;
    }

    public static long[] prefixReversal(int[] data) {
        int n = data.length;
        long[] pref = new long[n + 1];
        pref[n-1] = 0L;

        for (int i = 0, j = n-1; i < n; ++i, --j) {
            pref[i + 1] = (pref[i] * BASE + data[j]) % MOD;
        }

        return pref;
    }

    public static long[] pow10E6(int n) {
        long[] pow = new long[n + 1];
        pow[0] = 1L;

        for (int i = 1; i <= n; ++i) {
            pow[i] = (pow[i - 1] * BASE) % MOD;
        }

        return pow;
    }

    public static long subHash(long[] pref, long[] pow, int l, int r) {
        long result = (pref[r] - (pref[l] * pow[r - l]) % MOD + MOD) % MOD;
        return  result;
    }

    private static boolean isSimple(int num) {
        if (num < 2) {
            return false;
        }
        for (int k = 2; k <= Math.sqrt(num); k++) {
            if (num % k == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            parts = reader.readLine().split(" ");
            int[] numbers = new int[n];
            for(int i = 0; i<n; i++ ) {
                numbers[i] = Integer.parseInt(parts[i]);
            }

            if(n == 1) {
                writer.write("1");
                writer.newLine();
                return;
            }

            long[] pref = prefix(numbers);
            long[] reversalPrev = prefixReversal(numbers);
            long[] pow = pow10E6(n);
            List<Integer> answers = new ArrayList<>();

            for(int k = n/2; k >=1; k--) {
                if (subHash(pref, pow, k, k+k) == subHash(reversalPrev, pow, n-k, n)) {
                    answers.add(n-k);
                }
            }
            //0 offset
            answers.add(n);

            String answer = answers.stream().map(String::valueOf).collect(Collectors.joining(" "));
            writer.write(answer);
            writer.newLine();
        }
    }
}
