import java.util.Arrays;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 */
public class PrisonCells {
    int buildBitMask(int[] cells) {
        int bitMask = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 1) {
                bitMask += (1 << i);
            }
        }
        return bitMask;
    }
    int setBit(int bitMask, int i, int value) {
        if ((bitMask & (1 << i)) > 0 && value == 0) {
            bitMask -= (1 << i);
        } else if ((bitMask & (1 << i)) == 0 && value == 1) {
            bitMask += (1 << i);
        }
        return bitMask;
    }
    int getBit(int bitMask, int i) {
        return (bitMask & (1 << i)) >> i;
    }
    int[] toArray(int bitMask) {
        int[] result = new int[8];
        for (int i = 0; i < 8; i++) {
            if ((bitMask & (1 << i)) > 0) {
                result[i] = 1;
            }
        }
        return result;
    }
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] memo = new int[1 << 8];
        int last, next, newLast, bitMask = buildBitMask(cells);
        while (N > 0) {
            memo[bitMask] = N--;
            last = -1;
            for (int j = 0; j < cells.length; j++) {
                next = j + 1 < cells.length ? getBit(bitMask, j + 1) : -1;
                newLast = getBit(bitMask, j);
                bitMask = next == last ? setBit(bitMask, j, 1) : setBit(bitMask, j, 0);
                last = newLast;
            }
            if (memo[bitMask] > 0) {
                N %= memo[bitMask] - N;
            }
        }
        return toArray(bitMask);
    }
    public void test() {
        System.out.println(Arrays.toString(
                prisonAfterNDays(new int[] {0,1,0,1,1,0,0,1}, 7)
        ));
        System.out.println(Arrays.toString(
                prisonAfterNDays(new int[] {1,0,0,1,0,0,1,0}, 1000000000)
        ));
        System.out.println(Arrays.toString(
                prisonAfterNDays(new int[] {0,0,1,0,0,1,0,0}, 44640906)
        ));
        System.out.println(Arrays.toString(
                prisonAfterNDays(new int[] {0,0,1,1,1,1,0,0}, 8)
        ));
    }
}
