package training.sandbox;

public class ArrayShift {

    public static boolean goodOffset(int[] values, int offset) {
        return values[offset] >= values[0];
    }

    public static int findOffset(int[] values) {
        int l = 0;
        int r = values.length - 1;
        int m;

        while ((r - l) > 1) {
            m = (l +r) / 2;

            if(goodOffset(values, m)) {
                l = m;
            } else {
                r = m;
            }
        }

        return r;
    }

    public static boolean good(int value, int target) {
        return value <= target;
    }

    public static int search(int[] values, int target) {
        int offset = findOffset(values);

        int length = values.length;
        int l = 0;
        int r = length;
        int m;

        while ((r - l) > 1) {
            m = (l +r) / 2;

            if(good(values[(m +offset) % length], target)) {
                l = m;
            } else {
                r = m;
            }
        }

        int realLeft = (l + offset) % length;

        return values[realLeft] == target ? realLeft : -1;
    }
    public static void main(String[] args) {
        System.out.println(search(new int[] {2,3,4,5,6,7,0}, 5));
        System.out.println(search(new int[] {4,5,6,7,0,1,2}, 5));
        System.out.println(search(new int[] {4,5,6,7,0,1,2}, 1));
    }
}
