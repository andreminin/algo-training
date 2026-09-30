package training.yandex.contest2025.part78;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionE {

    /*
        Прямая трасса состоит из nn участков, по ней проложено mm автобусных маршрутов. Участки на трассе пронумерованы числами от 11 до nn слева направо, маршруты пронумерованы от 11 до mm. Маршрут ii проходит по участкам с lili​ по riri​ включительно. На jj-м участке изначально расположено ajaj​ выбоин. Дискомфорт от поездки по маршруту равен суммарному количеству выбоин на участках, по которым проходит маршрут.

        В бюджете автодорожного предприятия есть средства на ремонт не более чем kk выбоин (естественно, количество выбоин на участке не может стать отрицательным). Определите, какой минимальной суммы дискомфорта от поездок по всем маршрутам можно добиться.
        Формат ввода

        В первой строке вводятся три числа nn, mm и kk — количество участков трассы, маршрутов и количество выбоин, которые можно починить ( 1≤n≤1051≤n≤105, 1≤m≤1061≤m≤106, 0≤k≤10120≤k≤1012).

        Во второй строке вводится nn целых чисел aiai​ — количество выбоин на участках трассы ( 0≤ai≤1070≤ai​≤107).

        В следующих mm строках вводится по два числа lili​ и riri​, задающие начальный и конечный участок каждого из маршрутов ( 1≤li≤ri⩽n1≤li​≤ri​⩽n).
        Формат вывода

        Выведите минимальный суммарный дискомфорт на маршрутах после ремонта выбоин в пределах бюджета.
        Пример 1
        Ввод
        Вывод

        4 2 2
        1 2 3 4
        1 4
        3 4



        13

        Пример 2
        Ввод
        Вывод

        4 2 5
        1 2 0 0
        1 4
        3 4



        0

     */

    static class Pair {
        int w;
        long a;

        Pair(int w, long a) {
            this.w = w;
            this.a = a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        long k = Long.parseLong(parts[2]);

        long[] a = new long[n + 1];
        parts = reader.readLine().split(" ");

        for (int i = 1; i <= n; i++) {
            a[i] = Long.parseLong(parts[i - 1]);
        }

        int[] diff = new int[n + 2];
        for (int i = 0; i < m; i++) {
            parts = reader.readLine().split(" ");
            int l = Integer.parseInt(parts[0]);
            int r = Integer.parseInt(parts[1]);
            diff[l]++;

            if (r + 1 <= n) {
                diff[r + 1]--;
            }
        }

        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = w[i - 1] + diff[i];
        }

        long sumAW = 0;
        long sumA = 0;
        for (int i = 1; i <= n; i++) {
            sumAW += a[i] * w[i];
            sumA += a[i];
        }

        if (k >= sumA) {
            writer.write("0");
            writer.flush();
            return;
        }

        List<Pair> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(new Pair(w[i], a[i]));
        }

        list.sort((p1, p2) -> Integer.compare(p2.w, p1.w));

        long totalCount = 0;
        long totalGain = 0;

        for (Pair p : list) {
            if (totalCount + p.a <= k) {
                totalCount += p.a;
                totalGain += p.a * p.w;
            } else {
                long rem = k - totalCount;
                totalGain += rem * p.w;
                break;
            }
        }

        writer.write(String.valueOf(sumAW - totalGain));
        writer.flush();
    }
}
