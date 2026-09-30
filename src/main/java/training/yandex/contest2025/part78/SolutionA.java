package training.yandex.contest2025.part78;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class SolutionA {
   /*
У компании открылся второй офис и для перемещения сотрудников между офисами было решено организовать автобусные рейсы, которые будут ездить по расписанию из первого офиса во второй и обратно. Расписание уже готово, но непонятно, каким минимальным количеством автобусов удастся обслужить все рейсы.

Расписание состоит из NN рейсов из первого офиса во второй и MM обратных рейсов. Для каждого рейса известно время отправления и время прибытия с точностью до минуты. Любой рейс полностью выполняется в течение одних календарных суток (с 00:00 до 23:59 включительно), никакой рейс не прибывает на следующий день после отправления. Каждый рейс занимает как минимум одну минуту, то есть ни один рейс не прибывает к офису назначения в ту же минуту, в которую он отправился. После прибытия автобус готов в ту же минуту отправиться в обратный рейс. Одновременно возле офиса может находиться, отправляться или прибывать любое количество автобусов. Автобусы не могут перемещаться вне расписания в течение дня. Вам не нужно учитывать подготовку к выполнению расписания следующего дня — необходимо только определить минимальное количество автобусов для обслуживания рейсов в течение одного дня.
Формат ввода

В первой строке находится число NN ( 1≤N≤1000001 ≤N≤ 100000) — количество рейсов из первого офиса во второй. В следующих NN строках находятся описания рейсов, по одному на строке: время отправления и время прибытия, разделенные дефисом («-»). И время прибытия, и время отправления записаны в формате HH:MM, где HH — час (число от 0 до 23, при необходимости, дополненное ведущим нулем до двух цифр), MM — минута (число от 0 до 59, при необходимости, дополненное ведущим нулем до двух цифр).

В N+2N+ 2-й строке находится число MM ( 1≤M≤1000001 ≤M≤ 100000) — количество рейсов из второго офиса в первый. В следующих MM строках находятся описания этих рейсов в том же формате.
Формат вывода

Выведите единственное число — минимально возможное количество автобусов, достаточное для обслуживания всех рейсов.
Пример 1
Ввод
Вывод

4
06:45-10:20
07:36-11:26
19:00-22:35
20:08-23:58
7
06:35-10:10
07:15-11:10
11:00-14:48
14:00-17:48
15:40-19:28
18:35-22:23
20:20-23:55



7

Пример 2
Ввод
Вывод

2
10:00-12:00
15:00-17:00
2
12:30-14:30
17:30-19:30



1

Пример 3
Ввод
Вывод

2
10:10-10:11
10:10-10:11
2
10:11-10:12
10:11-10:12

    */


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            List<int[]> trips = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                String[] parts = line.split("-");

                int start = timeToMinutes(parts[0]);
                int end = timeToMinutes(parts[1]);

                trips.add(new int[]{start, end, 0});
            }

            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                String line = reader.readLine();
                String[] parts = line.split("-");
                int start = timeToMinutes(parts[0]);
                int end = timeToMinutes(parts[1]);

                trips.add(new int[]{start, end, 1});
            }

            trips.sort(Comparator.comparingInt(a -> a[0]));

            TreeMap<Integer, Integer> busesAtFirstOffice = new TreeMap<>();
            TreeMap<Integer, Integer> busesAtSecondOffice = new TreeMap<>();
            int count = 0;

            for (int[] trip : trips) {
                if (trip[2] == 0) {
                    count += handleBusTrip(trip[0], trip[1], busesAtFirstOffice, busesAtSecondOffice);
                } else {
                    count += handleBusTrip(trip[0], trip[1], busesAtSecondOffice, busesAtFirstOffice);
                }
            }

            writer.write(String.valueOf(count));
            writer.newLine();
        }
    }

    private static int handleBusTrip(int dep,
                                     int arr,
                                     TreeMap<Integer, Integer> busesAtDeparture,
                                     TreeMap<Integer, Integer> busesAtDestination)
    {
        int count = 0;

        Integer time = busesAtDeparture.floorKey(dep);
        if (time != null) {
            int busCount = busesAtDeparture.get(time);

            if (busCount > 1) {
                busesAtDeparture.put(time, busCount - 1);
            } else {
                busesAtDeparture.remove(time);
            }
        } else {
            count++;
        }

        busesAtDestination.put(arr, busesAtDestination.getOrDefault(arr, 0) + 1);

        return count;
    }

    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }
}
