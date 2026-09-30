package training.yandex.contest2025.part12;

import java.io.*;
import java.util.*;

public class SolutionB {
    /*
      Мама отправила Васю купить продукты в супермаркете и забрать посылку из пункта выдачи

        Дорога от дома до супермаркета имеет длину aa метров, от дома до пункта выдачи — bb метров, а дорога от супермаркета до пункта выдачи — cc метров. Вася может ходить по этим дорогам в любом направлении.

        Скорость Васи зависит от того, несет ли он что-нибудь или идёт без груза. Если он не несет ни продукты, ни посылку, то его скорость равна v0v0​ метров в минуту. Если он несет что-то одно, то его скорость равна v1v1​ метров в минуту ( v1≤v0v1​≤v0​). Если он несет и продукты, и посылку, его скорость равна v2v2​ метров в минуту ( v2≤v1v2​≤v1​).

        Вася может мгновенно купить продукты, получить посылку или занести имеющийся у него груз домой.

        Определите минимальное время, за которое Вася может сходить за продуктами и посылкой и вернуться домой.
        Формат ввода

        В единственной строке ввода записаны шесть целых чисел aa, bb, cc, v0v0​, v1v1​, v2v2​ — длина дороги от дома до супермаркета, длина дороги от дома до пункта выдачи, длина дороги от супермаркета до пункта выдачи, скорость Васи без груза, скорость Васи с продуктами либо посылкой, скорость Васи с продуктами и посылкой ( 1≤a,b,c≤1001≤a,b,c≤100; 1≤v2≤v1≤v0≤1001≤v2​≤v1​≤v0​≤100).
        Формат вывода

        В единственной строке выведите одно вещественное число — минимальное количество минут, которое потребуется Васе, чтобы сходить за продуктами и посылкой и вернуться домой. Абсолютная или относительная погрешность ответа не должна превышает 10−410−4.
        Пример 1
        Ввод
        Вывод

        1 2 2 10 10 10



        0.500000000000000

        Пример 2
        Ввод
        Вывод

        4 1 2 5 5 5



        1.200000000000000

        Пример 3
        Ввод
        Вывод

        2 3 4 7 6 5



        1.495238095238095

        Пример 4
        Ввод
        Вывод

        1 6 3 7 6 5



        1.271428571428571

        Пример 5
        Ввод
        Вывод

        2 3 4 10 9 2



        1.055555555555556

        Примечания

        В первом тесте Вася может действовать следующим образом: дойти до супермаркета, купить продукты, дойти до пункта выдачи, забрать посылку, вернуться домой. Суммарное время будет равно av0+cv1+bv2=110+210+210=510v0​a​+v1​c​+v2​b​=101​+102​+102​=105​.

        Во втором тесте Вася может действовать следующим образом: дойти до пункта выдачи, забрать посылку, дойти до супермаркета, купить продукты, дойти до пункта выдачи, вернуться домой. Суммарное время будет равно bv0+cv1+cv2+bv2=65v0​b​+v1​c​+v2​c​+v2​b​=56​.

        В третьем тесте Вася может действовать следующим образом: дойти до пункта выдачи, забрать посылку, дойти до супермаркета, купить продукты, вернуться домой. Суммарное время будет равно bv0+cv1+av2=37+46+25=314210v0​b​+v1​c​+v2​a​=73​+64​+52​=210314​.

        В четвертом тесте Вася может действовать следующим образом: дойти до магазина, дойти до почты, забрать посылку, дойти до магазина, купить продукты, вернуться домой. Суммарное время будет равно av0+cv0+cv1+av2=17+37+36+15=267210v0​a​+v0​c​+v1​c​+v2​a​=71​+73​+63​+51​=210267​.

        В пятом тесте Вася может действовать следующим образом: дойти до почты, забрать посылку, вернуться домой, оставить посылку, дойти до магазина, купить продукты, вернуться домой. Суммарное время будет равно bv0+bv1+av0+av1=310+39+210+29=9590v0​b​+v1​b​+v0​a​+v1​a​=103​+93​+102​+92​=9095​.
     */

    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = reader.readLine().split(" ");
        int home_market = Integer.parseInt(inputs[0]);
        int home_postal = Integer.parseInt(inputs[1]);
        int market_postal = Integer.parseInt(inputs[2]);
        double v0 = Double.parseDouble(inputs[3]);
        double v1 = Double.parseDouble(inputs[4]);
        double v2 = Double.parseDouble(inputs[5]);

