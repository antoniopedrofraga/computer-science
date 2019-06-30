/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 *
 * Use quick select to find this element in O(n) average case.
 *
 * Time complexity: O(n) average case O(n 2) worst case
 * Memory complexity: O(1)
 */
public class KthLargestElement {
    void swap(int [] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = choosePivot(nums, l, r);
            if (mid == k - 1) break;
            if (mid < k - 1) l = mid + 1;
            else r = mid - 1;
        }
        return nums[k - 1];
    }
    int choosePivot(int [] nums, int l, int r) {
        int random = (int) (Math.random() * (r - l + 1)) + l;
        swap(nums, random, r);
        int j = l;
        for (int i = l; i < r; i++) {
            if (nums[i] > nums[r]) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, j, r);
        return j;
    }

    public void test() {
        System.out.println(
                findKthLargest(
                        new int[]{3,2,1,5,6,4},
                        2
                )
        );
        System.out.println(
                findKthLargest(
                        new int[]{3,2,3,1,2,4,5,5,6},
                        4
                )
        );
    }
}
