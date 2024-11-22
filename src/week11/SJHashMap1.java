/*
 * Course: CSC-1020
 * HashMap Homework
 * Name: Gabe Limberg
 * Last Updated: 11-15-24
 */

package limbergg;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Class that utilizes a hash map with linear probing
 * @param <K> key
 * @param <V> value
 */
public class SJHashMap1<K, V> implements Map<K, V> {

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
    private static final int INITIAL_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    private Entry<K, V>[] entries;
    private int numKeys;
    private int deletedKeys;
    private final Entry<K, V> deleted = new Entry<>(null, null);
    /**
     *
     */
    public SJHashMap1() {
        numKeys = 0;
        deletedKeys = 0;
        entries = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % entries.length;

        if (index < 0) {
            index += entries.length;
        }

        return index;
    }

    @Override
    public int size() {
        return this.numKeys;
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
            if (key.equals(entries[index].key)) {
                return true;
            }
            ++index;

            index %= entries.length;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Entry<K, V> e: entries) {
            if (e != null) {
                if (e.getValue() == value) {
                    return true;
                }
            }
        }
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
            entries[index] = new Entry<>(key, value);
            ++numKeys;

            if ((double) (numKeys + deletedKeys) / entries.length >= LOAD_FACTOR) {
                rehash();
            }
            result = value;
        } else {
            while (entries[index] != null) {
                if(key.equals(entries[index].key)) {
                    result = entries[index].setValue(value);
                    return result;
                }
                ++index;
                index %= entries.length;
            }
            entries[index] = new Entry<>(key, value);
            ++numKeys;
            result = value;
        }

        return result;
    }

    private void rehash() {
        Entry<K, V>[] oldEntries = entries;
        entries = new Entry[entries.length * 2 + 1];
        numKeys = 0;
        deletedKeys = 0;
        for (Entry<K, V> entry : oldEntries) {
            if (entry != null && entry != deleted) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public V remove(Object key) {
        V result = null;
        int index = findIndex(key);

        while (entries[index] != null) {
            if(key.equals(entries[index].key)) {
                result = entries[index].value;
                entries[index] = deleted;
                --numKeys;
                ++deletedKeys;
            }
        }

        return result;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        Arrays.fill(entries, null);
        numKeys = 0;
        deletedKeys = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * String representation of the HashTable
     * @return String
     */
    public String toString() {
        // TODO
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for(Entry<K, V> e: entries) {
            if (e != null) {
                sb.append(e.getKey()).append("=").append(e.getValue()).append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public double averageProbes() {
        return 0;
    }
}
