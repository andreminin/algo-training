package training.leetcode.algorithm;

public class Solution3100_WaterBottles2 {
    /*
      3100. Water Bottles II

        You are given two integers numBottles and numExchange.
        numBottles represents the number of full water bottles that you initially have. In one operation, you
        can perform one of the following operations:

            Drink any number of full water bottles turning them into empty bottles.
            Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.

        Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange. For example,
         if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
        Return the maximum number of water bottles you can drink.
     */

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int count;
        for(count = numBottles; numExchange <= numBottles; ) {
            numBottles -= numExchange;
            count++;
            numBottles++;
            numExchange++;
        }

        // same logic using while
         /*
        int count = numBottles;

        while (numExchange <= numBottles) {
            numBottles -= numExchange;
            count++;
            numBottles++;
            numExchange++;
        } */

        return count;
    }

    public static void main(String[] args) {
        Solution3100_WaterBottles2 solution = new Solution3100_WaterBottles2();

        System.out.println(solution.maxBottlesDrunk(13, 6));
        System.out.println(solution.maxBottlesDrunk(10, 3));
    }
}
