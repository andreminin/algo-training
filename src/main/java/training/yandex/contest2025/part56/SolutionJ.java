package training.yandex.contest2025.part56;

import java.io.*;
import java.util.Arrays;


public class SolutionJ {
    /*
        По результатам 10237 сезона тренировок по алгоритмам участники, решившие достаточное количество задач, были приглашены на собеседование.

        Собеседования будут проводиться в течение nn дней. Каждый кандидат записался на собеседование в один из этих дней. В ii-й день пройти собеседование хотят aiai​ кандидатов, а максимальное количество собеседований, которые можно провести в этот день, равно bibi​.

        Все кандидаты должны пройти собеседование, но собеседование для некоторых из них, возможно, придётся перенести на другой день.

        Если кандидат хотел пройти собеседование в ii-й день, а в итоге его перенесли на день jj, то недовольство этого кандидата будет равно ∣i−j∣∣i−j∣.

        Помогите распределить кандидатов, чтобы для всех ii в ii-й день собеседование проходило не более bibi​ кандидатов, а максимальное недовольство среди всех кандидатов было минимальным.
        Формат ввода

        В первой строке вводится одно целое число nn — количество дней, в которые будут проводится собеседования ( 1≤n≤1061≤n≤106).

        Во второй строке вводятся nn целых чисел aiai​ — количество кандидатов, которые хотят пройти собеседование в ii-й день ( 1≤ai≤1091≤ai​≤109).

        В третьей вводятся nn целых чисел bibi​ — максимальное количество собеседований, которые можно провести в ii-й день ( 0≤bi≤1090≤bi​≤109).
        Формат вывода

        Выведите единственное целое число — минимальное kk, задающее максимальное недовольство кандидатов. Если решения не существует, следует вывести −1−1.
        Пример 1
        Ввод
        Вывод

        4
        6 14 70 1
        70 3 16 5



        2

        Пример 2
        Ввод
        Вывод

        1
        2
        2



        0

        Пример 3
        Ввод
        Вывод

        1
        3
        2

     */

    static boolean isBad(int k, long[] candidates, long[] capacities, long[] usedCap, int n, int[] qIdx, long[] qCap) {
        Arrays.fill(usedCap, 0, n, 0L);

        int queueHead = 0;
        int queueTail = 0;
        long capWindow = 0;
        int right = 0;

        for (int i = 0; i < n; i++) {
            while (right < n && right <= i + k) {
                long capLeft = capacities[right] - usedCap[right];

                if (capLeft > 0) {
                    qIdx[queueTail] = right;
                    qCap[queueTail] = capLeft;
                    capWindow += capLeft;
                    queueTail++;
                }

                right++;
            }

            while (queueHead < queueTail && qIdx[queueHead] < i - k) {
                capWindow -= qCap[queueHead];
                queueHead++;
            }

            long need = candidates[i];

            if (need == 0) {
                continue;
            }

            if (capWindow < need) {
                return true;
            }

            while (need > 0) {
                long take = Math.min(need, qCap[queueHead]);

                qCap[queueHead] -= take;
                usedCap[qIdx[queueHead]] += take;
                capWindow -= take;
                need -= take;

                if (qCap[queueHead] == 0) {
                    queueHead++;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int days = Integer.parseInt(reader.readLine());

            String[] parts = reader.readLine().split(" ");
            long[] candidates = new long[days];
            for (int i = 0; i < parts.length; i++) {
                candidates[i] = Long.parseLong(parts[i]);
            }

            parts = reader.readLine().split(" ");
            long[] capacities = new long[days];
            for (int i = 0; i < parts.length; i++) {
                capacities[i] = Long.parseLong(parts[i]);
            }

            long totalCandidates = 0;
            long totalCapacity = 0;

            for (int i = 0; i < days; i++) {
                totalCandidates += candidates[i];
                totalCapacity += capacities[i];
            }

            if (totalCandidates > totalCapacity) {
                writer.write("-1");
                writer.newLine();
                return;
            }

            int left = 0;
            int right = days - 1;
            int answer = -1;
            long[] usedCap = new long[days];
            int[] qIdx = new int[days];
            long[] qCap = new long[days];
            int mid;

            while (left <= right) {
                mid = (left + right) / 2;

                if (isBad(mid, candidates, capacities, usedCap, days, qIdx, qCap)) {
                    left = mid + 1;
                } else {
                    answer = mid;
                    right = mid - 1;
                }
            }

            writer.write(String.valueOf(answer));
            writer.newLine();
        }
    }
}
