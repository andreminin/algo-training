package training.yandex.contest2025.part78;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolutionF {
    /*
        По железной дороге движутся nn поездов. Каждый поезд представляет собой отрезок. Поезд с номером ii в начальный момент времени занимает отрезок [ai;bi][ai​;bi​]. Поезда не стоят на месте — ii-й поезд движется с постоянной скоростью vivi​. Железная дорога двунаправлена, то есть поезда могут двигаться как в положительном направлении оси, так и в отрицательном. Отрезки, представляющие поезда, в любой момент времени могут пересекаться, вкладываться друг в друга и совпадать.

        В точке xx находится железнодорожный переезд, к которому в моменты titi​ подъезжают машины. Для каждой машины требуется вычислить минимальный момент времени, в который она сможет пересечь железнодорожный переезд.

        Машина может пересечь переезд, если он не занят поездом. Переезд считается занятым, если отрезок, представляющий собой некоторый поезд, содержит в себе точку xx. Причем если поезд подъезжает к перекрестку одновременно с машиной, то переезд считается занятым. Машины пересекают переезд мгновенно.
        Формат ввода

        В первой вводится три целых числа — nn, mm и xx ( 1≤n,m≤105,∣x∣≤1091≤n,m≤105,∣x∣≤109) количество поездов, движущихся по железной дороге, количество машин, подъезжающих к железнодорожному переезду и точка, в которой находится переезд.

        В следующих nn строках содержатся по три целых числа ai,bi,viai​,bi​,vi​ ( ∣ai∣≤109∣ai​∣≤109, ∣bi∣≤109∣bi​∣≤109, 1≤vi≤1091≤vi​≤109, ai≠biai​=bi​) — отрезок, задающий поезд и его скорость движения. Если ai<biai​<bi​, то поезд движется в положительном направлении оси, если ai>biai​>bi​ — в отрицательном.

        В следующей строке находятся mm неотрицательных целых чисел tjtj​ ( 0≤tj≤1090≤tj​≤109) — моменты времени, в которые к переезду подъедут машины.
        Формат вывода

        В mm строках выходного файла выведите mm вещественных чисел bjbj​  — минимальный момент времени, в который jj-я машина сможет пересечь железнодорожный переезд. Ответ будет считаться правильным, если относительная или абсолютная погрешность каждого bjbj​ не превосходит 10−610−6.
        Пример 1
        Ввод
        Вывод

        3 2 0
        -4 -1 1
        13 6 3
        -7 -6 1
        1 5

     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] firstLine = reader.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);
            int x = Integer.parseInt(firstLine[2]);
            double delta = 1e-12;

            List<double[]> blockingIntervals = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] trainData = reader.readLine().split(" ");
                int a = Integer.parseInt(trainData[0]);
                int b = Integer.parseInt(trainData[1]);
                int v = Integer.parseInt(trainData[2]);

                double start, end;

                if (a < b) {
                    // right direction
                    start = (x - b) * 1.0 / v;
                    end = (x - a) * 1.0 / v;
                } else {
                    // left direction
                    start = (a - x) * 1.0 / v;
                    end = (b - x) * 1.0 / v;
                }

                if (start > end) {
                    double temp = start;
                    start = end;
                    end = temp;
                }

                if (end >= 0) {
                    blockingIntervals.add(new double[]{Math.max(0, start), end});
                }
            }

            if (blockingIntervals.isEmpty()) {
                String[] carTimes = reader.readLine().split(" ");
                for (String timeStr : carTimes) {
                    int t = Integer.parseInt(timeStr);
                    writer.write(String.format("%.9f%n", (double) t));
                }
                return;
            }

            blockingIntervals.sort(Comparator.comparingDouble(i -> i[0]));

            List<double[]> merged = new ArrayList<>();
            double[] current = blockingIntervals.get(0);

            for (int i = 1; i < blockingIntervals.size(); i++) {
                double[] next = blockingIntervals.get(i);
                if (next[0] <= current[1] + delta) {
                    current[1] = Math.max(current[1], next[1]);
                } else {
                    merged.add(current);
                    current = next;
                }
            }
            merged.add(current);

            // Precompute prefix max & end times
            double[] maxEnd = new double[merged.size()];
            maxEnd[0] = merged.get(0)[1];
            for (int i = 1; i < merged.size(); i++) {
                maxEnd[i] = Math.max(maxEnd[i-1], merged.get(i)[1]);
            }

            String[] carTimes = reader.readLine().split(" ");

            for (String timeStr : carTimes) {
                int t_j = Integer.parseInt(timeStr);
                double answer = t_j;

                int left = 0, right = merged.size() - 1;
                int firstAfter = merged.size();

                // binary search
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (merged.get(mid)[0] > t_j + 1e-12) {
                        firstAfter = mid;
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

                if (firstAfter > 0) {
                    int lastBefore = firstAfter - 1;
                    double[] interval = merged.get(lastBefore);
                    if (t_j <= interval[1] + 1e-12) {
                        answer = Math.max(answer, interval[1]);
                    }
                }

                writer.write(String.format("%.9f%n", answer));
            }
        }
    }
}