        List<Double> strategies = new ArrayList<>();

        // 1. Home -> Supermarket(1) -> Pickup(2) -> Home
        strategies.add(home_market / v0 + market_postal / v1 + home_postal / v2);
        // 2. Home -> Supermarket(1) -> Home -> Pickup(1) -> Home
        //    Home -> Pickup(1) -> Home -> Supermarket(1) -> Home
        strategies.add(home_market / v0 + home_market / v1 + home_postal / v0 + home_postal / v1);
        // 3. Home -> Supermarket(1) -> Home -> Supermarket(0) -> Pickup(1) -> Home
        strategies.add(home_market / v0 + home_market / v1 + home_market / v0 + market_postal / v0 + home_postal / v1);
        // 4. Home -> Supermarket(1) -> Home -> Supermarket(0) -> Pickup(1) -> Supermarket(1) -> Home
        strategies.add(home_market / v0 + home_market / v1 + home_market / v0 + market_postal / v0 + market_postal / v1 + home_market / v1);
        // 5. Home -> Supermarket(0) -> Pickup(1) -> Supermarket(2) -> Home
        strategies.add(home_market / v0 + market_postal / v0 + market_postal / v2 + home_market / v2);

        // 6. Home -> Pickup(1) -> Supermarket(2) -> Home
        strategies.add(home_postal / v0 + market_postal / v1 + home_market / v2);
        // 7. Home -> Pickup(1) -> Home -> Pickup(0) -> Supermarket(1) -> Home
        strategies.add(home_postal / v0 + home_postal / v1 + home_postal / v0 + market_postal / v0 + home_market / v1);
        // 8. Home -> Pickup(1) -> Home -> Pickup(0) -> Supermarket(1) -> Pickup(1) -> Home
        strategies.add(home_postal / v0 + home_postal / v1 + home_postal / v0 + market_postal / v0 + market_postal / v1 + home_postal / v1);
        // 9. Home -> Pickup(0) -> Supermarket(1) -> Pickup(2) -> Home
        strategies.add(market_postal / v0 + market_postal / v0 + market_postal / v2 + home_postal / v2);

        double min = Collections.min(strategies);

        writer.write(String.format("%.6f", min));
        writer.newLine();

