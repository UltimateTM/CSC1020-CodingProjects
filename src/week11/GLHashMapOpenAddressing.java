package week11;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Hash map with linear probing
 */
public class GLHashMapOpenAddressing<K, V> implements Map<K, V> {

    // entry class
    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
          this.key = key;
          this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;

        }
    }
    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR = 0.8;
    // array of size 11 of Entry
    private Entry<K, V>[] entries;
    private int numKeys;
    private int deletedKeys;
    private Entry<K, V> deleted = new Entry<>(null, null);

    public GLHashMapOpenAddressing() {
        numKeys = 0;
        deletedKeys = 0;
        entries = (Entry<K, V>[]) new Object[INITIAL_CAPACITY];

    }
    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = findIndex(key);

        if (entries[index] == null) {
            return false;
        }

        while (entries[index] != null) {
            // if key that is in parameter matches key in entries array
            if (key.equals(entries[index].key)) {
                return true;
            }
            ++index;

            // loops to the beginning of the array
            index %= entries.length;
        }

        return false;
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % entries.length;

        // if the result of key.hashCode() results in less than 0
        if (index < 0) {
            index += entries.length;
        }

        return index;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int index = findIndex(key);

        if (entries[index] == null) {
            return null;
        }

        while (entries[index] != null) {
            // if key that is in parameter matches key in entries array
            if (key.equals(entries[index].key)) {
                return entries[index].value;
            }
            ++index;

            // loops to the beginning of the array
            index %= entries.length;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        V result = null;
        int index = findIndex(key);

        if(entries[index] == null) {
            // if empty, we add
            entries[index] = new Entry<>(key, value);
            ++numKeys;

            if ((double) (numKeys + deletedKeys) / entries.length >= LOAD_FACTOR) {
                rehash();
            }
            result = value;
        } else {
            // otherwise, we do linear probing
            // go until its empty
            while (entries[index] != null) {
                if(key.equals(entries[index].key)) {
                    // replace value, do not increment size
                    result = entries[index].setValue(value);
                    return result;
                }
                ++index;
                // loops to the beginning of the array
                index %= entries.length;
            }
            entries[index] = new Entry<>(key, value);
            ++numKeys;
            result = value;
        }
        return result;
    }

    private void rehash() {
        //Entry<K, V>[] newEntries = (Entry<K, V>[]) new Object[entries.length * 2 + 1];
        Entry<K, V>[] newEntries = (Entry<K, V>[]) new Entry[entries.length * 2 + 1];

        for (Entry<K, V> e : entries) {
            if (e != null && !e.equals(deleted)) {
                int index = e.key.hashCode() % newEntries.length;

                if (index < 0) {
                    index += newEntries.length;
                }

                while (newEntries[index] != null) {
                    ++index;
                    index %= newEntries.length;
                }
                newEntries[index] = e;
            }
        }
        deletedKeys = 0;
        entries = newEntries;
    }

    @Override
    public V remove(Object key) {
        V result = null;
        int index = findIndex(key);

        while (entries[index] != null) {
            if(key.equals(entries[index].key)) {
                // found item to delete
                result = entries[index].value;
                // replace with dummy value
                entries[index] = deleted;
                --numKeys;
                ++deletedKeys;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public Collection<V> values() {
        return List.of();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }
}
