public class SortColors {
    static private int RED = 0;
    static private int WHITE = 1;
    static private int BLUE = 2;
    public void sortColors(int[] nums) {
        int redIndex = 0, whiteIndex = 0, blueIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == RED) {
                nums[redIndex] = RED;
                if (whiteIndex < blueIndex) {
                    nums[i] = BLUE;
                }
                if (redIndex < whiteIndex) {
                    nums[whiteIndex] = WHITE;
                }
                redIndex++;
                whiteIndex++;
                blueIndex++;
            } else if (nums[i] == WHITE) {
                nums[whiteIndex] = WHITE;
                if (whiteIndex < blueIndex) {
                    nums[i] = BLUE;
                }
                whiteIndex++;
                blueIndex++;
            } else if (nums[i] == BLUE) {
                nums[blueIndex] = BLUE;
                blueIndex++;
            }
        }
    }
}
