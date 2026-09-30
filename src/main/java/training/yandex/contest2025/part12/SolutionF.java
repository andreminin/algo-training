package training.yandex.contest2025.part12;

import java.io.*;

public class SolutionF {
    /*
      F. Плюсы, минусы и вопросы

        Ввод	стандартный ввод или input.txt
        Вывод	стандартный вывод или output.txt

        Дана таблица из nn mm символов, каждый символ может быть либо «+», либо «-», либо «?». Символ «+» означает
         число +1+1, символ «-» — число −1−1, а символ «?» может быть заменён на «+» или «-».

        Определите максимальную достижимую разность суммы в строке с наибольшей суммой и суммы в столбце
         с наименьшей суммой после замены всех символов «?».
        Формат ввода

        В первой строке записаны два числа nn и mm ( 1≤n,m≤10001≤n,m≤1000) — количество строк и столбцов в таблице соответственно.

        Далее идут nn строк по mm символов, содержащие только «+», «-» и «?».
        Формат вывода

        Выведите одно число — максимально возможную разность.
        Пример 1
        Ввод
        Вывод

        4 3
        +-+
        ??-
        ?-?
        ++?



        5

        Пример 2
        Ввод
        Вывод

        6 10
        ??+++-?-?-
        -??+???--+
        ?-+?+-?+--
        ??????--?+
        ++--?--+-?
        ?-?+++?+-?

     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = reader.readLine().trim();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int[] rowMaxSums = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '+') {
                    sum += 1;
                } else if (grid[i][j] == '-') {
                    sum -= 1;
                } else {
                    // optimistic
                    sum += 1;
                }
            }
            rowMaxSums[i] = sum;
        }

        int[] colMinSums = new int[m];
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == '+') {
                    sum += 1;
                } else if (grid[i][j] == '-') {
                    sum -= 1;
                } else {
                    //optimistic
                    sum -= 1;
                }
            }
            colMinSums[j] = sum;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = rowMaxSums[i] - colMinSums[j];
                if (grid[i][j] == '?') {
                    // -1 or 1 will reduce difference by 2 - either column sum inc. by 2 when selected +1
                    // or row sum dec. by 2 when selected -1
                    value -= 2;
                }
                if (value > max) {
                    max = value;
                }
            }
        }

        writer.write(String.valueOf(max));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
