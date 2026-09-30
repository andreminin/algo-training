package training.yandex.contest2025.part78;

import java.io.*;
import java.util.*;

public class SolutionJ {
    /*
         В кружке автомоделирования проводятся соревнования. Они проходят на прямом участке трассы длиной LL и шириной WW. NN участников выставляют свои модели на стартовые позиции. По свистку все модели начинают движение.

        Цель состязания — быстрее всех доехать до линии финиша. Однако в пути каждую модель подстерегают опасности. А именно, модель может врезаться в борта трассы и тем самым выбывать из гонки, или две и более модели могут столкнуться и также выбыть из гонки. Выбывшие из гонки модели убираются с трассы.

        Введем систему координат, в которой ось OX будет направлена вдоль протяжения трассы, а ось OY — поперек ему. Тогда трасса является прямоугольником, ограниченным прямыми y=0y=0, y=Wy=W, x=0x=0 и x=Lx=L.

        Модель считается врезавшейся в борт трассы, если в некоторый момент времени её yy-координата будет равна либо 00, либо WW. Две модели считаются столкнувшимися, если в некоторый момент времени их координаты совпадают. Модели можно считать материальными точками.

        Модель побеждает в соревновании, если она успешно преодолела линию финиша (прямую x=Lx=L) и сделала это не позже любой другой модели. Возможно, что побеждает сразу несколько моделей, в этом случае, как говорится, «побеждает дружба». Возможно также, что ни одна модель по тем или иным причинам не сумеет преодолеть линию финиша.

        Заметим отдельно, что если модель проходит через какую-либо из точек (L,0)(L,0) или (L,W)(L,W), то считается, что она врезается в борт. Если две или более модели сталкиваются непосредственно на линии финиша, то они не считаются финишировавшими.

        Поведение большинства моделей довольно предсказуемо, поэтому часто можно предугадать ход состязания. В данной задаче мы будем приближенно считать, что все модели начинают двигаться из своих начальных точек с заданными, постоянными на протяжении соревнования, векторами скорости.
        Формат ввода

        В первой строке вводятся три числа NN, LL, WW. ( 1≤N≤10001≤N≤1000, 1≤L≤1041≤L≤104, 2≤W≤1042≤W≤104).

        В последующих NN строках вводятся описания соревнующихся моделей: по 4 целых числа xixi​, yiyi​, vxivxi​, vyivyi​, 1≤i≤N1≤i≤N. ( xi,yixi​,yi​) — это координаты стартовой точки модели с номером ii, ( vxi,vyivxi​,vyi​) — вектор скорости этой модели.

        Гарантируется, что стартовые точки всех моделей различны, находятся на трассе, и не располагаются ни на каком-либо борту трассы, ни на линии финиша. Координаты векторов скорости не превышают 104104 по абсолютному значению.
        Формат вывода

        В первой строке выведите количество победителей. Во второй строке выведите их номера в порядке возрастания.
        Пример 1
        Ввод
        Вывод

        1 1 2
        0 1 1 0



        1
        1

        Пример 2
        Ввод
        Вывод

        2 10 3
        0 1 2 0
        5 2 1 0



        2
        1 2

     */

    static final double DELTA = 1e-9;
    static final double INF = 1e18;

    static class Car {
        int id;
        double x, y, vx, vy;

