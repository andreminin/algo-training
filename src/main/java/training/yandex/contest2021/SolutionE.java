package training.yandex.contest2021;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SolutionE {

    /*
        Бригада скорой помощи выехала по вызову в один из отделенных районов. К сожалению, когда диспетчер получил вызов,
         он успел записать только адрес дома и номер квартиры K1K1​, а затем связь прервалась. Однако он вспомнил, что
         по этому же адресу дома некоторое время назад скорая помощь выезжала в квартиру K2K2​, которая расположена в подъезда P2P2​ на этаже N2N2​. Известно, что в доме MM этажей и количество квартир на каждой лестничной площадке одинаково. Напишите программу, которая вычисляет номер подъезда P1P1​ и номер этажа N1N1​ квартиры K1K1​.
        Формат ввода

        Во входном файле записаны пять положительных целых чисел K1K1​, MM, K2K2​, P2P2​, N2N2​. Все числа не превосходят 106106.
        Формат вывода

        Выведите два числа P1P1​ и N1N1​. Если входные данные не позволяют однозначно определить P1P1​ или N1N1​, вместо соответствующего числа напечатайте 00. Если входные данные противоречивы, напечатайте два числа –1–1 (минус один).

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int k1 = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            int k2 = Integer.parseInt(parts[2]);
            int p2 = Integer.parseInt(parts[3]);
            int n2 = Integer.parseInt(parts[4]);

            if (n2 > m) {
                writer.write("-1 -1");
                writer.newLine();
                return;
            }

            long absFloor = (long) (p2 - 1) * m + (n2 - 1);

            // первый этаж
            if (absFloor == 0) {
                if (k1 < k2) {
                    writer.write("1 1");
                    writer.newLine();
                } else {
                    Set<Integer> pSet = new HashSet<>();
                    Set<Integer> nSet = new HashSet<>();

                    for (int x = k2; x <= k1; x++) {
                        long xM = (long) x * m;
                        int p1 = (int) ((k1 - 1) / xM) + 1;
                        int n1 = (((k1 - 1) / x) % m) + 1;
                        pSet.add(p1);
                        nSet.add(n1);
                    }

                    pSet.add(1);
                    nSet.add(1);

                    int resP = (pSet.size() == 1) ? pSet.iterator().next() : 0;
                    int resN = (nSet.size() == 1) ? nSet.iterator().next() : 0;

                    writer.write(resP + " " + resN);
                    writer.newLine();
                }
            } else {
                long denominator = absFloor + 1;
                int low;

                if (k2 % denominator == 0) {
                    low = (int) (k2 / denominator);
                } else {
                    low = (int) (k2 / denominator) + 1;
                }

                int high = (int) ((k2 - 1) / absFloor);

                if (low > high) {
                    writer.write("-1 -1");
                    writer.newLine();
                } else {
                    Set<Integer> setP = new HashSet<>();
                    Set<Integer> setN = new HashSet<>();

                    for (int x = low; x <= high; x++) {
                        long xM = (long) x * m;
                        int p1 = (int) ((k1 - 1) / xM) + 1;
                        int n1 = (((k1 - 1) / x) % m) + 1;
                        setP.add(p1);
                        setN.add(n1);
                    }

                    int resP = (setP.size() == 1) ? setP.iterator().next() : 0;
                    int resN = (setN.size() == 1) ? setN.iterator().next() : 0;

                    writer.write(resP + " " + resN);
                    writer.newLine();
                }
            }
        }
    }
}
