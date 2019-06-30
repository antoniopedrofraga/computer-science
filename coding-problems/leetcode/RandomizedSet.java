import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 *
 * Approach: Use a hashmap that holds the index of the number in a list. If we want to remove that number swap it with the last number in the list and remove it.
 */

class RandomizedSet {
    private Map<Integer, Integer> indices;
    private List<Integer> list;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        indices = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indices.containsKey(val)) return false;
        int index = list.size();
        list.add(val);
        indices.put(val, index);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indices.containsKey(val)) return false;
        int index = indices.get(val),
                last = list.size() - 1;
        indices.remove(val);
        if (last != index) {
            list.set(index, list.get(last));
            indices.put(list.get(index), index);
        }
        list.remove(last);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

