package training.yandex.contest2023.part1;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SolutionD {
    /*
        Реализуйте сортировку слиянием, используя алгоритм из предыдущей задачи.

        На каждом шаге делите массив на две части, сортируйте их независимо и сливайте с помощью уже реализованной функции.
        Формат ввода

        В первой строке входного файла содержится число NN — количество элементов массива ( 0≤N≤1060≤N≤106).
        Во второй строке содержатся NN целых чисел aiai​, разделенных пробелами ( −109≤ai≤109−109≤ai​≤109).
        Формат вывода

        Выведите результат сортировки, то есть NN целых чисел, разделенных пробелами, в порядке неубывания.
        Пример
        Ввод
        Вывод

        5
        1 5 2 4 3



        1 2 3 4 5

     */

    // toA, toB exclusive
    public static int merge(int[] nums, int fromA, int toA, int fromB, int toB, int[] buffer) {
        int lengthA = toA - fromA;
        int lengthB = toB - fromB;
        int length = lengthA + lengthB;

        int offsetA = 0;
        int offsetB = 0;
        int min;

        for(int i = 0; i < length; i++) {
            if(offsetA < lengthA) {
                if(offsetB < lengthB) {
                    if(nums[fromA + offsetA] < nums[fromB + offsetB]) {
                        min = nums[fromA + offsetA];
                        offsetA++;
                    } else {
                        min = nums[fromB + offsetB];
                        offsetB++;
                    }
                } else {
                    min = nums[fromA + offsetA];
                    offsetA++;
                }
            } else if(offsetB < lengthB) {
                min = nums[fromB + offsetB];
                offsetB++;
            } else {
                throw new RuntimeException("Internal error! i="+i);
            }

            buffer[i] = min;
        }

        return length;
    }

    public static int[] sort(int[] nums) {
        if(nums == null || nums.length == 1) {
            return nums;
        }

        int[] buffer = new int[nums.length];
        sort(nums, 0, nums.length, buffer);

        return buffer;
    }

    // to - exclusive
    public static void sort(int[] nums, int from, int to,  int[] buffer) {
        int length = to - from;

        if(length <= 1) {
            return;
        }

        if(length == 2) {
            if(nums[from] > nums[from+1]) {
                int tmp = nums[from+1];
                nums[from+1] = nums[from];
                nums[from] = tmp;
            }
            return;
        }

        if(buffer == null) {
            buffer = new int[nums.length];
        }

        int middle = from + (to - from) / 2;
        sort(nums, from, middle, buffer);
        sort(nums, middle, to, buffer);
        merge(nums, from, middle, middle, to, buffer);

        System.arraycopy(buffer, 0, nums, from, length);
    }

    public static void test() {
        System.out.println(Arrays.stream(sort(new int[0])).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println(Arrays.stream(sort(new int[] {1, 5, 2, 4, 3})).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(Arrays.stream(sort(new int[] {1, 5, 12, 4, 32})).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int m = Integer.parseInt(reader.readLine());
            int[] nums = new int[m];
            String[] parts = reader.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                nums[i] = Integer.parseInt(parts[i]);
            }

            int[] buffer = new int[m];

            sort(nums, 0, nums.length, buffer);

            String joined = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            writer.write(joined);
            writer.newLine();
        }
    }
}
