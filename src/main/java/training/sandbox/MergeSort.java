package training.sandbox;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MergeSort {


    public class ImprovedMergeSort {

        public static void merge(int[] nums, int fromA, int toA, int fromB, int toB, int[] buffer) {
            int i = fromA;
            int j = fromB;
            int k = 0;

            // Более читаемое слияние двух отсортированных массивов
            while (i < toA && j < toB) {
                if (nums[i] <= nums[j]) {
                    buffer[k++] = nums[i++];
                } else {
                    buffer[k++] = nums[j++];
                }
            }

            // Копируем оставшиеся элементы (если есть)
            while (i < toA) {
                buffer[k++] = nums[i++];
            }
            while (j < toB) {
                buffer[k++] = nums[j++];
            }
        }



        // to - exclusive
        public static void sort(int[] nums, int from, int to, int[] buffer) {
            int length = to - from;

            if (length <= 1) {
                return;
            }

            // Убрана специальная обработка для length == 2 - она избыточна
            int middle = from + (to - from) / 2;

            // Сортируем обе половины
            sort(nums, from, middle, buffer);
            sort(nums, middle, to, buffer);

            // Сливаем результаты
            merge(nums, from, middle, middle, to, buffer);

            // Копируем из буфера обратно в исходный массив
            System.arraycopy(buffer, 0, nums, from, length);
        }

        public static void optimizedSort(int[] nums, int from, int to, int[] buffer) {
            int length = to - from;

            if (length <= 1) {
                return;
            }

            int middle = from + (to - from) / 2;

            // Рекурсивно сортируем обе половины
            optimizedSort(nums, from, middle, buffer);
            optimizedSort(nums, middle, to, buffer);

            // Если массив уже отсортирован, пропускаем слияние
            if (nums[middle - 1] <= nums[middle]) {
                return;
            }

            // Сливаем в буфер
            merge(nums, from, middle, middle, to, buffer);

            // Копируем обратно
            System.arraycopy(buffer, 0, nums, from, length);
        }

        // Более безопасная публичная API
        public static int[] sortArray(int[] nums) {
            if (nums == null) return null;
            if (nums.length <= 1) return nums.clone();

            int[] result = nums.clone(); // Работаем с копией
            int[] buffer = new int[result.length];

            optimizedSort(result, 0, result.length, buffer);
            return result;
        }

        public static int[] sort(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return nums != null ? nums.clone() : null; // Всегда возвращаем копию для consistency
            }

            int[] buffer = new int[nums.length];
            sort(nums, 0, nums.length, buffer);

            return buffer;
        }

        public static void test() {
            // Тестирование различных случаев
            testCase(new int[0], "Пустой массив");
            testCase(new int[]{1}, "Массив из одного элемента");
            testCase(new int[]{1, 5, 2, 4, 3}, "Нечетный размер");
            testCase(new int[]{1, 5, 12, 4, 32}, "С большими числами");
            testCase(new int[]{5, 4, 3, 2, 1}, "Обратный порядок");
            testCase(new int[]{1, 2, 3, 4, 5}, "Уже отсортирован");
            testCase(new int[]{3, 3, 3, 3}, "Одинаковые элементы");
        }

        private static void testCase(int[] input, String description) {
            System.out.println(description + ":");
            System.out.println("  Вход:  " + Arrays.toString(input));
            int[] result = sortArray(input);
            System.out.println("  Выход: " + Arrays.toString(result));
            System.out.println("  Исходный не изменен: " +
                    (input.length == 0 || input[0] != (result.length > 0 ? result[0] : -1)));
            System.out.println();
        }

        public static void main(String[] args) throws IOException {
            // Запуск тестов
            test();

            // Чтение из консоли (опционально)
            if (args.length > 0 && args[0].equals("--console")) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

                    int n = Integer.parseInt(reader.readLine());
                    int[] nums = new int[n];
                    String[] parts = reader.readLine().split(" ");
                    for (int i = 0; i < n; i++) {
                        nums[i] = Integer.parseInt(parts[i]);
                    }

                    int[] sorted = sortArray(nums);

                    String joined = Arrays.stream(sorted)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" "));
                    writer.write(joined);
                    writer.newLine();
                }
            }
        }
    }
}
