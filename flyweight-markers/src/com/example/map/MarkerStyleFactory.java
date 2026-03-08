package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory caches and reuses MarkerStyle instances; key is composed from
 * shape/color/size/filled.  MapDataSource now obtains styles from this factory
 * rather than instantiating directly.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        MarkerStyle existing = cache.get(key);
        if (existing != null) {
            return existing;
        }
        MarkerStyle ms = new MarkerStyle(shape, color, size, filled);
        cache.put(key, ms);
        return ms;
    }

    public int cacheSize() {
        return cache.size();
    }
}
