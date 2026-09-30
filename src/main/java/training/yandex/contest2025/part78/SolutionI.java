package training.yandex.contest2025.part78;

import java.io.*;
import java.util.*;

public class SolutionI {
    /*
       Группа «Коллективные действия» проводит акцию «Лозунг» в лесу. Они подготовили транспарант с надписью «Я НИ НА ЧТО НЕ ЖАЛУЮСЬ И МНЕ ВСЕ НРАВИТСЯ, НЕСМОТРЯ НА ТО, ЧТО Я ЗДЕСЬ НИКОГДА НЕ БЫЛ И НЕ ЗНАЮ НИЧЕГО ОБ ЭТИХ МЕСТАХ», квадрат длины транспаранта составил dd.

        Акция проходит в лесу, в котором растут nn деревьев с целочисленными координатами xx и yy. Транспарант можно натянуть между парой деревьев, квадрат расстояния между которыми в точности равен dd. Чтобы посетителям было интереснее, группа планирует каждый день вешать транспарант между новой парой деревьев. Определите, сколько дней можно выбирать новую пару деревьев для проведения акции.
        Формат ввода

        В первой строке входного файла даны два целых числа nn ( 1≤n≤1051≤n≤105) — количество деревьев в лесу и dd ( 1≤d≤1081≤d≤108) — квадрат длины транспаранта.

        В следующих nn строках записаны пары целых чисел xi,yixi​,yi​ ( −108≤xi,yi≤108−108≤xi​,yi​≤108) — координаты деревьев. Все точки различны.
        Формат вывода

        Выведите количество подходящих пар деревьев.
        Пример
        Ввод
        Вывод

        9 1
        0 0
        1 0
        1 1
        0 1
        -1 1
        -1 0
        -1 -1
        0 -1
        1 -1



        12

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            long d = Long.parseLong(parts[1]);

            Set<Long> pointSet = new HashSet<>();
            long[] points = new long[n];

            for (int i = 0; i < n; i++) {
                parts = reader.readLine().split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                long encoded = ((long) x << 32) | (y & 0xFFFFFFFFL);

                points[i] = encoded;
                pointSet.add(encoded);
            }

            List<Integer> xShifts = new ArrayList<>();
            List<Integer> yShifts = new ArrayList<>();

            long maxDx = (long) Math.sqrt(d);
            for (long dx = 0; dx <= maxDx; dx++) {
                long remaining = d - dx * dx;
                if (remaining < 0) break;

                long dy = (long) Math.sqrt(remaining);

                if (dy * dy == remaining) {
                    if (dx == 0 && dy == 0) {
                        continue;
                    }

                    int dxInt = (int) dx;
                    int dyInt = (int) dy;

                    if (dx == 0) {
                        xShifts.add(0);
                        yShifts.add(dyInt);

                        xShifts.add(0);
                        yShifts.add(-dyInt);
                    } else if (dy == 0) {
                        xShifts.add(dxInt);
                        yShifts.add(0);

                        xShifts.add(-dxInt);
                        yShifts.add(0);
                    } else {
                        xShifts.add(dxInt);
                        yShifts.add(dyInt);

                        xShifts.add(dxInt);
                        yShifts.add(-dyInt);

                        xShifts.add(-dxInt);
                        yShifts.add(dyInt);

                        xShifts.add(-dxInt);
                        yShifts.add(-dyInt);
                    }
                }
            }

            long count = 0;
            int xShiftCount = xShifts.size();

            for (long point : points) {
                int px = (int) (point >> 32);
                int py = (int) point;

                for (int i = 0; i < xShiftCount; i++) {
                    int dx = xShifts.get(i);
                    int dy = yShifts.get(i);

                    long testPoint = ((long) (px + dx) << 32) | ((py + dy) & 0xFFFFFFFFL);

                    if (pointSet.contains(testPoint)) {
                        count++;
                    }
                }
            }

            // each pair counted twice, divide by 2
            writer.write(Long.toString(count / 2));
            writer.newLine();
        }
    }
}
