/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 *
 * Approach: Binary Search in both arrays for a valid median.
 * Time complexity: O(log(min(x, y))
 * Memory complexity: O(1)
 */

public class MedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int left = 0, right = nums1.length, mid1, mid2, total = nums1.length + nums2.length;
        while (left <= right) {
            mid1 = (right - left) / 2 + left;
            mid2 = ((total + 1) / 2) - mid1;
            int mid1Right = mid1 < nums1.length ? nums1[mid1] : Integer.MAX_VALUE,
                    mid1Left = mid1 - 1 >= 0 ? nums1[mid1 - 1] : Integer.MIN_VALUE,
                    mid2Right = mid2 < nums2.length ? nums2[mid2] : Integer.MAX_VALUE,
                    mid2Left = mid2 - 1 >= 0 ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            if (mid1Right < mid2Left) {
                left = mid1 + 1;
            } else if (mid1Left > mid2Right) {
                right = mid1 - 1;
            } else {
                if (total % 2 == 0) {
                    return Math.max(mid1Left, mid2Left) * 0.5 + Math.min(mid1Right, mid2Right) * 0.5;
                } else {
                    return Math.max(mid1Left, mid2Left);
                }
            }
        }
        return 0;
    }

    public void test() {
        System.out.println(findMedianSortedArrays(new int[]{1, 2, 3, 4} , new int[]{5, 6, 7, 8, 9, 10}));
        System.out.println(findMedianSortedArrays(new int[]{1} , new int[]{3}));
        System.out.println(findMedianSortedArrays(new int[]{1, 3} , new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2} , new int[]{3, 4}));
        System.out.println(findMedianSortedArrays(new int[]{4} , new int[]{1,2,3,5}));
        System.out.println(findMedianSortedArrays(new int[]{} , new int[]{1}));
    }
}
