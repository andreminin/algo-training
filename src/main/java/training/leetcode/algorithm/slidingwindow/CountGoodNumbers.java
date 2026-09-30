package training.leetcode.algorithm.slidingwindow;

public class CountGoodNumbers {

    private static final int MOD = 1000000007;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long part1 = fastExponentiation(5, evenCount);
        long part2 = fastExponentiation(4, oddCount);

        return (int) ((part1 * part2) % MOD);
    }

    private long fastExponentiation(long base, long exponent) {
        long result = 1;
        base %= MOD;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CountGoodNumbers solution = new CountGoodNumbers();

        long result = solution.countGoodNumbers(50);

        long duration = System.currentTimeMillis() - start;

        assert 564908303L == result;

        System.out.println("Result: "+result +", duration: "+duration);
    }

}
