package training.yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Solution_5321 {
    /*
        Новый год на носу, и Кодерун решил нарядить ёлку, но не простую, а геометрическую.

        Он знает, что ёлка состоит из k k слоёв, каждый из которых, в свою очередь, состоит из 2 i − 1 2i−1 треугольных
        веточек. На каждую веточку он хочет повесить по шарику. Узнайте, сколько шариков понадобится Кодеруну для того,
         чтобы украсить всю ёлку.

        Формат ввода

        На вход программе подаётся одно натуральное число k k ( 1 ≤ k ≤ 25 ) (1≤k≤25) − − количество слоёв ёлки.
        Формат вывода

        В качестве ответа выведите одно число - количество шариков, необходимых для украшения ёлки.
        Ограничения

        Ограничение времени
            1 с
        Ограничение памяти
            64 МБ

        Примеры
        Пример 1
        Ввод
        3
        Вывод
        7

        Пример 2
        Ввод
        21
        Вывод
        2097151

        Пример 3
        Ввод
        22
        Вывод
        4194303
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
        Пример ввода и вывода числа n, где -10^9 < n < 10^9:

        writer.write(String.valueOf(n));
        */

        int n = Integer.parseInt(reader.readLine());

        n = (1 << n)-1;

        writer.write(String.valueOf(n));

        reader.close();
        writer.close();
    }

}
