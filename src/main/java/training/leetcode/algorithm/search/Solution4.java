package training.leetcode.algorithm.search;

public class Solution4 {
    /*
        Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

        The overall run time complexity should be O(log (m+n)).



        Example 1:

        Input: nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation: merged array = [1,2,3] and median is 2.

        Example 2:

        Input: nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

        Explanation

        Initial Check: The code first ensures that the binary search is performed on the smaller array to optimize the search process.

        Binary Search Setup: The binary search is initialized with low and high pointers set to the start and end of the smaller array.

        Partition Calculation: For each midpoint in the binary search, the corresponding partition in the larger array is
        calculated such that the total elements in the left half of both arrays are approximately half of the combined length.

        Boundary Checks: The code checks the elements around the partitions in both arrays to ensure the left partition
         elements are less than or equal to the right partition elements. If not, the search continues by adjusting the pointers.

        Median Calculation: Once the correct partition is found, the median is calculated based on whether the combined length of the arrays is even or odd.
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int length1 = nums1.length;
        int length2 = nums2.length;
        int low = 0, high = length1;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (length1 + length2 + 1) / 2 - partitionX;

            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == length1) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == length2) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                if ((length1 + length2) % 2 == 0) {
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                } else {
                    return Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