        Car(int id, double x, double y, double vx, double vy) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }
    }

    static class Event implements Comparable<Event> {
        double time;
        int i, j;

        Event(double time, int i, int j) {
            this.time = time;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Event other) {
            if (Math.abs(time - other.time) < DELTA) return 0;
            return time < other.time ? -1 : 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        double L = Double.parseDouble(parts[1]);
        double W = Double.parseDouble(parts[2]);

        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            parts = reader.readLine().split(" ");
            double x = Integer.parseInt(parts[0]);
            double y = Integer.parseInt(parts[1]);
            double vx = Integer.parseInt(parts[2]);
            double vy = Integer.parseInt(parts[3]);
            cars[i] = new Car(i + 1, x, y, vx, vy);
        }

        double[] finishTime = new double[n];
        boolean[] canFinish = new boolean[n];
        Arrays.fill(canFinish, true);

        for (int i = 0; i < n; i++) {
            Car car = cars[i];
            finishTime[i] = INF;

            if (Math.abs(car.vx) < DELTA) {
                canFinish[i] = false;
                continue;
            }

            double tFinish = (L - car.x) / car.vx;
            if (tFinish < -DELTA) {
                canFinish[i] = false;
                continue;
            }

            boolean hitsWall = false;
            if (Math.abs(car.vy) > DELTA) {
                if (car.vy > 0) {
                    double tTop = (W - car.y) / car.vy;
                    if (tTop >= -DELTA && tTop <= tFinish + DELTA) {
                        hitsWall = true;
                    }
                } else {
                    double tBottom = -car.y / car.vy;  // car.y + vy*t = 0 => t = -car.y/vy
                    if (tBottom >= -DELTA && tBottom <= tFinish + DELTA) {
                        hitsWall = true;
                    }
                }
            }

            double yAtFinish = car.y + car.vy * tFinish;
            if (yAtFinish <= DELTA || yAtFinish >= W - DELTA) {
                hitsWall = true;
            }

            if (hitsWall) {
                canFinish[i] = false;
            } else {
                finishTime[i] = tFinish;
            }
        }

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double t = collisionTime(cars[i], cars[j]);
                if (t < -DELTA) {
                    continue;
                }

                double maxRelevantTime = INF;
                if (canFinish[i]) {
                    maxRelevantTime = Math.min(maxRelevantTime, finishTime[i]);
                }
                if (canFinish[j]) {
                    maxRelevantTime = Math.min(maxRelevantTime, finishTime[j]);
                }

                if (t <= maxRelevantTime + DELTA) {
                    events.add(new Event(t, i, j));
                }
            }
        }

        Collections.sort(events);

        boolean[] eliminated = new boolean[n];
        int eventIndex = 0;

        while (eventIndex < events.size()) {
            double currentTime = events.get(eventIndex).time;

            Set<Integer> involved = new HashSet<>();
            int j = eventIndex;
            while (j < events.size() && Math.abs(events.get(j).time - currentTime) < DELTA) {
                Event e = events.get(j);
                if (!eliminated[e.i] && !eliminated[e.j]) {
                    involved.add(e.i);
                    involved.add(e.j);
                }
                j++;
            }

            for (int carIdx : involved) {
                eliminated[carIdx] = true;
            }

            eventIndex = j;
        }

        double minTime = INF;
        List<Integer> winners = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (canFinish[i] && !eliminated[i]) {
                if (finishTime[i] < minTime - DELTA) {
                    minTime = finishTime[i];
                    winners.clear();
                }
                if (Math.abs(finishTime[i] - minTime) < DELTA) {
                    winners.add(cars[i].id);
                }
            }
        }

        Collections.sort(winners);

        writer.write(String.valueOf(winners.size()));
        writer.newLine();

        if (!winners.isEmpty()) {
            for (int i = 0; i < winners.size(); i++) {
                if (i > 0) writer.write(" ");
                writer.write(String.valueOf(winners.get(i)));
            }
            writer.newLine();
        }

        writer.flush();
    }

    private static double collisionTime(Car a, Car b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double dvx = a.vx - b.vx;
        double dvy = a.vy - b.vy;

        if (Math.abs(dvx) < DELTA && Math.abs(dvy) < DELTA) {
            if (Math.abs(dx) < DELTA && Math.abs(dy) < DELTA) {
                return 0.0;
            }
            return -1.0;
        }

        if (Math.abs(dvx) < DELTA) {
            if (Math.abs(dx) > DELTA) {
                return -1.0;
            }
            if (Math.abs(dvy) < DELTA) {
                return -1.0;
            }
            double t = -dy / dvy;

            return (t >= -DELTA) ? Math.max(0, t) : -1.0;
        }

        if (Math.abs(dvy) < DELTA) {
            if (Math.abs(dy) > DELTA) {
                return -1.0;
            }
            double t = -dx / dvx;

            return (t >= -DELTA) ? Math.max(0, t) : -1.0;
        }

        double t1 = -dx / dvx;
        double t2 = -dy / dvy;

        if (Math.abs(t1 - t2) < DELTA && t1 >= -DELTA) {
            return Math.max(0, t1);
        }

        return -1.0;
    }
}
