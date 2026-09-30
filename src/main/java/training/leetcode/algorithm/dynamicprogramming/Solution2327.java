package training.leetcode.algorithm.dynamicprogramming;

public class Solution2327 {
    final static int MOD = 1000000007;
    /*
        On day 1, one person discovers a secret.

        You are given an integer delay, which means that each person will share the secret with a new person every day,
        starting from delay days after discovering the secret. You are also given an integer forget, which means that each
        person will forget the secret forget days after discovering it. A person cannot share the secret on the same day
        they forgot it, or on any day afterwards.

        Given an integer n, return the number of people who know the secret at the end of day n.
       Since the answer may be very large, return it modulo 109 + 7.
     */

    // Prefix sum
    public int peopleAwareOfSecret2(int n, int delay, int forget) {

        long[] peoplePerDay = new long[n + 1];
        long[] modPrefixSum = new long[n + 1];

        peoplePerDay[1] = 1;
        modPrefixSum[1] = 1;

        for (int day = 2; day <= n; day++) {
            int start = Math.max(1, day - forget + 1);
            int end = Math.max(0, day - delay);

            if (end >= start) {
                long canShareToday = modPrefixSum[end] - modPrefixSum[start - 1];
                //handle negative value - add MOD and divide by MOD
                canShareToday = (canShareToday % MOD + MOD) % MOD;

                peoplePerDay[day] = canShareToday;
            }

            modPrefixSum[day] = (modPrefixSum[day - 1] + peoplePerDay[day]) % MOD;
        }

        long total = 0;
        for (int day = Math.max(1, n - forget + 1); day <= n; day++) {
            total = (total + peoplePerDay[day]) % MOD;
        }

        return (int) total;
    }

    //Sliding window
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n + 1];
        dp[1] = 1; // day 1, one person knows

        long share = 0; // number of people currently able to share

        for (int day = 2; day <= n; day++) {
            // people start sharing today
            if (day - delay >= 1) {
                share = (share + dp[day - delay]) % MOD;
            }
            // people forget today
            if (day - forget >= 1) {
                share = (share - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = share; // new people who learn today
        }

        long ans = 0;
        // sum people who still remember on day n
        for (int day = n - forget + 1; day <= n; day++) {
            if (day >= 1) {
                ans = (ans + dp[day]) % MOD;
            }
        }
        return (int) ans;
    }
}
