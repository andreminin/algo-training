package training.leetcode.algorithm;

public class Solution1518_WaterBottles {
    /*
        1518. Water Bottles

        There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water
         bottles from the market with one full water bottle.

        The operation of drinking a full water bottle turns it into an empty bottle.

        Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
     */

    public int numWaterBottles(int numBottles, int numExchange) {
        int count = numBottles;

        while(numBottles >= numExchange) {
            int remain = numBottles % numExchange;
            int exchanged = numBottles / numExchange;

            numBottles = remain + exchanged;

            count += exchanged;
        }

        return count;
    }
}
