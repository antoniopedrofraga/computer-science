import java.util.HashMap;
import java.util.Map;

/**
 * A dummy description of LRUCache
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
class LRUCache {
    class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode previous;
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, ListNode> cache;
    int capacity;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
    }
    /**
     * Updates tail in case node is the tail
     */
    private void updateMostRecent(ListNode node) {
        ListNode previous = node.previous;
        ListNode next = node.next;

        if (previous == null) { return; }
        previous.next = next;
        if (next != null) {
            next.previous = previous;
        }
        if (node == tail && cache.size() > 1) {
            tail = tail.previous;
            tail.next = null;
        }
        node.next = head;
        node.previous = null;
        head.previous = node;
        head = node;
    }
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        ListNode node = cache.get(key);
        updateMostRecent(node);
        return node.value;
    }

    public void put(int key, int value) {
        ListNode node;
        if (head == null) {
            node = new ListNode(key, value);
            head = node;
            tail = node;
        } else if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            updateMostRecent(node);
        } else {
            node = new ListNode(key, value);
            node.next = head;
            head.previous = node;
            head = node;
        }
        cache.put(key, node);
        if (cache.size() > capacity) {
            int keyToDel = tail.key;
            tail = capacity == 1 ? node : tail.previous;
            tail.next = null;
            cache.remove(keyToDel);
        }
    }

    public static boolean test(String [] method, int [][] args, Integer [] expected) {
        LRUCache instance = null;
        for (int i = 0; i < method.length; i++) {
            if (method[i].equals("LRUCache")) {
                instance = new LRUCache(args[i][0]);
            } else if (method[i].equals("get")) {
                if (expected[i] != instance.get(args[i][0])) {
                    System.out.println("Wrong on get(" + args[i][0] + ")");
                    return false;
                }
                System.out.println("get(" + args[i][0] + ")");
            } else if (method[i].equals("put")) {
                instance.put(args[i][0], args[i][1]);
                System.out.println("put(" + args[i][0] + ", " + args[i][1] + ")");
            }
        }
        return true;
    }
}




