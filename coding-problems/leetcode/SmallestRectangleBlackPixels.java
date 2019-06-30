import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * Example:
 *
 * Input:
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * and x = 0, y = 2
 *
 * Output: 6
 *
 * Approach: It turns out we can do this in O(n log m + m log n).
 * Memory complexity: O(1)
 */
public class SmallestRectangleBlackPixels {
    final static List<int[]> moves = Arrays.asList(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});

    public int minAreaQuadratic(char[][] image, int x, int y) {
        if (image[x][y] != '1') return 0;
        int width = image.length, height = image[0].length;
        int minX = width, maxX = 0, minY = height, maxY = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(x * height + y);
        while (!q.isEmpty()) {
            int coords = q.remove();
            x = coords / height;
            y = coords % height;
            image[x][y] = 0;

            minX = Math.min(x, minX);
            minY = Math.min(y, minY);
            maxX = Math.max(x, maxX);
            maxY = Math.max(y, maxY);

            for (int[] move : moves) {
                int newX = x + move[0],
                        newY = y + move[1];
                if (newX >= 0 && newX < width && newY >= 0 && newY < height && image[newX][newY] == '1') {
                    q.add(newX * height + newY);
                }
            }
        }
        return (maxX - minX + 1) * (maxY - minY + 1);
    }

    public int minArea(char[][] image, int x, int y) {
        int width = image.length, height = image[0].length;
        int left = horizontalBinarySearch(image, 0, x, height, true),
                right = horizontalBinarySearch(image, x, width - 1, height, false),
                top = verticalBinarySearch(image, 0, y, width, true),
                bot = verticalBinarySearch(image, y, height - 1, width, false);
        return (right - left + 1) * (bot - top + 1);
    }

    private int horizontalBinarySearch(char[][] image, int i, int j, int height, boolean firstHalf) {
        while (i < j) {
            int mid = (firstHalf ? (j - i) : (j - i + 1)) / 2 + i;
            int k = 0;
            for (; k < height; k++) if (image[mid][k] == '1') break;
            if (firstHalf) {
                if (k < height) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            } else {
                if (k < height) {
                    i = mid;
                } else {
                    j = mid - 1;
                }
            }
        }
        return j;
    }

    private int verticalBinarySearch(char[][] image, int i, int j, int width, boolean firstHalf) {
        while (i < j) {
            int mid = (firstHalf ? (j - i) : (j - i + 1)) / 2 + i;
            int k = 0;
            for (; k < width; k++) if (image[k][mid] == '1') break;
            if (firstHalf) {
                if (k < width) {
                    j = mid;
                } else {
                    i = mid + 1;
                }
            } else {
                if (k < width) {
                    i = mid;
                } else {
                    j = mid - 1;
                }
            }
        }
        return i;
    }

    public void test() {
        System.out.println(minArea(new char[][] {
                {'0'},
                {'1'}
        }, 1, 0));
        System.out.println(minArea(new char[][] {
                {'0', '0', '1', '0'},
                {'0', '1', '1', '0'},
                {'0', '1', '0', '0'}
        }, 0, 2));
    }
}
