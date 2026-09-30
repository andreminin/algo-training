package training.sandbox;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static Random RANDOM = new Random();


    public static void quicksort(int[] numbers) {
        quicksort(numbers, 0, numbers.length - 1);
    }

    public static void quicksort(int[] numbers, int from, int to) {
        if (from >= to) return;

        int pivotIndex = partition(numbers, from, to);

        quicksort(numbers, from, pivotIndex - 1);
        quicksort(numbers, pivotIndex + 1, to);
    }

    public static int partition(int[] numbers, int from, int to) {
        int pivotIndex = from + RANDOM.nextInt(to - from + 1);
        int pivot = numbers[pivotIndex];

        // Move pivot to end
        swap(numbers, pivotIndex, to);

        int left = from;
        for (int i = from; i < to; i++) {
            if (numbers[i] <= pivot) {
                swap(numbers, left, i);
                left++;
            }
        }

        // Move pivot to final position
        swap(numbers, left, to);
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        quicksort(input);
        System.out.println("  Выход: " + Arrays.toString(input));
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        // Запуск тестов
        test();
    }
}
