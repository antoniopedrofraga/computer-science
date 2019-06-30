@SuppressWarnings("unchecked")
public class HashTable<K,V>  {

    private static final int INITIAL_CAPACITY = 16;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity = INITIAL_CAPACITY;

    Node<K,V> [] table = new Node[capacity];

    private int size = 0;

    private static int hash(Object o) {
        return o == null ? 0 : o.hashCode() ^ (o.hashCode() >>> 16);
    }

    class Node<K,V> {
        K key;
        V value;
        boolean deleted = false;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Node)) {
                return false;
            }
            Node<K,V> b = (Node<K,V>) o;
            return key.equals(b.key) && value.equals(b.value);
        }
    }

    private void resize() {
        /* Taking overflows into account */
        if (capacity << 1 == 0) {
            throw new OutOfMemoryError();
        }
        capacity <<= 1;
        Node<K,V> [] newTable = new Node[capacity];

        for (Node<K,V> node : table) {
            if (node == null || node.deleted) {
                continue;
            }
            K key = node.key;
            int j = probe(newTable, key);
            newTable[j] = node;
        }

        table = newTable;
    }

    private int probe(Node<K,V> [] probeTable, K key) {
        int j = hash(key) % capacity;

        /* linear probing */
        for (int i = 0; probeTable[j] != null || !probeTable[j].deleted; j = (hash(key) + ++i) % capacity) {}

        return j;
    }

    public void add(K key, V value) {
        int j = probe(table, key);
        table[j] = new Node(key, value);
        size++;
        if (size >= capacity * DEFAULT_LOAD_FACTOR) {
            resize();
        }
    }
    public V get(K key) {
        int j = hash(key) % capacity;
        for (int i = 0; ;j = (hash(key) + ++i) % capacity) {
            if (table[j] == null) {
                return null;
            } else if (!table[j].deleted && key.equals(table[j].key)) {
                return table[j].value;
            }
        }
    }

    public boolean exists(K key) {
        return get(key) != null;
    }

    public void remove(K key) {
        int j = hash(key) % capacity;
        for (int i = 0; ;j = (hash(key) + ++i) % capacity) {
            if (table[j] == null) {
                return;
            } else if (!table[j].deleted && key.equals(table[j].key)) {
                size--;
                table[j].deleted = true;
                return;
            }
        }
    }
}
