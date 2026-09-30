package training.yandex.contest2023.part2;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SolutionA {
    /*
        Дана строка SS, состоящая из строчных латинских букв.

        Определите, совпадают ли строки одинаковой длины LL, начинающиеся с позиций AA и BB.
        Формат ввода

        В первой строке записана SS ( 1≤∣S∣≤2⋅1051≤∣S∣≤2⋅105), состоящая из строчных латинских букв.

        Во второй строке записано число QQ ( 1≤Q≤2⋅1051≤Q≤2⋅105) — количество запросов.

        В следющих QQ строках записаны запросы: целые числа LL, AA и BB ( 1≤L≤∣S∣1≤L≤∣S∣, 0≤A,B≤(∣S∣−L)0≤A,B≤(∣S∣−L)) — длина подстрок и позиции, с которых они начинаются.
        Формат вывода

        Если строки совпадают — выведите "yes", иначе — "no".
        Пример 1
        Ввод
        Вывод

        acabaca
        3
        4 3 2
        3 4 0
        2 0 1



        no
        yes
        no

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
            long[] pref64 = prefixMul257(bytes);
            long[] pow64 = powMul257(bytes.length);

            int m = Integer.parseInt(reader.readLine());
            boolean[] results = new boolean[m];

            for (int i = 0; i < m; i++) {
                String[] parts = reader.readLine().split(" ");
                int length = Integer.parseInt(parts[0]);
                int l = Integer.parseInt(parts[1]);
                int r = Integer.parseInt(parts[2]);
                long sub1 = subHashMul257(pref64, pow64, l, l + length);
                long sub2 = subHashMul257(pref64, pow64, r, r + length);
                results[i] = sub1 == sub2;
            }

            for (boolean result : results) {
                writer.write(result ? "yes" : "no");
                writer.newLine();
            }
        }
    }
}
