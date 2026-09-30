package training.yandex.contest2025.part56;

import java.io.*;

public class SolutionC {

   /*
    Ивану с детства нравились газеты. У него даже была мечта стать главным редактором газеты. Однажды ему представился шанс осуществить свою мечту. Чтобы устроиться на работу в издательство, ему необходимо выполнить тестовое задание — сверстать рекламное объявление.

Задано поле шириной WW и высотой HH. Объявление должно состоять из одной или нескольких строк, в которых необходимо разместить в заданном порядке NN слов. Про ii-е слово известно, что при печати в стандартном масштабе оно занимает прямоугольник шириной aiai​ и высотой bibi​.

Чтобы объявление выглядело красиво, все слова в нем должны быть напечатаны в одном масштабе. При печати в масштабе kk размеры всех слов умножаются на kk. Если исходно слово занимало прямоугольник ai×biai​×bi​, то при печати в масштабе kk оно занимает прямоугольник размером (k⋅ai)×(k⋅bi)(k⋅ai​)×(k⋅bi​). Кроме того, если в строке более одного слова, то все слова в ней должны иметь одинаковую высоту. Разумеется, ни одно слово не должно выходить за границы поля.

На рисунке приведен пример красивого объявления с тремя словами.

image

Помогите Ивану найти максимальный масштаб, при котором можно сверстать объявление, которое удовлетворяет этим критериям. Обратите внимание, что менять порядок слов нельзя, они должны читаться по строкам сверху вниз, слева направо в том порядке, в котором заданы.
Формат ввода

В первой строке входного файла дано три числа: NN, WW и HH ( 1≤N≤1000001≤N≤100000, 1≤W,H≤1091≤W,H≤109) — число слов в объявлении, длина и высота объявления. В следующих NN строках дано по два целых числа, в ii-й из них заданы aiai​ и bibi​ ( 1≤ai,bi≤1091≤ai​,bi​≤109) — ширина и высота ii-го слова.
Формат вывода

Выведите одно вещественное число kk — максимальный масштаб. Ответ требуется вывести с относительной погрешностью не более 10−610−6. Это значит, что если правильный ответ aa, а вы вывели pp, то ваш ответ будет засчитан как правильный, если ∣a−p∣max(∣a∣,1)≤10−6max(∣a∣,1)∣a−p∣​≤10−6.
Пример 1
Ввод
Вывод

3 10 7
4 3
3 2
4 2



1.400000000000199973

Пример 2
Ввод
Вывод

2 10 1
2 1
3 2

    */

    private final static double MIN_DELTA = 1e-9;
    private final static double MAX_SIZE = 2e9;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");

            int n = Integer.parseInt(parts[0]);
            int W = Integer.parseInt(parts[1]);
            int H = Integer.parseInt(parts[2]);
            int[] a = new int[n];
            int[] b = new int[n];

            for (int i = 0; i < n; i++) {
                parts = reader.readLine().split(" ");
                a[i] = Integer.parseInt(parts[0]);
                b[i] = Integer.parseInt(parts[1]);
            }

            double low = 0.0;
            double high = MAX_SIZE;

            for (int iter = 0; iter < 100; iter++) {
                double mid = (low + high) / 2.0;
                if (isValidLayout(mid, n, W, H, a, b)) {
                    low = mid;
                } else {
                    high = mid;
                }
            }

            writer.write(String.format("%.8f", low));
            writer.newLine();
        }
    }

    public static boolean isValidLayout(double k, int n, double width, double height, int[] wordWidths, int[] wordHeights) {
        for (int i = 0; i < n; i++) {
            if (k * wordWidths[i] > width + MIN_DELTA || k * wordHeights[i] > height + MIN_DELTA) {

                return false;
            }
        }

        double totalHeight = 0;
        double currentLineWidth = 0;
        double currentLineHeight = -1;

        for (int i = 0; i < n; i++) {
            double w = k * wordWidths[i];
            double currentHeight = wordHeights[i];

            if (currentLineWidth == 0.0) {
                currentLineWidth = w;
                currentLineHeight = currentHeight;
            } else {
                if (Math.abs(currentHeight - currentLineHeight) < MIN_DELTA && currentLineWidth + w <= width + MIN_DELTA) {
                    currentLineWidth += w;
                } else {
                    totalHeight += k * currentLineHeight;

                    if (totalHeight > height + MIN_DELTA) {

                        return false;
                    }

                    currentLineWidth = w;
                    currentLineHeight = currentHeight;
                }
            }
        }

        totalHeight += k * currentLineHeight;

        return totalHeight <= height + MIN_DELTA;
    }
}
