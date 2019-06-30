public class MedianTwoArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        boolean pair = ((nums1.length + nums2.length) & 1) != 1;
        int mid = (nums1.length + nums2.length) / 2, i = 0, j = 0, values = 1;
        double result = 0.0, previous = 0.0;

        if (!pair) {
            mid++;
        } else {
            values++;
        }

        while (i < nums1.length || j < nums2.length || values > 0) {
            if (i + j >= mid) {
                values--;
                result += previous;
            }
            if (values <= 0) {
                break;
            }

            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    previous = nums1[i];
                    i++;
                } else {
                    previous = nums2[j];
                    j++;
                }
            } else if (i < nums1.length) {
                previous = nums1[i];
                i++;
            } else if (j < nums2.length) {
                previous = nums2[j];
                j++;
            }
        }

        return pair ? result / 2.0 : result;
    }
}
