package training.yandex.contest2025.part78;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class SolutionG {
    /*
        Вася снимается в популярном сериале и вместе со сценаристами составил план с количеством серий на ближайшие nn сезонов (один сезон снимают за один месяц). В сезоне ii планируется sisi​ серий. Кроме съемок в этом сериале у Васи есть много других дел, так что для каждого из nn предстоящих месяцев он определил коэффициент важности других дел aiai​.

        Однако концепция изменилась и теперь в каждом сезоне должно быть одинаковое количество серий. Если количество серий в каждом сезоне станет равно ee, то в ii-м сезоне отличие в количестве серий от плана будет равно di=∣e−si∣di​=∣e−si​∣ и Васе нужно будет доплатить di×aidi​×ai​ рублей за причиненные неудобства.

        Сериал популярный, но бюджет не бесконечен, так что помогите шоураннерам определить такое ee, чтобы суммарная доплата Васе за все предстоящие сезоны была минимальной. Если способов выбора такого ee несколько — выберите самое маленькое ee (короткие сезоны больше нравятся зрителям).
        Формат ввода

        В первой строке вводится число nn ( 1≤n≤1051≤n≤105) — количество запланированных сезонов.

        Во второй строке вводится nn чисел sisi​ ( 1≤si≤1061≤si​≤106) — запланированное количество серий в ii-м сезоне.

        В третьей строке вводится nn чисел aiai​ ( 1≤ai≤1061≤ai​≤106) — коэффициент важности других Васиных дел на время съемки ii-о сезона.
        Формат вывода

        Выведите два числа: сколько серий будет в каждом из сезонов и сколько рублей придется доплатить Васе за причиненные неудобства. Если вариантов выбора количества серий несколько, выведите минимальное.
        Пример 1
        Ввод
        Вывод

        6
        6 7 8 8 7 7
        10 6 3 1 1 4



        7 14

        Пример 2
        Ввод
        Вывод

        5
        7 5 7 9 8
        10 8 7 8 5



        7 37

        Пример 3
        Ввод
        Вывод

        5
        8 5 10 9 7
        2 5 4 8 4

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());

            String[] parts = reader.readLine().split(" ");
            int[] s = new int[n];
            for (int i = 0; i < n; i++) {
                s[i] = Integer.parseInt(parts[i]);
            }

            parts = reader.readLine().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }

            int[][] sa = new int[n][2];
            for (int i = 0; i < n; i++) {
                sa[i][0] = s[i];
                sa[i][1] = a[i];
            }

            Arrays.sort(sa, Comparator.comparingInt(x -> x[0]));

            long totalWeight = 0;
            for (int i = 0; i < n; i++) {
                totalWeight += sa[i][1];
            }

            long half = (totalWeight + 1) / 2;
            long sum = 0;
            int e = 0;

            for (int i = 0; i < n; i++) {
                sum += sa[i][1];

                if (sum >= half) {
                    e = sa[i][0];
                    break;
                }
            }

            long totalCost = 0;
            for (int i = 0; i < n; i++) {
                totalCost += (long) Math.abs(e - sa[i][0]) * sa[i][1];
            }

            writer.write(e + " " + totalCost);
        }
    }
}
