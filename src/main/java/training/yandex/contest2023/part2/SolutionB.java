package training.yandex.contest2023.part2;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SolutionB {

    /*
        Строка SS была записана много раз подряд, после чего от получившейся строки взяли префикс и дали вам. Ваша задача определить минимально возможную длину исходной строки SS.
        Формат ввода

        В первой и единственной строке входного файла записана строка, которая содержит только латинские буквы, длина строки не превышает 5000050000 символов.
        Формат вывода

        Выведите ответ на задачу.
        Пример 1
        Ввод
        Вывод
        zzz

        1

        Пример 2
        Ввод
        Вывод
        bcabcab

        3

     */
    private static final long MOD = 1_000_000_007L;
    private static final int BASE = 257;

    public static long[] prefixMul257(byte[] data) {
        int n = data.length;
        long[] pref = new long[n + 1];
        pref[0] = 0L;

        for (int i = 0; i < n; ++i) {
            pref[i + 1] = (pref[i] * BASE + (data[i] & 0xFF)) % MOD;
        }

        return pref;
    }

    public static long[] powMul257(int n) {
        long[] pow = new long[n + 1];
        pow[0] = 1L;

        for (int i = 1; i <= n; ++i) {
            pow[i] = (pow[i - 1] * BASE) % MOD;
        }

        return pow;
    }

    public static long subHashMul257(long[] pref, long[] pow, int l, int r) {
        return (pref[r] - (pref[l] * pow[r - l]) % MOD + MOD) % MOD;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String text = reader.readLine();
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            int n = bytes.length;

            long[] pref = prefixMul257(bytes);
            long[] pow = powMul257(n);

            int answer = n;

            for (int k = 1; k < n; ++k) {
                int len = n - k;

                // compare hash of prefix [0, n-k) with suffix [k, n)
                if (subHashMul257(pref, pow, 0, len) == subHashMul257(pref, pow, k, n)) {
                    answer = k;
                    break;
                }
            }

            writer.write(String.valueOf(answer));
        }
    }
}
