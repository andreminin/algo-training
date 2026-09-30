package training.yandex.contest2025.part12;

import java.io.*;
import java.util.*;

public class SolutionI {
    /*
        Британские учёные доказали, что развитие систем ИИ и автономного транспорта привело к снижению коэффициента
        интеллекта у водителей автомобилей. В городе М решили бороться за повышение коэффициента интеллекта, усложнив
         правила дорожного движения и запретив автономный транспорт и навигаторы. Город М имеет регулярную планировку:
         весь город разделен на квадратные кварталы одинакового размера, улицы направлены с севера на юг и с запада на восток.
         По новым ПДД на каждом перекрестке разрешено только два манёвра: поворот налево и разворот (поворот направо и
          проезд прямо запрещены), после чего можно проехать один квартал до следующего перекрестка.

        Вам предстоит разработать подпольный навигатор, который будет прокладывать маршрут от одного перекрестка до другого
        с минимальным количеством маневров. При этом на начальном перекрестке водитель может поднять автомобиль и направить
        его в любую сторону вручную (программа физического развития жителей города М уже успешно завершилась).
        Формат ввода

        В первой строке записаны числа xx и yy ( 1≤x≤1091≤x≤109, 1≤y≤1091≤y≤109) — номера улиц по вертикали и горизонтали,
         на перекрестке которых стартует автомобиль.

        Во второй строке записаны числа ff и gg ( 1≤f≤1091≤f≤109, 1≤g≤1091≤g≤109) — номера улиц по вертикали и горизонтали,
        на перекресток которых необходимо попасть.

        Улицы занумерованы подряд идущими целыми числами, можно считать количество улиц бесконечным. Вертикальные улицы
        нумеруются слева-направо, горизонтальные сверху-вниз.
        Формат вывода

        Выведите минимальное количество маневров, которые необходимо совершить.
        Пример 1
        Ввод
        Вывод

        8 7
        3 4

        19

        Пример 2
        Ввод
        Вывод

        3 4
        4 3

        1
     */

    static class State {
        long x, y;
        int dir; // 0=E, 1=N, 2=W, 3=S
        long cost;

        State(long x, long y, int dir, long cost) {
            this.x = x; this.y = y; this.dir = dir; this.cost = cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, dir);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof State s)) return false;
            return s.x == x && s.y == y && s.dir == dir;
        }
    }

    private static final int[][] DIRS = {
            {1, 0},  // east
            {0, 1},  // north
            {-1, 0}, // west
            {0, -1}  // south
    };

    public static long bfs(long x1, long y1, long x2, long y2) {
        if (x1 == x2 && y1 == y2) return 0;

        Queue<State> q = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();

        // first step: can start in any direction (free)
        for (int d = 0; d < 4; d++) {
            long nx = x1 + DIRS[d][0];
            long ny = y1 + DIRS[d][1];
            State s = new State(nx, ny, d, 0);
            if (!visited.contains(s)) {
                visited.add(s);
                q.add(s);
            }
        }

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.x == x2 && cur.y == y2) {
                return cur.cost;
            }

            // left turn
            int left = (cur.dir + 1) % 4;
            long lx = cur.x + DIRS[left][0];
            long ly = cur.y + DIRS[left][1];
            State ls = new State(lx, ly, left, cur.cost + 1);
            if (visited.add(ls)) q.add(ls);

            // U-turn
            int back = (cur.dir + 2) % 4;
            long bx = cur.x + DIRS[back][0];
            long by = cur.y + DIRS[back][1];
            State bs = new State(bx, by, back, cur.cost + 1);
            if (visited.add(bs)) q.add(bs);
        }

        return -1; // unreachable (should never happen)
    }




    private static void assertEquals(String label, long got, long expect) {
        System.out.printf("%s: got=%d expected=%d %s\n", label, got, expect, (got==expect) ? "OK" : "ERROR");
    }

    public static void main2(String[] args) {
        assertEquals("(7,7 -> 7,7)", findPath(7,7,7,7), bfs(7,7,7,7));
        assertEquals("(8,7 -> 3,4)", findPath(8,7,3,4), bfs(8,7,3,4));
        assertEquals("(3,4 -> 4,3)", findPath(3,4,4,3), bfs(3,4,4,3));
        assertEquals("(5,5 -> 6,10)", findPath(5,5,6,10), bfs(5,5,6,10));
        assertEquals("(5,5 -> 10,6)", findPath(5,5,6,10), bfs(5,5,10,6));
        assertEquals("(1,1 -> 18,7)", findPath(1,1,18,7), bfs(1,1,18,7));

        assertEquals("(3,4 -> 8,7)", findPath(3,4,8,7), 19);
        assertEquals("(3,4 -> 3,9)", findPath(3,4,3,9), 12);
        assertEquals("(3,4 -> 8,4)", findPath(3,4,8,4), 12);
    }

    public static long findPath(long x1, long y1, long x2, long y2) {
        if (x1 == x2 && y1 == y2) return 0;
        //shift
        x2 -= x1;
        y2 -= y1;
        x1 = 0;
        y1 = 0;

        //rotate
        if(x2 >= 0) {
            if(y2 >= 0) {
                //do nothing
            } else {
                long tmp = x2;
                x2 = -y2;
                y2 = tmp;
            }
        } else {
            if(y2 >= 0) {
                long tmp = y2;
                y2 = -x2;
                x2 = tmp;
            } else {
                x2 = -x2;
                y2 = -y2;
            }
        }

        // edge cases
        // axes
        if(x2 == 0) {
            //first step is free
            if(y2 == 1) {
                return 0;
            } else {
                return 3*(y2 - 1);
            }
        }
        if(y2 == 0) {
            //first step is free
            if(x2 == 1) {
                return 0;
            } else {
                return 3*(x2 - 1);
            }
        }
        // 1 lines
        if(x2 == 1) {
            if(y2 == 1) {
                return 1;
            }
            return 3*(y2 - 1)+1;
        }

        if(y2 == 1) {
            return 3*(x2 - 1)+1;
        }

        // forward = 3, left = 1; shift left/right = 3, right = 5
        return (x2+y2 - 1)*3 - 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = reader.readLine().split(" ");
        long x1 = Long.parseLong(line[0]);
        long y1 = Long.parseLong(line[1]);

        line = reader.readLine().split(" ");
        long x2 = Long.parseLong(line[0]);
        long y2 = Long.parseLong(line[1]);

        long steps = findPath(x1, y1, x2, y2);

        writer.write(String.valueOf(steps));
        writer.newLine();
        reader.close();
        writer.close();
    }
}
