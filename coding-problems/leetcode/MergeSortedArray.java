/**
 * A dummy description of MergeSortedArray
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class MergeSortedArray {
    private int getNums1Pos(int [] nums1, int i, int m) {
        return nums1.length - m + i;
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1; i >= 0; i--) {
            int pos = getNums1Pos(nums1, i, m);
            nums1[pos] = nums1[i];
        }
        int i = 0, j = 0;
        while (i < m || j < n) {
            int n1 = i < m ? nums1[getNums1Pos(nums1, i, m)] : Integer.MAX_VALUE;
            int n2 = j < n ? nums2[j] : Integer.MAX_VALUE;
            if (n1 < n2) {
                nums1[i + j] = n1;
                i++;
            } else {
                nums1[i + j] = n2;
                j++;
            }
        }
    }
}
