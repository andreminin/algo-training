package training.sandbox;




public class UniversalBinarySearch {

    // Интерфейс для условия
    @FunctionalInterface
    public interface isGood {
        boolean test(int value, int target);
    }

    // Универсальный поиск границы
    public static int findBoundary(int[] arr, int target, isGood condition) {
        int left = 0;
        int right = arr.length;

        while (right - left > 1) {
            int mid = left + (right - left) / 2;

            if (condition.test(arr[mid], target)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;  // Граница между good и !good
    }

    // Примеры использования
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int target = 6;

        // 1. Поиск последнего элемента <= target (ваш случай)
        int lastLeq = findBoundary(arr, target, (v, t) -> v <= t);
        System.out.println("Последний <= " + target + ": " +
                (lastLeq >= 0 ? arr[lastLeq] : "не найден"));

        // 2. Поиск последнего элемента < target
        int lastLess = findBoundary(arr, target, (v, t) -> v < t);
        System.out.println("Последний < " + target + ": " + arr[lastLess]);

        // 3. Проверка на точное совпадение
        if (lastLeq >= 0 && arr[lastLeq] == target) {
            System.out.println("Точное совпадение найдено на позиции: " + lastLeq);
        }
    }
}