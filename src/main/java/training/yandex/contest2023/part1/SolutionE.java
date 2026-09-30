package training.yandex.contest2023.part1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionE {
    /*
        Поразрядная сортировка является одним из видов сортировки, которые работают практически за линейное от размера сортируемого массива время. Такая скорость достигается за счет того, что эта сортировка использует внутреннюю структуру сортируемых объектов. Изначально этот алгоритм использовался для сортировки перфокарт. Первая его компьютерная реализация была создана в университете MIT Гарольдом Сьюардом (Harold Н. Seward). Опишем алгоритм подробнее. Пусть задан массив строк s1s1​ , ..., sisi​ причём все строки имеют одинаковую длину m. Работа алгоритма состоит из m фаз. На i -ой фазе строки сортируются па i -ой с конца букве. Происходит это следующим образом. Будем, для простоты, в этой задаче рассматривать строки из цифр от 0 до 9. Для каждой цифры создается «корзина» («bucket»), после чего строки sisi​ распределяются по «корзинам» в соответствии с i-ой цифрой с конца. Строки, у которых i-ая с конца цифра равна j попадают в j-ую корзину (например, строка 123 на первой фазе попадет в третью корзину, на второй — во вторую, на третьей — в первую). После этого элементы извлекаются из корзин в порядке увеличения номера корзины. Таким образом, после первой фазы строки отсортированы по последней цифре, после двух фаз — по двум последним, ..., после m фаз — по всем. При важно, чтобы элементы в корзинах сохраняли тот же порядок, что и в исходном массиве (до начала этой фазы). Например, если массив до первой фазы имеет вид: 111, 112, 211, 311, то элементы по корзинам распределятся следующим образом: в первой корзине будет. 111, 211, 311, а второй: 112. Напишите программу, детально показывающую работу этого алгоритма на заданном массиве.
        Формат ввода

        Первая строка входного файла содержит целое число n (1 ≤ n ≤ 1000) . Последующие n строк содержат каждая по одной строке sisi​ . Длины всех sisi​ , одинаковы и не превосходят 20. Все sisi​ состоят только из цифр от 0 до 9.
        Формат вывода

        В выходной файл выведите исходный массив строк в, состояние «корзин» после распределения элементов по ним для каждой фазы и отсортированный массив. Следуйте формату, приведенному в примере.
        Пример
        Ввод
        Вывод

        9
        12
        32
        45
        67
        98
        29
        61
        35
        09



        Initial array:
        12, 32, 45, 67, 98, 29, 61, 35, 09
        **********
        Phase 1
        Bucket 0: empty
        Bucket 1: 61
        Bucket 2: 12, 32
        Bucket 3: empty
        Bucket 4: empty
        Bucket 5: 45, 35
        Bucket 6: empty
        Bucket 7: 67
        Bucket 8: 98
        Bucket 9: 29, 09
        **********
        Phase 2
        Bucket 0: 09
        Bucket 1: 12
        Bucket 2: 29
        Bucket 3: 32, 35
        Bucket 4: 45
        Bucket 5: empty
        Bucket 6: 61, 67
        Bucket 7: empty
        Bucket 8: empty
        Bucket 9: 98
        **********
        Sorted array:
        09, 12, 29, 32, 35, 45, 61, 67, 98

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int count = Integer.parseInt(reader.readLine());

            List<String> numbers = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                numbers.add(reader.readLine());
            }

            int phases = numbers.get(0).length();

            writer.write("Initial array:");
            writer.newLine();
            writer.write(String.join(", ", numbers));
            writer.newLine();
            writer.write("**********");
            writer.newLine();

            List<List<String>> buckets = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                buckets.add(new ArrayList<>());
            }

            for (int phase = 1; phase <= phases; phase++) {
                for (List<String> bucket : buckets) {
                    bucket.clear();
                }

                for (String number : numbers) {
                    int digitIndex = number.length() - phase;
                    int digit = number.charAt(digitIndex) - '0';
                    buckets.get(digit).add(number);
                }

                writer.write("Phase " + phase);
                writer.newLine();

                for (int i = 0; i < 10; i++) {
                    writer.write("Bucket " + i + ": ");
                    List<String> bucket = buckets.get(i);
                    if (bucket.isEmpty()) {
                        writer.write("empty");
                    } else {
                        writer.write(String.join(", ", bucket));
                    }
                    writer.newLine();
                }
                writer.write("**********");
                writer.newLine();

                numbers.clear();
                for (List<String> bucket : buckets) {
                    numbers.addAll(bucket);
                }
            }

            writer.write("Sorted array:");
            writer.newLine();
            writer.write(String.join(", ", numbers));
            writer.newLine();
        }
    }
}
