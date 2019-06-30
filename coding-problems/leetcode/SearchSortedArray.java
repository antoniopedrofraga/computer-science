/**
 * Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 *
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 *
 *
 *
 * Example 1:
 *
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: array = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 *
 * Note:
 *
 * You may assume that all elements in the array are unique.
 * The value of each element in the array will be in the range [-9999, 9999].
 *
 * Time complexity: O(log n)
 * Memory complexity: O(1)
 */
public class SearchSortedArray {
    private int getRealIndex(int [] nums, int pivot, int index) {
        int reverseIndex = nums.length - index;
        if (pivot - reverseIndex >= 0) {
            return pivot - reverseIndex;
        } else {
            return pivot + index;
        }
    }
    public boolean search(int [] nums, int target) {
        int pivot = nums.length;
        int high = nums.length - 1, low = 0, mid = nums.length / 2;

        while (low <= high) {
            if (nums[low] == target ||
                    nums[mid] == target ||
                    nums[high] == target) {
                return true;
            }
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                pivot = mid;
                break;
            } else if (high + 1 < nums.length && nums[high] < nums[high + 1]) {
                pivot = high + 1;
                break;
            } else if (low - 1 > 0 && nums[low] < nums[low - 1]) {
                pivot = low;
                break;
            }
            if (nums[mid] == nums[high] && nums[mid] == nums[low]) {
                // run a linear search
                for (int i = low + 1; i <= high; i++) {
                    if (nums[i] < nums[i - 1]) {
                        pivot = i;
                        break;
                    }
                }
                break;
            } else if (nums[low] > nums[mid] || nums[mid] == nums[high]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            mid = low + (high - low) / 2;
        }

        high = nums.length - 1;
        low = 0;
        mid = nums.length / 2;

        while (low <= high) {
            int realMid = getRealIndex(nums, pivot, mid);
            if (target == nums[realMid]) {
                return true;
            } else if (target < nums[realMid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            mid = low + (high - low) / 2;
        }
        return false;
    }

}
