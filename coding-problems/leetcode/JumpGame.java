

public class JumpGame {
    public boolean canJump(int[] nums) {
        int canReach = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (canReach >= nums.length - 1) {
                return true;
            } else if (canReach < i) {
                return false;
            }
            canReach = Math.max(canReach, i + nums[i]);
        }
        return true;
    }
}
