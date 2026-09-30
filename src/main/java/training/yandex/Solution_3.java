package training.yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Collectors;

public class Solution_3 {
    /*
    3. Вывести маршрут максимальной стоимости
    Средняя

    В левом верхнем углу прямоугольной таблицы размером N × M N×M находится черепашка. В каждой клетке таблицы записано
     некоторое число. Черепашка может перемещаться вправо или вниз, при этом маршрут черепашки заканчивается в правом нижнем углу таблицы.

    Подсчитаем сумму чисел, записанных в клетках, через которую проползла черепашка (включая начальную и конечную клетку).
     Найдите наибольшее возможное значение этой суммы и маршрут, на котором достигается эта сумма.
    Формат ввода

    В первой строке входных данных записаны два натуральных числа N и M, не превосходящих 100 — размеры таблицы.
    Далее идет N строк, каждая из которых содержит M чисел, разделенных пробелами — описание таблицы. Все числа в клетках
    таблицы целые и могут принимать значения от 0 до 100.
    Формат вывода

    Первая строка выходных данных содержит максимальную возможную сумму, вторая — маршрут, на котором достигается эта сумма.
     Маршрут выводится в виде последовательности, которая должна содержать N-1 букву D, означающую передвижение вниз и
      M-1 букву R, означающую передвижение направо. Если таких последовательностей несколько, необходимо вывести ровно одну (любую) из них

        Пример 1
        Ввод

        5 5
        9 9 9 9 9
        3 0 0 0 0
        9 9 9 9 9
        6 6 6 6 8
        9 9 9 9 9

        Вывод

        74
        D D R R R R D D


      Вычисление максимальной стоимости: Заполняем матрицу dp, где dp[i][j] хранит максимальную стоимость достижения
      клетки (i, j). Для каждой клетки значение dp[i][j] вычисляется как сумма значения в текущей клетке и
       максимального из значений сверху или слева.

      Восстановление маршрута: Начиная с правого нижнего угла, двигаемся в направлении, которое даёт максимальное
        значение в dp, записывая шаги в обратном порядке. Затем разворачиваем полученную строку шагов.
   */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] dimension = reader.readLine().split(" ");
        int n = Integer.parseInt(dimension[0]);
        int m = Integer.parseInt(dimension[1]);

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];

        //1st column
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }

        //1st row
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j-1] + matrix[0][j];
        }

        //other cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
            }
        }

        writer.write(String.valueOf(dp[n-1][m-1]));
        writer.newLine();

        StringBuilder path = new StringBuilder();
        int i = n - 1;
        int j = m - 1;

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0) {
                if (dp[i-1][j] > dp[i][j-1]) {
                    path.append('D');
                    i--;
                } else {
                    path.append('R');
                    j--;
                }
            } else if (i > 0) {
                path.append('D');
                i--;
            } else {
                path.append('R');
                j--;
            }
        }

        String formattedPath = path.reverse().chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining(" "));

        writer.write(formattedPath);
        writer.newLine();

        reader.close();
        writer.close();
    }
}
