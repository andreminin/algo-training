package training.yandex.contest2023.part1;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SolutionC {
    /*
       Базовый алгоритм для сортировки слиянием — алгоритм слияния двух упорядоченных массивов в один упорядоченный массив.
        Эта операция выполняется за линейное время с линейным потреблением памяти. Реализуйте слияние двух массивов в качестве первого шага для написания сортировки слиянием.
        Формат ввода

        В первой строке входного файла содержится число NN — количество элементов первого массива ( 0≤N≤1060≤N≤106).
        Во второй строке содержатся NN целых чисел aiai​, разделенных пробелами, отсортированные по неубыванию ( −109≤ai≤109−109≤ai​≤109).
        В третьей строке входного файла содержится число MM — количество элементов второго массива ( 0≤M≤1060≤M≤106).
        В третьей строке содежатся MM целых чисел bibi​, разделенных пробелами, отсортированные по неубыванию ( −109≤bi≤109−109≤bi​≤109).
        Формат вывода

        Выведите результат слияния этих двух массивов, то есть M+NM+N целых чисел, разделенных пробелами, в порядке неубывания.
        Пример 1
        Ввод
        Вывод

        5
        1 3 5 5 9
        3
        2 5 6

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int m = Integer.parseInt(reader.readLine());
            int[] a = new int[m];
            String[] parts = reader.readLine().split(" ");
            for(int i = 0; i < m; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }
            int n = Integer.parseInt(reader.readLine());
            int[] b = new int[n];
            parts = reader.readLine().split(" ");
            for(int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(parts[i]);
            }

            // merge
            int[] merged = new int[m + n];
            int[][] sources = new int[][] { a, b};
            int[] offsets = new int[] {0, 0};
            int minSourceIdx;
            int min;

            for(int i = 0; i < merged.length; i++) {
                min = Integer.MAX_VALUE;
                minSourceIdx = -1;
                for(int j = 0; j < sources.length; j++) {
                    if(offsets[j] < sources[j].length && sources[j][offsets[j]] < min) {
                        min = sources[j][offsets[j]];
                        minSourceIdx = j;
                    }
                }

                if(minSourceIdx < 0) {
                    break;
                }
                merged[i] = sources[minSourceIdx][offsets[minSourceIdx]++];
            }

            String joined = Arrays.stream(merged).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            writer.write(joined);
            writer.newLine();
        }
    }
}
