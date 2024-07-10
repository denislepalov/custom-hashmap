package ru.aston.lepd.customhashmap;

/**
 * A {@code CustomHashMap} is an implementation of the {@code Map} interface that uses a hash table
 * to store key-value pairs. This class provides the basic operations (put, get and remove).
 *
 * <p>This implementation is not synchronized and permits null values, but don't permit null keys.</p>
 *
 * <p>Features of {@code CustomHashMap}:
 * <ul>
 *   <li>Permits null values, but don't permit null keys.</li>
 *   <li>Provides constant-time performance for basic operations (put, get and remove).</li>
 *   <li>Iterates over the elements in no particular order.</li>
 *   <li>Not synchronized, suitable for non-thread-safe contexts.</li>
 * </ul>
 * </p>
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
class CustomHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private CustomLinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        this.table = new CustomLinkedList[capacity];
        this.capacity = capacity;
    }

    private static class Entry<K, V> {
        int hash;
        K key;
        V value;

        Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Puts the key-value pair to HashMap. If the map
     * previously contained a mapping for the key, the old value is replaced by the
     * specified value.
     *
     * <p>Don't permit null keys.</p>
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @throws NullPointerException if the key is null
     */
    public void put(K key, V value) {
        int index = findBucketIndex(key);
        if (table[index] == null) {
            table[index] = new CustomLinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.hash == key.hashCode() && entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key.hashCode(), key, value));
        size++;
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this map contains
     * no mapping for the key.

     * <p>A return value of {@code null} does not necessarily indicate that the map contains no mapping
     * for the key; it's also possible that the map explicitly maps the key to {@code null}.</p>
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public V get(K key) {
        int index = findBucketIndex(key);
        if (table[index] == null) {
            return null;
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.hash == key.hashCode() && entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }


    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key key whose mapping is to be removed from the map

     * @throws NullPointerException if the specified key is null
     * */
    public void remove(K key) {
        int index = findBucketIndex(key);
        if (table[index] == null) {
            return;
        }
        Entry<K, V> toRemove = null;
        for (Entry<K, V> entry : table[index]) {
            if (entry.hash == key.hashCode() && entry.key.equals(key)) {
                toRemove = entry;
                break;
            }
        }
        if (toRemove != null) {
            table[index].remove(toRemove);
            size--;
        }
    }

    /**
     * Resizes the hash table when the number of key-value mappings exceeds the capacity threshold.
     *
     * <p>The resizing operation involves rehashing all of the existing entries and
     * redistributing them into a new table with a larger capacity.</p>
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        CustomLinkedList<Entry<K, V>>[] newTable = new CustomLinkedList[newCapacity];

        for (CustomLinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int newIndex = entry.key.hashCode() % (newCapacity - 1);
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new CustomLinkedList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    /**
     * Calculates the bucket index for the specified key.
     * @param key key whose mapping is to be removed from the map
     * @return index of bucket
     * @throws NullPointerException if the specified key is null
     */
    private int findBucketIndex(K key) {
        return capacity - 1 == 0
                ? 0
                : key.hashCode() % (capacity - 1);
    }

    /**
     * @return the number of key-value pairs
     */
    public int size() {
        return size;
    }


}
