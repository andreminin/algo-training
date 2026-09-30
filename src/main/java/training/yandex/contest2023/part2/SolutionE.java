package training.yandex.contest2023.part2;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SolutionE {

    /*
     Строка называется палиндромом, если она читается одинаково как слева направо, так и справа налево. Например, строки abba, ata являются палиндромами.

    Вам дана строка. Ее подстрокой называется некоторая непустая последовательность подряд идущих символов. Напишите программу, которая определит, сколько подстрок данной строки является палиндромами.
    Формат ввода

    Вводится одна строка, состоящая из прописных латинских букв. Длина строки не превышает 100000 символов.
    Формат вывода

    Выведите одно число — количество подстрок данной строки, которые являются палиндромами
    Пример 1
    Ввод
    Вывод

    aaa



    6

    Пример 2
    Ввод
    Вывод

    aba



    4

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
        if (l < 0 || r > pref.length || l >= r) {
            return 0;
        }
        return (pref[r] - (pref[l] * pow[r - l]) % MOD + MOD) % MOD;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();
            if (s.isEmpty()) {
                writer.write("0");
                return;
            }

            byte[] data = s.getBytes(StandardCharsets.US_ASCII);
            int n = data.length;

            long[] pref = prefixMul257(data);
            long[] pow = powMul257(n);

            byte[] revData = new byte[n];
            for (int i = 0; i < n; i++) {
                revData[i] = data[n - 1 - i];
            }
            long[] prefRev = prefixMul257(revData);

            long count = 0;

            // Odd length
            for (int i = 0; i < n; i++) {
                int low = 0;
                int high = Math.min(i, n - 1 - i);
                int maxRadius = 0;

                while (low <= high) {
                    int mid = (low + high) / 2;
                    long directHash = subHashMul257(pref, pow, i - mid, i + mid + 1);
                    long reverseHash = subHashMul257(prefRev, pow, n - 1 - (i + mid), n - 1 - (i - mid) + 1);

                    if (directHash == reverseHash) {
                        maxRadius = mid;
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                count += (maxRadius + 1);
            }

            // Even length
            for (int i = 0; i < n - 1; i++) {
                int low = 1;
                int high = Math.min(i + 1, n - 1 - i);
                int maxRadius = 0;

                while (low <= high) {
                    int mid = (low + high) / 2;
                    long directHash = subHashMul257(pref, pow, i - mid + 1, i + mid + 1);
                    long reverseHash = subHashMul257(prefRev, pow, n - 1 - (i + mid), n - 1 - (i - mid + 1) + 1);

                    if (directHash == reverseHash) {
                        maxRadius = mid;
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                count += maxRadius;
            }

            writer.write(String.valueOf(count));
        }
    }


}
