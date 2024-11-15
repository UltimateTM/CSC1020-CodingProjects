package week11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GLMap<K, V> implements Map<K, V> {
    private class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
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
            V oldValue = value;
            this.value = value;
            return oldValue;
        }
    }

    private ArrayList<Entry<K, V>> data;
    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        // need to account for null values
        for (Entry<K, V> e: data) {
            if(e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> e: data) {
            if(e.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (Entry<K, V> e: data) {
            if(e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        for (Entry<K, V> e: data) {
            if(e.key.equals(key)) {
               data.remove(key);
            }
        }
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public Collection values() {
        return List.of();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public V put(K key, V value) {
        // See if key exists
        if(!containsKey(key)) {
            data.add(new Entry<>(key, value));
            return value;
        } else {
            for(Entry<K, V> e : data) {
                if (e.key.equals(key)) {
                    return e.setValue(value);
                }
            }
        }
        return null;
    }
}
