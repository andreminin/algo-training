package training.yandex.contest2025.part34;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class SolutionI {
    /*
       Дана таблица из n×mn×m целых чисел. Необходимо найти в этой таблице самую длинную цепочку из последовательных натуральных чисел. Цепочкой называется последовательность соседних по стороне клеток таблицы.

        Определите максимальную длину цепочки.
        Формат ввода

        В первой строке записаны числа nn и mm ( 1≤n,m≤10001≤n,m≤1000). В следующих nn строках задано по mm чисел ai,jai,j​ — элементы таблицы ( 1≤ai,j≤10×n×m1≤ai,j​≤10×n×m).
        Формат вывода

        Выведите единственное число — максимальную длину цепочки из последовательных чисел в таблице.
        Пример 1
        Ввод
        Вывод

        3 3
        1 2 3
        6 5 4
        7 8 9



        9

        Пример 2
        Ввод
        Вывод

        3 3
        2 2 2
        2 3 2
        2 2 2



        2

     */

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    static class Cell {
        int value;
        int r;
        int c;
        Cell(int value, int r, int c) {
            this.value = value;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] dimensions = reader.readLine().split(" ");
            int n = Integer.parseInt(dimensions[0]);
            int m = Integer.parseInt(dimensions[1]);

            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                String[] row = reader.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(row[j]);
                }
            }

            Cell[] cells = new Cell[n * m];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cells[index++] = new Cell(grid[i][j], i, j);
                }
            }

            Arrays.sort(cells, Comparator.comparingInt(cell -> cell.value));

            int[][] dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], 1);
            }

            int maxChain = 1;

            for (Cell cell : cells) {
                int r = cell.r;
                int c = cell.c;
                int currentValue = cell.value;

                for (int d = 0; d < 4; d++) {
                    int neighRow = r + dx[d];
                    int neighCol = c + dy[d];

                    if (neighRow >= 0 && neighRow < n && neighCol >= 0 && neighCol < m && grid[neighRow][neighCol] == currentValue - 1) {
                        dp[r][c] = Math.max(dp[r][c], dp[neighRow][neighCol] + 1);
                    }
                }

                maxChain = Math.max(maxChain, dp[r][c]);
            }

            writer.write(String.valueOf(maxChain));
            writer.newLine();
        }
    }

}
