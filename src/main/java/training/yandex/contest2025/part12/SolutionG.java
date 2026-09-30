package training.yandex.contest2025.part12;

import java.io.*;

class SolutionG {
    /*
        На большом игровом поле кто-то играл в крестики-нолики (возможно, не соблюдая правила). Цель игры — выстроить
         пять одинаковых фигур по горизонтали, вертикали или диагонали. Определите, найдется ли такая пятёрка фигур или нет.
        Формат ввода

        В первой строке ввода записаны числа nn и mm ( 1≤n,m≤10001≤n,m≤1000) — размеры игрового поля.

        В следующих nn строках записано по mm символов ”X”, ”O” или ”.”, которые задают крестик, нолик и пустую
        клетку, соответственно. ”X” и ”O” — заглавные английские буквы
        Формат вывода

        Выведите Yes, если найдется пять одинаковых фигур подряд, и No в противном случае.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = reader.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        boolean found = false;
        char ch;

        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < m && !found; j++) {
                ch = grid[i][j];
                if (ch == '.') continue;

                for (int[] dir : directions) {
                    int dx = dir[0];
                    int dy = dir[1];

                    if (i + 4 * dx >= 0
                            && i + 4 * dx < n
                            && j + 4 * dy >= 0
                            && j + 4 * dy < m)
                    {
                        boolean valid = true;
                        for (int k = 1; k <= 4; k++) {
                            if (grid[i + k * dx][j + k * dy] != ch) {
                                valid = false;
                                break;
                            }
                        }

                        if (valid) {
                            found = true;
                            break;
                        }
                    }
                }
            }
        }

        writer.write(found ? "Yes" : "No");
        writer.newLine();

        reader.close();
        writer.close();
    }
}