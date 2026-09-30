package training.yandex.contest2025.part12;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SolutionC {
    /*
      C. Кибербезопасность
        Не решена
        Ограничение времени	2 секунды
        Ограничение памяти	256 Мб
        Ввод	стандартный ввод
        Вывод	стандартный вывод

        На работе у Васи требуют менять пароль каждую неделю, причём новый пароль не должен совпадать ни с одним из
         предыдущих. Любимый Васин пароль — это строка ss, состоящая из строчных английских букв. Этот пароль он использует
         первую неделю. Придумывать и запоминать новые пароли Васе лень. Поэтому он решил менять местами пару букв в исходном
          пароле ss и получать новый пароль. Определите, сколько недель Вася сможет генерировать новые пароли с учетом пароля ss на первой неделе.
        Формат ввода

        Вводится строка ss ( 1≤∣s∣≤1051≤∣s∣≤105), состоящая из строчных английских букв — начальный пароль Васи.
        Формат вывода

        Выведите одно число — количество недель, в течение которых Вася сможет использовать исходный и измененные пароли.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int n = input.length();
        long totalSwaps = (long) n * (n - 1) / 2;

        long sameCharSwaps = 0;
        for (int count : freqMap.values()) {
            if (count > 1) {
                sameCharSwaps += (long) count * (count - 1) / 2;
            }
        }

        long result = (totalSwaps - sameCharSwaps) + 1;

        writer.write(String.valueOf(result));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
