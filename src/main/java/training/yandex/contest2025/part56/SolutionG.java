package training.yandex.contest2025.part56;

import java.io.*;
import java.util.Arrays;

public class SolutionG {

   /*
    Недавно учёные открыли суперструнную гравитацию, сила которой вычисляется довольно необычно. Частица АА существует в
    nn-мерном пространстве, его координаты равны a1,a2,…,ana1​,a2​,…,an​. Частица BB существует в mm-мерном пространстве, его координаты равны b1,b2,…,bmb1​,b2​,…,bm​

        Сила гравитации между двумя этими частицами равна ∑i,j(i−j)⋅∣ai−bj∣∑i,j​(i−j)⋅∣ai​−bj​∣. Подсчитайте силу гравитации.
        Формат ввода

        В первой строке содержится число nn — количество измерений, в пространстве частицы AA ( 1≤n≤1051≤n≤105).

        Во второй строке содержится nn чисел aiai​ — координаты частицы AA ( 1≤ai≤1041≤ai​≤104).

        В третьей строке содержится число mm — количество измерений, в пространстве частицы BB ( 1≤m≤1051≤m≤105).

        Во второй строке содержится mm чисел bibi​ — координаты частицы BB ( 1≤bi≤1041≤bi​≤104).
        Формат вывода

        Выведите силу гравитации между частицами AA и BB.
        Пример 1
        Ввод
        Вывод

        3
        1 2 3
        3
        1 2 3



        0

        Пример 2
        Ввод
        Вывод

        4
        1 4 3 6
        3
        8 1 1

    */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine().trim());
            int[] a = new int[n];
            String[] aParts = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(aParts[i]);
            }

            int m = Integer.parseInt(reader.readLine().trim());
            int[] b = new int[m];
            String[] bParts = reader.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(bParts[i]);
            }

            long s1 = computeS(a, b);
            long s2 = computeS(b, a);

            writer.write(String.valueOf(s1 - s2));
            writer.newLine();
        }
    }

    private static long computeS(int[] point1, int[] point2) {
        int[] sorted2 = point2.clone();
        Arrays.sort(sorted2);

        int length1 = point1.length;
        int length2 = point2.length;

        long[] prefix2 = new long[length2 + 1];
        for (int i = 0; i < length2; i++) {
            prefix2[i + 1] = prefix2[i] + sorted2[i];
        }

        long res = 0;
        for (int i = 0; i < length1; i++) {
            int x = point1[i];
            int k = lowerBound(sorted2, x);
            long leftSum = (long) x * k - prefix2[k];
            long rightSum = (prefix2[length2] - prefix2[k]) - (long) x * (length2 - k);
            long total = leftSum + rightSum;

            res += total * (i + 1);
        }
        return res;
    }

    private static int lowerBound(int[] values, int value) {
        int left = 0;
        int right = values.length;

        while (left < right) {
            int mid = (right + left) / 2;
            if (values[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
