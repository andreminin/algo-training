package training.yandex.contest2025.part12;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SolutionD {
    /*
    D. Отборочный контест

        Ввод	стандартный ввод или input.txt
        Вывод	стандартный вывод или output.txt

        Вася составляет отборочный контест на стажировку. Сотрудники компании подготовили nn задач, для каждой из задач
        определена тема, к которой она относится. Отборочный контест состоит ровно из kk задач. Помогите Васе выбрать
        kk задач из nn так, чтобы они покрывали как можно больше различных тем.
        Формат ввода

        В первой строке вводится два целых числа n,kn,k ( 1≤k≤n≤1000001≤k≤n≤100000) — количество задач, придуманных
         сотрудниками, и количество задач в контесте. В следующей строке записано nn чисел aiai​
         ( 1≤ai≤1091≤ai​≤109) — тема, к которой относится задача с номером ii.
        Формат вывода

        Выведите ровно kk целых чисел через пробел — темы задач, которые нужно взять в контест. Если правильных
         ответов несколько — выведите любой из них.

        Пример 1
        Ввод
        5 3
        1 1 1 2 2
        Вывод
        1 2 1

        Пример 2
        Ввод
        10 4
        8 8 8 8 8 8 8 8 2 1

        Вывод
        1 2 8 8

     */

    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int taskCount = Integer.parseInt(input[0]);
        int resultSize = Integer.parseInt(input[1]);

        input = reader.readLine().split(" ");
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < taskCount; i++) {
            int id = Integer.parseInt(input[i]);
            freqMap.merge(id, 1, Integer::sum);
        }

        Map<Integer, List<Integer>> revertedFreqMap = freqMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));

        List<Integer> frequencies = new ArrayList<>(revertedFreqMap.keySet());
        Collections.sort(frequencies);

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int freqIdx = frequencies.size() - 1;
        while (i < resultSize) {
            int freq = freqIdx >= 0 ? frequencies.get(freqIdx) : frequencies.get(frequencies.size() - 1);
            freqIdx--;

            List<Integer> resultIds = revertedFreqMap.get(freq);
            for (int resultId : resultIds) {
                sb.append(resultId).append(" ");
                i++;
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }

        writer.write(sb.toString());
        writer.newLine();

        reader.close();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int taskCount = Integer.parseInt(input[0]);
        int resultSize = Integer.parseInt(input[1]);

        input = reader.readLine().split(" ");
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < taskCount; i++) {
            String txt = input[i];
            int id = Integer.parseInt(txt);
            freqMap.merge(id, 1, Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> sortedIds = new ArrayList<>(freqMap.entrySet());
        sortedIds.sort((a, b) -> b.getValue() - a.getValue());

        Set<Integer> selectedIds = new HashSet<>();
        List<Integer> contestIds = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : sortedIds) {
            if (contestIds.size() < resultSize) {
                contestIds.add(entry.getKey());
                selectedIds.add(entry.getKey());
            } else {
                break;
            }
        }

        if (contestIds.size() < resultSize) {
            for (Map.Entry<Integer, Integer> entry : sortedIds) {
                int theme = entry.getKey();
                int available = entry.getValue() - (selectedIds.contains(theme) ? 1 : 0);

                while (available > 0 && contestIds.size() < resultSize) {
                    contestIds.add(theme);
                    available--;
                }

                if (contestIds.size() >= resultSize) {
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int theme : contestIds) {
            sb.append(theme).append(" ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }

        writer.write(sb.toString());
        writer.newLine();

        reader.close();
        writer.close();
    }
}
