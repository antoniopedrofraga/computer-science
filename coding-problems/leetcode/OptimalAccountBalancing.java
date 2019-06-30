import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 *
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 *
 * Note:
 *
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 *
 * Input:
 * [[0,1,10], [2,0,5]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 *
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 *
 * Output:
 * 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * Time complexity: O(n ^ n)
 * Memory complexity: O(n)
 */
public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> money = new HashMap<>();
        List<Integer> people = new ArrayList<>();
        for (int [] transaction : transactions) {
            int p1Money = money.getOrDefault(transaction[0], 0);
            int p2Money = money.getOrDefault(transaction[1], 0);
            if (!money.containsKey(transaction[0])) people.add(transaction[0]);
            if (!money.containsKey(transaction[1])) people.add(transaction[1]);
            money.put(transaction[0], p1Money - transaction[2]);
            money.put(transaction[1], p2Money + transaction[2]);
        }
        // Run dfs
        return dfs(money, people, 0);
    }

    public int dfs(Map<Integer, Integer> money, List<Integer> people, int i) {
        while (i < people.size() && money.get(people.get(i)) == 0) { i++; }
        if (i == people.size()) return 0;

        int min = money.size();
        int p1 = people.get(i);
        int temp1 = money.get(p1);

        for (int j = i + 1; j < people.size(); j++) {
            int p2 = people.get(j);
            int temp2 = money.get(p2);
            if ((temp1 ^ temp2) < 0) {
                money.put(p2, temp1 + temp2);
                min = Math.min(min, 1 + dfs(money, people,  i + 1));
                money.put(p2, temp2);
            }
        }
        return min;
    }

    public void test() {
        int [][] tuples1 = new int [][] {
                {0, 1, 10},
                {2, 0, 5}
        };
        int [][] tuples2 = new int [][] {
                {0,1,10}, 
                {1,0,1}, 
                {1,2,5}, 
                {2,0,5}
        };
        int [][] tuples3 = new int[][] {
                {1,8,1},{1,13,21},{2,8,10},{3,9,20},{4,10,61},{5,11,61},{6,12,59},{7,13,60}
        };
        System.out.println(minTransfers(tuples1));
        System.out.println(minTransfers(tuples2));
        System.out.println(minTransfers(tuples3));
    }

}
