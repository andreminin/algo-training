package training.yandex.contest2024.part4;

import java.io.*;

public class SolutionC {

    /*
Как известно, Саруман Радужный очень любит порядок. Поэтому все полки его войска стоят друг за другом, причем каждый следующий полк содержит количество орков не меньше, чем предыдущий.

Перед тем как напасть на Хельмову Падь, Саруман решил провести несколько вылазок для разведки. Чтобы его отряды никто не заметил, он решил каждый раз отправлять несколько подряд идущих полков так, чтобы суммарное количество орков в них было равно определенному числу. Так как это всего лишь разведка, каждый полк после вылазки возвращается на свое место. Задачу выбрать нужные полки он поручил Гриме Змеиному Языку. А Грима не поскупится на вознаграждение, если вы ему поможете.
Формат ввода

В первой строке входного файла находится два целых числа: nn ( 1≤n≤2⋅1051≤n≤2⋅105) — количество полков и mm ( 1≤m≤2⋅1051≤m≤2⋅105) – количество предстоящих вылазок.

В следующей строке записано nn чисел aiai​, где aiai​ — число орков в ii-ом полке ( 1≤ai≤109,ai≤ai+11≤ai​≤109,ai​≤ai+1​).

Далее в mm строках записаны запросы вида: количество полков ll ( 1≤l≤n1≤l≤n), которые должны будут отправиться в эту вылазку, и суммарное количество орков в этих полках ss ( 1≤s≤2⋅10161≤s≤2⋅1016)
Формат вывода

Для каждого запроса выведите номер полка, с которого начнутся те ll, которые необходимо отправить на вылазку. Если таких полков несколько, выведите любой. Если же так выбрать полки нельзя, выведите −1−1.
Пример
Ввод
Вывод

5 2
1 3 5 7 9
2 4
1 3

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            long[] prefixSum = new long[n + 1];
            parts = reader.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = prefixSum[i - 1] + Long.parseLong(parts[i - 1]);
            }

            for (int i = 0; i < m; i++) {
                parts = reader.readLine().split(" ");
                int l =  Integer.parseInt(parts[0]);
                long s = Long.parseLong(parts[1]);

                if (l > n) {
                    writer.write("-1");
                    writer.newLine();
                    continue;
                }

                long minSum = prefixSum[l] - prefixSum[0];
                long maxSum = prefixSum[n] - prefixSum[n - l];

                if (s < minSum || s > maxSum) {
                    writer.write("-1");
                    writer.newLine();
                    continue;
                }

                int left = 0;
                int right = n - l;
                int ans = -1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    long currentSum = prefixSum[mid + l] - prefixSum[mid];

                    if (currentSum == s) {
                        ans = mid;
                        break;
                    } else if (currentSum < s) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (ans == -1) {
                    writer.write("-1");
                    writer.newLine();
                } else {
                    writer.write(String.valueOf(ans+1));
                    writer.newLine();
                }
            }
        }
    }
}
