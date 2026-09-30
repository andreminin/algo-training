package training.leetcode.algorithm.fastandslowpntrs;

public class Solution287 {

    /*
    The mathematics guarantees that regardless of the cycle length and the meeting point, the distance from the
     start to the entrance (F) is equal to the distance from the meeting point to the entrance (b) plus integer
      cycles. Therefore, moving both pointers at the same pace will make them meet exactly at the entrance.

    This is why the second loop reliably finds the duplicate number (the cycle start).
     */

    public int findDuplicate(int[] nums) {
        // Using Floyd cycle detection
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);


        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /*
    Key Insight: Why the Second Loop Finds the Cycle Start

    Define the Variables:

        Let F be the distance from the start to the cycle entrance (the duplicate number).

        Let C be the length of the cycle.

        Let a be the distance from the cycle entrance to the meeting point (inside the cycle).

        Let b be the distance from the meeting point back to the cycle entrance (so a + b = C).

    What Happens in Phase 1:

        The slow pointer moves one step at a time, while the fast pointer moves two steps.

        When they meet, the slow pointer has traveled F + a steps.

        The fast pointer has traveled 2*(F + a) steps. But since the fast pointer has also traveled F + a + n*C (because it has gone around the cycle n times), we have:
        2*(F + a) = F + a + n*C
        => F + a = n*C
        => F = n*C - a

    Rewriting F:

        Since a + b = C, we can write n*C - a = (n-1)*C + (C - a) = (n-1)*C + b.

        So, F = (n-1)*C + b.

    What This Means:

        This equation tells us that the distance from the start to the cycle entrance (F) is equal to b (the distance from the meeting point to the cycle entrance) plus some integer multiple of the cycle length ((n-1)*C).

    How the Second Loop Works:

        After phase 1, we reset the slow pointer to the start (so it is at distance 0 from the start).

        The fast pointer remains at the meeting point (which is at distance a from the cycle entrance).

        Now, we move both pointers one step at a time.

        The slow pointer will take F steps to reach the cycle entrance.

        The fast pointer, starting from the meeting point, will take b steps to reach the cycle entrance (because b is the distance from the meeting point to the entrance). But wait, we also have the (n-1)*C part? Actually, since the cycle has length C, moving b + (n-1)*C steps is equivalent to moving b steps (because going around the cycle n-1 times brings it back to the same point).

        Therefore, after b steps, the fast pointer will be at the cycle entrance.

        But from the equation, F = b + (n-1)*C, so after F steps, the slow pointer reaches the entrance, and the fast pointer has moved b + (n-1)*C = F steps from the meeting point, which also brings it to the entrance.

    They Meet at the Entrance:

        Thus, after exactly F steps, both pointers will meet at the cycle entrance, which is the duplicate number.
     */
}