        reader.close();
        writer.close();
    }

    private static final double EPS = 1e-9;

    static int stateIndex(int loc, int prod, int parc) {
        return loc * 9 + prod * 3 + parc;
    }

    static double edgeLength(int u, int v, double a, double b, double c) {
        if ((u == 0 && v == 1) || (u == 1 && v == 0)) return a;
        if ((u == 0 && v == 2) || (u == 2 && v == 0)) return b;
        return c;
    }

    static class Node implements Comparable<Node> {
        int idx;
        double d;

        Node(int idx, double d) {
            this.idx = idx;
            this.d = d;
        }

        public int compareTo(Node o) {
            return Double.compare(this.d, o.d);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");

        double home_market = Double.parseDouble(inputs[0]);
        double home_postal = Double.parseDouble(inputs[1]);
        double market_postal = Double.parseDouble(inputs[2]);
        double v0 = Double.parseDouble(inputs[3]);
        double v1 = Double.parseDouble(inputs[4]);
        double v2 = Double.parseDouble(inputs[5]);

        // state index: idx = location*9 + product*3 + parcel
        // loc: 0=home, 1=market, 2=postal
        // prod: 0=market,1=carried,2=delivered
        // parc: 0=postal,1=carried,2=delivered
        final int N = 27;
        double[] dist = new double[N];
        Arrays.fill(dist, Double.MAX_VALUE);

        int startIdx = stateIndex(0, 0, 0);
        int goalIdx = stateIndex(0, 2, 2); // at home, both delivered

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[startIdx] = 0.0;
        pq.add(new Node(startIdx, 0.0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int idx = cur.idx;
            double dcur = cur.d;
            if (idx == goalIdx) {
                // Found solution
                break;
            }
            if (dcur > dist[idx] + EPS) continue;

            // Process zero-time actions using BFS
            boolean[] seenLocal = new boolean[N];
            Deque<Integer> dq = new ArrayDeque<>();
            seenLocal[idx] = true;
            dq.add(idx);

            while (!dq.isEmpty()) {
                int s = dq.removeFirst();
                int sLoc = s / 9;
                int sRem = s % 9;
                int sProd = sRem / 3;
                int sParc = sRem % 3;

                // Zero-time actions from state s

                // Pick up product
                if (sLoc == 1 && sProd == 0) {
                    int nxt = stateIndex(sLoc, 1, sParc);
                    if (!seenLocal[nxt]) {
                        seenLocal[nxt] = true;
                        dq.addLast(nxt);
                        if (dist[nxt] > dcur - EPS) {
                            dist[nxt] = dcur;
                            pq.add(new Node(nxt, dcur));
                        }
                    }
                }

                // Pick up parcel
                if (sLoc == 2 && sParc == 0) {
                    int nxt = stateIndex(sLoc, sProd, 1);
                    if (!seenLocal[nxt]) {
                        seenLocal[nxt] = true;
                        dq.addLast(nxt);
                        if (dist[nxt] > dcur - EPS) {
                            dist[nxt] = dcur;
                            pq.add(new Node(nxt, dcur));
                        }
                    }
                }

                // Deliver product
                if (sLoc == 0 && sProd == 1) {
                    int nxt = stateIndex(sLoc, 2, sParc);
                    if (!seenLocal[nxt]) {
                        seenLocal[nxt] = true;
                        dq.addLast(nxt);
                        if (dist[nxt] > dcur - EPS) {
                            dist[nxt] = dcur;
                            pq.add(new Node(nxt, dcur));
                        }
                    }
                }

                // Deliver parcel
                if (sLoc == 0 && sParc == 1) {
                    int nxt = stateIndex(sLoc, sProd, 2);
                    if (!seenLocal[nxt]) {
                        seenLocal[nxt] = true;
                        dq.addLast(nxt);
                        if (dist[nxt] > dcur - EPS) {
                            dist[nxt] = dcur;
                            pq.add(new Node(nxt, dcur));
                        }
                    }
                }
            }

            // movement actions from all states
            for (int s = 0; s < N; s++) {
                if (!seenLocal[s]) continue;

                int sLoc = s / 9;
                int sRem = s % 9;
                int sProd = sRem / 3;
                int sParc = sRem % 3;

                // carry count and speed
                int carrying = 0;
                if (sProd == 1) carrying++;
                if (sParc == 1) carrying++;
                double speed = (carrying == 0 ? v0 : (carrying == 1 ? v1 : v2));

                // move to other locations
                for (int tLoc = 0; tLoc < 3; tLoc++) {
                    if (tLoc == sLoc) continue;
                    double len = edgeLength(sLoc, tLoc, home_market, home_postal, market_postal);
                    double tcost = len / speed;
                    int tIdx = stateIndex(tLoc, sProd, sParc);
                    double nd = dcur + tcost;
                    if (nd < dist[tIdx] - EPS) {
                        dist[tIdx] = nd;
                        pq.add(new Node(tIdx, nd));
                    }
                }
            }
        }

        double ans = dist[goalIdx];

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(String.format("%.6f", ans));
        writer.newLine();

        reader.close();
        writer.close();
    }




}
