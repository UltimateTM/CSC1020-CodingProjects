package week11;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Hash map with chaining
 */
public class GLHashMapChain<K, V> implements Map<K, V> {
    // Entry class
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

    // Array of size 11 lists of Entry
    // Still need load factor and rehashing
    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR = 3.0; // 300% full
    private List<Entry<K, V>>[] entries;
    private int size;

    public GLHashMapChain() {
        size = 0;
        entries = (List<Entry<K,V>>[]) new List[INITIAL_CAPACITY];
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % entries.length;
        if (index < 0) {
            index += entries.length;
        }
        return index;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = findIndex(key);
        if (entries[index] != null) {
            for (Entry<K, V> e : entries[index]) {
                if (key.equals(e.key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int index = findIndex(key);
        if (entries[index] != null) {
            for (Entry<K, V> e : entries[index]) {
                if (key.equals(e.key)) {
                    return e.value;
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = findIndex(value);
        if (entries[index] == null) {
            entries[index] = new LinkedList<>();
        }

        for (Entry<K, V> e : entries[index]) {
            if (key.equals(e.key)) {
                return e.setValue(value);
            }
        }

        entries[index].add(new Entry<>(key, value));
        ++size;
        return value;
    }

    @Override
    public V remove(Object key) {
        int index = findIndex(key);

        if(entries[index] != null) {
            for (Entry<K, V> e : entries[index]) {
                if (key.equals(e.key)) {
                    V old = e.value;
                    entries[index].remove(e);
                    --size;
                    return old;
                }
            }
        }
        return null;
    }

    private void rehash() {
        List<Entry<K, V>>[] oldEntries = entries;
        //this.entries = (List<Entry<K, V>>[]) new Object[oldEntries.length * 2 + 1];
        this.entries = (List<Entry<K, V>>[]) new List[oldEntries.length * 2 + 1];
        for (List<Entry<K, V>> list : oldEntries) {
            if (list != null) {
                for (Entry<K, V> e : list) {
                    put(e.key, e.value);
                }
            }
        }
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
        return Set.of();
    }
}
