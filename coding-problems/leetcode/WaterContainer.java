public class WaterContainer {
    public int maxArea(int[] height) {
        int area = 0, i = 0, j = height.length - 1;
        while (i < j) {
            int current_area = Math.min(height[i], height[j]) * (j - i);
            area = Math.max(area, current_area);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return area;
    }
}
