package training.yandex.contest2025.part34;

import java.io.*;

public class SolutionF {

    /*
        В компьютерной игре игрок двигается по дороге сверху-вниз, цель игры — собрать как можно больше монеток. Дорога
        представляет из себя таблицу из n×3n×3 клеток. Каждая клетка либо пуста, либо содержит монетку, либо стену. Игрок
         начинает двигаться с первой строки, у него есть три варианта выбора начальной клетки и двигается до тех пор, пока
         не врежется в стену, либо не дойдет до конца дороги (не окажется в строчке nn).

        Если игрок находится в строке rr и столбце cc — (r;c)(r;c), тогда за один ход он может попасть в три клетки:
        (r+1;c−1)(r+1;c−1), (r+1;c)(r+1;c), (r+1;c+1)(r+1;c+1), если клетка находится в пределах дороги и не содержит стены.

        Определите наибольшее количество монет, которое сможет собрать игрок.
        Формат ввода

        В первой строке вводится число nn ( 1≤n≤1041≤n≤104) — количество строк в таблице. В следующих nn строках дано по три
        имвола сс, описывающих строку таблицы. cc равен ”.”, если клетка пустая, ”C”, если в этой клетке монета, и ”W”, если стена.
         Если в первой строке во всех клетках находятся стены, то игра заканчивается сразу.
        Формат вывода

        Выведите одно число — максимальное количество монеток, которое можно собрать.
        Пример 1
        Ввод
        Вывод

        5
        W.W
        C.C
        WW.
        CC.
        CWW



        3

        Пример 2
        Ввод
        Вывод

        4
        W.W
        CWC
        W.W
        CWW

     */
    private static final int COLS = 3;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int rows = Integer.parseInt(reader.readLine());

            char[][] symbols = new char[rows][];
            for (int i = 0; i < rows; i++) {
                String line = reader.readLine();
                symbols[i] = new char[COLS];
                for (int j = 0; j < COLS; j++) {
                    symbols[i][j] = line.charAt(j);
                }
            }

            boolean allWallsFirstRow = true;
            for (int j = 0; j < COLS; j++) {
                if (symbols[0][j] != 'W') {
                    allWallsFirstRow = false;
                    break;
                }
            }

            if (allWallsFirstRow) {
                writer.write("0");
                writer.newLine();
                return;
            }

            int[][] dp = new int[rows][COLS];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < COLS; j++) {
                    dp[i][j] = -1;
                }
            }

            for (int j = 0; j < COLS; j++) {
                if (symbols[0][j] != 'W') {
                    dp[0][j] = (symbols[0][j] == 'C') ? 1 : 0;
                }
            }

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (symbols[i][j] == 'W') {
                        continue;
                    }

                    int maxFromPrevious = -1;
                    if (j > 0 && dp[i-1][j-1] != -1) {
                        maxFromPrevious = Math.max(maxFromPrevious, dp[i-1][j-1]);
                    }
                    if (dp[i-1][j] != -1) {
                        maxFromPrevious = Math.max(maxFromPrevious, dp[i-1][j]);
                    }
                    if (j < COLS-1 && dp[i-1][j+1] != -1) {
                        maxFromPrevious = Math.max(maxFromPrevious, dp[i-1][j+1]);
                    }

                    if (maxFromPrevious != -1) {
                        dp[i][j] = maxFromPrevious + ((symbols[i][j] == 'C') ? 1 : 0);
                    }
                }
            }

            int maxCoins = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (dp[i][j] > maxCoins) {
                        maxCoins = dp[i][j];
                    }
                }
            }

            writer.write(String.valueOf(maxCoins));
            writer.newLine();
        }
    }
}
