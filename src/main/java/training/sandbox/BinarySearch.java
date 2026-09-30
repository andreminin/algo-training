package training.sandbox;

public class BinarySearch {

    public static boolean good(int value, int target) {
        return value <= target;
    }

    public static int search(int[] values, int target) {
        int l = 0;
        int r = values.length;
        int m;

        while ((r - l) > 1) {
            m = (l +r) / 2;

            if(good(values[m], target)) {
                l = m;
            } else {
                r = m;
            }
        }

        return values[l] == target ? l : -1;
    }


    public static void main(String[] args) {
        System.out.println(search(new int[] {1,2,3,4,5,6,7,8,9,10}, 5));
        System.out.println(search(new int[] {0,1,2}, 0));
        System.out.println(search(new int[] {0,1,2}, 1));
        System.out.println(search(new int[] {0,1,2}, 2));
    }

}
