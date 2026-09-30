package training.yandex.contest2025.part12;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionH {
    /*
      Строка ss была разрезана на kk частей одинаковой длины. По строке ss и перемешанным частям определите, в каком
       порядке их нужно склеить, чтобы получить строку ss.
        Формат ввода

        В первой строке записано два числа nn и mm ( 1≤n,m≤1061≤n,m≤106) — длина строки и количество кусков соответственно.

        Во второй строке записана строка ss ( 1≤∣s∣≤1061≤∣s∣≤106) — строка, которую необходимо получить.

        В ii-й из следующих mm строк дана строка titi​ — описание ii-го куска исходной строки.

        Гарантируется, что nn делится на mm и из данных кусков можно составить исходную строку.
        Формат вывода

        Выведите mm различных целых чисел aiai​ ( 1≤ai≤m1≤ai​≤m), таких, что если заменить число на ii-й позиции
         куском с номером aiai​ и склеить получившиеся куски, получится исходная строка.

        Если ответов несколько, выведите любой.
        Пример
        Ввод
        Вывод

        12 3
        cabacaqwerty
        erty
        caba
        caqw

     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int segmentLength = n / m;

        String source = reader.readLine();

        Map<String, List<Integer>> segmentIndices = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String segment = reader.readLine();
            List<Integer> indices = segmentIndices.get(segment);
            if(indices == null) {
                indices = new ArrayList<>();
            }
            indices.add(i);
            segmentIndices.put(segment, indices);
        }

        for(int i = 0; i < m; i++) {
            String segment = source.substring(i * segmentLength, Math.min(source.length(), (i+1) * segmentLength));
            List<Integer> indices = segmentIndices.get(segment);
            if(indices.isEmpty()) {
                throw new RuntimeException("Error when source ="+source+", segmentIndices="+segmentIndices);
            }
            int index = indices.remove(0) + 1;
            if(indices.isEmpty()) {
                segmentIndices.remove(segment);
            }

            if(i > 0) {
                writer.write(" ");
            }
            writer.write(String.valueOf(index));
        }

        writer.newLine();
        reader.close();
        writer.close();
    }
}
