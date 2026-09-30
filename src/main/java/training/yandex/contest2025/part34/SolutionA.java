package training.yandex.contest2025.part34;

import java.io.*;

public class SolutionA {

    /*
        На вершине лесенки, содержащей NN ступенек, находится мячик, который начинает прыгать по ним вниз, к основанию.
         Мячик может прыгнуть на следующую ступеньку, на ступеньку через одну или через 2. То есть, если мячик лежит
         на 8-ой ступеньке, то он может переместиться на 5-ую, 6-ую или 7-ую.

        Определите число всевозможных «маршрутов» мячика с вершины на землю.
        Формат ввода

        Вводится одно число 1≤N≤301≤N≤30.
        Формат вывода

        Выведите одно число — количество маршрутов.
        Пример 1
        Ввод
        Вывод

        4
        7

        Пример 2
        Ввод
        Вывод

        5
        13
     */


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();
        int steps = Integer.parseInt(input);

        int[] dp = new int[steps+1];
        dp[0] = 0;
        if(steps >= 1) {
            dp[1] = 1;
            if(steps >= 2) {
                dp[2] = 2;
            }
            if(steps >= 3) {
                dp[3] = 4;
            }
        }

        for(int i = 4; i<dp.length; i++) {
            dp[i] = dp[i-1] + dp[i - 2] + dp[i-3];
        }

        int routes = dp[steps];

        writer.write(String.valueOf(routes));
        writer.newLine();

        reader.close();
        writer.close();
    }
}
