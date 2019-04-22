package com.test.catsanddogs;

import java.util.concurrent.ConcurrentHashMap;

public class PseudoCache<K, V> {

    private final ConcurrentHashMap<K, V> data = new ConcurrentHashMap<>();

    public boolean isValid() {
        return !data.isEmpty();
    }

    public void put(K key, V value) {
        data.put(key, value);
    }

    public V get(K key) {
        return data.get(key);
    }

    public boolean isContains(String name) {
        return data.containsKey(name);
    }

}
