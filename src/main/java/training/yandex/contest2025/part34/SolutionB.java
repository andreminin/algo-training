package training.yandex.contest2025.part34;

import java.io.*;

public class SolutionB {
    /*
        Школьники хотят начать поход в некоторой точке на левом берегу и закончить поход в некоторой точке на правом берегу,
         возможно, переправляясь через реки несколько раз. Как известно, переправа как через реку, так и через приток
         представляет собой определённую сложность, поэтому они хотят минимизировать число совершённых переправ.
         Переправляться через реку и притоки можно только перпендикулярно их течению (т.е. нельзя переправиться через реку и приток «по диагонали»).

        Школьники заранее изучили карту и записали, в какой последовательности в Москву-реку впадают притоки на всём их маршруте.

        Помогите школьникам по данному описанию притоков определить минимальное количество переправ, которое им придётся совершить во время похода.
        Формат ввода

        Единственная строка содержит описание Москвы-реки между начальной и конечной точкой похода. Длина строки не превосходит 200200 символов.

        Каждый символ строки может быть одной из трёх латинских букв L, R или B. Буква L означает, что очередной приток впадает
        в реку с левого берега, R — приток впадает в реку с правого берега и B — притоки впадают с обоих берегов реки в одном
        месте. Поход начинается на левом берегу перед описанной частью реки и заканчивается на правом берегу после описанной части.
        Формат вывода

        Выведите одно целое число — минимальное количество переправ.
        Пример
        Ввод
        Вывод

        LLBLRRBRL

        5

        LLBLRRBRB
        6

        BLBLRRBRB
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();
        int m = input.length();

        // can be coded as dp[m][2] - store [2] left/right current counters instead of array
        int leftCost = 0;
        int rightCost = 1;
        int newLeftCost;
        int newRightCost;

        if(m > 0) {
            for (int i = 0; i < m; i++) {
                char ch = input.charAt(i);

                switch (ch) {
                    case 'L':
                        newLeftCost = Math.min(leftCost + 1, rightCost + 1);
                        newRightCost = Math.min(rightCost, leftCost + 2);
                        break;
                    case 'R':
                        newLeftCost = Math.min(leftCost, rightCost + 2);
                        newRightCost = Math.min(rightCost + 1, leftCost + 1);
                        break;
                    case 'B':
                        newLeftCost = Math.min(leftCost + 1, rightCost + 2);
                        newRightCost = Math.min(rightCost + 1, leftCost + 2);
                        break;
                    default:
                        newLeftCost = leftCost;
                        newRightCost = rightCost;
                }

                leftCost = newLeftCost;
                rightCost = newRightCost;
            }
        }

        writer.write(String.valueOf(rightCost));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
